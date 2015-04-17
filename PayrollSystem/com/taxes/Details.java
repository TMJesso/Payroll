package com.taxes;

public class Details {
	private double minAmount;
	private double lessThanAmount;
	private int numberAllowances;
	private double incomeTax;
	private double minTax;
	private double taxPercent;
	
	private Taxpro taxpro;
	
	public Details(double minAmount, double lessThanAmount, int numberAllowances, double incomeTax) {
		this.minAmount = minAmount;
		this.lessThanAmount = lessThanAmount;
		this.numberAllowances = numberAllowances;
		this.incomeTax = incomeTax;
	}
	
	public Details(double minAmount, double lessThanAmount, double minTax, double taxPercent) {
		this.minAmount = minAmount;
		this.lessThanAmount = lessThanAmount;
		this.minTax = minTax;
		this.taxPercent = taxPercent;
	}

	// get
	
	/**
	 * @return the minTax
	 */
	public double getMinTax() {
		return minTax;
	}

	/**
	 * @return the taxPercent
	 */
	public double getTaxPercent() {
		return taxPercent;
	}


	/**
	 * @return the minAmount
	 */
	public double getMinAmount() {
		return minAmount;
	}

	/**
	 * @return the lessThanAmount
	 */
	public double getLessThanAmount() {
		return lessThanAmount;
	}

	/**
	 * @return the numberAllowances
	 */
	public int getNumberAllowances() {
		return numberAllowances;
	}

	/**
	 * @return the incomeTax
	 */
	public double getIncomeTax() {
		return incomeTax;
	}

	/**
	 * @return the percent
	 */
	public Taxpro getTaxpro() {
		return taxpro;
	}

	
	// set
	
	/**
	 * @param minAmount the minAmount to set
	 */
	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}

	/**
	 * @param lessThanAmount the lessThanAmount to set
	 */
	public void setLessThanAmount(double lessThanAmount) {
		this.lessThanAmount = lessThanAmount;
	}

	/**
	 * @param numberAllowances the numberAllowances to set
	 */
	public void setNumberAllowances(int numberAllowances) {
		this.numberAllowances = numberAllowances;
	}

	/**
	 * @param incomeTax the incomeTax to set
	 */
	public void setIncomeTax(double incomeTax) {
		this.incomeTax = incomeTax;
	}

	/**
	 * @param percent the percent to set
	 */
	public void setPercent(Taxpro taxpro) {
		this.taxpro = taxpro;
	}
	
	/**
	 * @param minTax the minTax to set
	 */
	public void setMinTax(double minTax) {
		this.minTax = minTax;
	}

	/**
	 * @param taxPercent the taxPercent to set
	 */
	public void setTaxPercent(double taxPercent) {
		this.taxPercent = taxPercent;
	}

}
