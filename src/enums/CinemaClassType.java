package enums;
import java.io.Serializable;
import model.*;


public enum CinemaClassType implements Serializable {
	
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
    CinemaClassType(String className) {
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
				return PriceCalculator.getInstance().getBasePrice();
			case PLATINUM:
				return PriceCalculator.getInstance().getPlatinumBasePrice();
			case GOLD:
				return PriceCalculator.getInstance().getGoldBasePrice();
			default:
				return PriceCalculator.getInstance().getBasePrice();
		}
	}
}