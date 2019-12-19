/* Course Name: Object Oriented Programming (Java)
   Student Name: Xin Guo
   Class Name: SortAppointmentByCalendar
   Date: 2019-11-27
 */
package cst8284.asgmt4.scheduler;

import java.util.Comparator;
import java.util.Calendar;

/**
 * This class implements Appointment type Comparator interface and overrides the compare() method
 * so that it returns an integer value representing the difference between the Calendar’s of the two
 * Appointment’s input as parameters to the compare() method.
 * 
 * @author Xin Guo
 * @version 1.0
 */
public class SortAppointmentByCalendar implements Comparator<Appointment> {
	/**
	 * Overrides the compare() method so that it returns an integer value representing the difference 
	 * between the Calendar’s of the two Appointment’s input as parameters to the compare() method.
	 */
	@Override
	public int compare(Appointment apt1, Appointment apt2) {
		return (int) (apt1.getCalendar().getTimeInMillis() - apt2.getCalendar().getTimeInMillis());
	}
}
