package autoliners.hoegh.autoloan.service.implementation;

import autoliners.hoegh.autoloan.config.InterestConfig;
import autoliners.hoegh.autoloan.factory.LoanStrategyFactory;
import autoliners.hoegh.autoloan.model.Loan;
import autoliners.hoegh.autoloan.model.LoanParam;
import autoliners.hoegh.autoloan.model.TotalInterest;
import autoliners.hoegh.autoloan.service.LoanService;
import autoliners.hoegh.autoloan.strategy.LoanStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoanServiceImpl implements LoanService {

    private LoanStrategyFactory factory;
    private InterestConfig interestConfig;

    @Autowired
    public LoanServiceImpl(LoanStrategyFactory factory, InterestConfig interestConfig) {
        this.factory = factory;
        this.interestConfig = interestConfig;
    }

    @Override
    public TotalInterest getMonthlyAmortization(Loan loan) {

        LoanStrategy viewer = factory.getViewer(loan.getLoanType());
        double interest = interestConfig.getInterest(loan.getLoanType()) / 100.0;

        log.debug("Loan strategy applied {} for interest rate of {}", viewer, interest);

        LoanParam loanParam = LoanParam.builder()
                .mortgage(loan.getMortgage())
                .monthlyMaxPay(loan.getMaxMonthlyPay())
                .terms(loan.getTerms() * 12)
                .interest(interest).build();

        return viewer.calculate(loanParam);
    }
}
