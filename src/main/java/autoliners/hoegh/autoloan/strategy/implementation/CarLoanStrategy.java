package autoliners.hoegh.autoloan.strategy.implementation;

import autoliners.hoegh.autoloan.model.Loan;
import autoliners.hoegh.autoloan.model.LoanParam;
import autoliners.hoegh.autoloan.model.TotalInterest;
import autoliners.hoegh.autoloan.strategy.LoanStrategy;
import autoliners.hoegh.autoloan.util.MonthlyInterestBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CarLoanStrategy implements LoanStrategy {

    @Override
    public Loan.LoanTypeEnum getType() {
        return Loan.LoanTypeEnum.CAR;
    }

    @Override
    public TotalInterest calculate(LoanParam loanParam) {

        log.info("Processing record for House Loan {}", loanParam);

        TotalInterest totalInterest = new TotalInterest();

        double mortgage = loanParam.getMortgage();
        double monthlyMaxPay = loanParam.getMonthlyMaxPay();

        while (mortgage > monthlyMaxPay) {

            double amortizationInterest = (mortgage * (loanParam.getInterest() / 12));
            double amortizationCapital = monthlyMaxPay - amortizationInterest;

            totalInterest.add(MonthlyInterestBuilder.buildInterestList(
                    mortgage, amortizationInterest, amortizationCapital, loanParam.getTerms()));

            loanParam.setTerms(loanParam.getTerms() - 1);
            mortgage -= amortizationCapital;
        }

        return totalInterest;
    }
}
