package Model;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a cineplex containing cinemas.
 */
public class Cineplex implements Serializable {
	
	/**
	 * The name of this cineplex.
	 */
    private String cineplexName;
    
    /**
     * The cinemas that this cineplex contains.
     */
    private ArrayList<Cinema> cinemas;

    /**
     * Creates a new cineplex with the given name and cinemas.
     * @param cineplexName This cineplex's name.
     * @param cinemaList The cinemas that this cineplex contains.
     */
    public Cineplex(String cineplexName, ArrayList<Cinema> cinemaList){
        this.cineplexName = cineplexName;
        this.cinemas = new ArrayList<>();
    }
    
    /**
     * Gets the name of this cineplex.
     * @return this cineplex's name.
     */
    public String getName() {
		return cineplexName;
	}
	
    /**
     * Changes the name of this cineplex.
     * @param n This cineplex's new name.
     */
	public void setName(String n) {
		cineplexName = n;
	}
	
	/**
	 * Gets the list of cinemas that this cineplex contains.
	 * @return The cinemas that belong to this cineplex.
	 */
	public ArrayList<Cinema> getCinemas(){
		return cinemas;
	}

	/**
	 * Adds the cinema to the existing list of cinemas.
	 * @param cinema The new cinema to be added.
	 */
	public void addCinema(Cinema cinema){
    	this.cinemas.add(cinema);
	}


}