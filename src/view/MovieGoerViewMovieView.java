package view;

import java.util.ArrayList;
import operator.FileInputOutputOperator;
import operator.ViewOperator;
import model.Movies;
import enums.*;


/**
 * This BaseView class presents the various options for users for a given selected movies, such as booking of tickets or leaving reviews.
 */

public class MovieGoerViewMovieView extends StartView {

	/**
	 * This is the view options
	 */
	private ArrayList<String> options = new ArrayList<>();

	/**
	 * This is the view title
	 */
	private String title;

	/**
	 * This is view content
	 */
	private String viewContent = "You have chosen this movie, please choose one of the following options: ";

	/**
	 * This is the selected movie
	 */
	private Movies movie;

	/**
	 * This creates a new MovieViewingView with a given Movie object
	 * @param movie The selected movie for viewing the options available
	 */
	public MovieGoerViewMovieView(Movies movie) {
		this.movie = movie;
		options.add("View Movie Detail");
		if (movie.getStatus() == MovieStatus.COMINGSOON){
//			options.add("Book");  //logically coming soon movies should not be able to be booked
		}else if (movie.getStatus() == MovieStatus.ENDED){
			options.add("Leave Review");
			options.add("Read Reviews");
		}else {
			options.add("Book Ticket");
			options.add("Leave Review");
			options.add("Read Reviews");
		}
		options.add("Back to Previous Page");
		this.title = "Movie: " + movie.getTitle() + " [ " + movie.getStatus().displayName() + " ] ";
	}

	/**
	 * This method helps to transform this BaseView into active state
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
		
		if (input == options.size()) {
			ViewOperator.popView();
		}else if (input == 1) {
			ViewOperator.pushView(new MovieDetailView(this.movie));
		}else if (movie.getStatus() == MovieStatus.COMINGSOON) {
//			if (input == 2){
//				ViewsManager.pushView(new BookingBrowseOptionsView(this.movie));
//			}
		}else if (movie.getStatus() == MovieStatus.ENDED) {
			if (input == 2) {
				ViewOperator.pushView(new MovieGoerReviewingView(this.movie));
			}else if (input == 3) {
				ViewOperator.pushView(new MovieGoerReviewView(this.movie));
			}
		}else {
			if (input == 2){
				ViewOperator.pushView(new MovieGoerBookingFilterView(this.movie));
			}
			else if (input == 3) {
				ViewOperator.pushView(new MovieGoerReviewingView(this.movie));
			}else if (input == 4) {
				ViewOperator.pushView(new MovieGoerReviewView(this.movie));
			}
		}

	}

}