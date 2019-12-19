/* Course Name: Object Oriented Programming (Java)
   Student Name: Xin Guo
   Class Name: Activity
   Date: 2019-11-27
 */
package cst8284.asgmt4.scheduler;

import java.io.Serializable;
/**
 * This class implements Serializable interface and instantiates activity with description and category.
 * 
 * @author Xin Guo
 * @version 1.0
 */
public class Activity implements Serializable {
	/**
	 * Represents the activity description.
	 */
	private String descriptionOfWork;
	/**
	 * Represents the activity type.
	 */
	private String category;
	/**
	 * Two-String Activity constructor which calls the setDescription() method and setCategory() method
	 * to instantiate an activity with activity description and category.
	 * 
	 * @param description describes the details of the activity
	 * @param category describes the activity type
	 */
	public Activity(String description, String category) {
		setDescription(description);
		setCategory(category);
	}
	/**
	 * Returns the activity description.
	 * 
	 * @return activity description in String
	 */
	public String getDescription() {return descriptionOfWork;}
	/**
	 * Sets the activity's description.
	 * 
	 * @param description describes the details of the activity in String
	 */
	public void setDescription(String description) {this.descriptionOfWork = description;}
	/**
	 * Returns the activity category.
	 * 
	 * @return activity category in String
	 */
	public String getCategory() {return category;}
	/**
	 * Sets the activity's category.
	 * 
	 * @param category describes the category of the activity in String
	 */
	public void setCategory(String category) {this.category = category;}
	/**
	 * Overrides toString() method in class java.lang.Object.
	 */
	public String toString() {return getCategory() + "\n" + getDescription();}
}
