package Controller;


import Model.*;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * This class is responsible for calculating the ticket prices based on the various factors.
 */

public class PriceManager {

    //factors:
    //age
    //cinema class
    //day of week
    //type of movie

    /**
     * This methods helps to calculate the price of a movie Ticket based on the following factors: Age, CinemaClass, Day of Week, MovieType (eg 3D or Blockbuster)
     * @param ticket Ticket object to be used for price calculation.
     * @return Price of the ticket
     */

    public static double calculateTicketPrice(Ticket ticket){

        double finalPrice = 0.0;
        //the order of applying these factors matters!
        finalPrice = applyCinemaClassFactor(ticket.getShowtime().getCinema().getCinemaClass());

        finalPrice = applyDayOfWeekOrPHFactor(finalPrice,ticket.getShowtime().getShowDatetime().toLocalDate());
        finalPrice = applyMovieTypeFactor(finalPrice,ticket.getShowtime().getMovieType());

        finalPrice = applyAgeFactor(finalPrice,ticket);
        return finalPrice;
    }


    /**
     * This method returns the price based on the class of the cinema
     * @param cinemaClass The cinema class of the cinema in which the movie ticket belongs to
     * @return The base price based on each cinema class
     */
    private static double applyCinemaClassFactor(CinemaClass cinemaClass){
        return cinemaClass.getBasePrice();
    }

    /**
     * This method modifies the price by applying increment to the ticket price based on the day of week or whether is a public holiday
     * @param price The price
     * @param date The date of the showtime that is linked with the ticket bought
     * @return The new price of the ticket after the increment
     */
    private static double applyDayOfWeekOrPHFactor(double price, LocalDate date){

        //first check whether is in public holiday
        for (PublicHoliday ph:PriceConfiguration.getInstance().getPublicHolidays()){
            if (ph.getDate().isEqual(date)){
                return price + PriceConfiguration.getInstance().getPublicHolidayIncrement();
            }
        }

        //if not in public holiday, check if is in weekend
        if ((date.getDayOfWeek() == DayOfWeek.SATURDAY) || (date.getDayOfWeek() == DayOfWeek.SUNDAY)){
            return price + PriceConfiguration.getInstance().getWeekendIncrement();
        }

        return price;
    }

    /**
     * This method modifies the price by applying increment to the ticket price based on the movie type, such as 3D movie or blockbuster movie
     * @param price The price
     * @param movieType The type of the movie that is linked to the ticket bought
     * @return The new price of the ticket after the increment
     */

    private static double applyMovieTypeFactor(double price, MovieType movieType){
        return price + movieType.getPriceIncrement();
    }


    /**
     * This method modifies the price by applying percentage discounts to the price based on the age group
     * @param price The price
     * @param ticket The ticket type, such as an adult ticket or a senior citizen ticket
     * @return The new price of the ticket after the increment
     */

    private static double applyAgeFactor(double price,Ticket ticket){
        return price * ticket.getFractionalCostOutOfOriginal();
    }


    /**
     * Given a price, this method returns the price value after applying GST increment.
     * This is used for calculation of the final price of an order/transaction, not the individual tickets.
     * @param price Price before GST
     * @return Price after GST
     */
    public static double applyGSTFactor(double price){
        return (price + price * PriceConfiguration.getInstance().getGstPercentageIncrease());
    }
}