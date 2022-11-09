package model;

import operator.PricingOperator;
import java.io.Serializable;

/**
 * Represents a ticket bought by moviegoers.
 */
public abstract class Ticket implements Serializable {

	/**
	 * The discount of this ticket depending on its type
	 */
	private double fractionalCostOutOfOriginal;
	
	/**
	 * The price of this ticket.
	 */
	private double price;
	
	/**
	 * The seat this ticket buys.
	 */
	private Seat seat;
	
	/**
	 * The showtime of the movie for this ticket.
	 */
	private ShowTime showtime;

	/**
	 * Abstract class constructor of a ticket for the given seat and showtime.
	 * @param seat This ticket's seat.
	 * @param showtime This ticket's showtime.
	 */
	public Ticket(Seat seat, ShowTime showtime) {
		this.seat = seat;
		this.showtime = showtime;
		price = this.calculateTicketPrice();
	}

	/**
	 * Abstract method that gets the discount depending on type of ticket. 
	 * @return
	 */
	public abstract double getFractionalCostOutOfOriginal(); //subclass must configure the percentage discount

	/**
	 * Calculates the ticket price depending on the various factors - cinema class, day of week, movie type etc.
	 * @return This ticket's price.
	 */
	private double calculateTicketPrice(){
		return PricingOperator.calculateTicketPrice(this);
	}
	
	/**
	 * Gets the seat that this ticket buys.
	 * @return This ticket's seat.
	 */
	public Seat getSeat() {
		return seat;
	}
	
	/**
	 * Changes the seat for this ticket.
	 * @param s New seat for this ticket.
	 */
	public void setSeat(Seat s) {
		seat = s;
	}
	
	/**
	 * Gets the showtime for this ticket.
	 * @return this ticket's showtime
	 */
	public ShowTime getShowtime() {
		return showtime;
	}
	
	/**
	 * Gets the price of this ticket.
	 * @return This ticket's price.
	 */
	public double getPrice() {
		return price;
	}
	
	//
	/**
	 * Changes the price of this ticket.
	 * @param p New price of this ticket.
	 */
	public void setPrice(double p) {
		price = p;
	}

	/**
	 * Changes this ticket's attributes to string format for display to moviegoer.
	 */
	@Override
	public String toString(){
		String returnString = "";
		returnString += "Seat ID: "+this.getSeat().toString() + "\n";
		returnString += "Price: $" + String.format("%.2f",this.getPrice()) + "\n";
		returnString += "Showtime: \n       " + this.getShowtime().toString();
		return  returnString;
	}
	
	
}