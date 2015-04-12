// CommissionEmployee.java
// CommissionEmployee class represents a commission employee.

// CommissionEmployee inherits from Employee and has
// access to Employee's protected members.
package original;


 public class CommissionEmployee extends Employee {

   protected double grossSales; // gross weekly sales
   protected double commissionRate; // commission percentage

   // five-argument constructor
   /** 
 * @param first
 * @param last
 * @param ssn
 * @param sales
 * @param rate
 */
public CommissionEmployee( String first, String last, String ssn, double sales, double rate )
   {
	  super( first, last, ssn);
      setGrossSales( sales ); // validate and store gross sales
      setCommissionRate( rate ); // validate and store commission rate
   }

   // set gross sales amount
   public void setGrossSales( double sales )
   {
      grossSales = ( sales < 0.0 ) ? 0.0 : sales;
   }

   // return gross sales amount
   public double getGrossSales()
   {
      return grossSales;
   }

   // set commission rate
   public void setCommissionRate( double rate )
   {
      commissionRate = ( rate > 0.0 && rate < 1.0 ) ? rate : 0.0;
   }

   // return commission rate
   public double getCommissionRate()
   {
      return commissionRate;
   }

   // calculate earnings
   public double earnings()
   {
      return commissionRate * grossSales;
   }

   // return String representation of CommissionEmployee object
   public String toString()
   {
      return String.format( "%s: %s %s\n%s: %s\n%s: %.2f\n%s: %.2f",
         "commission employee", firstName, lastName,
         "social security number", socialSecurityNumber,
         "gross sales", grossSales,
         "commission rate", commissionRate );
   }
} // end class CommissionEmployee
