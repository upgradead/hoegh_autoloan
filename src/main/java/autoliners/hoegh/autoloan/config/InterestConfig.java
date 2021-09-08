package autoliners.hoegh.autoloan.config;

import autoliners.hoegh.autoloan.model.Loan;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "loan")
@Data
public class InterestConfig {

    private Map<String, Integer> interests;

    private int defaultInterest;

    public int getInterest(Loan.LoanTypeEnum type) {
        return getInterests().entrySet().stream()
                .filter(x -> x.getKey().equals(type.toString()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(defaultInterest);
    }
}
