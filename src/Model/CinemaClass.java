package Model;
import java.io.Serializable;
/**
 * enumerates the different cinema classes.
 */
public enum CinemaClass implements Serializable {
		NORMAL("Normal"),
		GOLD("Gold"),
		PLATINUM("Platinum");

		/**
		 * The class name of a cinema.
		 */
	    private String className;

	    /**
	     * Constant classes that a cinema can be of.
	     * @param className Name of class.
	     */
	    CinemaClass(String className) {
	        this.className = className;
	    }
	    
	    /**
	     * Gets the class name of a cinema.
	     * @return
	     */
	    public String getClassName() { return className; }

	    /**
	     * Gets the base price of a ticket for a cinema of its respective class.
	     * @return the base price of a ticket for a cinema.
	     */
	    public double getBasePrice(){
			switch (this){
				case NORMAL:
					return PriceConfiguration.getInstance().getBasePrice();
				case PLATINUM:
					return PriceConfiguration.getInstance().getPlatinumBasePrice();
				case GOLD:
					return PriceConfiguration.getInstance().getGoldBasePrice();
				default:
					return PriceConfiguration.getInstance().getBasePrice();
			}
		}
}