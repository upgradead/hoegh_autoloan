package autoliners.hoegh.autoloan.util;

import autoliners.hoegh.autoloan.model.MonthlyInterest;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class MonthlyInterestBuilder {

    private MonthlyInterestBuilder() {
    }

    public static MonthlyInterest buildInterestList(double mortgage, double amortizationInterest, double amortizationCapital, int term) {

        //Should be replaced with clone expensive with the new operation
        MonthlyInterest monthlyInterest = new MonthlyInterest();
        monthlyInterest.capital(BigDecimal.valueOf(mortgage).setScale(2, RoundingMode.HALF_UP).doubleValue());
        monthlyInterest.interest(BigDecimal.valueOf(amortizationInterest).setScale(2, RoundingMode.HALF_UP).doubleValue());
        monthlyInterest.capitalLeft(BigDecimal.valueOf(amortizationCapital).setScale(2, RoundingMode.HALF_UP).doubleValue());
        monthlyInterest.setTerm(term);

        return monthlyInterest;
    }
}
