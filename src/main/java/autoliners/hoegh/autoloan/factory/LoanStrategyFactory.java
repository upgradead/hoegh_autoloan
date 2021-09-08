package autoliners.hoegh.autoloan.factory;

import autoliners.hoegh.autoloan.exception.LoanValidationException;
import autoliners.hoegh.autoloan.model.Loan;
import autoliners.hoegh.autoloan.strategy.LoanStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class LoanStrategyFactory {

    private static final Map<Loan.LoanTypeEnum, LoanStrategy> strategyMap = new EnumMap<>(Loan.LoanTypeEnum.class);

    @Autowired
    private LoanStrategyFactory(List<LoanStrategy> strategies) {
        for (LoanStrategy strategy : strategies) {
            strategyMap.put(strategy.getType(), strategy);
        }
    }

    public LoanStrategy getViewer(Loan.LoanTypeEnum viewerType) {
        LoanStrategy strategy = strategyMap.get(viewerType);
        if (strategy == null) {
            throw new LoanValidationException("Loan type not found " + viewerType.toString());
        }
        return strategy;
    }
}
