package autoliners.hoegh.autoloan.controller;

import autoliners.hoegh.autoloan.exception.LoanValidationException;
import autoliners.hoegh.autoloan.model.Loan;
import autoliners.hoegh.autoloan.model.MonthlyInterest;
import autoliners.hoegh.autoloan.model.TotalInterest;
import autoliners.hoegh.autoloan.service.LoanService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.when;

public class LoanControllerTest {

    private LoanService loanMockService;
    private LoanController loanController;
    private static Loan loanRequest;

    @BeforeClass
    public static void initBeforeClass(){
        loanRequest = new Loan();
        loanRequest.setMortgage(5);
    }

    @Before
    public void init(){
        loanMockService = Mockito.mock(LoanService.class);
        loanController = new LoanController(loanMockService);
    }

    @Test
    public void getAmortizationHappyPath(){

        TotalInterest totalInterest = new TotalInterest();
        totalInterest.add(new MonthlyInterest());

        when(loanMockService.getMonthlyAmortization(Mockito.any(Loan.class))).thenReturn(totalInterest);

        ResponseEntity<TotalInterest> amortizationResponse = loanController.getMonthlyAmortization(loanRequest);

        assertEquals(HttpStatus.OK, amortizationResponse.getStatusCode());
        assertFalse(CollectionUtils.isEmpty(amortizationResponse.getBody()));
        Mockito.verify(loanMockService, atLeastOnce()).getMonthlyAmortization(Mockito.any(Loan.class));
    }

    @Test
    public void whenMortgageIsZeroThenThrowLoanValidationException(){
        when(loanMockService.getMonthlyAmortization(Mockito.any(Loan.class))).thenThrow(LoanValidationException.class);

        try{
            loanController.getMonthlyAmortization(loanRequest);
            fail();
        }catch (LoanValidationException lve){
            Assert.assertNotNull(lve);
        }catch (Exception e){
            fail();
        }
        Mockito.verify(loanMockService, Mockito.times(1)).getMonthlyAmortization(Mockito.any(Loan.class));
    }
}