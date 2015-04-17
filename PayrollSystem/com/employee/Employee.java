// Employee.java
// Employee superclass

package com.employee;

public abstract class Employee {
   //protected members can be accessed directly by members of this class, members of
   //all subclasses, as well as members of all classes in the same package
   private String firstName;
   private String lastName;
   private String socialSecurityNumber;
   private boolean status;
   private int numberOfAllowances;
   private java.util.Date createDate;
   private int hireMonth;
   private int hireDay;
   private int hireYear;

   public Employee( String first, String last, String ssn, boolean status, int numberOfAllowances, int hireMonth, int hireDay, int hireYear) {
      this.firstName = first;
      this.lastName = last;
      this.socialSecurityNumber = ssn;
      this.status = status;
      this.numberOfAllowances = numberOfAllowances;
      this.createDate = new java.util.Date();
      this.hireMonth = hireMonth;
      this.hireDay = hireDay;
      this.hireYear = hireYear;
   } // end three-argument Employee constructor

   // getters
   
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
   
   public int getNumberOfAllowances() {
	   return this.numberOfAllowances;
   }
   
   public int getHireMonth() {
	   return this.hireMonth;
   }
   
   public int getHireDay() {
	   return this.hireDay;
   }
   
   public int getHireYear() {
	   return this.hireYear;
   }
   
   public java.util.Date getCreateDate() {
	   return this.createDate;
   }
   
   // setters
   
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
      // ###-##-####
   } // end method setSocialSecurityNumber
   
   public void setHireMonth(int hireMonth) {
	   this.hireMonth = hireMonth;
   }
   
   public void setHireDay(int hireDay) {
	   this.hireDay = hireDay;
   }
   
   public void setHireYear(int hireYear) {
	   this.hireYear = hireYear;
   }
   
   // return String representation of Employee object
   public String toString() {
      return String.format( "%s %s\nsocial security number: %s",
         getFirstName(), getLastName(), getSocialSecurityNumber() );
   } // end method toString

   public abstract double earnings();
} // end abstract class Employee


