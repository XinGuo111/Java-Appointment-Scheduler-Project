/* Course Name: Object Oriented Programming (Java)
   Student Name: Xin Guo
   Class Name: Appointment
   Date: 2019-11-27
 */
package cst8284.asgmt4.scheduler;

import java.io.Serializable;
import java.util.Calendar;
import java.util.regex.Pattern;
/**
 * This class implements Serializable interface and instantiates appointment object with 
 * calendar, first name, last name and telephone number of the patient, and the details of the appointment.
 * It throws bad appointment data exception if the input name of the patient:
 * <ul>
 * <li> contains illegal characters other than alphabetic characters, the dash (-), the period (.), and the apostrophe (')
 * <li> first name or last name exceeds 30 characters
 * </ul>
 * 
 * @author Xin Guo
 * @version 1.0
 */
public class Appointment implements Serializable {
	/**
	 * Represents the appointment date.
	 */
	private Calendar aptDate;
	/**
	 * Represents the first name and last name of the patient.
	 */
	private String firstName, lastName;
	/**
	 * Represents the telephone number of the patient.
	 */
	private TelephoneNumber phone;
	/**
	 * Represents the details of the appointment.
	 */
	private Activity activity;
	/**
	 * Four-args Appointment constructor which is chained to five-args constructor. It takes the full name
	 * of the patient in String and splits it by space ' '. The part before space is defined as first name
	 * and the part after space is defined as last name.
	 * 
	 * @param cal calendar of the appointment in type Calendar
	 * @param fullName first name and last name of the patient in String
	 * @param phone telephone number of the patient in type TelephoneNumber
	 * @param act details of the appointment in type Activity
	 */
	public Appointment(Calendar cal, String fullName, TelephoneNumber phone, Activity act) {
		this(cal, fullName.trim().split(" ")[0], fullName.trim().split(" ")[1], phone, act);
	}
	/**
	 * Five-args Appointment constructor which calls setFirstName() method, setLastName() method, 
	 * setCalendar() method, setPhone() method and setActivity() method to instantiate appointment object
	 * with calendar, first name, last name and telephone number of the patient, and the details of the appointment. 
	 * 
	 * @param cal calendar of the appointment in type Calendar
	 * @param firstName first name of the patient in String
	 * @param lastName last name of the patient in String
	 * @param phone telephone number of the patient in type TelephoneNumber
	 * @param act details of the appointment in type Activity
	 */
	public Appointment(Calendar cal, String firstName, String lastName, TelephoneNumber phone, Activity act) {
		setFirstName(firstName.trim()); 
		setLastName(lastName.trim());
		setCalendar(cal); 
		setPhone(phone);
		setActivity(act);
	}
	/**
	 * This method checks if the input name of the patient is in correct format.
	 * <p>
	 * If the name is correct, it returns a boolean value "true".
	 * 
	 * @param name name that needs to be checked for correct format
	 * @return a boolean value whether the input name is in correct format
	 * @throws BadAppointmentDataException If the name contains illegal characters other than alphabetic characters, the dash (-), the period (.),
	 * and the apostrophe ('), it throws a bad appointment data exception with the description 
	 * "Illegal characters in name" and output message "Name cannot include characters other than alphabetic characters,
	 * the dash (-), the period (.), and the apostrophe (')".
	 * @throws BadAppointmentDataException If the first name or last name exceeds 30 characters, it throws a bad appointment data exception with the description 
	 * "Name exceeds maximum length" and output message "Name cannot exceed 30 characters".
	 */
	private static boolean isNameCorrect(String name) {
		boolean correctName = false;
		// checking for illegal characters in name
		// stack overflow. Check for special characters in a string using regex in java [duplicate]. [Webpage]. Retrieved from https://stackoverflow.com/questions/48031098/check-for-special-characters-in-a-string-using-regex-in-java
		Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/<>^*()%!]");
		if (regex.matcher(name).find() || name.matches(".*\\d.*"))
			throw new BadAppointmentDataException("Name cannot include characters other than alphabetic characters, the dash (-), the period (.), and the apostrophe (')", "Illegal characters in name");
		// checking for name exceeds maximum length
		if(name.trim().length()>30 || name.trim().length()>30)
			throw new BadAppointmentDataException("Name cannot exceed 30 characters", "Name exceeds maximum length");
		else
			correctName = true;
		return correctName;
	}
	/**
	 * Returns the appointment date.
	 * 
	 * @return appointment date in type Calendar
	 */
	public Calendar getCalendar() {return aptDate;}
	/**
	 * Sets the appointment date.
	 * 
	 * @param aptDate appointment date in type Calendar
	 */
	public void setCalendar(Calendar aptDate) {this.aptDate = aptDate;}
	/**
	 * Returns the first name of the patient.
	 * 
	 * @return first name of the patient in String
	 */
	public String getFirstName() {return firstName; }
	/**
	 * Calls the isNameCorrect() method to check if the first name is in correct format.
	 * If yes, sets the first name of the patient.
	 * 
	 * @param firstName first name of the patient in String
	 */
	public void setFirstName(String firstName) {if(isNameCorrect(firstName)) this.firstName = firstName;}
	/**
	 * Returns the last name of the patient.
	 * 
	 * @return last name of the patient in String
	 */
	public String getLastName() {return lastName;}
	/**
	 * Calls the isNameCorrect() method to check if the last name is in correct format.
	 * If yes, sets the last name of the patient.
	 * 
	 * @param lastName last name of the patient in String
	 */
	public void setLastName(String lastName) {if(isNameCorrect(lastName)) this.lastName = lastName;}
	/**
	 * Returns the telephone number of the patient.
	 * 
	 * @return telephone number of the patient in type TelephoneNumber
	 */
	public TelephoneNumber getPhone() {return phone;}
	/**
	 * Set the telephone number of the patient.
	 * 
	 * @param phone telephone number of the patient in type TelephoneNumber
	 */
	public void setPhone(TelephoneNumber phone) {this.phone = phone;}
	/**
	 * Returns the details of the appointment.
	 * 
	 * @return details of the appointment in type Activity
	 */
	public Activity getActivity() {return activity;}
	/**
	 * Sets the details of the appointment.
	 * 
	 * @param activity details of the appointment in type Activity
	 */
	public void setActivity(Activity activity) {this.activity = activity;}
	/**
	 * Overrides toString() method in class java.lang.Object.
	 */
	public String toString() {
		return getCalendar().getTime().toString() + "\n" +
			   getFirstName() + " " + getLastName() + "\n" + 
			   getPhone().toString() + "\n" +
			   getActivity().toString();
	}

}
