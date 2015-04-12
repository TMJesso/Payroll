/*
 * Payrol.java
 * Interacts with the user to create employees ...
 *
 * @author
 * @since
 * @version 1.0
*/
package original;
import java.util.*;

public class Payrol {
	//Create an array list of accounts

	public static void main(String[] args) {
		ActualPayrol somePayrol  = new ActualPayrol();
		somePayrol.doPayrol();

	}
}
//-----------------------------------------------------
class ActualPayrol {
	ArrayList<Employee> employees = new ArrayList<Employee>();

	public void doPayrol() {
		ActualPayrol myPayroll = new ActualPayrol();
		myPayroll.addHourlyEmployee();
		myPayroll.addSalariedEmployee();
		myPayroll.addHourlyEmployee();
		myPayroll.addSalariedEmployee();
		myPayroll.displayEmployee();
		myPayroll.findEmployeeBySoc();
		myPayroll.raiseSalary(10.0);
		


	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	
	public void removeEmployee(Employee employee) {
		employees.remove(employee);
	}
	

	public void addSalariedEmployee() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("=======================================\nSalaried Employee\n");
		System.out.print("Enter Employee's frist name: ");
		String fName = input.nextLine();
		System.out.print("Enter Employee's last name: ");
		String lName = input.nextLine();
		System.out.print("Enter Employee's Social Security Number: ");
		String ssn = input.nextLine();
		System.out.print("Enter Employee's Salary: ");
		double salary = input.nextDouble();
		
		employees.add(new SalariedEmployee(fName, lName, ssn, salary));
	}
	
	public void removeSalariedEmployee(SalariedEmployee salary) {
		employees.remove(salary);
	}
	
	public void addHourlyEmployee() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("=======================================\nHourly Employee\n");
		System.out.print("Enter Employee's frist name: ");
		String fName = input.nextLine();
		System.out.print("Enter Employee's last name: ");
		String lName = input.nextLine();
		System.out.print("Enter Employee's Social Security Number: ");
		String ssn = input.nextLine();
		System.out.print("Enter Employee's hourly Wage: ");
		double salary = input.nextDouble();
		System.out.print("Enter Number of hours Worked: ");
		double hours = input.nextDouble();
		
		
		employees.add(new HourlyEmployee(fName, lName, ssn, salary, hours));
	}
	
	public void removeHourlyEmployee(HourlyEmployee hourly) {
		employees.remove(hourly);
	}
	
	public void displayEmployee() {
		for (Employee e: employees) {
			System.out.println(e);
		}
	}
	
	public void findEmployeeBySoc() throws ArrayIndexOutOfBoundsException {
		System.out.println("Find Employee by Social Security Number\n===============================\n");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter SSN to find ");
		String ssn = input.nextLine();
		for (Employee e: employees) {
			if (e.getSocialSecurityNumber().equals(ssn)) {
				System.out.print(e);
			}
		}
		//System.out.println(employees.get(employees.indexOf(ssn)));
		
	}
	
	public void raiseSalary(double increase) {
		for (Employee e: employees) {
			if (e instanceof SalariedEmployee) {
				SalariedEmployee se = (SalariedEmployee)e;
				System.out.println("\n=========================\nBefore Raise\n" + e);
				se.setWeeklySalary(se.getWeeklySalary() * (1 + (increase/100)));
				System.out.println("\n=========================\nAfter Raise\n" + e);
			
			}
		}
	}
	
	
}