package org.usra.loanService.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.usra.loanService.dto.LoanFormRequest;
import org.usra.loanService.dto.LoanStatusRequest;
import org.usra.loanService.enums.LoanStatus;
import org.usra.loanService.model.LoanApplication;
import org.usra.loanService.repository.CustomerLoanRepository;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CustomerLoanService {

    private final LoanRiskRatingsService loanRiskRatingsService;
    private final CustomerLoanRepository customerLoanRepository;

    public String processLoanApplication(LoanFormRequest loanFormRequest){
        boolean isCustomerEligibleForLoan = loanRiskRatingsService.evaluateLoanEligibility(loanFormRequest);

        if (isCustomerEligibleForLoan){
            loanRiskRatingsService.saveLoanApplicationForRecord(loanFormRequest, true);
            return "Loan processed successfully";
        }else {
            loanRiskRatingsService.saveLoanApplicationForRecord(loanFormRequest, false);
            return "Sorry! you are not eligible for loan at this time. Please try again later";
        }
    }

    public String getLoanStatus(String quoteId){
        Optional<LoanApplication> optionalLoanApplication = customerLoanRepository.findByQuoteId(quoteId);
        if (optionalLoanApplication.isPresent()){
            return optionalLoanApplication.get().getStatus().getDisplayName();
        }
        return "Not Found";
    }

    public String updateLoanStatus(String quoteId, LoanStatusRequest loanStatusRequest) {
        Optional<LoanApplication> optionalLoanApplication = customerLoanRepository.findByQuoteId(quoteId);
        if (!optionalLoanApplication.isPresent()){
            return "No entry found for the quoteId: " + quoteId;
        }
        LoanApplication loanApplication = optionalLoanApplication.get().toBuilder().status(LoanStatus.fromValue(loanStatusRequest.getStatus())).build();
        if (loanStatusRequest.getRemarks()!= null){
//            LoanApplication loanApp = loanApplication.toBuilder().remarks(loanStatusRequest.getRemarks()).build();
//            loanRiskRatingsService.updateLoanStatus(loanApplication);
            loanApplication.setRemarks(loanStatusRequest.getRemarks());
        }
        log.info("Loan application is update for quoteId: {}. New response: {} ",quoteId ,loanApplication);
        loanRiskRatingsService.updateLoanStatus(loanApplication);
        return "Done";
    }
}
