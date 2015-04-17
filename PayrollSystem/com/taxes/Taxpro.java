package com.taxes;

public class Taxpro {
	private String status;
	private String frequency;
	private int withHoldingAllowance;
	
	/*
	 * withHoldingAllowance is ONLY for Percentage method
	 * 
	 * 2015 amount for ONE Withholding allowance
	 * 
	 * withHoldingAllowance 
	 * Daily		  15.40
	 * Weekly		  76.90
	 * Biweekly		 153.80
	 * Semimonthly	 166.70
	 * Monthly		 333.30
	 * Quarterly	1000.00
	 * Semiannually	2000.00
	 * Annually		4000.00
	 * 
	 * 
	 * Allowance * # employee allowances claimed = total Allowances for employee
	 * 
	 * Gross - total allowances = Adjusted Gross Income
	 * 
	 * Adjusted Gross Income * taxes = taxes to be withheld from employee
	 * 
	 * 
	 * 
	 */
	
	public Taxpro(String status, String frequency, int withHoldingAllowance) {
		this.status = status;
		this.frequency = frequency;
		this.withHoldingAllowance = withHoldingAllowance;
	}
	
	public Taxpro(String status, String frequency) {
		this.status = status;
		this.frequency = frequency;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the frequency
	 */
	public String getFrequency() {
		return frequency;
	}

	/**
	 * @return the withHoldingAllowance
	 */
	public int getWithHoldingAllowance() {
		return withHoldingAllowance;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	/**
	 * @param withHoldingAllowance the withHoldingAllowance to set
	 */
	public void setWithHoldingAllowance(int withHoldingAllowance) {
		this.withHoldingAllowance = withHoldingAllowance;
	}
	
	
}
