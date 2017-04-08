// BasePlusCommissionEmployee.java

// BasePlusCommissionEmployee inherits from CommissionEmployee and has
// access to CommissionEmployee's protected members.
package com.employee;

public class BasePlusCommissionEmployee extends CommissionEmployee {
   private double baseSalary; // base salary per week

   // six-argument constructor
   public BasePlusCommissionEmployee( String first, String last, String ssn, boolean status, double sales, double rate, double salary, int allowances, int hireMonth, int hireDay, int hireYear ) {
      super( first, last, ssn, status, sales, rate, allowances, hireMonth, hireDay, hireYear);
      setBaseSalary( salary ); // validate and store base salary
   } 

   // set base salary
   public void setBaseSalary( double salary ) {
      baseSalary = ( salary < 0.0 ) ? 0.0 : salary;
   }

   // return base salary
   public double getBaseSalary() {
      return baseSalary;
   }

   // calculate earnings
   public double earnings() {
      return getBaseSalary() + ( getCommissionRate() * getGrossSales() );
   }

   // return String representation of BasePlusCommissionEmployee
   public String toString() {
      return String.format(
         "%s: %s %s\n%s: %s\n%s: %.2f\n%s: %.2f\n%s: %.2f",
         "base-salaried commission employee", getFirstName(), getLastName(),
         "social security number", getSocialSecurityNumber(),
         "gross sales", getGrossSales(), "commission rate", getCommissionRate(),
         "base salary", getBaseSalary() );
   }
} // end class BasePlusCommissionEmployee
