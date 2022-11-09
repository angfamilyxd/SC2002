package view;
import java.util.ArrayList;
import java.util.Arrays;

import operator.FileInputOutputOperator;
import operator.ViewOperator;


/**
 * This View class is the main menu for the movie-goer
 */

public class MovieGoerMainView extends StartView {

	/**
	 * This is the View options
	 */
	private ArrayList<String> options = new ArrayList<>(Arrays.asList(
			"List Movie",
			"Check Booking History",
			"Back to Previous Page"
	));

	/**
	 * This is the View title
	 */
	private String title = "Movie-Goer Menu";

	/**
	 * This is the View content
	 */
	private String viewContent = "What would you like to do?";


	/**
	 * This method helps to transform this View into active state
	 */
	public void activate() {
		super.setOptions(this.options);
		super.setTitle(this.title);
		super.setViewContent(this.viewContent);
		
		super.activate();
		
		int userInput = FileInputOutputOperator.getUserInputInt("Please input your choice",1,options.size());
		processUserInput(userInput);
	}

	/**
	 * This method helps to manage execution of code based on the user put choice on the view options.
	 * @param input the index of the options
	 */

	@Override
	protected void processUserInput(int input) {
		
		switch (input) {
			case 1:
				ViewOperator.pushView(new MovieGoerSearchMovieView());
				break;
			case 2:
				String username = FileInputOutputOperator.getUserInputString("Please input your username: ");
				ViewOperator.pushView(new MovieGoerBookingHistoryView(username));
				break;
			case 3:
				ViewOperator.popView();
			}
	}

}