package View;
import java.util.ArrayList;
import Controller.ViewsManager;
import Model.Movie;


/**
 * This View class is responsible for printing the list of movies in the movie-goer module
 */
public class MovieGoerListMoviesView extends BaseListMoviesView {

	/**
	 * This is the constructor which takes an array of movies as an argument
	 * @param movies The array of movies to be listed
	 */

	public MovieGoerListMoviesView(ArrayList<Movie> movies) {
		super(movies);
	}

	/**
	 * This method helps to manage execution of code based on the user put choice on the View options.
	 * @param input the index of the options
	 */
	@Override
	protected void processUserInput(int input) {
		
		if (input == -1) {
			ViewsManager.popView();
		}else {
			ViewsManager.pushView(new MovieViewingView(this.movies.get(input - 1)));
		}
	}

}