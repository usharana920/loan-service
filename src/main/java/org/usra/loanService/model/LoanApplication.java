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
    private Integer formId;
    private String firstName;
    private String lastName;
    private String occupation;
    private int salary;
    private int creditScore;

    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address_id")
    private Address address;

    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private LoanStatus Status;
    @CreationTimestamp
    private LocalDate createdAt;
}
