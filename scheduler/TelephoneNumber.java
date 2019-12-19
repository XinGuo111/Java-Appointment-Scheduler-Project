/* Course Name: Object Oriented Programming (Java)
   Student Name: Xin Guo
   Class Name: TelephoneNumber
   Date: 2019-11-27
 */
package cst8284.asgmt4.scheduler;

import java.io.Serializable;
import java.util.regex.Pattern;
/**
 * This class implements Serializable interface and instantiates telephone number with
 * area code, line number and prefix. It throws bad appointment data exception if the input telephone number:
 * <ul>
 * <li> is not in the correct format AAA-PPP-NNNN
 * <li> contains bad characters other than numbers and the character '-'
 * <li> area code starts with a '0' or a '1'
 * </ul>
 * 
 * @author Xin Guo
 * @version 1.0
 */
public class TelephoneNumber implements Serializable {
	/**
	 * Represents the area code, line number and prefix of telephone number.
	 */
	private int areaCode, lineNumber, prefix;
	/**
	 * One-String TelephoneNumber constructor takes a telephone number in String and splits it by the character '-'
	 * to instantiate area code, line number and prefix. It checks the format of the telephone number. The correct format
	 * is AAA-PPP-NNNN, where AAA is the area code, PPP is the prefix and NNNN is the line number.
	 * <p>
	 * If the telephone number is correct, it calls setAreaCode() method, setPrefix() method and setLineNumber() method
	 * to set the area code, line number and prefix of telephone number.
	 * 
	 * @param phoneNumber telephone number input from the user in String
	 * @throws BadAppointmentDataException If area code or prefix is not three digits or line number is not four digits, it throws a bad appointment data exception
	 * with the description "Incorrect format" and output message "Missing digit(s); correct format is AAA-PPP-NNNN, 
	 * where AAA is the area code and PPP-NNNN is the local number".
	 * @throws BadAppointmentDataException If the area code starts with a '0' or a '1', it throws a bad appointment data exception with the description 
	 * "Invalid number" and output message "Area code can’t start with a '0' or a '1'".
	 * @throws BadAppointmentDataException If the area code contains bad characters other than numbers and the character '-', it throws a bad appointment data exception
	 * with the description "Bad character(s) in input string" and output message "Telephone numbers can only contain numbers or the character '-'".
	 */
	public TelephoneNumber(String phoneNumber) {
		// checking for incorrect format
				if((phoneNumber.split("-")[0].trim()).length()!=3 || (phoneNumber.split("-")[1].trim()).length()!=3 || (phoneNumber.split("-")[2].trim()).length()!=4)
					throw new BadAppointmentDataException("Missing digit(s); correct format is AAA-PPP-NNNN, where AAA is the area code and PPP-NNNN is the local number", "Incorrect format");
		// checking for bad character(s) in input string
		// stack overflow. Check for special characters in a string using regex in java [duplicate]. [Webpage]. Retrieved from https://stackoverflow.com/questions/48031098/check-for-special-characters-in-a-string-using-regex-in-java
		Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!]");
		if (regex.matcher(phoneNumber).find())
			throw new BadAppointmentDataException("Telephone numbers can only contain numbers or the character '-'", "Bad character(s) in input string");
		int areaCode = Integer.parseInt(phoneNumber.split("-")[0].trim());
		// checking for invalid number
		if(areaCode/100==0 || areaCode/100==1)
			throw new BadAppointmentDataException("Area code can't start with a '0' or a '1'", "Invalid number");
		int prefix = Integer.parseInt(phoneNumber.split("-")[1].trim());
		int lineNumber = Integer.parseInt(phoneNumber.split("-")[2].trim());
		
		setAreaCode(areaCode); setPrefix(prefix); setLineNumber(lineNumber);
	}	
	/**
	 * Returns the area code of telephone number.
	 * 
	 * @return area code of telephone number in integer
	 */
	public int getAreaCode() {return areaCode;}
	/**
	 * Sets the area code of telephone number.
	 * 
	 * @param areaCode area code of telephone number in integer
	 */
	public void setAreaCode(int areaCode) {this.areaCode = areaCode;}
	/**
	 * Returns the prefix of telephone number.
	 * 
	 * @return prefix of telephone number in integer
	 */
	public int getPrefix() { return prefix;}
	/**
	 * Sets the prefix of telephone number.
	 * 
	 * @param prefix prefix of telephone number in integer
	 */
	public void setPrefix(int prefix) {this.prefix = prefix;}
	/**
	 * Returns the line number of telephone number.
	 * 
	 * @return line number of telephone number in integer
	 */
	public int getLineNumber() {return lineNumber;}
	/**
	 * Sets the line number of telephone number.
	 * 
	 * @param lineNumber line number of telephone number in integer
	 */
	public void setLineNumber(int lineNumber) {this.lineNumber = lineNumber;}
	/**
	 * Overrides toString() method in class java.lang.Object.
	 */
	public String toString() {return "(" + getAreaCode() +") "+ getPrefix() + "-" + getLineNumber();}
}
