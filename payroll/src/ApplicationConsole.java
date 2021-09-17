import console.ConsoleCommand;
import console.RunPayrollConsole;
import console.SubmitSaleConsole;
import console.SubmitTimecardConsole;
import console.common.BaseConsole;
import console.employee.AddEmployeeConsole;
import console.employee.EditEmployeeConsole;
import console.employee.ListEmployeesConsole;
import console.employee.RemoveEmployeeConsole;
import console.payment.AddPaymentSchedule;
import console.payment.EditPaymentSchedule;
import console.payment.ListPaymentSchedulesConsole;
import di.UseCaseFactory;

import java.util.HashMap;
import java.util.Map;

public final class ApplicationConsole extends BaseConsole {

    private final Map<Integer, ConsoleCommand> commands;

    public ApplicationConsole(UseCaseFactory useCaseFactory) {
        super();
        this.commands = new HashMap<>();
        commands.put(1, new ListEmployeesConsole(useCaseFactory.getEmployeeUseCase()));
        commands.put(2, new AddEmployeeConsole(useCaseFactory.getEmployeeUseCase()));
        commands.put(3, new EditEmployeeConsole(useCaseFactory.getEmployeeUseCase()));
        commands.put(4, new RemoveEmployeeConsole(useCaseFactory.getEmployeeUseCase()));
        commands.put(5, new SubmitTimecardConsole(useCaseFactory.getTimecardUseCase()));
        commands.put(6, new SubmitSaleConsole(useCaseFactory.getSaleUseCase()));
        commands.put(7, new SubmitTimecardConsole(useCaseFactory.getTimecardUseCase()));
        commands.put(8, new RunPayrollConsole(useCaseFactory.getPayrollUseCase()));
        commands.put(9, new ListPaymentSchedulesConsole(useCaseFactory.getPaymentScheduleUseCase()));
        commands.put(10, new AddPaymentSchedule(useCaseFactory.getPaymentScheduleUseCase()));
        commands.put(11, new EditPaymentSchedule(useCaseFactory.getPaymentScheduleUseCase()));
    }

    public void start() {
        int option;
        do {
            showMenu();
            option = Integer.parseInt(scanner.nextLine());
            breakLine();

            ConsoleCommand command = commands.get(option);

            if (command != null) {
                command.execute();
            } else if (option != 0) {
                println("[ERR] Invalid menu option");
                holdOutput();
            }
        } while (option != 0);

        println("Exiting...");
        holdOutput();
    }
    private void showMenu() {
        println("===== PAYROLL =====");
        breakLine();
        println("1 - List Employees");
        println("2 - Add Employee");
        println("3 - Edit Employee");
        println("4 - Remove Employee");
        println("5 - Submit Timecard");
        println("6 - Submit Sale");
        println("7 - Submit Service Fee");
        println("8 - Run Payroll to Today");
        println("9 - List Payment Schedules");
        println("10 - Add Payment Schedules");
        println("11 - Edit Employee Payment Schedule");
        println("0 - Exit");
        breakLine();
        print("-> Option: ");
    }
}