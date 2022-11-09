package view;

import java.util.ArrayList;

import operator.ViewOperator;
import model.Movies;


/**
 * This View class is responsible for listing movies in the admin module
 */
public class StaffMovieListingView extends MainMovieListingView {

	/**
	 * This is the constructor, which requires an ArrayList of Movie as the argument
	 * @param movies The list of movies to be listed
	 */
	public StaffMovieListingView(ArrayList<Movies> movies) {
		super(movies);
	}

	/**
	 * This method helps to manage execution of code based on the user put choice on the View options.
	 * @param input the index of the options
	 */
	@Override
	protected void processUserInput(int input) {

		if (input == -1) {
			ViewOperator.popView();
		}else {
			ViewOperator.pushView(new StaffEditMovieDetailsView(this.movies.get(input - 1)));
		}

	}

}