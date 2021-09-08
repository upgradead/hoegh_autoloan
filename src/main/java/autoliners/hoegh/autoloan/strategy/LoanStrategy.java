package autoliners.hoegh.autoloan.strategy;

import autoliners.hoegh.autoloan.model.Loan;
import autoliners.hoegh.autoloan.model.LoanParam;
import autoliners.hoegh.autoloan.model.TotalInterest;

public interface LoanStrategy {

     Loan.LoanTypeEnum getType();

     TotalInterest calculate(LoanParam loanParam);

}
