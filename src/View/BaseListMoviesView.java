package View;

import Controller.IOManager;
import Model.Movie;

import java.util.ArrayList;

/**
 * This is the base View class for listing movies.
 * This is created because there are various parts of the applications that needs to list movies.
 * For example, both the admin module and the movie goer module needs to make use of listing movies in certain parts within their modules.
 * Therefore, this View class act as an abstraction and manage the listing of movies, while leaving the low level implementation to the subclasses of this class, such as processing of user options input.
 */

public abstract class BaseListMoviesView extends BaseView {

    /**
     * This is the View options
     */
    protected ArrayList<String> options = new ArrayList<>();

    /**
     * This is the View title
     */
    protected String title = "Movies List";

    /**
     * This is the View content
     */
    protected String viewContent = "Here are the movies available:";

    /**
     * This is the ArrayList of movies to be use for printing out the list of movies
     */
    protected ArrayList<Movie> movies = new ArrayList<>();


    /**
     * This is the constructor of this abstract class which requires ArrayList of movies as argument
     * @param movies The movies to be printed in the BaseView
     */
    public BaseListMoviesView(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    /**
     * This method helps to format the String to be printed for each movie object
     * @param movies The movies to be printed in the BaseView
     * @return The formatted String description of the movie objects passed in
     */
    protected ArrayList<String> convertMoviesObjectToOptionStrings(ArrayList<Movie> movies){
        ArrayList<String> movieStrings = new ArrayList<>();
        for (Movie movie : movies) {
            movieStrings.add(movie.getTitle() + " (" + movie.getMovieCensorshipRating().displayName() + ")");
        }
        return movieStrings;
    }

    /**
     * This transforms this View into active state
     */
    public void activate() {
        super.setTitle(this.title);
        super.setViewContent(this.viewContent);
        super.activate();
        this.options = convertMoviesObjectToOptionStrings(movies);
        int userInput = IOManager.printMultipageOptionsWithReturnedChoice(this.options,10);

        processUserInput(userInput);
    }

}