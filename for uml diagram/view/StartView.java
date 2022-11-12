package view;

import java.util.ArrayList;
import operator.FileInputOutputOperator;

/**
 * This is the base View class. All the subsequent views class will be the subclasses of this class.
 * This class helps to define the basic structure of View in our application.
 * A BaseView has 3 basic components: the title, the view content as well as the options for user to choose and interact with.
 */

public abstract class StartView {

	/**
	 * This is the View String content
	 */
	private String viewContent = " ";

	/**
	 * This is the View title
	 */
	private String title = " ";

	/**
	 * This is the list of options available for users in the BaseView
	 */
	private ArrayList<String> options = new ArrayList<String>();


	/**
	 * This method allows subclasses to set the view content of the BaseView
	 * @param content The string content to be printed as the View content
	 */
	protected void setViewContent(String content) {
		this.viewContent = content;
	}


	/**
	 * This method allows subclasses to set the title of the BaseView
	 * @param title The title to be printed on top of the BaseView
	 */
	protected void setTitle(String title) {
		this.title = title;
	}

	/**
	 * This method allows subclasses to set the View options that are available for users to interact with
	 * @param options The options to be printed for users to choose
	 */
	protected void setOptions(ArrayList<String> options) {
		this.options = options;
	}


	/**
	 * This activate method makes the View state to be active, in which the View will print out its title, menu and options in the console.
	 */
	public void activate() {
    	printViewTitle();
    	printViewContent();
    	printOptions();
    }

	/**
	 * This method helps to handle user input when user choose one of the options in the BaseView
	 * @param input the index of the options
	 */
	protected abstract void processUserInput(int input);

	/**
	 * This method helps to print the View title
	 */
    protected void printViewTitle() {
    	FileInputOutputOperator.printMenuTitle(title);
    }

	/**
	 * This method helps to print the View options
	 */
	protected void printOptions() {
    	if (options.size() != 0) {
    		FileInputOutputOperator.printMenuOptions(options);
    	}
    }

	/**
	 * This method helps to print the View content
	 */
	protected void printViewContent() {
		FileInputOutputOperator.printMenuContent(viewContent);
    }

}