package org.usra.loanService.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.usra.loanService.model.LoanDuration;

import java.time.LocalDate;
import java.time.Period;

@Slf4j
@UtilityClass
public class DateUtil {
    public static LoanDuration calculateLoanDuration(LocalDate startDate, LocalDate endDate) {
        Period period = Period.between(startDate, endDate);
        return LoanDuration.builder().years(period.getYears()).month(period.getMonths()).day(period.getDays()).build();
    }

}
