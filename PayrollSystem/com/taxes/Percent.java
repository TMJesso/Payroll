package com.taxes;

public class Percent {
	private String status;
	private String frequency;
	private int withHoldingAllowance;
	
	public Percent(String status, String frequency, int withHoldingAllowance) {
		this.status = status;
		this.frequency = frequency;
		this.withHoldingAllowance = withHoldingAllowance;
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
