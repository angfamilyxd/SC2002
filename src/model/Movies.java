package model;
import java.io.Serializable;
import java.util.ArrayList;
import enums.*;


/**
 * Represents a movie that is screened in a cinema.
 */
public class Movies implements Serializable{

	/**
	 * The title of this movie.
	 */
	private String title;
	
	/**
	 * The director of this movie.
	 */
	private String director = "";
	
	/**
	 * The synopsis of this movie.
	 */
	private String synopsis = "";
	
	/**
	 * The rating of this movie based on reviewers.
	 */
	private double rating = -1.0;
	
	/**
	 * The reviews for this movie.
	 */
	private ArrayList<Review> reviews;
	
	/**
	 * The casts in this movie.
	 */
	private ArrayList<String> casts;
	
	/**
	 * The current showing status of this movie.
	 */
	private MovieStatus status;
	
	/**
	 * The maturity rating of this movie.
	 */
	private MovieRating movieCensorshipRating;

	/**
	 * Creates a new movie with the given title, status and maturity rating.
	 * @param title This movie's title.
	 * @param status This movie's status.
	 * @param movieCensorshipRating This movie's maturity rating.
	 */
	public Movies(String title,MovieStatus status,MovieRating movieCensorshipRating) {
		this.title = title;
		this.status = status;
		this.reviews = new ArrayList<Review>();
		this.casts = new ArrayList<String>();
		this.movieCensorshipRating = movieCensorshipRating;
	}
	
	/**
	 * Sets the director of this movie.
	 * @param directorName This movie's director.
	 */
	public void setDirector(String directorName) {
		this.director = directorName;
	}
	
	/**
	 * Sets the synopsis of this movie.
	 * @param synopsis This movie's synopsis.
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	/**
	 * Changes the current showing status of this movie.
	 * @param status This movie's new status.
	 */
	public void setStatus(MovieStatus status) {
		this.status = status;
	}

	/**
	 * Changes the title of this movie.
	 * @param title This movie's new title.
	 */
	public void setTitle(String title) {this.title = title;}

	/**
	 * Sets the casts of the movie.
	 * @param casts This movie's casts.
	 * 				Should have at least two casts.
	 */
	public void setCast(ArrayList<String> casts){this.casts = casts;}
	
	/**
	 * Adds a new review for this movie.
	 * @param review The new review for this movie.
	 */
	public void addReview(Review review) {
		this.reviews.add(0,review);
	}
	
	/**
	 * Adds another cast of this movie.
	 * @param cast The new cast to be added.
	 */
	public void addCast(String cast) {
		this.casts.add(cast);
	}
	
	/**
	 * Gets the title of this movie.
	 * @return This movie's title.
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Gets the synopsis of this movie.
	 * @return This movie's synopsis.
	 */
	public String getSynopsis() {
		return this.synopsis;
	}
	
	/**
	 * Gets the director of this movie.
	 * @return This movie's director.
	 */
	public String getDirector() {
		return this.director;
	}
	
	/**
	 * Gets the reviews for this movie.
	 * @return this movie's reviews.
	 */
	public ArrayList<Review> getReviews() {
		return this.reviews;
	}
	
	/**
	 * Gets the showing status of this movie.
	 * @return This movie's status.
	 */
	public MovieStatus getStatus() { 
		return this.status;
	}

	/**
	 * Gets the casts of this movie.
	 * @return This movie's casts.
	 */
	public ArrayList<String> getCasts(){ return this.casts;}

	/**
	 * Changes the maturity rating of this movie.
	 * @param movieCensorshipRating This movie's new maturity rating.
	 */
    public void setMovieCensorshipRating(MovieRating movieCensorshipRating) {
        this.movieCensorshipRating = movieCensorshipRating;
    }

    /**
     * Gets the maturity rating of this movie.
     * @return This movie's maturity rating.
     */
    public MovieRating getMovieCensorshipRating() {
        return movieCensorshipRating;
    }

    /**
     * Gets the rating of this movie.
     * @return This movie's rating. Rating should be between 1 and 5.
     */
    public double getRating() {
		if (this.reviews.size() == 0) {return -1;}
		int count = 0;
		double rating = 0;
		for (Review review : this.reviews) {
			rating += review.getRating();
			count++;
		}
		return (rating / count);
	}
	
    /**
     * Gets the rating of this movie in string format.
     * @return This movie's rating, NA if no ratings yet.
     */
	public String getRatingString() {
		if (this.getRating() == -1) {
			return "NA";
		}else {
			return String.format("%.1f",this.getRating());
		}
	}


	/**
	 * Converts attributes of this movie to string format for storing in database.
	 */
	@Override
	public String toString() {
		String returnString = "";
		returnString += "Title: " + this.title + " (" + this.movieCensorshipRating.displayName() + ")" + "\n\n";
		returnString += "Director: " + this.director + "\n\n";
		returnString += "Synopsis:\n" + this.synopsis + "\n\n";
		returnString += "Casts: ";
		for (int x = 0 ;x < this.casts.size() ; x++) {
			returnString += this.casts.get(x) + ((x == (casts.size() - 1) ? " " : ", "));
		}
		returnString += "\n\n" + "Status: " + this.status.displayName() + "\n\n";
		returnString += "Overall Rating: " + this.getRatingString() + "\n";
		return returnString;
	}
}