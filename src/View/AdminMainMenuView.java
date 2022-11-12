package View;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import Controller.DatabaseManager;
import Controller.IOManager;
import Controller.ViewsManager;
import Model.*;


/**
 * This View is the main menu for the admin module
 */

public class AdminMainMenuView extends BaseView {

	/**
	 * This is the options for the View
	 */
	private ArrayList<String> options = new ArrayList<>(Arrays.asList(
			"Add New Movie",
			"Modify Movie Information and Showtime",
			"Configure System Settings",
			"List the Top 5 Movies",
			"Create Another Staff Account",
			"List All Staff",
			"return to the previous page"
	));

	/**
	 * This is the title for the View
	 */
	private String title = "Admin Main Menu";

	/**
	 * This is the content for the View
	 */
	private String viewContent = "Welcome! Please select one of the options below: ";
	private boolean isAuthorised = false;


	/**
	 * This method transforms the View into active state
	 */
	@Override
	public void activate() {
		
		super.setOptions(this.options);
		super.setTitle(this.title);
		super.setViewContent(this.viewContent);

		if (isAuthorised){
			super.activate();
			int userInput = IOManager.getUserInputInt("Please input a choice",1,options.size());
			processUserInput(userInput);
		}

		else if (this.authorisedUser()){
			super.activate();
			isAuthorised = true;
			int userInput = IOManager.getUserInputInt("Please input a choice",1,options.size());
			processUserInput(userInput);
		}

		else{
			System.out.println("Credentials are not correct");
			ViewsManager.popView();
		}


	}

	/**
	 * This method helps to manage execution of code based on the user put choice on the View options.
	 * @param input the index of the options
	 */
	@Override
	protected void processUserInput(int input) {
		if (input == options.size()) {
			ViewsManager.popView();
		}else if (input == 1) {
			ViewsManager.pushView(new AdminCreateNewMovieView());
		}else if (input == 2) {
			ViewsManager.pushView(new AdminMovieBrowseOptionsView());
		}

		else if (input == 6){
			for (AdminUser adminUser : DatabaseManager.retrieveAllAdminUsers()){
				System.out.println("     " + adminUser.getUsername());
			}
			System.out.println("\n");
			IOManager.getUserInputString("Press any key twice to go back");
			this.activate();
		}

		else if (input == 5){
			String userInputUsername = IOManager.getUserInputString("Please input your new username: ");
			String userInputPassword = IOManager.getUserInputString("Please input your new password");
			boolean alreadyIn = false;
			for (AdminUser adminUser:DatabaseManager.retrieveAllAdminUsers()){
				if (adminUser.getUsername().toLowerCase().equals((new AdminUser(userInputUsername,userInputPassword)).getUsername().toLowerCase())){
					alreadyIn = true;
					System.out.println("User already exists");
				}
			}

			if (!alreadyIn){
				DatabaseManager.saveNewAdminUser(new AdminUser(userInputUsername,userInputPassword));
				System.out.println("Saved!");
			}

			this.activate();
		}

		else if (input == 4){
			ArrayList<String> userChoices = new ArrayList<>();
			userChoices.add("Top 5 by ticket sales");
			userChoices.add("Top 5 by reviewers' ratings");
			IOManager.printMenuOptions(userChoices);

			int userChoice = IOManager.getUserInputInt("Please select one of the options",1,userChoices.size());

			if (userChoice == 1){

				ArrayList<Order> orders = DatabaseManager.retrieveAllOrders();
				ArrayList<Movie> movies = DatabaseManager.retrieveAllMovies();
				ArrayList<Movie> resultMovies = new ArrayList<>();

				ArrayList<Integer> resultSales = new ArrayList<>();

				for (Movie movie: movies){
					int sales = 0;
					for (Order order: orders){
						for (Ticket ticket:order.getTickets()){
							if (ticket.getShowtime().getMovie().getTitle().equals(movie.getTitle())){
								sales += 1;
							}
						}
					}
					resultSales.add(sales);
				}

				for (int x = 0 ; x < 5 ;x++){
					int max = Collections.max(resultSales);
					if (max == 0){break;}
					int index = resultSales.indexOf(max);
					resultMovies.add(movies.get(index));
					resultSales.set(index,-1);
				}

				ViewsManager.pushView(new AdminListTop5MoviesView(resultMovies, AdminListTop5MoviesView.Top5MoviesOption.TICKETSALES));



			}else{
				ArrayList<Movie> movies = DatabaseManager.retrieveAllMovies();
				movies= (ArrayList<Movie>) movies.stream().filter(movie -> movie.getRating() != -1.0).collect(Collectors.toList());
				movies= (ArrayList<Movie>) movies.stream().filter(movie -> (movie.getStatus() == MovieStatus.PREVIEW) || (movie.getStatus() == MovieStatus.NOWSHOWING)).collect(Collectors.toList());
				Collections.sort(movies, Comparator.comparing(Movie::getRating));
				Collections.reverse(movies);
				movies = new ArrayList<>(movies.subList((movies.size() > 5 ? movies.size() - 5 : 0),movies.size()));
				ViewsManager.pushView(new AdminListTop5MoviesView(movies, AdminListTop5MoviesView.Top5MoviesOption.RATINGS));
			}
		}else if (input == 3){
			ViewsManager.pushView(new AdminSystemSettingsView());
		}
	}

	/**
	 * This method is responsible for getting username and password input, and return true if the user is authorised
	 * @return Returns true if the user is authorised, else returns false
	 */
	private boolean authorisedUser(){
		String username = IOManager.getUserInputString("Username: ");
		String password = IOManager.getUserInputString("Password: ");
		ArrayList<AdminUser> users = DatabaseManager.retrieveAllAdminUsers();
		boolean userIsAuthorised = false;

		for (AdminUser user: users){
			if (user.equals(new AdminUser(username,password))){
				userIsAuthorised = true;
				break;
			}
		}

		if (!userIsAuthorised){
			return false;
		}

		return true;
	}

}