// CommissionEmployee.java
// CommissionEmployee class represents a commission employee.

// CommissionEmployee inherits from Employee and has
// access to Employee's protected members.

package com.employee;


public class CommissionEmployee extends Employee {

   private double grossSales; // gross weekly sales
   private double commissionRate; // commission percentage

   // five-argument constructor
   public CommissionEmployee( String first, String last, String ssn, boolean status, double sales, double rate, int allowances, int hireMonth, int hireDay, int hireYear ) {
	  super( first, last, ssn, status, allowances, hireMonth, hireDay, hireYear);
      setGrossSales( sales ); // validate and store gross sales
      setCommissionRate( rate ); // validate and store commission rate
   }

   // set gross sales amount
   public void setGrossSales( double sales ) {
      grossSales = ( sales < 0.0 ) ? 0.0 : sales;
   }

   // return gross sales amount
   public double getGrossSales() {
      return grossSales;
   }

   // set commission rate
   public void setCommissionRate( double rate ) {
      commissionRate = (( rate > 0.0 && rate < 1.0 ) ? rate : 0.0);
   }

   // return commission rate
   public double getCommissionRate() {
      return this.commissionRate;
   }

   // calculate earnings
   public double earnings() {
      return this.commissionRate * this.grossSales;
   }

   // return String representation of CommissionEmployee object
   public String toString() {
      return String.format( "%s: %s %s\n%s: %s\n%s: %.2f\n%s: %.2f",
         "commission employee", getFirstName(), getLastName(),
         "social security number", getSocialSecurityNumber(),
         "gross sales", getGrossSales(),
         "commission rate", getCommissionRate() );
   }
} // end class CommissionEmployee
