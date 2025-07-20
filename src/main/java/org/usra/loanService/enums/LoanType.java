package org.usra.loanService.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum LoanType {
    HOME_LOAN("Home Loan"),
    PERSONAL_LOAN("Personal Loan"),
    STUDENT_LOAN("Student Loan"),
    AUTO_LOAN("Auto Loan");

    private final String displayName;

    LoanType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static LoanType fromValue(String value) {
        for (LoanType type : LoanType.values()) {
            if (type.displayName.equalsIgnoreCase(value) || type.name().equalsIgnoreCase(value.replace(" ","_"))) {
                log.debug("loan type: {}", type);
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown loan type: " + value);
    }
}
