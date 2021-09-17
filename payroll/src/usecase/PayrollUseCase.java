package usecase;

import model.ServiceFee;
import model.employee.Employee;
import model.payment.Payment;
import model.payment.PaymentSchedule;
import model.payment.earnings.MonthlyPaymentEarningsStrategy;
import model.payment.earnings.PaymentEarningsStrategy;
import model.payment.earnings.WeeklyPaymentEarningsStrategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class PayrollUseCase {

    private static final double SYNDICATE_FEE = 0.1f;

    private final EmployeeUseCase employeeUseCase;

    public PayrollUseCase(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    public List<Payment> calculatePayments(Date payDay) {
        List<Payment> payments = new ArrayList<>();
        List<Employee> employees = employeeUseCase.getAll();

        for (Employee employee : employees) {
            // checking pay day
            PaymentSchedule schedule = employee.getPaymentSchedule();
            if (!schedule.isPayDay(payDay)) continue;

            PaymentEarningsStrategy earningsStrategy;
            switch (schedule.getType()) {
                case WEEKLY:
                    earningsStrategy = new WeeklyPaymentEarningsStrategy(employee);
                    break;
                case MONTHLY:
                    earningsStrategy = new MonthlyPaymentEarningsStrategy(employee);
                    break;
                default:
                    throw new IllegalStateException("Invalid schedule type");
            }

            // calculating earnings
            double earnings = earningsStrategy.calculate(payDay);

            // calculating discounts
            double discounts = SYNDICATE_FEE * earnings;
            for (ServiceFee fee : employee.getServiceFees()) {
                discounts += fee.getFee() * earnings;
            }

            Payment payment = new Payment(employee, earnings, discounts, earnings - discounts);
            payments.add(payment);
        }

        return payments;
    }

}