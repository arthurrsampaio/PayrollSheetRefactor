package model.payment.earnings;

import model.Sale;
import model.Timecard;
import model.employee.CommissionedEmployee;
import model.employee.HourlyEmployee;
import model.employee.SalariedEmployee;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class PaymentEarningsUtils {

    public static double calculateHourlyEmployeeEarnings(HourlyEmployee employee, Date payDay, int dateField, int dateAmount) {
        Calendar startRangeCalendar = Calendar.getInstance();
        startRangeCalendar.setTime(payDay);
        startRangeCalendar.add(dateField, -1 * dateAmount);

        long startRange = startRangeCalendar.getTime().getTime();
        long endRange = payDay.getTime();

        List<Timecard> monthTimecards = new ArrayList<>();

        for (Timecard card : employee.getTimecards()) {
            long cardStartTime = card.getStartTime().getTime();
            if (cardStartTime >= startRange && cardStartTime <= endRange) {
                monthTimecards.add(card);
            }
        }

        double earnings = 0;

        for (Timecard card : monthTimecards) {
            double workingHours = Math.min(8, card.getWorkingHours());
            double extraHours = card.getWorkingHours() - 8;

            earnings += workingHours * employee.getPricePerHour();
            earnings += extraHours * employee.getPricePerHour() * 1.5;
        }

        return earnings;
    }

    public static double calculateSalariedEmployeeEarnings(SalariedEmployee employee, int weeksWorked) {
        double percentage = weeksWorked / 4.0;
        return employee.getSalary() * percentage;
    }

    public static double calculateCommissionedEmployeeEarnings(CommissionedEmployee employee, Date payDay, int dateField, int dateAmount) {
        Calendar startRangeCalendar = Calendar.getInstance();
        startRangeCalendar.setTime(payDay);
        startRangeCalendar.add(dateField, -1 * dateAmount);

        long startRange = startRangeCalendar.getTime().getTime();
        long endRange = payDay.getTime();

        List<Sale> monthSales = new ArrayList<>();

        for (Sale sale : employee.getSales()) {
            long saleTime = sale.getDate().getTime();
            if (saleTime >= startRange && saleTime <= endRange) {
                monthSales.add(sale);
            }
        }

        double earnings = employee.getSalary();

        for (Sale sale : monthSales) {
            earnings += sale.getPrice() * employee.getCommission();
        }

        return earnings;
    }

}