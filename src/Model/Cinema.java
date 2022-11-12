package Model;

import java.io.Serializable;

import java.util.ArrayList;


/**
 * Represents a cinema found within a cineplex.
 */
public class Cinema implements Serializable {
    
	/**
	 * The cineplex that this cinema belongs to
	 */
    private Cineplex cineplex;
    
    /**
     * The class of this cinema.
     */
	private CinemaClass cinemaClass;
	
	/**
	 * The code of this cinema, unique for every cinema found in each cineplex.
	 */
	private int code;
	
	/**
	 * The showtimes of movies screening in this cinema.
	 */
	private ArrayList<Showtime> showtimes = new ArrayList<>();
	
	/**
	 * The layout of seats found inside this cinema.
	 */
	private SeatLayout seatLayout;

	/**
	 * Creates a new cinema in the given cineplex, with the given class, code and seat layout.
	 * @param cineplex The cineplex that this cinema belongs to.
	 * @param cinemaClass The cinema's class.
	 * @param code This cinema's code.
	 * @param seatLayout This cinema's seat layout.
	 */
	public Cinema(Cineplex cineplex, CinemaClass cinemaClass, int code,SeatLayout seatLayout) {
		this.cineplex = cineplex;
		this.cinemaClass = cinemaClass;
		this.code = code;
		this.seatLayout = seatLayout;
	}
	
	/**
	 * Gets the class of this cinema.
	 * @return this cinema's class.
	 */
	public CinemaClass getCinemaClass() {
		return this.cinemaClass;
	}

	/**
	 * Gets the showtimes of movies to be screened in this cinema.
	 * @return This cinema's movie showtimes;
	 */
	public ArrayList<Showtime> getShowtimes() {
		return showtimes;
	}

	/**
	 * Adds a showtime to the array of showtimes for this cinema.
	 * @param showtime New showtime to be added.
	 */
	public void addShowTime(Showtime showtime){
		this.showtimes.add(showtime);
	}

	/**
	 * Gets the cineplex that this cinema is found in.
	 * @return this cinema's cineplex.
	 */
	public Cineplex getCineplex() {
		return cineplex;
	}

	/**
	 * Changes the class of this cinema.
	 * @param c New cinema class.
	 */
	public void setClass(CinemaClass c) {
		cinemaClass = c;
	}
	
	/**
	 * Gets the code of this cinema.
	 * @return This cinema's code.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Changes the code of this cinema.
	 * @param code New cinema code.
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Initialises all the showtimes of movies to be screened in this cinema.
	 * @param showtimes Array of this cinema's showtimes.
	 */
	public void setShowtimes(ArrayList<Showtime> showtimes) {
		this.showtimes = showtimes;
	}

	/**
	 * Gets the seat layout of this cinema.
	 * @return this cinema's seat layout.
	 */
	public SeatLayout getLayout() {
		return seatLayout;
	}


}