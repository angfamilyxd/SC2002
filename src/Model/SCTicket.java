package Model;

/**
 * Represents a senior citizen ticket that can be bought by moviegoers.
 */
public class SCTicket extends Ticket {

	/**
	 * Creates a new senior citizen ticket with the given seat and showtime.
	 * @param seat This ticket's seat.
	 * @param showtime This ticket's showtime.
	 */
	public SCTicket(Seat seat, Showtime showtime) {
		super(seat, showtime);
	}

	/**
	 * Gets the discounted price factor for a senior citizen ticket.
	 */
	@Override
	public double getFractionalCostOutOfOriginal() {
		return PriceConfiguration.getInstance().getSeniorCitizenPercentageOff();
	}

	/**
	 * Changes this ticket's attributes to string format for display to moviegoer.
	 */
	@Override
	public String toString() {
		return "Ticket Type: Senior Citizen\n" + super.toString();
	}
}