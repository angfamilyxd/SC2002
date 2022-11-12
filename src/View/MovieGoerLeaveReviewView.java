package View;

import java.util.ArrayList;
import Controller.DatabaseManager;
import Controller.IOManager;
import Controller.ViewsManager;
import Model.Movie;
import Model.Review;


/**
 * This View class is responsible for getting review from the movie-goer
 */
public class MovieGoerLeaveReviewView extends BaseView {

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
	private Movie selectedMovie;
	
	public MovieGoerLeaveReviewView(Movie movie) {
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
			String username = IOManager.getUserInputString("Please input your name: ");
			double userInputRating = IOManager.getUserInputDouble("Please input a your rating (1-5): ",1.0,5.0);
			String reviewContent = IOManager.getUserInputString("Please input your review: ");
			
			Review newReview = new Review(username,userInputRating,reviewContent);
			System.out.println("\n\n   " + newReview.toString());
			
			ArrayList<String> userOptions = new ArrayList<>();
			userOptions.add("Reenter the values");
			userOptions.add("Save and Submit");
			userOptions.add("Go back to previous page without saving");
			IOManager.printMenuOptions(userOptions);
			int userOptionChoice = IOManager.getUserInputInt("Here is your review, choose the following option: ",1,userOptions.size());
			
			if (userOptionChoice == 2) {
				selectedMovie.addReview(newReview);
				DatabaseManager.modifyMovieWithNewValues(selectedMovie);
				break;
			}else if (userOptionChoice == 3) {
				break;
			}
			
		}
		
		ViewsManager.popView();
		
		
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