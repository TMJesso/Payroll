package com.payroll;
import java.util.Scanner;

public class SimpleMenuExample
{

	public static void main(String[] args)
	{

			boolean finished = false;

			while (finished == false)
			{
				// Menu Display and Get user input
				int inputInt = 0;
				while (inputInt == 0)
				{
					inputInt = displayMenuAndGetInput();

					// if the input is out of range
					if ((inputInt < 1) || (inputInt > 4))
					{
						System.out.println("\nThe input is out of range!");
						System.out.println();
						inputInt = 0;
					}
				} //end while

				// switch to correspondence function
				switch (inputInt)
				{
					case 1:
						//do something
						break;
					case 2:
						//do something
						break;
					case 3:
						//do something
						break;

					case 4:
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
//--------------------------------------------------------------------------------------------

	public static int displayMenuAndGetInput()
	{
		int inputInt ;

		// Menu Display
		System.out.println("");
		System.out.println("     Menu");
		System.out.println(" =====================");
		System.out.println(" 1.  Menu Item");
		System.out.println(" 2.  Menu Item");
		System.out.println(" 3.  Menu Item");
		System.out.println(" 4.  Exit");
		System.out.println("");

		// Get the input from the user
		System.out.print("Please input your choice (1-4): ");

		Scanner input = new Scanner( System.in );

		inputInt = input.nextInt();

		return inputInt;
	}

}