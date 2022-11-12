package enums;
import java.io.Serializable;
import model.*;

/**
 * enumerates the different movie types.
 */
public enum MovieType implements Serializable {

    NORMAL("Normal"),
    THREED("3D"),
    BLOCKBUSTER("Blockbuster");

	/**
	 * The names of different movie types.
	 */
    private String typeName;

    /**
     * Constant movie types that a movie can have.
     * @param typeName The tpye of movie.
     */
    MovieType(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Gets the movie type.
     * @return The movie type.
     */
    public String typeName() { return typeName; }

    /**
     * Gets the increment in price for a special movie type.
     * @return the price increment of the movie type.
     */
    public double getPriceIncrement(){
        switch (this){
            case NORMAL:
                return 0;
            case THREED:
                return PriceCalculator.getInstance().getThreeDMovieIncrement();
            case BLOCKBUSTER:
                return PriceCalculator.getInstance().getBlockbusterMovieIncrement();
            default:
                return 0;
        }
    }
}