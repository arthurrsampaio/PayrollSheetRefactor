package model.payment.earnings;

import model.employee.CommissionedEmployee;
import model.employee.Employee;
import model.employee.HourlyEmployee;
import model.employee.SalariedEmployee;
import model.payment.WeeklyPaymentSchedule;

import java.util.Calendar;
import java.util.Date;

public class WeeklyPaymentEarningsStrategy implements PaymentEarningsStrategy {

    private final Employee employee;
    private final WeeklyPaymentSchedule schedule;

    public WeeklyPaymentEarningsStrategy(Employee employee) {
        this.employee = employee;
        this.schedule = (WeeklyPaymentSchedule) employee.getPaymentSchedule();
    }

    @Override
    public double calculate(Date payDay) {
        switch (employee.getType()) {
            case HOURLY:
                HourlyEmployee hourly = (HourlyEmployee) employee;
                return PaymentEarningsUtils.calculateHourlyEmployeeEarnings(hourly, payDay, Calendar.WEEK_OF_MONTH, schedule.getWeeks());

            case SALARIED:
                SalariedEmployee salaried = (SalariedEmployee) employee;
                return PaymentEarningsUtils.calculateSalariedEmployeeEarnings(salaried, schedule.getWeeks());

            case COMMISSIONED:
                CommissionedEmployee commissioned = (CommissionedEmployee) employee;
                return PaymentEarningsUtils.calculateCommissionedEmployeeEarnings(
                    commissioned,
                    payDay,
                    Calendar.WEEK_OF_MONTH,
                    schedule.getWeeks()
                );

            default:
                return 0;
        }
    }

}