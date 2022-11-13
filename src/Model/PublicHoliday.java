package Model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a public holiday for price increments.
 */
public class PublicHoliday implements Serializable {
	
	/**
	 * The name of this public holiday.
	 */
    private String name;
    
    /**
     * The date of this public holiday.
     */
    private LocalDate date;

    /**
     * Creates a new public holiday with the given name and date.
     * @param name This public holiday's name.
     * @param date This public holiday's date.
     */
    public PublicHoliday(String name, LocalDate date){
        this.name = name;
        this.date = date;
    }

    /**
     * Gets the name of this public holiday.
     * @return This public holiday's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the date of this public holiday.
     * @return this public holiday's date.
     */
    public LocalDate getDate() {
        return date;
    }
}