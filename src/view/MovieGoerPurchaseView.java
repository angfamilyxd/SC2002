package view;

import operator.FileInputOutputOperator;
import operator.ViewOperator;
import operator.DataOperator;
import operator.PricingOperator;
import model.*;
import enums.*;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * This View class is primarily responsible for facilitating the booking and purchasing of tickets from movie-goer
 */
public class MovieGoerPurchaseView extends StartView {

    /**
     * This is the options for the BaseView
     */
    private ArrayList<String> options = new ArrayList<>(Arrays.asList(
            "Confirm",
            "Back to Previous Seat Selection Page to reselect seat",
            "Cancel and back to main menu"
    ));

    /**
     * This is the title for the BaseView
     */
    private String title = "Purchasing Ticket";

    /**
     * This is the content for the BaseView
     */
    private String viewContent = "";

    /**
     * This is the array of tickets to be used for purchase by the movie goer
     */
    ArrayList<Ticket> createdTickets;

    /**
     * This is the array of selected seats by the user to be used for booking
     */
    private ArrayList<Seat> selectedSeats;

    /**
     * This is the selected showtime for booking of the movie
     */
    private ShowTime selectedShowtime;

    public MovieGoerPurchaseView(ArrayList<Seat> selectedSeats, ShowTime selectedShowtime){
        this.selectedSeats = selectedSeats;
        this.selectedShowtime = selectedShowtime;
    }


    /**
     * This method helps to transform this view into active state
     */
    @Override
    public void activate() {
        super.setTitle(this.title);
        super.setViewContent(this.viewContent);
        super.activate();

        int ticketCount = 0;
        createdTickets = new ArrayList<>();
        int adultCount;
        int childCount;
        int seniorCitizenCount;

        while (true){
            while (true){
                ticketCount = 0;
                adultCount = 0;
                childCount = 0;
                seniorCitizenCount = 0;
                adultCount = FileInputOutputOperator.getUserInputInt("How many adults? ");
                ticketCount += adultCount; if (ticketCount == selectedSeats.size()) break;
                childCount = FileInputOutputOperator.getUserInputInt("How many children? ");
                ticketCount += childCount; if (ticketCount == selectedSeats.size()) break;
                seniorCitizenCount = FileInputOutputOperator.getUserInputInt("How many senior citizen? ");
                ticketCount += seniorCitizenCount; if (ticketCount == selectedSeats.size()) break;

                System.out.printf("The total number of people (%d) does not tally with the number of selected seats (%d), please try again\n",ticketCount,selectedSeats.size());
                ticketCount = 0;
                continue;
            }

            if ((selectedShowtime.getMovie().getMovieCensorshipRating() == MovieRating.PG) || (selectedShowtime.getMovie().getMovieCensorshipRating() == MovieRating.G)){
                break;
            }else if (childCount == 0){
                break;
            }else{
                System.out.println("This movie is rated to be " + selectedShowtime.getMovie().getMovieCensorshipRating().displayName() + ", therefore children under 13 are not allowed.");
                ArrayList<String> optionChoices = new ArrayList<>();
                optionChoices.add("Input the seat count again: ");
                optionChoices.add("Go back to previous page to reselect seat");
                FileInputOutputOperator.printMenuOptions(optionChoices);
                int optionChoice = FileInputOutputOperator.getUserInputInt("Please choose one option",1,optionChoices.size());

                if (optionChoice == 2){
                	ViewOperator.popView();
                }
            }
        }


        for (Seat seat : this.selectedSeats){
            if (adultCount != 0){
                createdTickets.add(new AdultPriceTicket(seat,this.selectedShowtime));
                adultCount--;
            }

            else if (childCount != 0){
                createdTickets.add(new ChildrenPriceTicket(seat,this.selectedShowtime));
                childCount--;
            }

            else if (seniorCitizenCount != 0){
                createdTickets.add(new SeniorPriceTicket(seat,this.selectedShowtime));
                seniorCitizenCount--;
            }
        }

        System.out.println("\nHere is your order: \n");

        String returnString = "";

        returnString += "---------------------------------------------\n";
        returnString += "                      Order                  \n";
        returnString += "---------------------------------------------\n";

        returnString += "Tickets: \n\n";
        double totalPrice = 0.0;
        for (Ticket ticket : createdTickets){
            returnString += ticket.toString() + "\n";
            totalPrice += ticket.getPrice();
        }

        for (PublicHoliday publicHoliday : PriceCalculator.getInstance().getPublicHolidays()){
            if (publicHoliday.getDate().isEqual(selectedShowtime.getShowDatetime().toLocalDate())){
                returnString += "Public Holiday: " + publicHoliday.getName() + "\n";
                returnString += "Public Holiday Additional Charge Per Ticket: $" + String.format("%.2f",PriceCalculator.getInstance().getPublicHolidayIncrement());
                returnString += "\n";
                break;
            }
        }

        returnString += String.format("Total Price Before GST : $%.2f\n",totalPrice);
        double priceAfterGST = PricingOperator.applyGSTFactor(totalPrice);
        returnString += String.format("GST : $%.2f\n",priceAfterGST - totalPrice);
        returnString += String.format("Total Price: $%.2f",priceAfterGST);

        returnString += "\n---------------------------------------------\n";

        System.out.println(returnString);

        FileInputOutputOperator.printMenuOptions(this.options);

        int userInput = FileInputOutputOperator.getUserInputInt("Please input a choice",1,options.size());
        processUserInput(userInput);
    }

    /**
     * This method helps to manage execution of code based on the user put choice on the View options.
     * @param input the index of the options
     */
    @Override
    protected void processUserInput(int input) {

        if (input == 2){
        	ViewOperator.popView();
        }

        else if (input == 3){
        	ViewOperator.popViews(8);
        }

        else if (input == 1){
            String buyerName = FileInputOutputOperator.getUserInputString("Please input your name: ");
            String emailAddress = FileInputOutputOperator.getUserInputString("Please input your email address: ");
            Transaction order = new Transaction(createdTickets,buyerName,emailAddress);
            System.out.println("Bought Successfully");
            //save to database

            for (Ticket ticket : createdTickets){
                ticket.getShowtime().getSeatLayout().getSeat(ticket.getSeat().getRow(),ticket.getSeat().getCol()).bookSeat();
            }

            Cineplex cineplex = this.createdTickets.get(0).getShowtime().getCinema().getCineplex();
            DataOperator.updateCineplexValues(cineplex);
            DataOperator.saveNewOrder(order);
            System.out.println(order.toString());
            FileInputOutputOperator.getUserInputString("Please take a photo of this order to be used for verification\nEnter any key to go back to main menu");
            ViewOperator.popToBaseView();
        }

    }


}