package console.payment;

import console.ConsoleCommand;
import console.common.BaseConsole;
import model.payment.PaymentSchedule;
import usecase.PaymentScheduleUseCase;

public class ListPaymentSchedulesConsole extends BaseConsole implements ConsoleCommand {

    private final PaymentScheduleUseCase paymentScheduleUseCase;

    public ListPaymentSchedulesConsole(PaymentScheduleUseCase paymentScheduleUseCase) {
        this.paymentScheduleUseCase = paymentScheduleUseCase;
    }

    @Override
    public void execute() {
        println("----- Listing Payment Schedules -----");
        breakLine();

        for (PaymentSchedule s : paymentScheduleUseCase.getAll()) {
            println(s.toString());
        }
        holdOutput();
        breakLine();
    }

}
