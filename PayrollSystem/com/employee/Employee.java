// Employee.java
// Employee superclass

package com.employee;

public class Employee {
   //protected members can be accessed directly by members of this class, members of
   //all subclasses, as well as members of all classes in the same package
   private String firstName;
   private String lastName;
   private String socialSecurityNumber;
   private boolean status;

   /** employee constructor
    * 
    * @param first
    * @param last
    * @param ssn
    * @param status type boolean it is either single(true) or married(false)
    */
   public Employee( String first, String last, String ssn, boolean status) {
      this.firstName = first;
      this.lastName = last;
      this.socialSecurityNumber = ssn;
      this.status = status;
   } // end three-argument Employee constructor

   // return first name
   public String getFirstName() {
      return this.firstName;
   } // end method getFirstName
   
   public String getStatus() {
	   return ((this.status)? "Single" : "Married");
   }
   
   // return last name
   public String getLastName() {
      return this.lastName;
   } // end method getLastName


   // return social security number
   public String getSocialSecurityNumber() {
      return this.socialSecurityNumber;
   } // end method getSocialSecurityNumber
   
  // set first name
   public void setFirstName( String first ) {
      this.firstName = first;
   } // end method setFirstName

   // set last name
   public void setLastName( String last ) {
      this.lastName = last;
   } // end method setLastName
   
   // set social security number
   public void setSocialSecurityNumber( String ssn ) {
      this.socialSecurityNumber = ssn; // should validate
   } // end method setSocialSecurityNumber
   
   // return String representation of Employee object
   public String toString() {
      return String.format( "%s %s\nsocial security number: %s",
         getFirstName(), getLastName(), getSocialSecurityNumber() );
   } // end method toString

   public double earnings() {
	   return 0.0;
	   //To be implemented by sub-classes
   }
} // end abstract class Employee


