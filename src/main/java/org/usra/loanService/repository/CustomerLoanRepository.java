package org.usra.loanService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.usra.loanService.model.LoanApplication;

import java.util.Optional;

@Repository
public interface CustomerLoanRepository extends JpaRepository<LoanApplication, Integer> {

    @Query("SELECT loan FROM LoanApplication loan WHERE loan.quoteId = :quoteId")
     Optional<LoanApplication> findByQuoteId(String quoteId);
}
