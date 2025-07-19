package org.usra.loanService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.usra.loanService.enums.LoanType;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanFormRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String occupation;
    private int salary;
    private int creditScore;
    private double loanAmount;
    private int expenses;
    private int duration;
    private AddressRequest addressRequest;
    private LoanType loanType;
    private String phoneNumber;
    private String socialSecurityNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate loanStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate loanEndDate;

}
