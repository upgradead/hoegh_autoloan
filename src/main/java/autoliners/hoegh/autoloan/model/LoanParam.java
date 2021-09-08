package autoliners.hoegh.autoloan.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoanParam {

    private double mortgage;
    private double monthlyMaxPay;
    private int terms;
    private double interest;

    @Override
    public String toString (){
        StringBuilder sb = new StringBuilder();
        sb.append("Mortgage ").append(mortgage).append("\r\n");
        sb.append("Monthly Maximum Pay ").append(monthlyMaxPay).append("\r\n");
        sb.append("Terms ").append(terms).append("\r\n");
        sb.append("Interest ").append(interest);
        return sb.toString();
    }
}
