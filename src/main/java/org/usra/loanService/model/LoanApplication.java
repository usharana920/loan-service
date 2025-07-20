package org.usra.loanService.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.usra.loanService.enums.LoanStatus;
import org.usra.loanService.enums.LoanType;

import java.time.LocalDate;

@Entity
@Table(name = "loanApplication_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int loanId;

    private String firstName;
    private String lastName;
    private String email;
    private String occupation;
    private int salary;
    private int creditScore;
    private int expenses;

    private double loanAmount;
    private double monthlyEMI;
    @Enumerated(EnumType.STRING)
    private LoanType loanType;
    private LocalDate loanStartDate;
    private LocalDate loanEndDate;
    @CreationTimestamp
    private LocalDate quoteCreatedAt;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_loanDuration_id")
    private LoanDuration loanDuration;
    private String quoteId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address_id")
    private Address address;
    private String phoneNumber;
    private String socialSecurityNumber;
    @Enumerated(EnumType.STRING)
    private LoanStatus Status;

}
