package View;

import Controller.IOManager;
import Controller.ViewsManager;
import Model.Showtime;
import java.util.ArrayList;


/**
 * This class is primarily responsible for showing the users the list of available showtimes
 */
public class MovieGoerBookingListShowTimesView extends BaseView {

    /**
     * This is the list of showtimes available
     */
    private ArrayList<Showtime> showTimes;
    /**
     * This is the options for the BaseView
     */
    private ArrayList<String> options = new ArrayList<>();

    /**
     * This is the title for the BaseView
     */
    private String title = "Showtime List for Movie: ";

    /**
     * This is the content of the BaseView
     */
    private String viewContent = "Here are the ShowTimes";


    /**
     * This is the constructor, which takes in an ArrayList of showtime as in argument
     * @param showTimes The ArrayList of showtimes to be shown in the BaseView
     */
    public MovieGoerBookingListShowTimesView(ArrayList<Showtime> showTimes){
        this.showTimes = showTimes;
        if (this.showTimes.size() != 0){
            this.title += this.showTimes.get(0).getMovie().getTitle();
        }
    }


    /**
     * This method transforms this view into active state
     */
    @Override
    public void activate() {

        super.setTitle(this.title);
        super.setViewContent(this.viewContent);
        super.activate();

        int userInput = IOManager.printMultipageOptionsWithReturnedChoice(this.showTimes,5);
        processUserInput(userInput);
    }

    /**
     * This method helps to manage execution of code based on the user put choice on the View options.
     * @param input the index of the options
     */
    @Override
    protected void processUserInput(int input) {

        if (input == -1){
            ViewsManager.popView();
        }else{
            ViewsManager.pushView(new MovieGoerBookingSeatSelectionView(this.showTimes.get(input - 1)));
        }

    }
}