package org.usra.loanService.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.usra.loanService.dto.LoanFormRequest;
import org.usra.loanService.enums.LoanStatus;
import org.usra.loanService.model.Address;
import org.usra.loanService.model.LoanApplication;
import org.usra.loanService.model.LoanDuration;
import org.usra.loanService.repository.CustomerLoanRepository;
import org.usra.loanService.util.DateUtil;
import org.usra.loanService.util.LoanUtils;

import static org.usra.loanService.constants.Constants.MINIMUM_CREDIT_SCORE;
import static org.usra.loanService.constants.Constants.MINIMUM_SALARY;


@Slf4j
@Service
@RequiredArgsConstructor
public class LoanRiskRatingsService {

    private final CustomerLoanRepository customerLoanRepository;

    public boolean evaluateLoanEligibility(LoanFormRequest loanFormRequest){
        if (loanFormRequest!= null && loanFormRequest.getSalary() >= MINIMUM_SALARY && loanFormRequest.getCreditScore() >= MINIMUM_CREDIT_SCORE){
            return true;
        }
        return false;
    }

    public void saveLoanApplicationForRecord(LoanFormRequest loanFormRequest, boolean isCustomerEligibleForLoan) {
        Address address;
        LoanApplication loanApplication;
        LoanDuration duration;
        ModelMapper modelMapper = new ModelMapper();

        String quoteId = LoanUtils.generateLoanQuoteId();
        address = modelMapper.map(loanFormRequest.getAddressRequest(), Address.class);
        loanApplication = modelMapper.map(loanFormRequest, LoanApplication.class);

        loanApplication.setLoanType(loanFormRequest.getLoanType());
        loanApplication.setAddress(address);
        loanApplication.setQuoteId(quoteId);

        if(!isCustomerEligibleForLoan){
            loanApplication.setStatus(LoanStatus.fromValue("DENIED"));
        }else {
            duration = DateUtil.calculateLoanDuration(loanFormRequest.getLoanStartDate(), loanFormRequest.getLoanEndDate());
            double loanAPR = LoanUtils.calculateAPR(loanFormRequest);
            log.debug("Loan APR is: {}", loanAPR);
            double monthlyEMI = LoanUtils.calculateMonthlyEMI(loanFormRequest.getLoanAmount(), duration, loanAPR);
            log.debug("monthly EMI is: {}", monthlyEMI);
            loanApplication.setStatus(LoanStatus.fromValue("APPROVED"));
            loanApplication.setLoanDuration(duration);
            loanApplication.setMonthlyEMI(monthlyEMI);
        }

        customerLoanRepository.save(loanApplication);
        log.debug("LoanRiskRatingsService::loanApplication saved successfully {}", loanApplication);
    }

}
