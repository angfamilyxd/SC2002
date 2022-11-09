package enums;

import java.io.Serializable;

/**
 * enumerates the different showing statuses of a movie.
 */
public enum MovieStatus implements Serializable{
	
	COMINGSOON("Coming Soon"),
    PREVIEW("Preview"),
	ENDED("Ended"),
	NOWSHOWING("Now Showing");

	/**
	 * The showing status of a movie.
	 */
    private String displayName;

    /**
     * Constant showing statuses that a movie can have.
     * @param displayName The movie's status
     */
    MovieStatus(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the showing status.
     * @return The showing status.
     */
    public String displayName() { return displayName; }
}