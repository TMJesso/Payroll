package com.payroll;
import java.io.*;
import java.util.*;

import javax.swing.*;


public class LoadTaxes {

	public static void main(String[] args) {
		ActualLoadTaxes someTaxes  = new ActualLoadTaxes();
		try {
			someTaxes.doTaxes();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class ActualLoadTaxes {
	
	public void doTaxes() throws FileNotFoundException {
		System.out.println("Starting Bracket");
		fromWageBracketCSVtoDat();
		System.out.println("Starting Percent");
		convertDelimited();
		
	}
	
	
	//----------------------------------------------------------Menu------------------------------------
	
	/** wage bracket conversion
	 * 
	 * @throws FileNotFoundException
	 */
	public void fromWageBracketCSVtoDat() throws FileNotFoundException {
		boolean debug = false;
		String[][] file = new String[10][2];
		file[0][0] = "f:\\Programming\\Payroll\\singleWeekly.csv";
		file[0][1] = "datafiles/weeklySingleWageBracket.dat";
		
		file[1][0] = "f:\\Programming\\Payroll\\singleBiWeekly.csv";
		file[1][1] = "datafiles/biWeeklySingleWageBracket.dat";
		
		file[2][0] = "f:\\Programming\\Payroll\\singleSemiMonthly.csv";
		file[2][1] = "datafiles/semiMonthlySingleWageBracket.dat";
		
		file[3][0] = "f:\\Programming\\Payroll\\singleMonthly.csv";
		file[3][1] = "datafiles/monthlySingleWageBracket.dat";
		
		file[4][0] = "f:\\Programming\\Payroll\\singleDaily.csv";
		file[4][1] = "datafiles/dailySingleWageBracket.dat";
		
		file[5][0] = "f:\\Programming\\Payroll\\marriedWeekly.csv";
		file[5][1] = "datafiles/weeklyMarriedWageBracket.dat";
		
		file[6][0] = "f:\\Programming\\Payroll\\marriedBiWeekly.csv";
		file[6][1] = "datafiles/biWeeklyMarriedWageBracket.dat";
		
		file[7][0] = "f:\\Programming\\Payroll\\marriedSemiMonthly.csv";
		file[7][1] = "datafiles/semiMonthlyMarriedWageBracket.dat";
		
		file[8][0] = "f:\\Programming\\Payroll\\marriedMonthly.csv";
		file[8][1] = "datafiles/monthlyMarriedWageBracket.dat";
		
		file[9][0] = "f:\\Programming\\Payroll\\marriedDaily.csv";
		file[9][1] = "datafiles/dailyMarriedWageBracket.dat";
		int row = 0, col = 0;
		for (int x = 0; x < file.length; x++) {
		//int x = 5;
			row = getNumberofRows(file[x][0]);
			if (x < 5) {
				col = getNumberofColumns(file[x][0]);
			} else {
				col = getNumberofColumns(file[x][0])+1;
			}
			int[][] allTaxCode = new int[row][col];
			int[][] singleRate = new int[allTaxCode.length][13];
			int[][] marriedRate = new int[allTaxCode.length][13];
			if (debug) {
				sayOutput("x is : " + x ,1);
			}
			allTaxCode = loadWageBracket(file[x][0], allTaxCode);
			if (x <= 4) {
				singleRate = processWageBracket(allTaxCode, singleRate);
				// this is for testing and debugging only				
				//gt.buildOutput(singleRate, "Single Rate");
				writeWageBracket(singleRate, file[x][1]);
			} else {
				marriedRate = processWageBracket(allTaxCode, marriedRate);
				// this is for testing and debugging only
				//gt.buildOutput(marriedRate, "Married Rate");
				writeWageBracket(marriedRate, file[x][1]);
			}
		}
	}

	/** create percentage files from the WithholdingTables_2015.csv file
	 * in order to properly parse this and convert the appropriate files
	 * all heading and unused areas must be deleted from the file
	 * so it is a bare comma delimited file
	 * 
	 * also there needs to be a heading value between each section
	 * <p>example:
	 * 
	 * <p>Weekly
	 * 
	 * <p>,,$44 ,$222 ,,$0.00 ,plus,10%,,$44 ,,,$165 ,$520 ,,$0.00 ,plus,10%,,$165 ,,
	 * <p> ...
	 * <p>...
	 * <p>...
	 * <p>Biweekly
	 * <p>,,$88 ,$443 ,,$0.00 ,plus,10%,,$88 ,,,$331 ,"$1,040 ",,$0.00 ,plus,10%,,$331 ,,
	 * <p>...
	 * <p>...
	 * <p> etc.
	 */
	public void convertDelimited() throws FileNotFoundException {
		//String filename = gt.getString("Enter the path and filename of the comma delimited file to convert\n\n(example: f:\\Programming\\Payroll\\WithholdingTables_2015)\n\nDo not include the extenstion because it will be added automaticly\nHowever, the file must be saved using a comma delimited format \nand have the csv extension!"); // "f:\\Programming\\Payroll\\WithholdingTables_2015.csv";
		String filename = "datafiles/csv/WithholdingTables_2015";
		newloadPercent((filename + ".csv"));

	}

	
	//--------------------------------------------------------End Menu---------------------------------

	
	
	
	/** display the results of a integer, two-dimensional array in the form of a matrix */
	public void buildOutput( int array[][], String message ) {
		// loop through array's rows
		System.out.println(message);
		   for ( int row = 0; row < array.length; row++ ) {
	    	  // loop through columns of current row
	    	  for ( int column = 0; column < array[row].length; column++ ) {
	        	 System.out.print( array[ row ][ column ] + "  " );
	         }
	         System.out.println( "\n" );
	      }
	} // end method buildOutput

		/** display the results of a double, two-dimensional array in the form of a matrix */
	public void buildOutput( double array[][], String message) {
		// loop through array's rows
		System.out.println(message);
		for ( int row = 0; row < array.length; row++ ) {
			System.out.print(row + "# ");
			// loop through columns of current row
			for ( int column = 0; column < array[row].length; column++ ) {
				System.out.print( column + " : " + array[ row ][ column ] + "    " );
			}
			System.out.println( "\n" );
		}
	} // end method buildOutput

	/** get an integer using JOptionPane dialog box */
	public int getInteger(String message) {
		boolean loop = true;
		int num1 = 0;
		while (loop) {
			try {
				num1 = Integer.parseInt(JOptionPane.showInputDialog(message));
				loop = false;
				continue;
			} catch (NumberFormatException|NullPointerException|IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Invalid Entry!");
				continue;
			}
		}
		return num1;
	}

	/** get a double using a JOptionPane dialog box */
	public double getDouble(String message) {
		boolean loop = true;
		double num1 = 0;
		while (loop) {
			try {
				num1 = Double.parseDouble(JOptionPane.showInputDialog(message));
				loop = false;
				continue;
			} catch (NumberFormatException|NullPointerException|IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Invalid Entry!");
				continue;
			}
		}
		return num1;
	}
	
	/** get a string using a JOptionPane dialog box 
	 * @param message: display a message to the user while asking for input
	 * @return a string
	 * */
	public String getString(String message) {
		String input = "";
		boolean loop = true;
		while (loop) {
			try {
				input = JOptionPane.showInputDialog(message);
				input.charAt(0);
				loop = false;
			} catch (NullPointerException|IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Null String Encountered!");
				continue;
			}
		}
		return input;
	}
	
	
	/** display a message<br>
	 * 0 - using the JOptionPane dialog box<br>
	 * 1 - using the System.out method<br>
	 * 2 - using both JOptionPane and System.out<br>
	 * 
	 * @param message the message to be displayed
	 * @param num the integer to determine how it is displayed
	 * 
	 */
	public void sayOutput(String message, int num) {
		switch (num) {
		case 0: JOptionPane.showMessageDialog(null, message);break;
		case 1: System.out.println(message);break;
		case 2: JOptionPane.showMessageDialog(null, message);System.out.println(message);break;
		}
	}
	
	/** display a output message from JTextArea using JOptionPane dialog box<br>
	 * 
	 * @param outputArea the designated area to display the results in
	 * 
	 * 
	 */
	public void sayOutput(JTextArea outputArea) {
		JOptionPane.showMessageDialog(null, outputArea);
	}

	/** convert a string to an integer<br>
	 * 
	 * @param input the message to be displayed
	 * @return an integer value
	 */
	public int intString(String input) {
		boolean loop = true;
		int num1 = 0;
		while (loop) {
			try {
				num1 = Integer.parseInt(input);
				loop = false;
				continue;
			} catch (NumberFormatException|NullPointerException|IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Invalid integer reached!");
				continue;
			}
		}
		return num1;
	}

	/** convert a string to a double<br>
	 * 
	 * @param dblNumber the String to convert to a double
	 * @return a double
	 */
	public double dblString(String dblNumber) {
		boolean loop = true;
		double num1 = 0.0;
		while (loop) {
			try {
				dblNumber = dblNumber.trim();
				num1 = Double.parseDouble(dblNumber);
				loop = false;
				continue;
			} catch (NumberFormatException|NullPointerException|IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Invalid double reached!");
				continue;
			}
		}
		return num1;
	}

	
	/** search the quote and get the valid number and place it in the returning array<br>
	 * add the numbers to the array<br>
	 * 
	 * @param a [][] type double: a two dimensional array
	 * @param quote the string to look through
	 * @param line the element of the first dimension of the array
	 * @param nextOne the element of the second dimension of the array
	 * @return double [][]
	 * 
	 *  */
	private double[][] procArray(double[][] a, String quote, int line, int nextOne) {
		int x = 0;
		boolean percent = false, debug = true;
		while (x < quote.length()) {
			switch (quote.charAt(x)) {
				case 34: quote = quote.substring(x+1, quote.lastIndexOf("\""));break; // double quote
				case 36: quote = quote.substring(x+1, quote.length());break; // $
				case 37: quote = quote.substring(0, quote.indexOf("%"));percent = true;break; // %
				case 45: quote = quote.substring(quote.lastIndexOf("-"), quote.length());break; // -
			}
			if (debug) {
				sayOutput(quote + " : After Switch", 1);
			}
			if (x >= quote.length()) {
				continue;
			}
			if ((quote.charAt(x) == 44) && (x == 0 )) { // comma
				if (quote.lastIndexOf(",") < 0) {
					quote = quote.substring(x+1, quote.length());
				} else {
					quote = quote.substring(x+1,quote.lastIndexOf(","));
				}
			} else if ((quote.charAt(x) == 44) && (x == (quote.length()-1))) {
				quote = quote.substring(0, x);
			}
			x++;
		}
		if (debug) {
			sayOutput(quote + " : After comma", 1);
		}
		if (quote.equals("0.00") || quote.equals("--") || quote.equals("-")) {
			a[line][nextOne] = 0.00;
		} else {
			//} else if (quote.equalsIgnoreCase("weekly") || quote.equalsIgnoreCase("Daily") || quote.equalsIgnoreCase("biweekly") || quote.equalsIgnoreCase("semimonthly") || quote.equalsIgnoreCase("monthly") || quote.equalsIgnoreCase("quarterly") || quote.equalsIgnoreCase("semiannual") || quote.equalsIgnoreCase("annual")) {
			if (percent) {
				a[line][nextOne] = (dblString(quote)/100);
			} else {
				a[line][nextOne] = dblString(quote);
			}
		}
		return a;
	}
	
	/** parse the full array and make it either<br>
	* single or married depending on which side it is on<br>
	* left side in csv file is single, right side is married<br>
	* 
	* @param a [x][y] type double: the value of the singleRrate array
	* @param b [x][y] type double: the value of the shrinkMyArray array
	* @param toWhat type String: the whether it is single or married
	* @return a [x][y] the singleRate array
	*/
	public double[][] reLoadPercent(double[][] a, double[][] b, String toWhat) {
		boolean debug = false;
		if (toWhat.equalsIgnoreCase("single")) {
			for (int x = 0; x < a.length; x++) {
				for (int y = 0; y < a[x].length; y++) {
					if (debug) {
						System.out.println("x: " + x + " | y: " + y);
					}
					a[x][y] = b[x][y];
				}
				
			}
		} else if (toWhat.equalsIgnoreCase("married")) {
			for (int w = 0; w < a.length; w++) {
				for (int z = 0; z < a[w].length; z++) {
					a[w][z] = b[w][z+5];
				}
			}
		}
		return a;
	}
	
	/** shrink the original array to the usable information<br>
	* so it can be parsed into separate arrays<br>
	* 
	* @param a [x][y] type double: the array that will be used to get useful information from
	* @return newArray [x][y] type double: return the results
	*/
	public double[][] shrinkArray(double[][] a) {
		double[][] newArray = new double[a.length][10];
		int index = 2;
		for (int x = 0; x < newArray.length; x++) {
			index = 2;
			for (int y = 0; y < newArray[x].length; y++) {
				newArray[x][y] = a[x][index];
				if (index== 9) {
					index+=3;
				} else if (index == 2 || index == 12) {
					index++;
				} else {
					index+=2;
				}
			}
		}
		return newArray;
	}
	
	
	/** write wage bracket data to file<br>
	 * 
	 * @param a [][] type int: the values of the wage bracket data
	 * @param filename the filename (along with the path) where the file is to be written
	 * 
	 * @return void
	 * */
	public void writeWageBracket(int[][] a, String filename) {
		/** write percentage data do file */
		try {
			PrintWriter output = new PrintWriter(filename);
			for (int x = 0; x < a.length; x++) {
				for (int y = 0; y < a[x].length; y++) {
					if (y == a[x].length-1) {
						output.print(a[x][y]);
					} else {
						output.print(a[x][y] + ",");
					}
				}
				if (x < a.length-1) {
					output.println();
				}
			}
			output.close();
		} catch (Exception e) {
			sayOutput("A write error occured!", 0);
			
		}
	}
	
	
	/** process the allTaxCode into the single or married wage bracket files<br>
	 * 
	 * pass the array in the following order:<br>
	 * @param a [x][y] type int: allTaxCode = a
	 * @param b [x][y] type int: singleRate or marriedRate array = b 
	 * */
	public int[][] processWageBracket(int[][] a, int[][] b) {
		
		for (int x = 0; x < b.length;x++) {
			for (int y = 0; y < b[x].length;y++) {
				if (y < 2) {
					b[x][y] = a[x][y+2];
				} else {
					b[x][y] = a[x][y+3];
				}
			}
		}
		return b;
	}

	
	/** write percentage data to file<br>
	 * 
	 * @param a [x][y] type double: the array that contains the data to be written to file
	 * @param filename type String: the filename (with path) where the data will be stored
	 * @return void
	 * 
	 *  */
	public void writePercent(double[][] a, String filename) {
		
		try {
			PrintWriter output = new PrintWriter(filename);
			for (int x = 0; x < a.length; x++) {
				for (int y = 0; y < a[x].length; y++) {
					if (y == a[x].length-1) {
						output.print(a[x][y]);
					} else {
						output.print(a[x][y] + ",");
					}
				}
				if (x < a.length-1) {
					output.println();
				}
			}
			output.close();
		} catch (Exception e) {
			sayOutput("A write error occured!", 0);
			
		}
	}
	
	/** get data for wage bracket table<br><br>
	 * 
	 *  I think I can optimize this code using the split method call on the string<br>
	 *  so I need to recode this sometime in the future<br><br>
	 *  <strong>I will add this to my todo list</strong><br><br>
	 *  @param filename type String: filename (and path) where the data will be read from
	 *  @param a [x][y] type int: the array that will contain the data
	 *  @return a [x][y]
	 *  
	 *  
	 *  */
	public int[][] loadWageBracket(String filename, int[][] a) throws FileNotFoundException {
		boolean debug = false;
		Scanner input = new Scanner(new File(filename));
		String getLine = "", quote = "";
		int row = 0, col = 0;
		while (input.hasNext()) {
			getLine = input.nextLine();
			for (int x = 0; x < getLine.length(); x++) {
				while (getLine.charAt(x) == 44) { // comma
					a[row][col] = 0;
					col++;
					x++;
					continue;
				}
				if (getLine.charAt(x) == 36) { // $
					x++;
					if (getLine.indexOf(",", x) >= 0) {
						quote = getLine.substring(x,getLine.indexOf(",", x));
						x = getLine.indexOf(",",x);
					} else {
						quote = getLine.substring(x,getLine.length());
						x = getLine.length();
					}
				} else {
					if (getLine.indexOf(",", x) >= 0) {
						quote = getLine.substring(x,getLine.indexOf(",", x));
						x = getLine.indexOf(",",x);
					} else {
						quote = getLine.substring(x,getLine.length());
						x = getLine.length();
					}
				}
				quote = quote.trim();
				if (debug) {
					sayOutput("Quote: " + quote + "\nRow: " + row + "\nCol: " + col,1);
				}
				if (quote.isEmpty()) {
					a[row][col] = 0;
					col++;
					continue;
				} else {
					a[row][col] = intString(quote);
					col++;
					continue;
				}
			}
			row++;
			col = 0;
		}
		input.close();
		return a;
	}
	
	/* build the data files for single and married percentage 
	 * this is to be kept for my personal reference
	 * 
	 *
	public void createPercentFiles() throws FileNotFoundException  {
		boolean debug = false;
		double[][] allTaxCode = new double[7][22];
		double[][] singleRate = new double[7][5];
		double[][] marriedRate = new double[7][5];
		double[][] shrinkMyArray = new double[7][10];
		String filename = "f:\\Programming\\Payroll\\weeklySingleMarriedPercentage.csv";
		allTaxCode = loadPercent(filename, allTaxCode);
		shrinkMyArray = shrinkArray(allTaxCode);
		// parse the single rate
		singleRate = reLoadPercent(singleRate, shrinkMyArray, "single");
		// parse the married rate
		marriedRate = reLoadPercent(marriedRate, shrinkMyArray, "married");
			
		String newName = "f:\\Programming\\Payroll\\weeklySinglePercentage.dat";
		writePercent(singleRate, newName);
		newName = "f:\\Programming\\Payroll\\weeklyMarriedPercentage.dat";
		writePercent(marriedRate, newName);
		if (debug) {
			buildOutput(shrinkMyArray,"This is the shrunk array");
			buildOutput(singleRate, "Single Rate");
			buildOutput(marriedRate, "Married Rate");
		
		}

	}
	 /* */	
	
	
	/** how many rows do I need for the array from the file for the initialization of the array<br>
	 * 
	 * @param filename to be scanned
	 * @return rows type int: contains the number of rows
	 * 
	 * */
	public int getNumberofRows(String filename) throws FileNotFoundException {
		int rows = 0;
		Scanner input = new Scanner(new File(filename));
		// as long as there is a line to read, loop
		while (input.hasNext()) {
			// count how many lines are able to be read
			input.nextLine();
			rows++;
		}
		input.close();
		return rows;
	}
	
	
	/** how many columns are in the file for the initialization of the array<br>
	 * 
	 * @param filename type String: filename (with path) to be scanned
	 * @return col type int: the number of colums in the file (seperated with commas)
	 *  
	 *  
	 *  */
	public int getNumberofColumns(String filename) throws FileNotFoundException {
		int col = 0;
		String getLine = "";
		Scanner input = new Scanner(new File(filename));
		// get one line
		getLine = input.nextLine();
		// loop through the line and find all the commas
		// which indicate a new column
		for (int x = 0; x < getLine.length(); x++) {
			if (getLine.charAt(x) == 44) {
				col++;
			}
		}
		input.close();
		// for the array it needs to be one number higher then the actual number of columns
		col++;
		return col;
	}
	
	
	/** file io to read from a CSV file<br>
	 * setup arrays to be used in the prcessing of the file<br>
	 * 
	 * @param filename type String: filename (with path) to be scanned and gather useful data from
	 * @return void
	 * 
	 * 
	 * 
	 * 
	 * @throws FileNotFoundException
	 *  */
	public void newLoadPercent(String filename) throws FileNotFoundException {
		// declare arrays to be used to create the necessary files
		double[][] a = new double[7][22];
		double[][] singleRate = new double[7][5];
		double[][] marriedRate = new double[7][5];
		double[][] shrinkMyArray = new double[7][10];
		// declare whether I am in debug mode or not
		boolean debug = true;
		// declare filenames to be used for the singles and married and a debug value (period)
		String newSingleFile = "", newMarriedFile = "", period = "";
		// establish connection with the file
		Scanner input = new Scanner(new File(filename));
		// declare the line and the column strings to parse them
		String getLine = "", quote = "";
		// declare what row and column i am on and parsing to put into the a array
		int row = 0, col = 0;
		// as long as there is a line to read from the file
		while (input.hasNext()) {
			// get the line
			getLine = input.nextLine();
			// what heading am I on and set the appropriate file names
			// reset the row and col values to 0 then loop again
			switch (getLine) {
				case "Weekly": period = getLine; row = 0; col = 0; newSingleFile = "datafiles/weeklySinglePercent.dat";newMarriedFile = "datafiles/weeklyMarriedPercent.dat";continue;
				case "Biweekly": period = getLine; row = 0; col = 0; newSingleFile = "datafiles/biweeklySinglePercent.dat";newMarriedFile = "datafiles/biweeklyMarriedPercent.dat";continue;
				case "Semimonthly": period = getLine; row = 0; col = 0; newSingleFile = "datafiles/semimonthlySinglePercent.dat";newMarriedFile = "datafiles/semimonthlyMarriedPercent.dat";continue;
				case "Monthly": period = getLine; row = 0; col = 0; newSingleFile = "datafiles/monthlySinglePercent.dat";newMarriedFile = "datafiles/monthlyMarriedPercent.dat";continue;
				case "Quarterly": period = getLine; row = 0; col = 0; newSingleFile = "datafiles/quarterlySinglePercent.dat";newMarriedFile = "datafiles/quarterlyMarriedPercent.dat";continue;
				case "Semiannual": period = getLine; row = 0; col = 0; newSingleFile = "datafiles/semiannualSinglePercent.dat";newMarriedFile = "datafiles/semiannualMarriedPercent.dat";continue;
				case "Annual": period = getLine; row = 0; col = 0; newSingleFile = "datafiles/annualSinglePercent.dat";newMarriedFile = "datafiles/annualMarriedPercent.dat";continue;
				case "Daily": period = getLine; row = 0; col = 0; newSingleFile = "datafiles/dailySinglePercent.dat";newMarriedFile = "datafiles/dailyMarriedPercent.dat";continue;
			}
			
			if (debug) {
				// show what the line says
				sayOutput(getLine + " : Read Next Line from file", 1);
			}
			
			
			
			
		}
	}

	
	
	/** file io to read from a CSV file<br>
	 * setup arrays to be used in the prcessing of the file<br>
	 * 
	 * @param filename type String: filename (with path) to be scanned and gather useful data from
	 * @return void
	 * 
	 * 
	 * 
	 * 
	 * @throws FileNotFoundException
	 *  */
	public void newloadPercent(String filename) throws FileNotFoundException {
		// declare arrays to be used to create the necessary files
		double[][] a = new double[7][22];
		double[][] singleRate = new double[7][5];
		double[][] marriedRate = new double[7][5];
		double[][] shrinkMyArray = new double[7][10];
		// declare whether I am in debug mode or not
		boolean debug = true;
		// declare filenames to be used for the singles and married
		String newSingleFile = "", newMarriedFile = "", period = "";
		// establish connection with the file
		Scanner input = new Scanner(new File(filename));
		// declare the line and the column strings to parse them
		String getLine = "", quote = "";
		// declare what row and column i am on and parsing to put into the a array
		int row = 0, col = 0;
		// as long as there is a line to read from the file
		while (input.hasNext()) {
			// get the line
			getLine = input.nextLine();
			// what heading am I on and set the appropriate file names
			// reset the row and col values to 0 then loop again
			switch (getLine) {
				case "Weekly": period = getLine; row = 0; col = 0; newSingleFile = "datafiles/weeklySinglePercent.dat";newMarriedFile = "datafiles/weeklyMarriedPercent.dat";continue;
				case "Biweekly": period = getLine; row = 0; col = 0; newSingleFile = "datafiles/biweeklySinglePercent.dat";newMarriedFile = "datafiles/biweeklyMarriedPercent.dat";continue;
				case "Semimonthly": period = getLine; row = 0; col = 0; newSingleFile = "datafiles/semimonthlySinglePercent.dat";newMarriedFile = "datafiles/semimonthlyMarriedPercent.dat";continue;
				case "Monthly": period = getLine; row = 0; col = 0; newSingleFile = "datafiles/monthlySinglePercent.dat";newMarriedFile = "datafiles/monthlyMarriedPercent.dat";continue;
				case "Quarterly": period = getLine; row = 0; col = 0; newSingleFile = "datafiles/quarterlySinglePercent.dat";newMarriedFile = "datafiles/quarterlyMarriedPercent.dat";continue;
				case "Semiannual": period = getLine; row = 0; col = 0; newSingleFile = "datafiles/semiannualSinglePercent.dat";newMarriedFile = "datafiles/semiannualMarriedPercent.dat";continue;
				case "Annual": period = getLine; row = 0; col = 0; newSingleFile = "datafiles/annualSinglePercent.dat";newMarriedFile = "datafiles/annualMarriedPercent.dat";continue;
				case "Daily": period = getLine; row = 0; col = 0; newSingleFile = "datafiles/dailySinglePercent.dat";newMarriedFile = "datafiles/dailyMarriedPercent.dat";continue;
			}
			
			if (debug) {
				// show what the line says
				sayOutput(getLine + " : Read Next Line from file", 1);
			}
			
			// parse the line
			for (int x = 0; x < getLine.length(); x++) {
				if (debug) {
					sayOutput("Row: " + row + " Col: " + col, 1);
				}
				if ( ((char) getLine.charAt(x)) == ',') { // comma
					// if it is a comma then increase the column and get the next character
					col++;
					continue;
				} else if (((char) getLine.charAt(x)) == '"') { // double quote
					// if the character is a double quote then increase the pointer to the next character
					// put the contents that are between the double quotes into the QUOTES variable
					// remove any commas from the quote then send the the array to be parsed and cleaned
					// up and set to a double value and return the contents and step the line parser
					// forward the number of times as needed
					x++;
					quote = getLine.substring(x, getLine.indexOf("\"", x));
					quote = quote.trim();
					if (quote.indexOf(",") >= 0) {
						quote = quote.substring(0, quote.indexOf(",")) + quote.substring(quote.indexOf(",")+1, quote.length());
					}
					a = procArray(a, quote, row, col);
					x = getLine.indexOf("\"", x);
				} else {
					// it wasn't a double quote or a comma so get the contents between the commas
					if (debug) {
						System.out.println("\t\t\t" + x + "\t\t\t" + getLine.length() + "\t\t\t" + period);
					}
					
					quote = getLine.substring(x, ((getLine.indexOf(",", x)<0)?getLine.length():getLine.indexOf(",", x)));
					quote = quote.trim();

					// check to see if the contents is "plus"
					if ((quote.equalsIgnoreCase("plus"))) {
						col++;
						x = getLine.indexOf(",", x);
						continue;
					}
				}
				
				// send it to be parsed and cleaned and converted to a double value and return
				// the results then increase the line parser the number of times as needed
				if (getLine.indexOf(",",x) >= 0) {
//				if (quote.isEmpty() == false) {
					a = procArray(a, quote, row, col);
				}
				col++;
				x = getLine.indexOf(",", x);
			}
			row++;
			col = 0;
			// convert and write the information into the appropriate files
			if (row == 7) { // send the contents to the appropriate file
				// get rid of all unused elements
				shrinkMyArray = shrinkArray(a);
				// separate the singles
				singleRate = reLoadPercent(singleRate, shrinkMyArray, "single");
				// separate the married
				marriedRate = reLoadPercent(marriedRate, shrinkMyArray, "married");
				// write the singles file
				writePercent(singleRate, newSingleFile);
				// write the married file
				writePercent(marriedRate, newMarriedFile);
				
				// reset the array
				for (int x = 0; x < a.length; x++) {
					for (int y = 0; y < a[x].length; y++) {
						a[x][y] = 0;
					}
				}
			}
		}
		// close the input channel
		input.close();
	}

	
	
	public String getDataFileName(int index) {
		String filename = "";
		String[] files = new String[27];
		if (index > files.length){
			return null;
		}
		files[0] = "dailySingleWageBracket.dat";
		files[2] = "dailyMarriedWageBracket.dat";
		files[3] = "weeklySingleWageBracket.dat";
		files[4] = "weeklyMarriedWageBracket.dat";
		files[5] = "biWeeklySingleWageBracket.dat";
		files[6] = "biWeeklyMarriedWageBracket.dat";
		files[7] = "semiMonthlySingleWageBracket.dat";
		files[8] = "semiMonthlyMarriedWageBracket.dat";
		files[9] = "monthlySingleWageBracket.dat";
		files[10] = "monthlyMarriedWageBracket.dat";
		files[11] = "dailySinglePercent.dat";
		files[12] = "dailyMarriedPercent.dat";
		files[13] = "weeklySinglePercent.dat";
		files[14] = "weeklyMarriedPercent.dat";
		files[15] = "biweeklySinglePercent.dat";
		files[16] = "biweeklyMarriedPercent.dat";
		files[17] = "semimonthlySinglePercent.dat";
		files[18] = "semimonthlyMarriedPercent.dat";
		files[19] = "monthlySinglePercent.dat";
		files[20] = "monthlyMarriedPercent.dat";
		files[21] = "quarterlySinglePercent.dat";
		files[22] = "quarterlyMarriedPercent.dat";
		files[23] = "semiannualSinglePercent.dat";
		files[24] = "semiannualMarriedPercent.dat";
		files[25] = "annualSinglePercent.dat";
		files[26] = "annualMarriedPercent.dat";
		return filename;
	}
	
	public String getSourceFileName(int index) {
		String filename = "";
		String[] files = new String[11];
		if (index > files.length){
			return null;
		}
		files[0] = "singleDaily.csv";
		files[1] = "marriedDaily.csv";
		files[2] = "singleWeekly.csv";
		files[3] = "marriedWeekly.csv";
		files[4] = "singleBiWeekly.csv";
		files[5] = "marriedBiWeekly.csv";
		files[6] = "singleMonthly.csv";
		files[7] = "marriedMonthly.csv";
		files[8] = "singleSemiMonthly.csv";
		files[9] = "marriedSemiMonthly.csv";
		files[10] = "WithholdingTables_2015.csv";
		return filename;
	}

	
	public String getDataPath() {
		String myPath = "datfiles/";
		return myPath;
	}
	
	public String getCSVPath() {
		String myPath = "datafiles/csv";
		return myPath;
	}
	
	
}
