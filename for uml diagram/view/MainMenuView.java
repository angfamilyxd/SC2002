package view;
import java.util.ArrayList;
import java.util.Arrays;

import model.Staff;
import operator.DataOperator;
import operator.FileInputOutputOperator;
import operator.ViewOperator;

/**
 * This is the MainMenuView class, which is the entry point of the application and is always the first view that the user of this system will see.
 */

public class MainMenuView extends StartView {

	/**
	 * This is the View options
	 */
	private ArrayList<String> options = new ArrayList<>(Arrays.asList(
			"Admin",
			"Movie-Goer"
	));

	/**
	 * This is the View title
	 */
	private String title = "Moblima Movie Booking System";

	/**
	 * This is the View content
	 */
	private String viewContent = "Welcome To Moblima Movie Booking System! Are you a staff or a movie-goer?";


	/**
	 * This method transforms the View into active state
	 */
	@Override
	public void activate() {
		
		super.setOptions(this.options);
		super.setTitle(this.title);
		super.setViewContent(this.viewContent);
		super.activate();
		
		int userInput = FileInputOutputOperator.getUserInputInt("Please input a choice",1,options.size());
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
			ViewOperator.pushView(new StaffMainView());
		case 2:
			ViewOperator.pushView(new MovieGoerMainView());
		}

	}




}