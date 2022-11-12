package View;

import Controller.ViewsManager;
import Model.Movie;

import java.util.ArrayList;

/**
 * This View class is responsible for showing top 5 movies (by sales or rating) for the movie-goer module
 */

public class MovieGoerListTop5MoviesView extends BaseListTop5MoviesView {


    /**
     * This is the constructor, which has two parameter, the movie as well as the ranking criteria (rank by ticket sales or rating)
     * @param movies The array of movies object that are in top 5
     * @param listOption The ranking criteria
     */
    public MovieGoerListTop5MoviesView(ArrayList<Movie> movies, Top5MoviesOption listOption) {
        super(movies, listOption);
    }

    /**
     * This method helps to manage execution of code based on the user input choice on the BaseView options.
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