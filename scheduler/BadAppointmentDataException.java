/* Course Name: Object Oriented Programming (Java)
   Student Name: Xin Guo
   Class Name: BadAppointmentDataException
   Date: 2019-11-27
 */
package cst8284.asgmt4.scheduler;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class inherits from RuntimeException and instantiates bad appointment data exception 
 * with description and output message.
 * 
 * @author Xin Guo
 * @version 1.0
 */
public class BadAppointmentDataException extends java.lang.RuntimeException{
//	JFrame frame = new JFrame();
//	frame.setLayout(new BorderLayout());
//	frame.pack();
//    frame.setVisible(true);
	/**
	 * It is used during the deserialization of an object, to ensure that a loaded class is compatible with the serialized object.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Represents bad appointment data exception description in String.
	 */
	private String Description;
	/**
	 * Sets the description for bad appointment data exception.
	 * 
	 * @param Description describe bad appointment data exception in String
	 */
	public void setDescription(String Description) {
		this.Description = Description;
	}
	/**
	 * Returns the description for bad appointment data exception.
	 * 
	 * @return description of bad appointment data exception in String
	 */
	public String getDescription() {
		return Description;
	}
	/**
	 * No-arg BadAppointmentDataException constructor which is chained to the two-String constructor.
	 */
	public BadAppointmentDataException() {
		this("Please try again", "Bad data entered");
	}
	/**
	 * Two-String BadAppointmentDataException constructor which instantiates BadAppointmentDataException object 
	 * with two Strings to describe bad appointment data exception and output message.
	 * 
	 * @param s1 calls super() method to pass the String up to the superclass so that it will be returned when you call ex.getMessage()
	 * @param s2 calls the setDescription() method to pass the String to describe bad appointment data exception
	 */
	public BadAppointmentDataException(String s1, String s2) {
		super(s1);
		setDescription(s2);
		
		//JOptionPane.showMessageDialog(null, s1, s2, JOptionPane.ERROR_MESSAGE);
	}	
}
