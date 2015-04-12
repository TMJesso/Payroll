/*
 * Payrol.java
 * Interacts with the user to create employees ...
 *
 * @author
 * @since
 * @version 1.0
*/

package com.payroll;
import java.util.ArrayList;

import com.employee.Employee;

public class Payroll {
	//Create an array list of accounts

	public static void main(String[] args) {
		ActualPayroll somePayrol  = new ActualPayroll();
		somePayrol.doPayroll();

	}
}
//-----------------------------------------------------
class ActualPayroll {
	ArrayList<Employee> employees = new ArrayList<Employee>();

	public void doPayroll() {
		//Write your client code here ....

	}

	//Add a method that adds an employee to the array list
	//Add a method that removes an employee from the array list
	//Add a method that creates a Salaried Employee and adds it to the arraylist
	//Add a method that creates an Hourly Employee and adds it to the list
	//In doPayroll(), invoke each of the above methods twice (effectively creating four employees)
	//Display all employees currently in the array list

	/*
	 Add a method that would search for all salaried employees in the array list and give them all 10% raise.
	 Note: you should use instanceof operator.

	 Call this method from doPayroll()
	*/



}