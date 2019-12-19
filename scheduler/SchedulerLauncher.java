/* Course Name: Object Oriented Programming (Java)
   Student Name: Xin Guo
   Class Name: SchedulerLauncher
   Date: 2019-11-27
 */
package cst8284.asgmt4.scheduler;

import cst8284.asgmt4.employee.Dentist;
/**
 * This class is to launch the booking system.
 * 
 * @author Xin Guo
 * @version 1.0
 */
public class SchedulerLauncher {
	/**
	 * The main method instantiates a new employee and then instantiate a new scheduler
	 * to call the launch() method in class Scheduler to launch the booking system.
	 * 
	 * @param args Java main method accepts a single argument of type String array. This is also called as java command line arguments.
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Scheduler(new Dentist("Dr. Andrews")).launch();
			}
		});
	}
}
