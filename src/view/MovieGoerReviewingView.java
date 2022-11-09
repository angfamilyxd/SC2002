package view;

import java.util.ArrayList;
import operator.FileInputOutputOperator;
import operator.ViewOperator;
import operator.DataOperator;
import model.Movies;
import model.Review;


/**
 * This View class is responsible for getting review from the movie-goer
 */
public class MovieGoerReviewingView extends StartView {

	/**
	 * This is the options for the BaseView
	 */
	private ArrayList<String> options = new ArrayList<>();

	/**
	 * This is the title for the BaseView
	 */
	private String title = "Leave Review";

	/**
	 * This is the content for the BaseView
	 */
	private String viewContent = " ";

	/**
	 * This is the selected movie where the user will leave the reviews on
	 */
	private Movies selectedMovie;
	
	public MovieGoerReviewingView(Movies movie) {
		this.selectedMovie = movie;
	}

	/**
	 * This method transforms the View state into active state
	 */
	public void activate() {
		super.setOptions(this.options);
		super.setTitle(this.title);
		super.setViewContent(this.viewContent);
		super.activate();
		
		boolean looping = true;
		
		while (looping) {
			String username = FileInputOutputOperator.getUserInputString("Please input your name: ");
			double userInputRating = FileInputOutputOperator.getUserInputDouble("Please input a your rating (1-5): ",1.0,5.0);
			String reviewContent = FileInputOutputOperator.getUserInputString("Please input your review: ");
			
			Review newReview = new Review(username,userInputRating,reviewContent);
			System.out.println("\n\n   " + newReview.toString());
			
			ArrayList<String> userOptions = new ArrayList<>();
			userOptions.add("Reenter the values");
			userOptions.add("Save and Submit");
			userOptions.add("Go back to previous page without saving");
			FileInputOutputOperator.printMenuOptions(userOptions);
			int userOptionChoice = FileInputOutputOperator.getUserInputInt("Here is your review, choose the following option: ",1,userOptions.size());
			
			if (userOptionChoice == 2) {
				selectedMovie.addReview(newReview);
				DataOperator.modifyMovieWithNewValues(selectedMovie);
				break;
			}else if (userOptionChoice == 3) {
				break;
			}
			
		}
		
		ViewOperator.popView();
		
		
	}

	/**
	 * This method helps to manage execution of code based on the user put choice on the View options.
	 * @param input the index of the options
	 */
	@Override
	protected void processUserInput(int input) {
		// TODO Auto-generated method stub

	}
}
