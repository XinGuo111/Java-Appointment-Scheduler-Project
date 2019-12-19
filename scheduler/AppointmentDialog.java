/* Course Name: Object Oriented Programming (Java)
   Student Name: Xin Guo
   Class Name: AppointmentDialog
   Date: 2019-11-27
 */
package cst8284.asgmt4.scheduler;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
//import javax.swing.text.JTextComponent;

/* Adapted, with considerable modification, from 
 * http://www.java2s.com/Code/Java/Swing-JFC/TextAcceleratorExample.htm,
 * which is sloppy code and should not be emulated.
 */

public class AppointmentDialog extends JFrame {
	
	private static final GridBagConstraints textConstants = new GridBagConstraints(
    	0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,  // gridx, gridy, gridwidth, gridheight, weightx, weighty
    	GridBagConstraints.EAST, 0, new Insets(2, 2, 2, 2), 1, 1); // anchor, fill, insets, ipadx, ipady
	private static final GridBagConstraints labelConstants = new GridBagConstraints(
    	1, GridBagConstraints.RELATIVE, 1, 1, 1.0, 0, 
    	GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);

	private static Container cp;
	private static final int labelWidth = 35;
	private static final Font defaultFont = new Font("SansSerif", Font.PLAIN, 16);	
	
	private static JTextField fullName;
	private static JTextField phoneNumber;
	private static JTextField aptDate;
	private static JTextField aptTime;
	private static JTextField actDescription;
	
	static JButton btExit = new JButton("Exit");
	static JRadioButton jRadioButton1 = new JRadioButton();
	static JRadioButton jRadioButton2 = new JRadioButton();
	static JRadioButton jRadioButton3 = new JRadioButton();
	static JRadioButton jRadioButton4 = new JRadioButton();
	static JButton jButton = new JButton("OK");
	static ButtonGroup G1 = new ButtonGroup(); 

	public static void saveAppointmentDialog(){
	    JFrame f = new JFrame("Save an appointment");  
	    cp = f.getContentPane();
	    cp.setLayout(new GridBagLayout());
	    //f.add(getSouthPanel(), BorderLayout.SOUTH);

	    setFullName(setRow("Enter Client Name (as FirstName LastName):", 'n'));
	    setPhoneNumber(setRow("Phone Number (e.g. 613-555-1212):", 'p'));
	    setAptDate(setRow("Appointment Date (entered as DDMMYYYY):", 'd'));
	    setAptTime(setRow("Appointment Time:", 't'));
	    setActDescription(setRow("Activity Description", 'a'));
	    
	    jRadioButton1.setText("Assessment"); 
	    jRadioButton2.setText("Filling"); 
	    jRadioButton3.setText("Crown"); 
	    jRadioButton4.setText("Cosmetic Repair"); 
	    
	    cp.add(jRadioButton1);
	    cp.add(jRadioButton2);
	    cp.add(jRadioButton3);
	    cp.add(jRadioButton4);
	    
	    G1.add(jRadioButton1); 
        G1.add(jRadioButton2); 
        G1.add(jRadioButton3); 
        G1.add(jRadioButton4); 
        

	    cp.add(setSouthPanelBtns(" Save ", new Scheduler.btSave()));
	    cp.add(setSouthPanelBtns(" Exit ", (ActionEvent e) -> f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING))));
//	    cp.add(btExit);
//		btExit.addActionListener(new ActionListener() {
//			//public static class btExit implements ActionListener{
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					//f.dispose();
//					//System.out.print("exit");
//					f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
//				}
//			});
	    
	    f.pack();
	    f.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent evt) {
	    	f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	      }
	    });
	    f.setVisible(true);
	  }
	
	public static void showAppointmentDialog(){
	    JFrame f = new JFrame("Get, set, change or delete an appointment");  
	    cp = f.getContentPane();
	    cp.setLayout(new GridBagLayout());

	    setAptDate(setRow("Appointment Date (entered as DDMMYYYY):", 'd'));
	    setAptTime(setRow("Appointment Time:", 't'));

	    cp.add(setSouthPanelBtns(" Find ", new Scheduler.btFind()));
	    cp.add(setSouthPanelBtns(" Change ", new Scheduler.btChange()));
	    cp.add(setSouthPanelBtns(" Delete ", new Scheduler.btDelete()));
	    cp.add(setSouthPanelBtns(" Exit ", (ActionEvent e) -> f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING))));
 
	    f.pack();
	    f.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent evt) {
	    	f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	      }
	    });
	    f.setVisible(true);
	  }
	
	public static void changeAppointmentDialog(){
	    JFrame f = new JFrame("New appointment information");  
	    cp = f.getContentPane();
	    cp.setLayout(new GridBagLayout());

	    setAptDate(setRow("Appointment Date (entered as DDMMYYYY):", 'd'));
	    setAptTime(setRow("Appointment Time:", 't'));

	    cp.add(setSouthPanelBtns(" Update ", new Scheduler.btUpdate()));
	    cp.add(setSouthPanelBtns(" Exit ", (ActionEvent e) -> f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING))));
 
	    f.pack();
	    f.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent evt) {
	    	f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	      }
	    });
	    f.setVisible(true);
	  }
	
	public static void showScheduleDialog(){
	    JFrame f = new JFrame("Display schedule");  
	    cp = f.getContentPane();
	    cp.setLayout(new GridBagLayout());

	    setAptDate(setRow("Appointment Date (entered as DDMMYYYY):", 'd'));
	    
	    cp.add(setSouthPanelBtns(" Display Day Schedule ", new Scheduler.btDisplay()));
	    cp.add(setSouthPanelBtns(" Exit ", (ActionEvent e) -> f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING))));
	    
	    f.pack();
	    f.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent evt) {
	    	f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	      }
	    });
	    f.setVisible(true);
	  }
	
	public static JTextField setRow(String label, char keyboardShortcut) {
		JLabel l; JTextField t;
		cp.add(l = new JLabel(label, SwingConstants.RIGHT), textConstants);
		l.setFont(defaultFont);
		l.setDisplayedMnemonic(keyboardShortcut);
	    cp.add(t = new JTextField(labelWidth), labelConstants);
	    t.setFocusAccelerator(keyboardShortcut);
	    return t;
	}
	
	private static JButton setSouthPanelBtns(String btnLabel, ActionListener act) {
		final Font font = new Font("SansSerif", Font.PLAIN, 20);
		JButton btn = new JButton(btnLabel);
		btn.setFont(font);
		btn.addActionListener(act);
		return btn;
	}
	
	public static JTextField getFullName() {
		return fullName;
	}
	
	public static void setFullName(JTextField fullName) {
		AppointmentDialog.fullName = fullName;
	}

	public static JTextField getPhoneNumber() {
		return phoneNumber;
	}
	
	public static void setPhoneNumber(JTextField phoneNumber) {
		AppointmentDialog.phoneNumber = phoneNumber;
	}

	public static JTextField getAptDate() {
		return aptDate;
	}
	
	public static void setAptDate(JTextField aptDate) {
		AppointmentDialog.aptDate = aptDate;
	}

	public static JTextField getAptTime() {
		return aptTime;
	}
	
	public static void setAptTime(JTextField aptTime) {
		AppointmentDialog.aptTime = aptTime;
	}

	public static JTextField getActDescription() {
		return actDescription;
	}
	
	public static void setActDescription(JTextField actDescription) {
		AppointmentDialog.actDescription = actDescription;
	}
	  
}
