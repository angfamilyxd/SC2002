package view;
import java.util.ArrayList;
import operator.ViewOperator;
import model.Movies;


/**
 * This View class is responsible for printing the list of movies in the movie-goer module
 */
public class MovieGoerMovieListingView extends MainMovieListingView {

	/**
	 * This is the constructor which takes an array of movies as an argument
	 * @param movies The array of movies to be listed
	 */

	public MovieGoerMovieListingView(ArrayList<Movies> movies) {
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
			ViewOperator.pushView(new MovieGoerViewMovieView(this.movies.get(input - 1)));
		}
	}

}