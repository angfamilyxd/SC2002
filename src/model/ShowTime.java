package model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import enums.*;

/**
 * Represents a showtime for a movie screening in a cinema.
 */
public class ShowTime implements Serializable {

	/**
	 * The date and time of this showtime.
	 */
	private LocalDateTime showDatetime;

	/**
	 * The movie being screened at this showtime.
	 */
	private Movies movie;

	/**
	 * The cinema that this showtime belongs to.
	 */
	private Cinema cinema;

	/**
	 * The type of movie being screened at this showtime.
	 */
	private MovieType movieType;

	/**
	 * The seat layout of the cinema where this showtime is at.
	 */
	private SeatLayout seatLayout; //showtime needs a reference of seat layout to keep track of booked seats

	/**
	 * Creates a new showtime with the given date and time, movie, cinema and movie type.
	 * @param showDatetime This showtime's date and time.
	 * @param movie This showtime's movie.
	 * @param cinema This showtime's cinema.
	 * @param movieType This showtime's movie type.
	 */
	public ShowTime(LocalDateTime showDatetime, Movies movie, Cinema cinema,MovieType movieType) {
		this.showDatetime = showDatetime;
		this.movie = movie;
		this.cinema = cinema;
		this.movieType = movieType;
		this.seatLayout = this.cinema.getLayout().getCopy(); //get a copy of the seat layout, not reference!
	}

	/**
	 * Gets the date and time of this showtime.
	 * @return This showtime's date and time.
	 */
	public LocalDateTime getShowDatetime() {
		return showDatetime;
	}

	/**
	 * Changes the date and time of this showtime.
	 * @param showDatetime New date and time of this showtime.
	 */
	public void setShowDatetime(LocalDateTime showDatetime) {
		this.showDatetime = showDatetime;
	}

	/**
	 * Gets movie that is screened at this showtime.
	 * @return This showtime's movie.
	 */
	public Movies getMovie() {
		return movie;
	}

	/**
	 * Changes the movie being screened at this showtime.
	 * @param movie New movie at this showtime.
	 */
	public void setMovie(Movies movie) {
		this.movie = movie;
	}

	/**
	 * Gets the cinema where this showtime is at.
	 * @return this showtime's cinema
	 */
	public Cinema getCinema() {
		return cinema;
	}

	/**
	 * Gets the type of movie at this showtime.
	 * @return This showtime's movie type.
	 */
	public MovieType getMovieType() {
		return movieType;
	}

	/**
	 * Changes the cinema of this showtime.
	 * @param c New cinema of this showtime.
	 */
	public void setCinema(Cinema c) {
		cinema = c;
	}

	/**
	 * Gets the seat layout of this showtime.
	 * @return This showtime's seat layout.
	 */
	public SeatLayout getSeatLayout() {
		return seatLayout;
	}

	/**
	 * Changes this showtime's attributes to string format for storage in database.
	 */
	@Override
	public String toString(){
		String returnString = "";
		returnString += this.cinema.getCineplex().getName();

		if (this.getCinema().getCinemaClass() != CinemaClassType.NORMAL){
			returnString += "   [ " + this.getCinema().getCinemaClass().name() + " ]";
		}

		returnString += "\n       ";
		returnString += "Cinema Code: " + this.getCinema().getCode();
		returnString += "\n       ";

		if (this.movieType != MovieType.NORMAL){
			returnString += "Movie Type: " + this.movieType.typeName() + " ";
			returnString += "\n       ";
		}


		returnString += this.showDatetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm - EEEE ")) + "\n  ";
		return returnString;
	}


}