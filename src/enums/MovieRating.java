package enums;
import java.io.Serializable;
/**
 * enumerates the different maturity ratings of a movie.
 */
public enum MovieRating implements Serializable{

    G("G"),
    PG("PG"),
    PG13("PG13"),
    NC16("NC16"),
    M18("M18"),
    R21("R21");

	/**
	 * The maturity rating of a movie.
	 */
    private String displayName;

    /**
     * Constant maturity ratings that a movie can have.
     * @param displayName Name of maturity rating.
     */
    MovieRating(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the maturity rating.
     * @return The maturity rating.
     */
    public String displayName() { return displayName; }
}