package view;

import operator.ViewOperator;
import model.Movies;
import java.util.ArrayList;


/**
 * This View class is responsible for listing the top 5 movies in the admin module
 */
public class StaffTop5MovieView extends MainTop5MovieView {

    /**
     * This is the constructor of the class, which requires both the list of movies as well as the ranking option as the parameter
     * @param movies The top 5 movies to be listed
     * @param listOption The criteria for comparing different movies for ranking
     */
    public StaffTop5MovieView(ArrayList<Movies> movies, Top5MoviesOption listOption) {
        super(movies, listOption);
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