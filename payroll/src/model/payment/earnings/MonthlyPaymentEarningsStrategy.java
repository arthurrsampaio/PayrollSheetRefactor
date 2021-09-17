package model.payment.earnings;

import model.employee.CommissionedEmployee;
import model.employee.Employee;
import model.employee.HourlyEmployee;
import model.employee.SalariedEmployee;

import java.util.Calendar;
import java.util.Date;

public class MonthlyPaymentEarningsStrategy implements PaymentEarningsStrategy {

    private final Employee employee;

    public MonthlyPaymentEarningsStrategy(Employee employee) {
        this.employee = employee;
    }

    @Override
    public double calculate(Date payDay) {
        switch (employee.getType()) {
            case HOURLY:
                HourlyEmployee hourly = (HourlyEmployee) employee;
                return PaymentEarningsUtils.calculateHourlyEmployeeEarnings(hourly, payDay, Calendar.MONTH, 1);

            case SALARIED:
                SalariedEmployee salaried = (SalariedEmployee) employee;
                return PaymentEarningsUtils.calculateSalariedEmployeeEarnings(salaried, 4);

            case COMMISSIONED:
                CommissionedEmployee commissioned = (CommissionedEmployee) employee;
                return PaymentEarningsUtils.calculateCommissionedEmployeeEarnings(commissioned, payDay, Calendar.MONTH, 1);

            default:
                return 0;
        }
    }

}
