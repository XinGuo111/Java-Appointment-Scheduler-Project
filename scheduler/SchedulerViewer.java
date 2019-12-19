/* Course Name: Object Oriented Programming (Java)
   Student Name: Xin Guo
   Class Name: SchedulerViewer
   Date: 2019-11-27
 */
package cst8284.asgmt4.scheduler;

//import cst8284.asgmt4.employee.Employee;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class SchedulerViewer extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Toolkit tk = Toolkit.getDefaultToolkit();
	private static final Dimension screenSize = tk.getScreenSize();
	private static final JTextArea scrollText = new JTextArea();

	//private static ArrayList<String> listOfStrings = null;
	private static File file = null;
	//static JFrame frame;

//	public static void main(String[] args) {
//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				loadAndShowWords();
//			}
//		});
//	}
	static JFrame frame = new JFrame();
	public static void launch() {
		frame.setTitle("Scheduling appointments for " + Scheduler.getEmployee().getName());
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int screenX = (int) screenSize.getWidth() / 2;
		int screenY = (int) (7 * screenSize.getHeight() / 8);
		
		/*Uncomment the following code when you have finished TODO #1 and test your code.
		  Make sure the word list appears in the scroll pane.  Then remove this comment.
		*/  
//		file = getFileFromUser("");
//		listOfStrings = FileIO.loadArrayListFromFile(file);
//		if (listOfStrings != null) {
//			reloadJTextArea();
//		}
			frame.add(getWestPanel(), BorderLayout.WEST);
			frame.add(getCenterPanel(scrollText, screenY), BorderLayout.CENTER);
			frame.setPreferredSize(new Dimension(screenX, screenY));

			frame.pack();
			frame.setVisible(true);
		
	}

	public static JPanel getCenterPanel(JTextArea jta, int height) {
		JScrollPane centerPane = new JScrollPane(jta);
		centerPane.setPreferredSize(new Dimension(400, 7 * height / 8));
		JPanel jp = new JPanel();
		jp.add(centerPane);
		return jp;
	}
	
	static JButton btExit = new JButton("Exit");
	
	public static JPanel getWestPanel() {
		JPanel controlPanel = new JPanel(new GridLayout(6, 1));
		JPanel westPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.weighty = 1;

		controlPanel.add(setWestPanelBtns("      Save Appointment      ", (ActionEvent e) -> AppointmentDialog.saveAppointmentDialog()));
		controlPanel.add(setWestPanelBtns("    Display Appointment     ", (ActionEvent e) -> AppointmentDialog.showAppointmentDialog()));
		controlPanel.add(setWestPanelBtns("      Display Schedule      ", (ActionEvent e) -> AppointmentDialog.showScheduleDialog()));
		controlPanel.add(setWestPanelBtns("  Save Appointment to File  ", (ActionEvent e) -> Scheduler.saveAppointmentsToFile(Scheduler.getAppointments(), "CurrentAppointments.apts")));
		controlPanel.add(setWestPanelBtns(" Load Appointment from File ", (ActionEvent e) -> Scheduler.loadAppointmentsFromFile("CurrentAppointments.apts", Scheduler.getAppointments())));
		controlPanel.add(setWestPanelBtns("            Exit            ", (ActionEvent e) -> frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING))));
	    
//		controlPanel.add(btExit);
//		btExit.addActionListener(new ActionListener() {
//			//public static class btExit implements ActionListener{
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					//f.dispose();
//					//System.out.print("exit");
//					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
//				}
//			});
		
		/* Uncomment this section when you are ready to display the file information in the 
		   left panel of the swing GUI as per TODO #2.  Be sure to remove this comment.
		*/ 
//		JTextArea tf = new JTextArea(FileIO.toStringFileIO(file));
//		tf.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
//		controlPanel.add(tf);
//		
		westPanel.add(controlPanel, gbc);
		return westPanel;
	}

	private static JButton setWestPanelBtns(String btnLabel, ActionListener act) {
		final Font font = new Font("SansSerif", Font.PLAIN, 20);
		JButton btn = new JButton(btnLabel);
		btn.setFont(font);
		btn.addActionListener(act);
		return btn;
	}

	// Adapted from: https://www.mkyong.com/swing/java-swing-jfilechooser-example/
//	private static File getFileFromUser(String fileName) {
//		File f = null;
//		JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//		fc.setFileFilter(new FileNameExtensionFilter(".txt Files", "txt"));	
//		do {
//			int returnValue = fc.showOpenDialog(null);
//			if (returnValue == JFileChooser.APPROVE_OPTION) {
//				f = fc.getSelectedFile();
//				if (FileIO.fileExists(f))
//					return f;
//			} else if (returnValue == JFileChooser.CANCEL_OPTION)
//				return null;
//		} while (true);
//	}

//	public static ArrayList<String> getListOfStrings() {
//		return listOfStrings;
//	}

	public static void reloadJTextArea(String str) {
		scrollText.setText(str);
		//String str[] = new String[Scheduler.getAppointments().size()]; 
//		Object[] objArr = Scheduler.getAppointments().toArray();
//        for (Object obj : objArr) { 
//        	scrollText.setText((String)obj);}
		//scrollText.setText(Scheduler.appointments.toArray(Scheduler.getAppointments()));
		//scrollText.setText(((Object) Scheduler.appointments).toStringFromArrayList(Scheduler.getAppointments()));
	}
}
