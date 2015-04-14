package com.payroll;
import java.io.*;
import java.util.*;

import javax.swing.*;

/** load taxes class<br>
 * 
 * @author Theral Jessop<br>
 * <strong>Copyright</strong> (c) 2015 Theral Jessop All Rights Reserved<br>
 * LoadTaxes.java<br>
 * 
 *
 */
public class LoadTaxes {
	
	public void doTaxes() throws FileNotFoundException {
		//System.out.println("Starting Bracket");
		fromWageBracketCSVtoDat();
		//System.out.println("Starting Percent");
		//convertDelimited();
		//newLoadPercent("datafiles/csv/WithholdingTables_2015.csv");
		newLoadingPercent();
	}
	
	
	//----------------------------------------------------------Menu------------------------------------
	
	/** wage bracket conversion
	 * 
	 * @throws FileNotFoundException
	 */
	public void fromWageBracketCSVtoDat() throws FileNotFoundException {
		boolean debug = false;
		String[][] file = new String[10][2];
		
		file[0][0] = getSourceFileName(2);
		file[0][1] = getDataFileName(2);
		
		file[1][0] = getSourceFileName(4);
		file[1][1] = getDataFileName(4);
		
		file[2][0] = getSourceFileName(6);
		file[2][1] = getDataFileName(6);
		
		file[3][0] = getSourceFileName(8);
		file[3][1] = getDataFileName(8);
		
		file[4][0] = getSourceFileName(0); // daily Single
		file[4][1] = getDataFileName(0);
		
		file[5][0] = getSourceFileName(3);
		file[5][1] = getDataFileName(3);
		
		file[6][0] = getSourceFileName(5);
		file[6][1] = getDataFileName(5);
		
		file[7][0] = getSourceFileName(7);
		file[7][1] = getDataFileName(7);
		
		file[8][0] = getSourceFileName(9);
		file[8][1] = getDataFileName(9);
		
		file[9][0] = getSourceFileName(1); // daily married
		file[9][1] = getDataFileName(1);
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

	
	
	//--------------------------------------------------------End Menu---------------------------------

	
	
	

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
		boolean isOK = true;
		double num1 = 0.0;
		while (isOK) {
			try {
				dblNumber = dblNumber.trim();
				num1 = Double.parseDouble(dblNumber);
				isOK = false;
			} catch (NumberFormatException|NullPointerException|IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Invalid double reached!");
			}
		}
		return num1;
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
	 * @author Theral Jessop<br>
	 * <strong>Copyright</strong> (c) 2015 Theral Jessop All Rights Reserved<br>
	 * 
	 * @param filename type String: filename (with path) to be scanned and gather useful data from
	 * @return void
	 * 
	 * 
	 * 
	 * 
	 * @throws FileNotFoundException
	 *  */
	public void newLoadingPercent() throws FileNotFoundException {
		String filename = "datafiles/csv/WithholdingTables_2015.csv";
		// declare whether I am in debug mode or not
		boolean debug = false;
		// declare filenames to be used for the singles and married and a debug value (period)
		String newSingleFile = "", newMarriedFile = "", period = "";
		// establish connection with the file
		@SuppressWarnings("resource")
		Scanner input = new Scanner(new File(filename));
		// declare the line and the column strings to parse them
		String getLine = "", quote = "";
		// declare what row and column i am on and parsing to put into the a array
		// get the first line
		int row = 0;
		int col = 0;
		// as long as there is a line to read from the file
		double[][] newValue = new double[7][10];
		while (input.hasNext()) {
			// what heading am I on and set the appropriate file names
			// get the next line
			getLine = input.nextLine();
			
			if (debug) {
				// show what the line says
				sayOutput((period + "   " + getLine + " : Read Next Line from file! Length: " + getLine.length()), 1);
			}
			// reset the row and col values to 0 then loop again
			switch (getLine) {
				case "Daily":
					period = getLine;
					row = 0;
					col = 0;
					newSingleFile = getDataFileName(10);
					newMarriedFile = getDataFileName(11); 
					continue;
				case "Weekly":
					period = getLine;
					row = 0; 
					col = 0; 
					newSingleFile = getDataFileName(12); 
					newMarriedFile = getDataFileName(13); 
					continue;
				case "Biweekly": 
					period = getLine;
					row = 0; 
					col = 0;
					newSingleFile = getDataFileName(14);
					newMarriedFile = getDataFileName(15); 
					continue;
				case "Semimonthly": 
					period = getLine;
					row = 0;
					col = 0;
					newSingleFile = getDataFileName(16);
					newMarriedFile = getDataFileName(17);
					continue;
				case "Monthly":
					period = getLine;
					row = 0;
					col = 0;
					newSingleFile = getDataFileName(18); 
					newMarriedFile = getDataFileName(19); 
					continue;
				case "Quarterly":
					period = getLine;
					row = 0; 
					col = 0;
					newSingleFile = getDataFileName(20);
					newMarriedFile = getDataFileName(21);
					continue;
				case "Semiannual":
					period = getLine;
					row = 0; 
					col = 0;
					newSingleFile = getDataFileName(22);
					newMarriedFile = getDataFileName(23);
					continue;
				case "Annual":
					period = getLine;
					row = 0;
					col = 0;
					newSingleFile = getDataFileName(24);
					newMarriedFile = getDataFileName(25); 
					continue;
			}
			if (debug) {
				System.out.println(period + "  " + newSingleFile + "  " + newMarriedFile);
			}
			char[] myString = getLine.toCharArray();
			for (int x = 0; x < myString.length; x++) {
				if (myString[x] == '%' || myString[x] == '$' || myString[x] == 'p' || myString[x] == 'l' || myString[x] == 'u' || myString[x] == 's' ) {
					continue;
				}
				if (myString[x] != ' ') {
					quote += myString[x];
				}
			}
			if (debug) {
				System.out.println(quote);
			}
			String[] tokens = quote.split(",");
			// find where the quotes are because in between the quotes is a comma that has to come out
			// in order to split by comma
			for (int y = 0; y < tokens.length; y++) {
				if (tokens[y].contains("\"")) {
					tokens[y] = tokens[y].substring(1, tokens[y].length()) + tokens[(y+1)].substring(0, (tokens[(y+1)].length()-1));
					tokens[(y+1)] = "";
				}
				if (tokens[y].equals("--")) {
					tokens[y] = "0.0";
				}
				if (tokens[y].equals("-")) {
					tokens[y] = "";
				}
			}
			col=0;
			for (int x = 0; x < tokens.length; x++) {
				if (tokens[x].isEmpty()) {
					continue;
				} else {
					newValue[row][col] = dblString(tokens[x]);
					col++;
				}
			}
			quote = "";
			row++;
			if (row > 6) {
				generatePercentFiles(newValue, newSingleFile, newMarriedFile);
			}

		}
	}

	
	public void generatePercentFiles(double[][] newArray, String single, String married) {
		//sayOutput(single, 0);
		//sayOutput(married, 0);
		try {
			PrintWriter writeSingle = new PrintWriter(single);
			for (int x = 0; x < newArray.length; x++) {
				for (int y = 0; y < 5; y++) {
					if (y == 4) {
						writeSingle.print(newArray[x][y]);
						writeSingle.println();
					} else {
						writeSingle.print(newArray[x][y] + ",");
					}
				}
			}
			writeSingle.close();
			PrintWriter writeMarried = new PrintWriter(married);
			for (int x = 0; x < newArray.length; x++) {
				for (int y = 5; y < newArray[x].length; y++) {
					if (y == 9) {
							writeMarried.print(newArray[x][y]);
							writeMarried.println();
					} else {
						writeMarried.print(newArray[x][y] + ",");
					}
				}
			}
			writeMarried.close();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			sayOutput("A write error occured!", 0);
		}

	}
	
	/** get data file names<br><br>
	 * it receives the index of which file to return<br>
	 * and it also gets the path from getDataPath()<br>
	 * 
	 * 
	 * @param index type int: element of the array to return
	 * @return filename containing the path and the name of where to save the data
	 * 
	 */
	public String getDataFileName(int index) {
		String filename = "";
		String[] files = new String[26];
		if (index > files.length){
			return null;
		}
		files[0] = "dailySingleWageBracket.dat";
		files[1] = "dailyMarriedWageBracket.dat";
		files[2] = "weeklySingleWageBracket.dat";
		files[3] = "weeklyMarriedWageBracket.dat";
		files[4] = "biWeeklySingleWageBracket.dat";
		files[5] = "biWeeklyMarriedWageBracket.dat";
		files[6] = "semiMonthlySingleWageBracket.dat";
		files[7] = "semiMonthlyMarriedWageBracket.dat";
		files[8] = "monthlySingleWageBracket.dat";
		files[9] = "monthlyMarriedWageBracket.dat";
		files[10] = "dailySinglePercent.dat";
		files[11] = "dailyMarriedPercent.dat";
		files[12] = "weeklySinglePercent.dat";
		files[13] = "weeklyMarriedPercent.dat";
		files[14] = "biweeklySinglePercent.dat";
		files[15] = "biweeklyMarriedPercent.dat";
		files[16] = "semimonthlySinglePercent.dat";
		files[17] = "semimonthlyMarriedPercent.dat";
		files[18] = "monthlySinglePercent.dat";
		files[19] = "monthlyMarriedPercent.dat";
		files[20] = "quarterlySinglePercent.dat";
		files[21] = "quarterlyMarriedPercent.dat";
		files[22] = "semiannualSinglePercent.dat";
		files[23] = "semiannualMarriedPercent.dat";
		files[24] = "annualSinglePercent.dat";
		files[25] = "annualMarriedPercent.dat";
		filename = (getDataPath().concat(files[index]));
		return filename;
	}
	
	/** data source file names<br><br>
	 * 
	 * 
	 * @param index
	 * @return
	 */
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
		files[6] = "singleSemiMonthly.csv";
		files[7] = "marriedSemiMonthly.csv";
		files[8] = "singleMonthly.csv";
		files[9] = "marriedMonthly.csv";
		files[10] = "WithholdingTables_2015.csv";
		filename = getCSVPath().concat(files[index]);
		return filename;
	}

	
	public String getDataPath() {
		String myPath = "datafiles/";
		return myPath;
	}
	
	public String getCSVPath() {
		String myPath = "datafiles/csv/";
		return myPath;
	}
	
	
}
