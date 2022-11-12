package view;

import operator.DataOperator;
import operator.FileInputOutputOperator;
import operator.ViewOperator;
import model.Cinema;
import model.Cineplex;
import model.Movies;
import model.ShowTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * This View class is responsible for getting the filter mode from the user and filter the showtimes accordingly
 */

public class MovieGoerBookingFilterView extends StartView {

    /**
     * This is the View options
     */
    private ArrayList<String> options = new ArrayList<>(Arrays.asList(
            "Filter by Cineplex",
            "Filter by Date",
            "Do not filter",
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
    private Movies selectedMovie;

    /**
     * This is constructor which requires a movie object as an argument
     * @param movie The selected Movie object
     */
    public MovieGoerBookingFilterView(Movies movie){
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

        int userInput = FileInputOutputOperator.getUserInputInt("Please input a choice",1,options.size());
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
                this.handleChooseCineplex();
                break;
            case 2:
                this.handleChooseByDate();
                break;
            case 3:
                this.handleDoNotFilter();
            default:
            	ViewOperator.popView();
                break;
        }
    }

    /**
     * This method handles the option when the user choose to handle showtimes by cineplex
     */
    private void handleChooseCineplex(){

        ArrayList<Cineplex> cineplexes = DataOperator.retrieveAllCineplexes();
        ArrayList<String> cineplexStrings = new ArrayList<>();
        for (Cineplex cineplex : cineplexes) { cineplexStrings.add(cineplex.getName());}
        FileInputOutputOperator.printMenuOptions(cineplexStrings);
        int userCineplexChoice = FileInputOutputOperator.getUserInputInt("Please choose one of the cineplex",1,cineplexes.size());
        Cineplex chosenCineplex = cineplexes.get(userCineplexChoice - 1);

        ArrayList<ShowTime> showtimes = new ArrayList<>();
        for (Cinema cinema : chosenCineplex.getCinemas()){
            for (ShowTime showtime : cinema.getShowtimes()){
                if (showtime.getMovie().getTitle().equals(selectedMovie.getTitle())){
                    if (showtime.getShowDatetime().isAfter(LocalDateTime.now())){
                        showtimes.add(showtime); //do not show any past showtime
                    }
                }
            }
        }

        //have to think of some way of sorting the showtimes
        //this is sort by date
        Collections.sort(showtimes, Comparator.comparing(ShowTime::getShowDatetime));
        ViewOperator.pushView(new MovieGoerBookingShowTimeView(showtimes));
    }

    /**
     * This method handles the option when the user choose to filter showtimes by date
     */
    private void handleChooseByDate(){

        ArrayList<Cineplex> cineplexes = DataOperator.retrieveAllCineplexes();

        LocalDate chosenDate = FileInputOutputOperator.getUserInputDate("Input the date: ");
        ArrayList<ShowTime> showtimes = new ArrayList<>();
        for (Cineplex cineplex : cineplexes){
            for (Cinema cinema : cineplex.getCinemas()){
                for (ShowTime showtime : cinema.getShowtimes()){
                    if (showtime.getMovie().getTitle().equals(selectedMovie.getTitle())){
                        if (showtime.getShowDatetime().toLocalDate().isEqual(chosenDate)){
                            showtimes.add(showtime);
                        }
                    }
                }
            }
        }
        Collections.sort(showtimes, Comparator.comparing(ShowTime::getShowDatetime));
        ViewOperator.pushView(new MovieGoerBookingShowTimeView(showtimes));

    }


    /**
     * This method handles the option when the user choose not to filter the showtimes
     */
    private void handleDoNotFilter(){
        ArrayList<Cineplex> cineplexes = DataOperator.retrieveAllCineplexes();
        ArrayList<ShowTime> showtimes = new ArrayList<>();
        for (Cineplex cineplex : cineplexes){
            for (Cinema cinema : cineplex.getCinemas()){
                for (ShowTime showtime : cinema.getShowtimes()){
                    if (showtime.getMovie().getTitle().equals(selectedMovie.getTitle())){
                        showtimes.add(showtime);
                    }
                }
            }
        }
        Collections.sort(showtimes, Comparator.comparing(ShowTime::getShowDatetime));
        ViewOperator.pushView(new MovieGoerBookingShowTimeView(showtimes));
    }


}