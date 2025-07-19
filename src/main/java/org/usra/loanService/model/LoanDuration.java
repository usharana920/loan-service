package org.usra.loanService.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loan_duration_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanDuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanDurationId;
    private int years;
    private int month;
    private int day;
}
