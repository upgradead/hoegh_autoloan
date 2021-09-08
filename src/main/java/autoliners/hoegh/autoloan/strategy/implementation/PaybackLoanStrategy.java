package autoliners.hoegh.autoloan.strategy.implementation;

import autoliners.hoegh.autoloan.model.*;
import autoliners.hoegh.autoloan.strategy.LoanStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

// Sample of new implementation of payback
public class PaybackLoanStrategy extends LoanStrategy {

    @Override
    public Loan.LoanTypeEnum getType() {
        return null;
    }

    @Override
    public TotalInterest calculate(LoanParam loanParam) {
        // Implementation for weekly payback
        buildInterestList(0,0,0,0);
        return getTotalInterest();
    }

    @Override
    public void buildInterestList(double mortgage, double amortizationInterest, double amortizationCapital, int term) {

        //Should be replaced with clone expensive with the new operation
        WeeklyInterest weeklyInterest = new WeeklyInterest();
        weeklyInterest.capital(BigDecimal.valueOf(mortgage).setScale(2, RoundingMode.HALF_UP).doubleValue());
        weeklyInterest.interest(BigDecimal.valueOf(amortizationInterest).setScale(2, RoundingMode.HALF_UP).doubleValue());
        weeklyInterest.capitalLeft(BigDecimal.valueOf(amortizationCapital).setScale(2, RoundingMode.HALF_UP).doubleValue());
        weeklyInterest.setTerm(term);

        getTotalInterest().add(weeklyInterest);
    }
}
