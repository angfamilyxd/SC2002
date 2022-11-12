package operator;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.*;


/**
 * This class is responsible for interacting with the database (text files).
 * This class manages the storing and retrieval of data from the various text files.
 */

public class DataOperator {


	/**
	 * This method saves a new movie into the existing database (Movies.txt). If the text file is not found, it will create a new text file containing this movie
	 * @param movie The movie object to be saved to database
	 */

	public static void saveMovieToDataBase(Movies movie){
		try {
			ArrayList<Movies> movies = retrieveAllMovies();
			movies.add(movie);
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("datafile/Movies.txt"));
			output.writeObject(movies);
		} catch (Exception e) {
			System.out.println("Error occured while trying to save to database, try again later");
		}
	}

	/**
	 * This method overwrites the existing movie database (Movies.txt) with the new ArrayList of movies in the parameter
	 * @param movies  The movies to be used to overwrite and replace the existing movies in the movies database
	 */

	public static void overwriteMoviesDatabaseWithMovies(ArrayList<Movies> movies){
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("datafile/Movies.txt"));
			output.writeObject(movies);
		} catch (Exception e) {
			System.out.println("Error occured while trying to save to database, try again later");
		}
	}


	/**
	 * This method retrieves all the movies stored in the database (Movies.txt). It will return an empty ArrayList of movies if the database (Movies.txt) is not found
	 * @return
	 */
	
	public static ArrayList<Movies> retrieveAllMovies(){
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("datafile/Movies.txt"));
			ArrayList<Movies> movies = new ArrayList<Movies>();
		    movies = (ArrayList<Movies>) input.readObject();
			return movies;
		}

		catch (FileNotFoundException ne){
			return (new ArrayList<Movies>());
		}

		catch (Exception e) {
			System.out.println("Error occured while trying to retrieve movies from database, try again later");
			return (new ArrayList<Movies>());
		}
	}

	/**
	 * This method helps to delete an existing movie with the same name as the parameter. If no movies are found with that name, no movies will be deleted
	 * @param movieName The name of the movie
	 */
	
	public static void deleteMovieByName(String movieName){
		try{
			ArrayList<Movies> originalMovies = retrieveAllMovies();
			ArrayList<Movies> filteredMovies= (ArrayList<Movies>) originalMovies.stream().filter(movie -> !movie.getTitle().equals(movieName)).collect(Collectors.toList());
			overwriteMoviesDatabaseWithMovies(filteredMovies);
		}catch(Exception e){
			System.out.println("Error occured while trying to delete from database, try again later");
		}
	}


	/**
	 * When we want to modify the details of an existing movie in the database (Movies.txt), we can pass the modified movie object as parameter and
	 * the existing movie in the database will be updated.
	 *
	 * Note that if no existing movies in the database that has the same name as the title name of the movie object in the parameter, the
	 * movie object will be inserted into the database as a new movie
	 *
	 * @param movie The movie object with updated value
	 */

	public static void modifyMovieWithNewValues(Movies movie) {
		try{
			deleteMovieByName(movie.getTitle());
			saveMovieToDataBase(movie);
		}catch(Exception e){
			System.out.println("Error occured while trying to save to database, try again later");
		}
	}





	//admin user

	/**
	 * This method helps to add new admin user into the admin users database (AdminUsers.txt).
	 * Note that if the database already contains another admin user with the same name (case insensitive), this admin user
	 * will not be saved.
	 * @param adminUser The new admin user to be inserted into the database
	 */

	public static void saveNewAdminUser(Staff adminUser){
		try {
			ArrayList<Staff> adminUsers = retrieveAllAdminUsers();

			boolean userIsAlreadyIn = false;

			for (Staff adminUserObj:adminUsers){
				if (adminUserObj.getUsername().toLowerCase().equals(adminUser.getUsername().toLowerCase())){
					System.out.println("User already in the system");
					userIsAlreadyIn = true;
					break;
				}
			}
			if (!userIsAlreadyIn) {
				adminUsers.add(adminUser);
				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("datafile/AdminUsers.txt"));
				output.writeObject(adminUsers);
			}
		} catch (Exception e) {
			System.out.println("Error occured while trying to save admin user to database, try again later");
		}
	}


	/**
	 * This method returns the ArrayList of all existing admin users in the database (AdminUsers.txt)
	 * @return List of existing admin users in the database
	 */

	public static ArrayList<Staff> retrieveAllAdminUsers(){
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("datafile/AdminUsers.txt"));
			ArrayList<Staff> adminUsers = new ArrayList<Staff>();
			adminUsers = (ArrayList<Staff>) input.readObject();
			return adminUsers;

		} catch (FileNotFoundException fileE){
			return (new ArrayList<Staff>());
		}

		catch (Exception e) {
			System.out.println("Error occured while trying to retrieve admin users from database, try again later");
			return (new ArrayList<Staff>());
		}
	}




	/**
	 * This method overwrites the database (Cineplexes.txt) with new cineplexes in the parameter
	 * @param cineplexes  The cineplexes to be used to overwrite the database
	 */

	public static void overwriteCineplexDatabaseWithNewCineplexes(ArrayList<Cineplex> cineplexes){
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("datafile/Cineplexes.txt"));
			output.writeObject(cineplexes);

		} catch (Exception e) {
			System.out.println("Error occured while trying to save cineplexes to database, try again later");
		}
	}

	/**
	 * This method returns the list of cineplexes in the database (Cineplexes.txt)
	 * @return An array of the cineplexes
	 */
	public static ArrayList<Cineplex> retrieveAllCineplexes(){
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("datafile/Cineplexes.txt"));
			ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
			cineplexes = (ArrayList<Cineplex>) input.readObject();
			return cineplexes;

		} catch (FileNotFoundException fileE){
			return (new ArrayList<Cineplex>());
		}

		catch (Exception e) {
			System.out.println("Error occured while trying to retrieve cineplexes from database, try again later");
			return (new ArrayList<Cineplex>());
		}
	}


	/**
	 * This method helps to update existing cineplex in the database with new state or values.
	 * This method works by searching the database and finding a cineplex with matching name,
	 * and replacing that cineplex with the new cineplex object in the parameter
	 *
	 * @param cineplex The cineplex with updated values
	 */
	public static void updateCineplexValues(Cineplex cineplex){
		ArrayList<Cineplex> cineplexesWithNewValue = new ArrayList<>();
		for (Cineplex cineplex1 : retrieveAllCineplexes()){
			if (cineplex1.getName().equals(cineplex.getName())){
				cineplexesWithNewValue.add(cineplex);
			}else{
				cineplexesWithNewValue.add(cineplex1);
			}
		}
		overwriteCineplexDatabaseWithNewCineplexes(cineplexesWithNewValue);
	}


	/**
	 * This method overwrites the existing orders database (Orders.txt) with the new orders array in the parameter
	 * @param orders The list of orders to be used for overwriting the database
	 */

	public static void overwriteOrdersDatabaseWithNewOrders(ArrayList<Transaction> orders){
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("datafile/Transactions.txt"));
			output.writeObject(orders);

		} catch (Exception e) {
			System.out.println("Error occured while trying to save orders to database, try again later");
		}
	}


	/**
	 * This method returns the existing orders in the database (Orders.txt)
	 * @return  Existing orders in the database
	 */
	public static ArrayList<Transaction> retrieveAllOrders(){
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("datafile/Transactions.txt"));
			ArrayList<Transaction> orders = new ArrayList<Transaction>();
			orders = (ArrayList<Transaction>) input.readObject();
			return orders;

		} catch (FileNotFoundException fileE){
			return (new ArrayList<Transaction>());
		}

		catch (Exception e) {
			System.out.println("Error occured while trying to retrieve orders from database, try again later");
			return (new ArrayList<Transaction>());
		}
	}


	/**
	 * This method helps to save new order object into the existing database (Orders.txt)
	 * @param order Order object to be inserted into the database
	 */

	public static void saveNewOrder(Transaction order){
		ArrayList<Transaction> orders = new ArrayList<>();
		orders = retrieveAllOrders();
		orders.add(order);
		overwriteOrdersDatabaseWithNewOrders(orders);
	}


	/**
	 * This method save the single instance of PriceConfiguration object into the database (PriceConfiguration.txt)
	 * @param priceConfiguration The PriceConfiguration object to be stored
	 */

	public static void savePriceConfiguration(PriceCalculator priceConfiguration){
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("datafile/PriceConfiguration.txt"));
			output.writeObject(priceConfiguration);

		} catch (Exception e) {
			System.out.println("Error occured while trying to save orders to database, try again later");
		}
	}


	/**
	 * This method helps to retrieve PriceConfiguration object from the database (PriceConfiguration.txt)
	 * @return The PriceConfiguration object from the database
	 */

	public static PriceCalculator retrievePriceConfiguration(){
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("datafile/PriceConfiguration.txt"));
			PriceCalculator priceConfiguration = (PriceCalculator) input.readObject();
			return priceConfiguration;

		} catch (FileNotFoundException fileE){
			return (null);
		}

		catch (Exception e) {
			System.out.println("Error occured while trying to retrieve orders from database, try again later");
			return (PriceCalculator.getInstance());
		}
	}

}