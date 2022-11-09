package model;

import java.io.Serializable;

/**
 * Represents a seat in the cinema.
 */
public class Seat implements Serializable {
	
	/**
	 * The row of this seat.
	 */
	private int row;
	
	/**
	 * The column of this seat.
	 */
	private int col;
	
	/**
	 * The booking status of this seat.
	 */
	private boolean booked;
	
	/**
	 * Creates a new seat with the given row and column
	 * @param r This seat's row.
	 * @param c This seat's column
	 */
	public Seat(int r, int c) {
		row = r;
		col = c;
	}
	
	/**
	 * Gets the row of this seat.
	 * @return This seat's row.
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Gets the column of this seat.
	 * @return This seat's column.
	 */
	public int getCol(){
		return col;
	}
	
	/**
	 * Gets the booking status of this seat.
	 * @return True if this seat is booked.
	 */
	public boolean isBooked() {
		return booked;
	}
	
	/**
	 * Changes the booking status of this seat to true.
	 */
	public void bookSeat() {
		booked = true;
	}

	/**
	 * Changes the booking status of this seat to false.
	 */
	public void unbookSeat() {booked = false;}

	/**
	 * Changes this seat's attributes to string format for storage in database.
	 */
	@Override
	public String toString(){
		char row = 'A';
		row = (char)(row + this.row);
		String returnString = String.valueOf(row) + String.valueOf(this.col + 1);
		return returnString;
	}
}