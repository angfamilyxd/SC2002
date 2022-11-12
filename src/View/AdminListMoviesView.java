package View;

import java.util.ArrayList;

import Controller.ViewsManager;
import Model.Movie;


/**
 * This View class is responsible for listing movies in the admin module
 */
public class AdminListMoviesView extends BaseListMoviesView {

	/**
	 * This is the constructor, which requires an ArrayList of Movie as the argument
	 * @param movies The list of movies to be listed
	 */
	public AdminListMoviesView(ArrayList<Movie> movies) {
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
			ViewsManager.pushView(new AdminChangeMovieInfoView(this.movies.get(input - 1)));
		}

	}

}