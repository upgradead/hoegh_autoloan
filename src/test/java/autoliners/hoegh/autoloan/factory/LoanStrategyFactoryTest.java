package autoliners.hoegh.autoloan.factory;

import autoliners.hoegh.autoloan.exception.LoanValidationException;
import autoliners.hoegh.autoloan.model.Loan;
import autoliners.hoegh.autoloan.strategy.implementation.CarLoanStrategy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LoanStrategyFactory.class, CarLoanStrategy.class})
public class LoanStrategyFactoryTest {

    @Autowired
    private LoanStrategyFactory factory;

    @Test
    public void getCarStrategyHappyPath() {
        Assert.assertNotNull(factory.getViewer(Loan.LoanTypeEnum.CAR));
    }

    @Test
    public void getHouseStrategyNotLoadedInContextThenThrowLoadValidationException() {
        try {
            factory.getViewer(Loan.LoanTypeEnum.HOUSE);
            fail();
        } catch (LoanValidationException e) {
            Assert.assertNotNull(e);
        } catch (Exception ex) {
            fail();
        }
    }

}