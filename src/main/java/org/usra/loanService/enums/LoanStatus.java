package org.usra.loanService.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum LoanStatus {

    LOAN_APPROVED("APPROVED"),
    LOAN_DENIED("DENIED");

    private final String displayName;

    LoanStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static LoanStatus fromValue(String value) {
        for (LoanStatus loanStatus : LoanStatus.values()) {
            if (loanStatus.displayName.equalsIgnoreCase(value) || loanStatus.name().equalsIgnoreCase(value.replace(" ","_"))) {
                log.debug("Loan Status is : {}", loanStatus);
                return loanStatus;
            }
        }
        throw new IllegalArgumentException("Unknown Loan Status: " + value);
    }
}
