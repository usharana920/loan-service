package org.usra.loanService.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.usra.loanService.dto.LoanFormRequest;
import org.usra.loanService.enums.LoanStatus;
import org.usra.loanService.model.Address;
import org.usra.loanService.model.LoanApplication;
import org.usra.loanService.repository.CustomerLoanRepository;

import static org.usra.loanService.constants.Constants.MINIMUM_CREDIT_SCORE;
import static org.usra.loanService.constants.Constants.MINIMUM_SALARY;


@Slf4j
@Service
@RequiredArgsConstructor
public class LoanRiskRatingsService {

    private final CustomerLoanRepository customerLoanRepository;

    public boolean evaluateLoanEligibility(LoanFormRequest loanFormRequest){
        saveLoanApplicationForRecord(loanFormRequest);
        if (loanFormRequest!= null && loanFormRequest.getSalary() >= MINIMUM_SALARY && loanFormRequest.getCreditScore() >= MINIMUM_CREDIT_SCORE){
            return true;
        }
        return false;
    }

    private void saveLoanApplicationForRecord(LoanFormRequest loanFormRequest) {
        ModelMapper modelMapper = new ModelMapper();
        Address address;
        LoanApplication loanApplication;


        address = modelMapper.map(loanFormRequest.getAddressRequest(), Address.class);
        loanApplication = modelMapper.map(loanFormRequest, LoanApplication.class);
        log.info("address: {}",address );
        log.info("loanApplication {}",loanApplication);
        loanApplication.setLoanType(loanFormRequest.getLoanType());
        loanApplication.setAddress(address);
        loanApplication.setStatus(LoanStatus.fromValue("APPROVED"));
        customerLoanRepository.save(loanApplication);
        log.debug("LoanRiskRatingsService::loanApplication saved successfully {}", loanApplication);
    }

}
