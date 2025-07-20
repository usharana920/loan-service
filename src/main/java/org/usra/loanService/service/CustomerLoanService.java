package org.usra.loanService.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.usra.loanService.dto.LoanFormRequest;
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

}
