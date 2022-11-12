package model;

import operator.DataOperator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is responsible for setting the prices of tickets based on various factors.
 */
public class PriceCalculator implements Serializable {

	/**
	 * Public holidays are stored here as there is a price increment for tickets bought on public holidays.
	 */
    private ArrayList<PublicHoliday> publicHolidays = new ArrayList<>();

    /**
     * Base price of a ticket bought for a normal class cinema on a weekday for a normal type movie.
     */
    private double basePrice = 9;
    
    /**
     * Base price of a ticket bought for a platinum class cinema on a weekday for a normal type movie.
     */
    private double platinumBasePrice = 15;
    
    /**
     * Base price of a ticket bought for a gold class cinema on a weekday for a normal type movie.
     */
    private double goldBasePrice = 20;

    /**
     * Weekend premium
     */
    private double weekendIncrement = 3;
    
    /**
     * Public holiday premium
     */
    private double publicHolidayIncrement = 6;

    /**
     * Premium for a 3D movie.
     */
    private double threeDMovieIncrement = 3;
    
    /**
     * Premium for a blockbuster movie.
     */
    private double blockbusterMovieIncrement = 5;

    /**
     * Discount for a child ticket.
     */
    private double childPercentageOff = 0.5;
    
    /**
     * Discount for a senior citizen ticket.
     */
    private double seniorCitizenPercentageOff = 0.6; //this means 0.6 of the original price, meaning 40% discount

    /**
     * Added charge for GST.
     */
    private double gstPercentageIncrease = 0.07;

    /**
     * Instance of price configuration initialised to null if database of prices not created.
     */
    private static PriceCalculator single_instance = null;

    /**
     * Constructor is private as objects need not be created by other classes.
     */
    private PriceCalculator() { } //singleton, make constructor private

    /**
     * Gets price configuration from database.
     * @return price configuration
     */
    public static PriceCalculator getInstance()
    {
        if (single_instance == null){
            if (DataOperator.retrievePriceConfiguration() == null){
                single_instance = new PriceCalculator();
            }else{
                single_instance = DataOperator.retrievePriceConfiguration(); //loading price configuration stored in text file
            }
        }
        return single_instance;
    }


    /**
     * Sets the public holidays whose dates will have a price increment.
     * @param publicHolidays New list of public holidays.
     */
    public void setPublicHolidays(ArrayList<PublicHoliday> publicHolidays) {
        this.publicHolidays = publicHolidays;
        DataOperator.savePriceConfiguration(this);
    }

    /**
     * Gets the list of public holidays that are already set.
     * @return List of public holidays
     */
    public ArrayList<PublicHoliday> getPublicHolidays() {
        return publicHolidays;
    }

    /**
     * Gets the base price of a ticket of a normal cinema class.
     * @return Base price of ticket of a normal cinema class.
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Changes the base price of a ticket of a normal cinema class.
     * @param basePrice New base price of normal cinema ticket.
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
        DataOperator.savePriceConfiguration(this);
    }

    /**
     * Gets the base price of a ticket of a platinum cinema class.
     * @return Base price of a ticket of platinum cinema class.
     */
    public double getPlatinumBasePrice() {
        return platinumBasePrice;
    }

    /**
     * Changes the base price of a ticket of a platinum cinema class.
     * @param platinumBasePrice New base price of platinum cinema ticket.
     */
    public void setPlatinumBasePrice(double platinumBasePrice) {
        this.platinumBasePrice = platinumBasePrice;
        DataOperator.savePriceConfiguration(this);
    }
    
    /**
     * Gets the base price of a ticket of gold cinema class.
     * @return Base price of a ticket of gold cinema class.
     */
    public double getGoldBasePrice() {
        return goldBasePrice;
    }

    /**
     * Changes the base price of a ticket of a gold cinema class.
     * @param goldBasePrice New base price of gold cinema ticket.
     */
    public void setGoldBasePrice(double goldBasePrice) {
        this.goldBasePrice = goldBasePrice;
        DataOperator.savePriceConfiguration(this);
    }
    
    /**
     * Gets the weekend premium
     * @return weekend price increment.
     */
    public double getWeekendIncrement() {
        return weekendIncrement;
    }

    /**
     * Changes the weekend price premium.
     * @param weekendIncrement New weekend price increment.
     */
    public void setWeekendIncrement(double weekendIncrement) {
        this.weekendIncrement = weekendIncrement;
        DataOperator.savePriceConfiguration(this);
    }

    /**
     * Gets the public holiday price premium.
     * @return Public holiday price increment.
     */
    public double getPublicHolidayIncrement() {
        return publicHolidayIncrement;
    }

    /**
     * Changes the public holiday price premium
     * @param publicHolidayIncrement New public holiday price increment.
     */
    public void setPublicHolidayIncrement(double publicHolidayIncrement) {
        this.publicHolidayIncrement = publicHolidayIncrement;
        DataOperator.savePriceConfiguration(this);
    }

    /**
     * Gets the 3D movie price premium.
     * @return price increment of a 3D movie ticket.
     */
    public double getThreeDMovieIncrement() {
        return threeDMovieIncrement;
    }

    /**
     * Changes the 3D movie price premium.
     * @param threeDMovieIncrement New 3D movie price increment.
     */
    public void setThreeDMovieIncrement(double threeDMovieIncrement) {
        this.threeDMovieIncrement = threeDMovieIncrement;
        DataOperator.savePriceConfiguration(this);
    }

    /**
     * Gets the blockbuster movie price premium.
     * @return price increment of a blockbuster movie ticket.
     */
    public double getBlockbusterMovieIncrement() {
        return blockbusterMovieIncrement;
    }

    /**
     * Changes the blockbuster movie price premium.
     * @param blockbusterMovieIncrement New blockbuster movie price increment.
     */
    public void setBlockbusterMovieIncrement(double blockbusterMovieIncrement) {
        this.blockbusterMovieIncrement = blockbusterMovieIncrement;
        DataOperator.savePriceConfiguration(this);
    }

    /**
     * Gets the discounted price factor of a child ticket.
     * @return discounted child ticket factor.
     */
    public double getChildPercentageOff() {
        return childPercentageOff;
    }

    /**
     * Changes the discounted price factor of a child ticket.
     * @param childPercentageOff New discounted price factor of a child ticket.
     */
    public void setChildPercentageOff(double childPercentageOff) {
        this.childPercentageOff = childPercentageOff;
        DataOperator.savePriceConfiguration(this);
    }

    /**
     * Gets the discounted price factor of a senior citizen ticket.
     * @return discounted senior citizen ticket factor.
     */
    public double getSeniorCitizenPercentageOff() {
        return seniorCitizenPercentageOff;
    }

    /**
     * Changes the discounted price factor of a senior citizen ticket.
     * @param seniorCitizenPercentageOff New discounted price factor of a senior citizen ticket.
     */
    public void setSeniorCitizenPercentageOff(double seniorCitizenPercentageOff) {
        this.seniorCitizenPercentageOff = seniorCitizenPercentageOff;
        DataOperator.savePriceConfiguration(this);
    }

    /**
     * Gets the GST charge.
     * @return GST percentage charge.
     */
    public double getGstPercentageIncrease() {
        return gstPercentageIncrease;
    }

    /**
     * Changes the GST charge
     * @param gstPercentageIncrease New GST percentage charge.
     */
    public void setGstPercentageIncrease(double gstPercentageIncrease) {
        this.gstPercentageIncrease = gstPercentageIncrease;
        DataOperator.savePriceConfiguration(this);
    }
}