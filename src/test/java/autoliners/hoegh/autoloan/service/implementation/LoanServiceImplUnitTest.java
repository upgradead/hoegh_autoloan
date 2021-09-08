package autoliners.hoegh.autoloan.service.implementation;


import autoliners.hoegh.autoloan.config.InterestConfig;
import autoliners.hoegh.autoloan.factory.LoanStrategyFactory;
import autoliners.hoegh.autoloan.model.Loan;
import autoliners.hoegh.autoloan.model.LoanParam;
import autoliners.hoegh.autoloan.model.TotalInterest;
import autoliners.hoegh.autoloan.strategy.LoanStrategy;
import autoliners.hoegh.autoloan.strategy.implementation.CarLoanStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class LoanServiceImplUnitTest {

    private LoanStrategyFactory loanStrategyFactoryMock;
    private InterestConfig interestConfigMock;
    private LoanServiceImpl loanService;

    @Before
    public void init() {
        loanStrategyFactoryMock = Mockito.mock(LoanStrategyFactory.class);
        interestConfigMock = Mockito.mock(InterestConfig.class);

        loanService = new LoanServiceImpl(loanStrategyFactoryMock, interestConfigMock);
    }

    @Test
    public void getMonthlyAmortizationHappyPath() {
        Loan loan = new Loan();
        loan.setMortgage(4);
        loan.setMaxMonthlyPay(5);
        loan.setTerms(6);

        LoanStrategy loanMockStrategy = Mockito.mock(CarLoanStrategy.class);
        when(loanStrategyFactoryMock.getViewer(Mockito.any())).thenReturn(loanMockStrategy);
        when(interestConfigMock.getInterest(Mockito.any(Loan.LoanTypeEnum.class))).thenReturn(5);
        when(loanMockStrategy.calculate(Mockito.any(LoanParam.class))).thenReturn(new TotalInterest());

        TotalInterest monthlyAmortization = loanService.getMonthlyAmortization(loan);
        Assert.assertNotNull(monthlyAmortization);

        verify(loanStrategyFactoryMock, atLeastOnce()).getViewer(Mockito.any());
        verify(loanMockStrategy, atLeastOnce()).calculate(Mockito.any(LoanParam.class));
    }
}