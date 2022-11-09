package view;

import operator.ViewOperator;
import model.Movies;

import java.util.ArrayList;

/**
 * This View class is responsible for showing top 5 movies (by sales or rating) for the movie-goer module
 */

public class MovieGoerTop5MovieView extends MainTop5MovieView {


    /**
     * This is the constructor, which has two parameter, the movie as well as the ranking criteria (rank by ticket sales or rating)
     * @param movies The array of movies object that are in top 5
     * @param listOption The ranking criteria
     */
    public MovieGoerTop5MovieView(ArrayList<Movies> movies, Top5MoviesOption listOption) {
        super(movies, listOption);
    }

    /**
     * This method helps to manage execution of code based on the user input choice on the BaseView options.
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