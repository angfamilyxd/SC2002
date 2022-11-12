package Model;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents the seat layout within a cinema.
 */
public class SeatLayout implements Serializable {
	
	/**
	 * The number of rows of seats in this seat layout.
	 */
    private int numOfRows;
    
    /**
     * The number of columns of seats in this seat layout.
     */
	private int numOfColumns;
	
	/**
	 * The seats in an existing row and column that do not exist in the layout. 
	 */
	private Seat[] nonExistentSeats;
	
	/**
	 * The seats that exist in the layout.
	 */
	private Seat[][] seats;

	/**
	 * Creates a new seat layout with the given number of rows and columns, 
	 * removing any non existent seats from the layout.
	 * @param rows Number of rows in this seat layout.
	 * @param cols NUmber of columns in this seat layout.
	 * @param nonExistentSeats Seats that do not exist in this seat layout.
	 */
	public SeatLayout(int rows, int cols,Seat[] nonExistentSeats){
		numOfRows = rows;
		numOfColumns = cols;
		this.nonExistentSeats = nonExistentSeats;
		seats = new Seat[rows][cols];
		initialiseSeats();
	}

	/**
	 * Gets the number of columns in this seat layout.
	 * @return Number of columns in this seat layout.
	 */
	public int getNumOfColumns() {
		return numOfColumns;
	}

	/**
	 * Gets the number of rows in this seat layout.
	 * @return Number of rows in this seat layout.
	 */
	public int getNumOfRows() {
		return numOfRows;
	}

	/**
	 * Creates the seats within the seat layout.
	 */
	public void initialiseSeats() {
		for (int row = 0 ; row < numOfRows ; row++){
			for (int col = 0 ; col < numOfColumns ; col++){
				if (seats[row][col] == null) {
					seats[row][col] = new Seat(row,col);
				}
			}
		}

		for (Seat nonExistentSeat:this.nonExistentSeats){
			seats[nonExistentSeat.getRow()][nonExistentSeat.getCol()] = null;
		}

	}


//	outsiders count the row and column from 1 onwards
//	public void bookSeat(int row,int col){
//		if (this.seats[row-1][col-1] == null){
//			System.out.println("This seat is not among the choices");
//			return;
//		}
//		this.seats[row-1][col-1].bookSeat();
//	}

	/**
	 * Gets a seat at the given row and column.
	 * @param row Row of seat in this seat layout.
	 * @param col Column of seat in this seat layout.
	 * @return The specified seat in this layout.
	 */
	public Seat getSeat(int row,int col){
		if (this.seats[row][col] == null){
			return null;
		}
		return this.seats[row][col];
	}

	/**
	 * Gets a copy of this seat layout.
	 * @return This seat layout
	 */
	public SeatLayout getCopy(){
		return new SeatLayout(this.numOfRows,this.numOfColumns,this.nonExistentSeats);
	}

	/**
	 * Prints the seat layout for moviegoers to visualise.
	 */
	public void printSeatLayout(){

		int screenWidth = numOfColumns * 5 + "a a                  ".length();
		for (int x = 0 ; x < screenWidth ; x++){
			System.out.printf("-");
		}

		System.out.printf("\n");

		for (int x2 = 0 ; x2 < ((screenWidth - "Screen".length())/2) ; x2++){
			System.out.printf(" ");
		}

		System.out.printf("Screen\n");

		for (int x = 0 ; x < screenWidth ; x++){
			System.out.printf("-");
		}

		System.out.printf("\n");
		System.out.printf("       ");

		for (int z = 0 ; z < numOfColumns ; z++){
			if (z == (numOfColumns / 2)){
				System.out.printf("       ");
			}
				if ((z+1) < 10){
					System.out.printf("  %d  ",z+1);
				}else{
					System.out.printf("  %d ",z+1);
				}



		}

		System.out.printf("\n");

		Character rowChar = 'a';
		for (int row = 0 ; row < numOfRows ; row++){
			System.out.printf(rowChar + "      ");
			for (int col = 0 ; col < numOfColumns ; col++){

				if (col == (numOfColumns / 2)){
					System.out.printf("       ");
				}

				if (seats[row][col] != null) {
					if (seats[row][col].isBooked()){
						System.out.printf("[ * ]");
					}else{
						System.out.printf("[   ]");
					}
				}else{
					System.out.printf("     ");
				}
			}
			System.out.printf("      " + rowChar);
			System.out.printf("\n");
			rowChar++;
		}
	}
}