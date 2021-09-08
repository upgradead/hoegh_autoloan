package autoliners.hoegh.autoloan.service;

import autoliners.hoegh.autoloan.model.Loan;
import autoliners.hoegh.autoloan.model.TotalInterest;
import org.springframework.stereotype.Service;

@Service
public interface LoanService {
    public TotalInterest getMonthlyAmortization(Loan loan);
}
