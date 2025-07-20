package org.usra.loanService.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class CorrelationIdGenerator {

    public static String generateCorrelationId(){
        return UUID.randomUUID().toString();
    }
}
