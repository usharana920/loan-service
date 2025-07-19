package org.usra.loanService.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum LoanStatus {

    LOAN_APPROVED("APPROVED"),
    LOAN_DENIED("DENIED"),
    LOAN_UNDER_REVIEW("UNDER REVIEW");

    private final String displayName;

    LoanStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static LoanStatus fromValue(String value) {
        for (LoanStatus type : LoanStatus.values()) {
            if (type.displayName.equalsIgnoreCase(value) || type.name().equalsIgnoreCase(value.replace(" ","_"))) {
                log.debug("loan type: {}", type);
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown loan type: " + value);
    }
}
