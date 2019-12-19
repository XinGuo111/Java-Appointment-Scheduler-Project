/* Course Name: Object Oriented Programming (Java)
   Student Name: Xin Guo
   Class Name: Employee
   Date: 2019-11-27
 */
package cst8284.asgmt4.employee;

import java.util.Scanner;
/**
 * Employee is the abstract base class for all employees related to the booking system, 
 * which allows the instantiation of an employee object to make his/her bookings. 
 * 
 * @author Xin Guo
 * @version 1.0
 */
public abstract class Employee {
	/**
	 * Represents the employee's full name.
	 */
	private String fullName;
	/**
	 * No-arg Employee constructor which is chained to the one-String constructor.
	 */
	protected Employee() {this("unknown");}
	/**
	 * One-String Employee constructor which instantiates employee object with full name.
	 * 
	 * @param fullName full name of the employee in String
	 */
	protected Employee(String fullName) {setName(fullName);}
	/**
	 * Instantiates a Scanner object to take in user input.
	 */
	protected static Scanner scan = new Scanner(System.in);
	/**
	 * Sets the employee's full name.
	 * 
	 * @param fullName full name of the employee in String
	 */
	public void setName(String fullName) {this.fullName = fullName;}
	/**
	 * Returns the employee's full name.
	 * 
	 * @return employee's full name in String
	 */
	public String getName() {return fullName;}
	/**
	 * An abstract base method to return the employee's activity type.
	 * 
	 * @return employee's activity type in String
	 */
	public abstract String getActivityType();
	/**
	 * Overrides toString() method in class java.lang.Object.
	 */
	@Override
	public String toString() {return getName();}
}