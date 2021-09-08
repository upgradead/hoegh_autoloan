package autoliners.hoegh.autoloan.strategy;

import autoliners.hoegh.autoloan.model.Loan;
import autoliners.hoegh.autoloan.model.LoanParam;
import autoliners.hoegh.autoloan.model.MonthlyInterest;
import autoliners.hoegh.autoloan.model.TotalInterest;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public abstract class LoanStrategy {

    private TotalInterest totalInterest = new TotalInterest();

    public abstract Loan.LoanTypeEnum getType();

    public abstract TotalInterest calculate(LoanParam loanParam);

    public void buildInterestList(double mortgage, double amortizationInterest, double amortizationCapital, int term) {

        //Should be replaced with clone expensive with the new operation
        MonthlyInterest monthlyInterest = new MonthlyInterest();
        monthlyInterest.capital(BigDecimal.valueOf(mortgage).setScale(2, RoundingMode.HALF_UP).doubleValue());
        monthlyInterest.interest(BigDecimal.valueOf(amortizationInterest).setScale(2, RoundingMode.HALF_UP).doubleValue());
        monthlyInterest.capitalLeft(BigDecimal.valueOf(amortizationCapital).setScale(2, RoundingMode.HALF_UP).doubleValue());
        monthlyInterest.setTerm(term);

        totalInterest.add(monthlyInterest);
    }
}
