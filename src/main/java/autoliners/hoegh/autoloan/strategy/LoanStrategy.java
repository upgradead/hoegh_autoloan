package autoliners.hoegh.autoloan.strategy;

import autoliners.hoegh.autoloan.model.Loan;
import autoliners.hoegh.autoloan.model.LoanParam;
import autoliners.hoegh.autoloan.model.TotalInterest;

public abstract class LoanStrategy {

    public abstract Loan.LoanTypeEnum getType();

    public abstract TotalInterest calculate(LoanParam loanParam);

}
