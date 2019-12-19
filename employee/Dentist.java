/* Course Name: Object Oriented Programming (Java)
   Student Name: Xin Guo
   Class Name: Dentist
   Date: 2019-11-27
 */
package cst8284.asgmt4.employee;
/**
 * This class represents a dentist inherits from the abstract class Employee.
 * 
 * @author Xin Guo
 * @version 1.0
 */
public class Dentist extends Employee {
	/**
	 * Represents different kinds of work descriptions stored as String in an array.
	 */
	private static String[] workDescription = {"Assessment", "Filling", "Crown", "Cosmetic Repair"};
	/**
	 * No-arg Dentist constructor calls the superclass Employee's one-String constructor.
	 * 
	 * @param fullName full name of the dentist in String
	 */
	public Dentist(String fullName) {
		super (fullName);
	}
	/**
	 * A method to return the employee's activity type.
	 * 
	 * @return employee's activity type in String
	 */
	public String getActivityType() {
		System.out.println("Enter a selection from the following menu:");
		int i = 1;
		for (String description: workDescription)
			System.out.println(i++ + "." + description);
		int ch = scan.nextInt();
		scan.nextLine(); // 'eat' the next line in the buffer
		System.out.println();  // add a space
		return workDescription[ch-1];
	}
}
