/*
 * Payrol.java
 * Interacts with the user to create employees ...
 *
 * @author
 * @since
 * @version 1.0
*/

package com.payroll;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
		boolean finished = false;
		String[] menu = new String[8];
		menu[0] = "Add Employee";
		menu[1] = "";
		menu[2] = "";
		menu[3] = "";
		menu[4] = "";
		menu[5] = "";
		menu[6] = "Generae Tax Files";
		menu[7] = "Exit";
		String menuTitle = "           Payroll Menu";
		while (finished == false)
		{
			// Menu Display and Get user input
			int inputInt = 0;
			while (inputInt == 0)
			{
				inputInt = displayMenuAndGetInput(menu, menuTitle);

				// if the input is out of range
				if ((inputInt < 1) || (inputInt > (menu.length - 1)))
				{
					System.out.println("\nThe input is out of range!");
					System.out.println();
					inputInt = 0;
				}
			} //end while

			// switch to correspondence function
			switch (inputInt)
			{
				case 1:// add employees
					String addMenuTitle = "      Chose Employee Type";
					String[] addMenu = new String[3];
					addMenu[0] = "Add Hourly Employee";
					addMenu[1] = "Add Salaried Employee";
					addMenu[2] = "Add Commision Employee";
					int menuInput = 0;
					while (menuInput == 0) {
						menuInput = displayMenuAndGetInput(addMenu, addMenuTitle);
						if ((menuInput < 1) || (menuInput > addMenu.length - 1)) {
							System.out.println("\nThe input is out of range!");
							System.out.println();
							menuInput = 0;
						}
					}
					switch (menuInput) {
						case 1:
							break;
						case 2:
							break;
						case 3:
							break;
						default:
							break;
					}
					break;
				case 2:
					//do something
					break;
				case 3:
					//do something
					break;
				case 4:
					//do something
					break;
				case 5:
					//do something
					break;
				case 6:
					//do something
					break;
				case 7:
					LoadTaxes taxes = new LoadTaxes();
					try {
						taxes.doTaxes();
					} catch (FileNotFoundException e) {
						System.out.println("Fatal Error Processing files... (Generate Tax Files)");
					}
					break;

				case 8:
					// exit
					finished = true;
					break;
				default:
					System.out.println("Invalid Input!");
					System.out.println("");
					break;
			} // end switch
		} // end while


	}
		public static int displayMenuAndGetInput(String[] menu, String title)
		{
			int inputInt ;

			// Menu Display
			System.out.println("");
			System.out.println(title);
			System.out.println(" ===========================");
			for (int x = 0; x  < menu.length; x++) {
				if (x < 10) {
					System.out.println(" " + (x+1) + ".)  " + menu[x]);
				} else {
					System.out.println((x+1) + ".)  " + menu[x]);
				}
			}
			System.out.println("");

			// Get the input from the user
			System.out.print("Please input your choice (1 - " + menu.length + "): ");

			@SuppressWarnings("resource")
			Scanner input = new Scanner( System.in );

			inputInt = input.nextInt();

			return inputInt;
		}



}