package model.payment.earnings;

import java.util.Date;

public interface PaymentEarningsStrategy {
    double calculate(Date payDay);
}