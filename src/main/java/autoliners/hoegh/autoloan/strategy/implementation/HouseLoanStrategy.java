package autoliners.hoegh.autoloan.strategy.implementation;

import autoliners.hoegh.autoloan.model.Loan;
import autoliners.hoegh.autoloan.model.LoanParam;
import autoliners.hoegh.autoloan.model.TotalInterest;
import autoliners.hoegh.autoloan.strategy.LoanStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HouseLoanStrategy extends LoanStrategy {

    @Override
    public Loan.LoanTypeEnum getType() {
        return Loan.LoanTypeEnum.HOUSE;
    }

    @Override
    public TotalInterest calculate(LoanParam loanParam) {

        double mortgage = loanParam.getMortgage();
        double monthlyMaxPay = loanParam.getMonthlyMaxPay();

        if (mortgage < monthlyMaxPay) {
            return getTotalInterest();
        }

        double amortizationInterest = (mortgage * (loanParam.getInterest() / 12));
        double amortizationCapital = monthlyMaxPay - amortizationInterest;

        buildInterestList(mortgage, amortizationInterest, amortizationCapital, loanParam.getTerms());

        loanParam.setTerms(loanParam.getTerms() - 1);
        loanParam.setMortgage(mortgage - amortizationCapital);

        calculate(loanParam);

        return getTotalInterest();
    }

}
