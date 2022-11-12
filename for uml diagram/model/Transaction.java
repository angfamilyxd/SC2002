package model;
import operator.PricingOperator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents an order made by a moviegoer.
 * An order can be made up of more than one ticket.
 */
public class Transaction implements Serializable {
	/**
	 * The tickets that make up this order.
	 */
    private ArrayList<Ticket> tickets;
    
    /**
     * This order's price
     */
	private double price; //this price should be inclusive of gst
	
	/**
	 * The buyer name of this order.
	 */
	private String buyerName;
	
	/**
	 * The buyer email address of this order.
	 */
	private String buyerEmailAddress;
	
	/**
	 * The transaction ID of this order.
	 */
	private String TID;
	
	/**
	 * Creates a new order with the selected tickets, the buyer name and the buyer's email address.
	 * @param t The tickets of this order.
	 * @param buyerName The name of the buyer.
	 * @param buyerEmailAddress The email address of the buyer.
	 */
	public Transaction(ArrayList<Ticket> t,String buyerName,String buyerEmailAddress) {
		tickets = t;
		price = calculatePrice();
		this.buyerName = buyerName;
		this.buyerEmailAddress = buyerEmailAddress;
		TID = this.calculateTID();
	}

	/**
	 * Gets the name of the buyer of this order.
	 * @return This order's buyer name.
	 */
	public String getBuyerName() {
		return buyerName;
	}

	/**
	 * Calculates the total price of this order.
	 * @return This order's price.
	 */
	public double calculatePrice() {
		int i;
		double total =0;
		for (i = 0; i < tickets.size(); i++) {
			total += tickets.get(i).getPrice();
		}
		total = PricingOperator.applyGSTFactor(total);
		return total;
	}

	/**
	 * Gets the tickets that make up the order.
	 * @return This order's tickets
	 */
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	/**
	 * Finds the transaction ID of this order.
	 * @return This order's transaction ID.
	 */
	public String calculateTID(){
		String tidString = "";
		tidString += String.format("%03d", this.tickets.get(0).getShowtime().getCinema().getCode());
		LocalDateTime localDateTime = LocalDateTime.now();
		tidString += localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
		return tidString;
	}

	/**
	 * Gets the price of this order.
	 * @return this order's price.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Converts this order's attributes to string format for displaying to buyer.
	 */
	@Override
	public String toString() {

		String returnString = "";

		returnString += "---------------------------------------------\n";
		returnString += "                      Order                  \n";
		returnString += "---------------------------------------------\n";

		returnString += "Transaction ID: " + this.TID + "\n";
		returnString += "Buyer Name: " + this.buyerName + "\n";
		returnString += "Email Address: " + this.buyerEmailAddress + "\n\n";

		returnString += "Tickets: \n\n";

		for (Ticket ticket : this.tickets){
			returnString += ticket.toString() + "\n";
		}

		returnString += String.format("Total Price: $%.2f",this.getPrice()) + " (inclusive of GST)";

		returnString += "\n---------------------------------------------\n";

		return returnString;
	}

	/**
	 * Summarises this order in string format for buyer when they view booking history.
	 * @return This order's summary in a string.
	 */
	public String toSummarisedString(){
		String returnString = "";

		returnString += "Transaction ID: " + this.TID + "\n";
		returnString += "     Buyer Name: " + this.buyerName + "\n";
		returnString += "     Email Address: " + this.buyerEmailAddress + "\n\n";

		returnString += "     Tickets: ";

		for (Ticket ticket : this.tickets){
			returnString += ticket.getSeat().toString() + ("  ");
		}

		returnString += String.format("\n     Total Price: $%.2f",this.getPrice()) + " (inclusive of GST)";
		returnString += "\n";

		return returnString;
	}
}