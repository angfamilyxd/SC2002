package view;

import operator.FileInputOutputOperator;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * This is the base View class for listing options by which movies can be filtered or search.
 * This is created because there are various parts of the applications that needs this functionality.
 * For example, both the admin module and the movie goer module needs to make use of listing movies, which requires the searching and filtering of movies, in certain parts within their modules.
 * Therefore, this View class act as an abstraction and manage the filtering of movies, while leaving the low level implementation to the subclasses of this class, such as processing of user options input.
 */

public abstract class MainSearchMovieView extends StartView {

    /**
     * This is the View options
     */
    private ArrayList<String> options = new ArrayList<>(Arrays.asList(
            "Search movie",
            "List Current Showing Movies",
            "List Preview Movies",
            "List Coming Soon Movies",
            "List past movies",
            "List all movies",
            "List current top 5 movies",
            "Back to Previous Page"
    ));

    /**
     * This is the View title
     */
    private String title = "Browse Movie by Options";

    /**
     * This is the View content
     */
    private String viewContent = "Please choose the mode by which you want to search for movies";

    /**
     * This method transform this view into active state
     */
    public void activate() {
        super.setOptions(this.options);
        super.setTitle(this.title);
        super.setViewContent(this.viewContent);
        super.activate();
        int userInput = FileInputOutputOperator.getUserInputInt("Please input your choice",1,options.size());
        processUserInput(userInput);
    }

}