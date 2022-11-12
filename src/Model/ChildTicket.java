package Model;

/**
 * Represents a child ticket that can be bought by moviegoers.
 */
public class ChildTicket extends Ticket {

	/**
	 * Creates a new child ticket with the given seat and showtime.
	 * @param seat This ticket's seat.
	 * @param showtime This ticket's showtime.
	 */
	public ChildTicket(Seat seat, Showtime showtime) {
		super(seat, showtime);
	}

	/**
	 * Gets the discounted price factor for a child ticket.
	 */
	@Override
	public double getFractionalCostOutOfOriginal() {
		return PriceConfiguration.getInstance().getChildPercentageOff();
	}

    /**
     * Changes this ticket's attributes to string format for display to moviegoer.
     */
	@Override
	public String toString() {
		return "Ticket Type: Child\n" + super.toString();
	}
}