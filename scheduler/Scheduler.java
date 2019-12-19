/* Course Name: Object Oriented Programming (Java)
   Student Name: Xin Guo
   Class Name: Scheduler
   Date: 2019-11-27
 */
package cst8284.asgmt4.scheduler;

import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import cst8284.asgmt4.employee.Employee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
/**
 * The Scheduler class contains the methods to print out the booking system menu and executes different functions
 * based on user's choice, including: Save Appointment, Delete Appointment, Change Appointment, Display Appointment,
 * Display Schedule, Save Appointments To File, and Load Appointments From File.
 * 
 * @author Xin Guo
 * @version 1.0
 */
public class Scheduler {
	static JFrame f = new JFrame("Notice");
	/**
	 * Instantiates a Scanner object to take in user input.
	 */
	private static Scanner scan = new Scanner(System.in);
	/**
	 * Represents an Arraylist of appointments with generic type Appointment.
	 */
	public static ArrayList<Appointment> appointments = new ArrayList<>();
	/**
	 * Represents employee in type Employee.
	 */
	private static Employee employee;
	/**
	 * Instantiates a DateFormat object to format the date as "ddMMyyyy".
	 */
	private static DateFormat dateFormat= new SimpleDateFormat("ddMMyyyy");

	/**
	 * One-arg Scheduler constructor which takes an employee and calls setEmployee() method 
	 * to instantiate a scheduler for the employee.
	 * 
	 * @param emp for which employee you want to instantiate a scheduler
	 */
	public Scheduler(Employee emp) {
		//System.out.println("Scheduling appointments for " + emp);
		setEmployee(emp);
	}
	/**
	 * Sets the employee of the scheduler.
	 * 
	 * @param emp employee of the scheduler
	 */
	private void setEmployee(Employee emp) {this.employee = emp;}
	/**
	 * Returns the employee of the scheduler.
	 * 
	 * @return employee of the scheduler in type Employee
	 */
	public static Employee getEmployee() {return employee;}
	/**
	 * This method contains do while loop to call displayMenu() to print out the booking system menu and to call executeMenuItem() method
	 * to execute different functions based on user's choice. The method will stop looping when the user choose to exit.
	 */
	public void launch() {
//		int choice = 0;
//		do {
//			choice = displayMenu();
//			SchedulerViewer.launch();
//		} while (choice != EXIT);
		SchedulerViewer.launch();
	}
	
	static String fullName;
	static String phoneNumber;
	static String aptDate;
	static String aptTime;
	static String actDescription;
	static String actType;

	public static class btSave implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			fullName = AppointmentDialog.getFullName().getText();
			phoneNumber = AppointmentDialog.getPhoneNumber().getText();
			aptDate = AppointmentDialog.getAptDate().getText();
			aptTime = AppointmentDialog.getAptTime().getText();
			actDescription = AppointmentDialog.getActDescription().getText();
			
			if (AppointmentDialog.jRadioButton1.isSelected()) { 
				actType = "Assessment"; 
            } else if(AppointmentDialog.jRadioButton2.isSelected()) { 
            	actType = "Filling"; 
            } else if (AppointmentDialog.jRadioButton3.isSelected()) { 
            	actType = "Crown"; 
            } else if (AppointmentDialog.jRadioButton4.isSelected()) { 
            	actType = "Cosmetic Repair"; 
            } else
            	actType = "";
			
			Scheduler.saveAppointment(Scheduler.makeAppointmentFromUserInput());
		}
	}
	
	public static class btFind implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			aptDate = AppointmentDialog.getAptDate().getText();
			aptTime = AppointmentDialog.getAptTime().getText();
			
			//Scheduler.displayAppointment(Scheduler.makeCalendarFromUserInput(false));

			if(displayAppointment(Scheduler.makeCalendarFromUserInput(false)))
				JOptionPane.showMessageDialog(f, apt.toString());
			else
				JOptionPane.showMessageDialog(f, "No appointment scheduled between "+ hr + ":00 and " + (hr + 1) + ":00\n");	
		}
	}
	
	static Calendar cal = null;
	public static class btChange implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			aptDate = AppointmentDialog.getAptDate().getText();
			aptTime = AppointmentDialog.getAptTime().getText();
			
			//Scheduler.changeAppointment(Scheduler.makeCalendarFromUserInput(false));

			cal = Scheduler.makeCalendarFromUserInput(false);
			if(displayAppointment(cal))
				JOptionPane.showMessageDialog(f, apt.toString());
			else
				JOptionPane.showMessageDialog(f, "No appointment scheduled between "+ hr + ":00 and " + (hr + 1) + ":00\n");
			
				if (displayAppointment(cal)) {  							// display existing appointment on this date/time
		  		    //String okToChange = getResponseTo("\nEnter 'Yes' to change the date and time of this appointment ");
		  		    int n = JOptionPane.showConfirmDialog(f, "Would you like to change this appointment?", "Change Appointment", JOptionPane.YES_NO_OPTION);
			  		  if (n == JOptionPane.YES_OPTION) {
			  			  JOptionPane.showMessageDialog(f, "Enter new date and time.");
			  			  AppointmentDialog.changeAppointmentDialog();
			  			   // get new date/time  
			          } else {
			          	JOptionPane.showMessageDialog(f, "Request cancelled.");
			          }
				}	
		}
	}
	
	public static class btUpdate implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			aptDate = AppointmentDialog.getAptDate().getText();
			aptTime = AppointmentDialog.getAptTime().getText();
			Calendar newCal = makeCalendarFromUserInput(false);
			
			if (findAppointment(newCal)==null) {				// appointment time not already taken
				  findAppointment(cal).setCalendar(newCal);		// set new date/time in appointment
				  //System.out.println("Appointment re-booked\n");
				  JOptionPane.showMessageDialog(f, "Appointment re-booked.");
					// new appointment time set
			} else //System.out.println("That time is already booked for an appointment\n");
			       JOptionPane.showMessageDialog(f, "That time is already booked for an appointment.");		
		}
	}
	
	public static class btDelete implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			aptDate = AppointmentDialog.getAptDate().getText();
			aptTime = AppointmentDialog.getAptTime().getText();
			
			//Scheduler.deleteAppointment(Scheduler.makeCalendarFromUserInput(false));
			if(displayAppointment(Scheduler.makeCalendarFromUserInput(false)))
				JOptionPane.showMessageDialog(f, apt.toString());
			else
				JOptionPane.showMessageDialog(f, "No appointment scheduled between "+ hr + ":00 and " + (hr + 1) + ":00\n");
			
			if (displayAppointment(Scheduler.makeCalendarFromUserInput(false))) {  							// display existing appointment on this date/time
				//String okToChange = getResponseTo("\nEnter 'Yes' to delete this appointment");
				int n = JOptionPane.showConfirmDialog(f, "Would you like to delete this appointment?", "Delete Appointment", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					getAppointments().remove(findAppointment(Scheduler.makeCalendarFromUserInput(false)));
					JOptionPane.showMessageDialog(f, "Appointment deleted.");
	            } else {
	            	JOptionPane.showMessageDialog(f, "Request cancelled.");
	            }
//				if (okToChange.trim().equals("Yes")) {  						// okay to proceed with change/deletion?
//					getAppointments().remove(findAppointment(cal)); 
//					//System.out.println("Appointment deleted"); 
//					JOptionPane.showMessageDialog(f, "Appointment deleted.");
//					return true;
//				} else //System.out.println("Request cancelled");
//				       JOptionPane.showMessageDialog(f, "Request cancelled.");
			}
		}
	}
	
	public static class btDisplay implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			aptDate = AppointmentDialog.getAptDate().getText();
			
			Calendar cal = null;
			String daySchedule = "";
		    cal = Scheduler.makeCalendarFromUserInput(true);
		    for (int hrCtr = 8; hrCtr < 17; hrCtr++) {
				cal.set(Calendar.HOUR_OF_DAY, hrCtr);
				if(displayAppointment(cal))
					daySchedule = daySchedule + apt.toString() + "\n";
				else
					daySchedule = daySchedule + "No appointment scheduled between "+ hr + ":00 and " + (hr + 1) + ":00\n";	
			}
		    SchedulerViewer.reloadJTextArea(daySchedule);
		}
	}
	
//	btExit.addActionListener(new ActionListener() {
//	//public static class btExit implements ActionListener{
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			//f.dispose();
//			System.out.print("exit");
//			f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
//		}
//	});
	/**
	 * This method asks the user to input date, client name, phone number and activity and then instantiates
	 * a new appointment. This part is wrapped in a try-catch block which will catch any Exceptions during execution.
	 * 
	 * @return the new appointment being made
	 */
    public static Appointment makeAppointmentFromUserInput() {
//    	boolean appointmentMade = false;
  	    Appointment apt = null;
//    	do {
    		try {
    			//fullName = getResponseTo("Enter Client Name (as FirstName LastName): ");
    			//phoneNumber = getResponseTo("Phone Number (e.g. 613-555-1212): ");
    			TelephoneNumber phone = new TelephoneNumber(phoneNumber);
    			Calendar cal = makeCalendarFromUserInput(false);
    			//activity = getResponseTo("Enter Activity: ");
    			//Activity act = new Activity(actDescription, getEmployee().getActivityType());
    			Activity act = new Activity(actDescription, actType);
    			apt = new Appointment(cal, fullName, phone, act);
    			//return();
        		//appointmentMade = true;
        	} catch (BadAppointmentDataException ex) {
        		//System.out.println(ex.getMessage() + "; please re-enter\n");
        		JOptionPane.showMessageDialog(f, ex.getMessage() + "; please re-enter\n");
        		//AppointmentDialog.saveAppointmentDialog();
        		//return null;
        	}
//    	} while(!appointmentMade);
		return apt;
    }
    /**
     * This method asks the user to input calendar information. If suppressHour is true, it will asks for
     * an appointment date like "DDMMYYYY". The first two digits are set as day, the third and fourth digits 
     * are set as month and the last four digits are set as year. If suppressHour is false, it will also asks
     * for appointment time specifies to an hour. In the end, it takes year, month, day and hour to instantiates
     * a new Calendar. 
     * 
     * @param suppressHour to decide whether the user need to input appointment time
     * @return new Calendar object
     * @throws BadAppointmentDataException If the input appointment date contains characters or it is not a legal date, it throws a bad appointment
     * data exception with the description "Bad calendar format" and output message "Bad calendar date entered; format is DDMMYYYY"
     */
    public static Calendar makeCalendarFromUserInput(boolean suppressHour) {
    	
    	dateFormat.setLenient(false);
    	
    	Calendar cal = Calendar.getInstance();
    	int hour = 0;
    	
    	cal.clear();
    	//aptDate = getResponseTo("Appointment Date (entered as DDMMYYYY): ");
		
		// checking for bad calendar format
		// stack overflow. Check for special characters in a string using regex in java [duplicate]. [Webpage]. Retrieved from https://stackoverflow.com/questions/48031098/check-for-special-characters-in-a-string-using-regex-in-java
		Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");
		if (regex.matcher(aptDate).find())
			throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYYY", "Bad calendar format");
		// stack overflow. How to sanity check a date in Java. [Webpage]. Retrieved from https://stackoverflow.com/questions/226910/how-to-sanity-check-a-date-in-java
		try {
			cal.setTime(dateFormat.parse(aptDate));
		} catch(Exception ex) {
			throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYYY", "Bad calendar format");
	    }
		
		int day = Integer.parseInt(aptDate.substring(0,2));
		int month = Integer.parseInt(aptDate.substring(2,4)) - 1;  // offset by one to account for zero-based month in Calendar
		int year = Integer.parseInt(aptDate.substring(4,8));
		
		if (!suppressHour) {				
		   //aptTime = getResponseTo("Appointment Time: ");
		   hour = processTimeString(aptTime);
		}
		
		cal.set(year, month, day, hour, 0);
				
		return (cal);
    }
    /**
     * This method takes the time String (read in any formats like 1 p.m., 1:00 p.m., 1, 13:00, 13),
     * and returns an integer corresponding to the correct 24-hour format value.
     * <ul>
     * <li> If the time contains ":", splits by ":" and takes the first part as hour.
     * <li> If the time contains " ", splits by " " and takes the first part as hour.
     * <li> If the hour is less than 8, adds it by 12 and makes it 24-hour format value.
     * </ul>
     * 
     * @param t user's input time in String
     * @return hour in integer corresponding to the correct 24-hour format value
     */
	private static int processTimeString(String t) {
		int hour = 0;
		t = t.trim();
		if (t.contains(":")) hour = Integer.parseInt(t.split(":")[0]);
		else if (t.contains (" ")) hour = Integer.parseInt(t.split(" ")[0]);
		else hour = Integer.parseInt(t);
		return ((hour < 8) ? hour+12 : hour);
	}
	/**
	 * This method uses an instance of your SortAppointmentByCalendar object to sort the ArrayList appointments
	 * using the Collections sort() method. Then calls the overloaded binarySearch() method to search for specific appointment.
	 * 
	 * @param cal calendar information used to find an appointment
	 * @return If the appointment exists, return the appointment. Else, return null.
	 */
    public static Appointment findAppointment(Calendar cal) {
    	// HowToDoInJava. Java sort arraylist of objects – Comparable and Comparator example. [Webpage]. Retrieved from https://howtodoinjava.com/sort/sort-arraylist-objects-comparable-comparator/
    	// ConcretePage.com. [2013] Example of Collections.binarySearch in Java. [Webpage]. Retrieved from https://www.concretepage.com/java/example-collections-binarysearch-java    	
    	Collections.sort(appointments, new SortAppointmentByCalendar());
    	int index = Collections.binarySearch(appointments, new Appointment(cal, "x x", null, null), new SortAppointmentByCalendar());
    	return ((index<0)? null : appointments.get(index));
    }
    /**
     * This method takes an appointment and calls findAppointment() method to check for time slot availability.
     * <ul>
     * <li> If time slot available, adds the appointment to the appointments ArrayList and returns a boolean value "true"
     * to indicate the appointment is added successfully.
     * <li> If time slot not available, informs user another appointment exists at that time and can not add appointment
     * and returns a boolean value "false" to indicate the appointment is not added successfully.
     * </ul>
     * <p>
     * Each time you save an appointment, it calls Collections.sort() method to sort the ArrayList of appointments
     * by Calendar.
     * 
     * @param apt new appointment wants to be saved
     * @return a boolean value represents whether the new appointment is added or not
     */
	public static boolean saveAppointment(Appointment apt) {	
		if(apt!=null) {
		Calendar cal = apt.getCalendar();  // Check that the appointment does not already exist
		if (findAppointment(cal)==null) {  // Time slot available, okay to add appointment
			getAppointments().add(apt);
			//System.out.println("Appointment saved.");
			JOptionPane.showMessageDialog(f, "Appointment saved.");
			Collections.sort(appointments, new SortAppointmentByCalendar());
			return true;
		}  // else time slot taken, need to make another choice
		//System.out.println("Cannot save; an appointment at that time already exists");
		JOptionPane.showMessageDialog(f, "Cannot save; an appointment at that time already exists.");
		return false;
		} else
			return false;
	}
	/**
	 * This method takes an calendar and displays existing appointment on this date/time. 
	 * <ul>
     * <li> If exits, calls getResponseTo() method to ask user whether to delete this appointment.
     * <li> If user enters "Yes", deletes the appointment from ArrayList and returns a boolean value "true"
     * to indicate the appointment is deleted successfully.
     * <li> If user enters anything other than "Yes", this appointment remains unchanged.
     * <li> If no appointment found on this date/time, return a boolean value "false" to represent appointment did not exist.
	 * </ul>
	 * 
	 * @param cal date/time of appointment wants to be deleted
	 * @return a boolean value represents whether the appointment is deleted or not
	 */
//	public static boolean deleteAppointment(Calendar cal) {
//		if (displayAppointment(cal)) {  							// display existing appointment on this date/time
//			//String okToChange = getResponseTo("\nEnter 'Yes' to delete this appointment");
//			int n = JOptionPane.showConfirmDialog(f, "Would you like to delete this appointment?", "Delete Appointment", JOptionPane.YES_NO_OPTION);
//			if (n == JOptionPane.YES_OPTION) {
//				getAppointments().remove(findAppointment(cal));
//				JOptionPane.showMessageDialog(f, "Appointment deleted.");
//				return true;
//            } else {
//            	JOptionPane.showMessageDialog(f, "Request cancelled.");
//            }
////			if (okToChange.trim().equals("Yes")) {  						// okay to proceed with change/deletion?
////				getAppointments().remove(findAppointment(cal)); 
////				//System.out.println("Appointment deleted"); 
////				JOptionPane.showMessageDialog(f, "Appointment deleted.");
////				return true;
////			} else //System.out.println("Request cancelled");
////			       JOptionPane.showMessageDialog(f, "Request cancelled.");
//		} return false;  // Appointment didn't exist at the date/time specified
//	}
	/**
	 * This method takes an calendar and displays existing appointment on this date/time. 
	 * <ul>
     * <li> If exits, calls getResponseTo() method to ask user whether to change the date and time of this appointment.
     * <li> If user enters "Yes", calls makeCalendarFromUserInput() method to gets new appointment's date and time.
     * Then calls findAppointment() method to check time slot availability. If available, sets new date/time in appointment.
     * Returns a boolean value "true" to indicate the appointment is changed successfully. If the new date/time is
     * not available, the appointment remains unchanged.
     * <li> If user enters anything other than "Yes", this appointment remains unchanged.
     * <li> If no appointment found on this date/time, return a boolean value "false" to represent appointment did not exist.
	 * </ul>
	 * 
	 * @param cal date/time of appointment wants to be changed
	 * @return a boolean value represents whether the appointment is changed or not
	 */
//	public static boolean changeAppointment(Calendar cal) {
//		if (displayAppointment(cal)) {  							// display existing appointment on this date/time
//  		    //String okToChange = getResponseTo("\nEnter 'Yes' to change the date and time of this appointment ");
//  		    int n = JOptionPane.showConfirmDialog(f, "Would you like to change this appointment?", "Change Appointment", JOptionPane.YES_NO_OPTION);
//	  		  if (n == JOptionPane.YES_OPTION) {
//	  			  //JOptionPane.showMessageDialog(f, "Enter new date and time.");
//	  			  aptDate = AppointmentDialog.getAptDate().getText();
//				  aptTime = AppointmentDialog.getAptTime().getText();
//	  			  Calendar newCal = makeCalendarFromUserInput(false); // get new date/time
//	  			  if (findAppointment(newCal)==null) {				// appointment time not already taken
//	  				  findAppointment(cal).setCalendar(newCal);		// set new date/time in appointment
//	  				  //System.out.println("Appointment re-booked\n");
//	  				  JOptionPane.showMessageDialog(f, "Appointment re-booked.");
//	  				  return true;									// new appointment time set
//				} else //System.out.println("That time is already booked for an appointment\n");
//				       JOptionPane.showMessageDialog(f, "That time is already booked for an appointment.");
//	          } else {
//	          	JOptionPane.showMessageDialog(f, "Request cancelled.");
//	          }
////  		    if (okToChange.trim().equals("Yes")) { 
////				//System.out.println("Enter new date and time");
////				JOptionPane.showMessageDialog(f, "Enter new date and time.");
////				Calendar newCal = makeCalendarFromUserInput(false); // get new date/time
////				if (findAppointment(newCal)==null) {				// appointment time not already taken
////					findAppointment(cal).setCalendar(newCal);		// set new date/time in appointment
////					//System.out.println("Appointment re-booked\n");
////					JOptionPane.showMessageDialog(f, "Appointment re-booked.");
////					return true;									// new appointment time set
////				} else //System.out.println("That time is already booked for an appointment\n");
////				       JOptionPane.showMessageDialog(f, "That time is already booked for an appointment.");
////			} else //System.out.println("Request cancelled"); 
////		           JOptionPane.showMessageDialog(f, "Request cancelled.");
//		} return false;  // Appointment does not exist, was unavailable, or cancelled
//	}
	/**
	 * This method takes a calendar and calls findAppointment() method to check whether an appointment
	 * exists at specific date/time. If yes, prints out the appointment information as a String. If no,
	 * prints out no appointment scheduled at this date/time.
	 * 
	 * @param cal date/time of appointment wants to be displayed
	 * @return a boolean value represents whether an appointment is scheduled at that date/time
	 */
	static Appointment apt;
	static int hr;
	public static boolean displayAppointment(Calendar cal) {
		apt = findAppointment(cal);
		hr = cal.get(Calendar.HOUR_OF_DAY);
//		System.out.print((apt!=null) ?
//		   "\n\n"+ apt.toString()+"\n": // Output the appointment as a string to the console, otherwise...
//  	       "No appointment scheduled between "+ hr + ":00 and " + (hr + 1) + ":00\n"
//		);
//		SchedulerViewer.reloadJTextArea((apt!=null) ?
//				   "\n\n"+ apt.toString()+"\n": // Output the appointment as a string to the console, otherwise...
//			  	       "No appointment scheduled between "+ hr + ":00 and " + (hr + 1) + ":00\n"
//					);
//		JOptionPane.showMessageDialog(f, ((apt!=null) ?
//				   "\n\n"+ apt.toString()+"\n": // Output the appointment as a string to the console, otherwise...
//			  	       "No appointment scheduled between "+ hr + ":00 and " + (hr + 1) + ":00\n"
//					),"Appointment Information", JOptionPane.INFORMATION_MESSAGE);
		return (apt!=null);
	}
	/**
	 * This method takes a calendar date and loops through each hour (from 8:00 to 17:00) of that day
	 * to call displayAppointment() to display appointments.
	 * 
	 * @param cal calendar date used to display appointments of that day
	 */
//	public static void displayDaySchedule(Calendar cal) {
//		for (int hrCtr = 8; hrCtr < 17; hrCtr++) {
//			cal.set(Calendar.HOUR_OF_DAY, hrCtr);
//			displayAppointment(cal);		
//		}
//	}
	/**
	 * This method loads each Appointment object in the ArrayList into a file.
	 * This part is wrapped in a try-catch block which will catch any Exceptions during execution.
	 * 
	 * @param apts the appointments need to be saved to file
	 * @param saveFile the file you want to save the appointments to
	 * @return a boolean value whether the appointments are saved to file successfully
	 */
	public static boolean saveAppointmentsToFile(ArrayList<Appointment> apts, String saveFile) {
		try (FileOutputStream fos = new FileOutputStream(saveFile);
			 ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			for (Appointment apt: apts) 
				oos.writeObject(apt);
			//System.out.println("Appointment data saved to " + saveFile);
			JOptionPane.showMessageDialog(f, "Appointment data saved to " + saveFile);
			return true;
		} catch (IOException e) {
			//System.out.println("Failed to load appointments from " + saveFile);
			JOptionPane.showMessageDialog(f, "Failed to load appointments from " + saveFile);
			return false;
		}
	}
	/**
	 * This method loads the file contents from file into the appointments ArrayList.
	 * This part is wrapped in a try-catch block which will catch any Exceptions during execution.
	 * 
	 * @param sourceFile the file you want to load the appointments from
	 * @param apts the appointments need to be loaded from file
	 * @return a boolean value whether the appointments are loaded from file successfully
	 */
	public static boolean loadAppointmentsFromFile(String sourceFile, ArrayList<Appointment> apts) {
		apts.clear();  // remove all existing appointments from the ArrayList before loading from file
		try (FileInputStream fis = new FileInputStream(sourceFile);
		     ObjectInputStream ois = new ObjectInputStream(fis);){
		        while(true) apts.add((Appointment)ois.readObject());
		} 
		catch (EOFException ex) {
			//System.out.println("Appointments successfully loaded from " + sourceFile);
			JOptionPane.showMessageDialog(f, "Appointments successfully loaded from " + sourceFile);
			return true;}
		catch (IOException | ClassNotFoundException e) {return false;} 	
	}
	/**
	 * Returns an ArrayList of appointments in type Appointment.
	 * 
	 * @return an ArrayList of appointments in type Appointment
	 */
	public static ArrayList<Appointment> getAppointments() {return appointments;}
	     
}
