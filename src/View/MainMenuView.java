package View;
import java.util.ArrayList;
import java.util.Arrays;

import Controller.IOManager;
import Controller.ViewsManager;

/**
 * This is the MainMenuView class, which is the entry point of the application and is always the first view that the user of this system will see.
 */

public class MainMenuView extends BaseView {

	/**
	 * This is the View options
	 */
	private ArrayList<String> options = new ArrayList<>(Arrays.asList(
			"Staff",
			"Movie-Goer"
	));

	/**
	 * This is the View title
	 */
	private String title = "MOBLIMA";

	/**
	 * This is the View content
	 */
	private String viewContent = "Moblima Movie Booking System!";


	/**
	 * This method transforms the View into active state
	 */
	@Override
	public void activate() {
		
		super.setOptions(this.options);
		super.setTitle(this.title);
		super.setViewContent(this.viewContent);
		super.activate();
		
		int userInput = IOManager.getUserInputInt("Please enter your choice",1,options.size());
		processUserInput(userInput);
	}


	/**
	 * This method helps to manage execution of code based on the user put choice on the View options.
	 * @param input the index of the options
	 */
	@Override
	protected void processUserInput(int input) {
		switch (input) {
		case 1:
			ViewsManager.pushView(new AdminMainMenuView());
		case 2:
			ViewsManager.pushView(new MovieGoerView());
		}

	}




}