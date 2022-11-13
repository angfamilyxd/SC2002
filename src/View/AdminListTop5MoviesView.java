package View;

import Controller.ViewsManager;
import Model.Movie;
import java.util.ArrayList;


/**
 * This View class is responsible for listing the top 5 movies in the admin module
 */
public class AdminListTop5MoviesView extends BaseListTop5MoviesView {

    /**
     * This is the constructor of the class, which requires both the list of movies as well as the ranking option as the parameter
     * @param movies The top 5 movies to be listed
     * @param listOption The criteria for comparing different movies for ranking
     */
    public AdminListTop5MoviesView(ArrayList<Movie> movies, Top5MoviesOption listOption) {
        super(movies, listOption);
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