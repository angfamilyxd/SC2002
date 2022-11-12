package view;

import operator.DataOperator;
import model.Movies;
import model.Transaction;
import model.Ticket;

import java.util.ArrayList;


/**
 * This is an abstract View class which provides the implementation of presenting top 5 movies.
 *  * This is created because there are various parts of the applications that needs to list Top 5 movies.
 *  * For example, both the admin module and the movie goer module needs to make use of listing Top 5 movies in certain parts within their modules.
 *  * Therefore, this View class act as an abstraction and manage the listing of the top 5 movies, while leaving the low level implementation to the subclasses of this class, such as processing of user options input.
 */
public abstract class MainTop5MovieView extends MainMovieListingView {

    /**
     * This is an enum which defines the criteria by which the top 5 movies are calculated.
     */
    public enum Top5MoviesOption{
        TICKETSALES,RATINGS
    }

    /**
     * This is the criteria by which the movies are being compared and ranked
     */
    private Top5MoviesOption listOption;

    /**
     * This constructor requires both the movies to be listed, as well as the criteria for which the movies are compared and ranked
     * @param movies The ArrayList of movies
     * @param listOption The criteria for comparing different movies for ranking
     */
    public MainTop5MovieView(ArrayList<Movies> movies, Top5MoviesOption listOption) {
        super(movies);
        this.listOption = listOption;
    }

    /**
     * This method helps to format the String to be printed for each movie object
     * @param movies The movies to be printed in the BaseView
     * @return
     */
    @Override
    protected ArrayList<String> convertMoviesObjectToOptionStrings(ArrayList<Movies> movies){
        ArrayList<String> movieStrings = new ArrayList<>();

        if (this.listOption == Top5MoviesOption.TICKETSALES){

            ArrayList<Transaction> orders = DataOperator.retrieveAllOrders();
            ArrayList<Integer> resultSales = new ArrayList<>();

            for (Movies movie: movies){
                int sales = 0;
                for (Transaction order: orders){
                    for (Ticket ticket:order.getTickets()){
                        if (ticket.getShowtime().getMovie().getTitle().equals(movie.getTitle())){
                            sales += 1;
                        }
                    }
                }
                resultSales.add(sales);
            }
            int i = 0;
            for (Movies movie : movies) {
                movieStrings.add(movie.getTitle() + "\n       Ticket Sales: " + resultSales.get(i) + "\n");
                i++;
            }
            return movieStrings;

        }

        else if (this.listOption == Top5MoviesOption.RATINGS){
            for (Movies movie : movies) {
                movieStrings.add(movie.getTitle() + "\n       Overall Rating: " + movie.getRatingString() + "\n");
            }
            return movieStrings;
        }

        else{
            return movieStrings;
        }
    }

}