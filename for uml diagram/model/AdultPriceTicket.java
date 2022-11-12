package model;


public class AdultPriceTicket extends Ticket {

	/**
	 * Creates a new adult ticket with the given seat and showtime.
	 * @param seat This ticket's seat.
	 * @param showtime This ticket's showtime.
	 */
    public AdultPriceTicket(Seat seat, ShowTime showtime) {
        super(seat, showtime);
    }

    /**
     * Gets the original price of ticket as adult tickets have no discount.
     */
    @Override
    public double getFractionalCostOutOfOriginal() {
        return 1;
    }

    /**
     * Changes this ticket's attributes to string format for display to moviegoer.
     */
    @Override
    public String toString() {
        return "Ticket Type: Adult\n" + super.toString();
    }
}