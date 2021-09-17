package console.employee;

import console.ConsoleCommand;
import console.common.BaseConsole;
import model.employee.Employee;
import usecase.EmployeeUseCase;

public final class ListEmployeesConsole extends BaseConsole implements ConsoleCommand {

    private final EmployeeUseCase employeeUseCase;

    public ListEmployeesConsole(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    @Override
    public void execute() {
        println("----- Listing Employees -----");
        breakLine();

        for (Employee e : employeeUseCase.getAll()) {
            println(e.toString());
        }
        holdOutput();
        breakLine();
    }

}
