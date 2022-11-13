package View;

import Controller.DatabaseManager;
import Controller.IOManager;
import Controller.ViewsManager;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.Showtime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * This View class is responsible for getting the filter mode from the user and filter the showtimes accordingly
 */

public class MovieGoerBookingFilterShowTimesView extends BaseView {

    /**
     * This is the View options
     */
	
    /*private ArrayList<String> options = new ArrayList<>(Arrays.asList(
            "Filter by Cineplex",
            "Filter by Date",
            "Do not filter",
            "Back to Previous Page"
    ));
	*/
	private ArrayList<String> options = new ArrayList<>(Arrays.asList(
            "Filter by Date",
            "Next Page",
            "Back to Previous Page"
    ));
	

    /**
     * This is the View title
     */
    private String title = "Booking Options: ";

    /**
     * This is the View content
     */
    private String viewContent = "How do you want to filter the showtime results?";

    /**
     * This is the selected movie object
     */
    private Movie selectedMovie;

    /**
     * This is constructor which requires a movie object as an argument
     * @param movie The selected Movie object
     */
    public MovieGoerBookingFilterShowTimesView(Movie movie){
        this.selectedMovie = movie;
        this.title += this.selectedMovie.getTitle();
    }

    /**
     * This method transforms the View into active state
     */
    @Override
    public void activate() {

        super.setOptions(this.options);
        super.setTitle(this.title);
        super.setViewContent(this.viewContent);
        super.activate();

        int userInput = IOManager.getUserInputInt("Please input a choice",1,options.size());
        processUserInput(userInput);
    }


    /**
     * This method helps to manage execution of code based on the user put choice on the View options.
     * @param input the index of the options
     */
    @Override
    protected void processUserInput(int input) {
        switch (input){
            case 1:
            	this.handleChooseByDate();
                break;
            case 2:
            	this.handleDoNotFilter();
                break;
            default:
                ViewsManager.popView();
                break;
        }
    }
    
    /**
     * This method handles the option when the user choose to filter showtimes by date
     */
    private void handleChooseByDate(){

        ArrayList<Cineplex> cineplexes = DatabaseManager.retrieveAllCineplexes();

        LocalDate chosenDate = IOManager.getUserInputDate("Input the date: ");
        ArrayList<Showtime> showtimes = new ArrayList<>();
        for (Cineplex cineplex : cineplexes){
            for (Cinema cinema : cineplex.getCinemas()){
                for (Showtime showtime : cinema.getShowtimes()){
                    if (showtime.getMovie().getTitle().equals(selectedMovie.getTitle())){
                        if (showtime.getShowDatetime().toLocalDate().isEqual(chosenDate)){
                            showtimes.add(showtime);
                        }
                    }
                }
            }
        }
        Collections.sort(showtimes, Comparator.comparing(Showtime::getShowDatetime));
        ViewsManager.pushView(new MovieGoerBookingListShowTimesView(showtimes));

    }


    /**
     * This method handles the option when the user choose not to filter the showtimes
     */
    private void handleDoNotFilter(){
        ArrayList<Cineplex> cineplexes = DatabaseManager.retrieveAllCineplexes();
        ArrayList<Showtime> showtimes = new ArrayList<>();
        for (Cineplex cineplex : cineplexes){
            for (Cinema cinema : cineplex.getCinemas()){
                for (Showtime showtime : cinema.getShowtimes()){
                    if (showtime.getMovie().getTitle().equals(selectedMovie.getTitle())){
                        showtimes.add(showtime);
                    }
                }
            }
        }
        Collections.sort(showtimes, Comparator.comparing(Showtime::getShowDatetime));
        ViewsManager.pushView(new MovieGoerBookingListShowTimesView(showtimes));
    }


}