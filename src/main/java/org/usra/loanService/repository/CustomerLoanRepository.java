package org.usra.loanService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.usra.loanService.model.LoanApplication;

@Repository
public interface CustomerLoanRepository extends JpaRepository<LoanApplication, Integer> {

}
