package org.usra.loanService.dto;

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
    private String occupation;
    private int salary;
    private int creditScore;
    private int expenses;
    private AddressRequest addressRequest;
    private LoanType loanType;
    private String phoneNumber;
    private LocalDate createdAt;

}
