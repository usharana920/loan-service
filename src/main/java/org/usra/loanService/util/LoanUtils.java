package org.usra.loanService.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.usra.loanService.dto.LoanFormRequest;
import org.usra.loanService.model.LoanDuration;

import java.util.Random;
import java.util.UUID;

@Slf4j
@UtilityClass
public class LoanUtils {
    public static double calculateMonthlyEMI(double principal, LoanDuration loanDuration, double apr) {
        int totalInterestDays = ((loanDuration.getYears()*365) + (loanDuration.getMonth()*30) + (loanDuration.getDay()));
        double simpleInterestRate = ((principal * totalInterestDays * apr)/(365*100));
        double monthlyEMI = (((principal + simpleInterestRate)/(totalInterestDays/30)));
        return monthlyEMI;
    }

    public static double calculateAPR(LoanFormRequest loanFormRequest) {
        int creditScore = loanFormRequest.getCreditScore();
        if (creditScore < 450){
            return 10.0;
        }
        else if (creditScore >= 450 && creditScore < 600){
            return 8.0;
        }
        else if (creditScore >= 600 && creditScore < 700){
            return 5.0;
        }
        else if (creditScore >= 700 && creditScore < 740){
            return 3.5;
        }
        else return 1.99;
    }

    public static String generateLoanQuoteId(){
        Random random = new Random();
        return String.valueOf(100_000_000 + random.nextInt(900_000_000));
    }

}
