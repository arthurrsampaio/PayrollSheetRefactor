package data;

public class EmployeeRepositorySingleton {
	
	static EmployeeRepository instance;

	public static EmployeeRepository getInstance() {
	  if (instance == null) {
	    instance = new EmployeeRepository();
	  }
	  return instance;
	}
}
