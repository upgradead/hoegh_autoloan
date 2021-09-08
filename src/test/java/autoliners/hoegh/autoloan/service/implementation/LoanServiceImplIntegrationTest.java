package autoliners.hoegh.autoloan.service.implementation;

import autoliners.hoegh.autoloan.config.InterestConfig;
import autoliners.hoegh.autoloan.factory.LoanStrategyFactory;
import autoliners.hoegh.autoloan.model.Loan;
import autoliners.hoegh.autoloan.model.MonthlyInterest;
import autoliners.hoegh.autoloan.model.TotalInterest;
import autoliners.hoegh.autoloan.service.LoanService;
import autoliners.hoegh.autoloan.strategy.implementation.CarLoanStrategy;
import autoliners.hoegh.autoloan.strategy.implementation.HouseLoanStrategy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LoanStrategyFactory.class, CarLoanStrategy.class, HouseLoanStrategy.class,
        LoanService.class, LoanServiceImpl.class, InterestConfig.class})
@EnableConfigurationProperties(value = InterestConfig.class)
public class LoanServiceImplIntegrationTest {

    @Autowired
    LoanServiceImpl loanService;

    @Test
    public void whenCarStrategyWith3PercentInterestThenReturnListOfMonthly() {

        Loan loan = new Loan();
        loan.setTerms(25);
        loan.setMaxMonthlyPay(2520);
        loan.setMortgage(531408);
        loan.setLoanType(Loan.LoanTypeEnum.CAR);
        TotalInterest monthlyAmortization = loanService.getMonthlyAmortization(loan);
        Assert.assertEquals(299, monthlyAmortization.size());
        MonthlyInterest monthlyInterest = monthlyAmortization.get(298);
        Assert.assertEquals(Integer.valueOf(2), monthlyInterest.getTerm());
        Assert.assertEquals(Double.valueOf(12.55), monthlyInterest.getInterest());
    }

    @Test
    public void whenHouseStrategyWith4PercentInterestThenReturnListOfMonthly() {
        Loan loan = new Loan();
        loan.setTerms(25);
        loan.setMaxMonthlyPay(2520);
        loan.setMortgage(531408);
        loan.setLoanType(Loan.LoanTypeEnum.HOUSE);
        TotalInterest monthlyAmortization = loanService.getMonthlyAmortization(loan);
        Assert.assertEquals(364, monthlyAmortization.size());
        MonthlyInterest monthlyInterest = monthlyAmortization.get(0);
        Assert.assertEquals(Integer.valueOf(300), monthlyInterest.getTerm());
        Assert.assertEquals(Double.valueOf(1771.36), monthlyInterest.getInterest());
    }
}
