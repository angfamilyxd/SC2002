package view;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import operator.DataOperator;
import operator.FileInputOutputOperator;
import operator.ViewOperator;
import model.*;
import enums.*;

/**
 * This View is the main menu for the admin module
 */

public class StaffMainView extends StartView {

	/**
	 * This is the options for the View
	 */
	private ArrayList<String> options = new ArrayList<>(Arrays.asList(
			"Create New Movie",
			"Update Movie Information or modify showtime",
			"Configure System Settings",
			"List Current Top 5 Movies",
			"Add Another Admin User to the System",
			"List all Admin Users exist on this System",
			"Back to Previous Page"
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
			int userInput = FileInputOutputOperator.getUserInputInt("Please input a choice",1,options.size());
			processUserInput(userInput);
		}

		else if (this.authorisedUser()){
			super.activate();
			isAuthorised = true;
			int userInput = FileInputOutputOperator.getUserInputInt("Please input a choice",1,options.size());
			processUserInput(userInput);
		}

		else{
			System.out.println("Credentials are not correct");
			ViewOperator.popView();
		}


	}

	/**
	 * This method helps to manage execution of code based on the user put choice on the View options.
	 * @param input the index of the options
	 */
	@Override
	protected void processUserInput(int input) {
		if (input == options.size()) {
			ViewOperator.popView();
		}else if (input == 1) {
			ViewOperator.pushView(new StaffAddMovieView());
		}else if (input == 2) {
			ViewOperator.pushView(new StaffSearchMovieView());
		}

		else if (input == 6){
			for (Staff adminUser : DataOperator.retrieveAllAdminUsers()){
				System.out.println("     " + adminUser.getUsername());
			}
			System.out.println("\n");
			FileInputOutputOperator.getUserInputString("Press any key twice to go back");
			this.activate();
		}

		else if (input == 5){
			String userInputUsername = FileInputOutputOperator.getUserInputString("Please input your new username: ");
			String userInputPassword = FileInputOutputOperator.getUserInputString("Please input your new password");
			boolean alreadyIn = false;
			for (Staff adminUser:DataOperator.retrieveAllAdminUsers()){
				if (adminUser.getUsername().toLowerCase().equals((new Staff(userInputUsername,userInputPassword)).getUsername().toLowerCase())){
					alreadyIn = true;
					System.out.println("User already exists");
				}
			}

			if (!alreadyIn){
				DataOperator.saveNewAdminUser(new Staff(userInputUsername,userInputPassword));
				System.out.println("Saved!");
			}

			this.activate();
		}

		else if (input == 4){
			ArrayList<String> userChoices = new ArrayList<>();
			userChoices.add("Top 5 by ticket sales");
			userChoices.add("Top 5 by reviewers' ratings");
			FileInputOutputOperator.printMenuOptions(userChoices);

			int userChoice = FileInputOutputOperator.getUserInputInt("Please select one of the options",1,userChoices.size());

			if (userChoice == 1){

				ArrayList<Transaction> orders = DataOperator.retrieveAllOrders();
				ArrayList<Movies> movies = DataOperator.retrieveAllMovies();
				ArrayList<Movies> resultMovies = new ArrayList<>();

				ArrayList<Integer> resultSales = new ArrayList<>();

				for (Movies movie: movies){
					int sales = 0;
					for (Transaction order: orders){
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

				ViewOperator.pushView(new StaffTop5MovieView(resultMovies, StaffTop5MovieView.Top5MoviesOption.TICKETSALES));



			}else{
				ArrayList<Movies> movies = DataOperator.retrieveAllMovies();
				movies= (ArrayList<Movies>) movies.stream().filter(movie -> movie.getRating() != -1.0).collect(Collectors.toList());
				movies= (ArrayList<Movies>) movies.stream().filter(movie -> (movie.getStatus() == MovieStatus.PREVIEW) || (movie.getStatus() == MovieStatus.NOWSHOWING)).collect(Collectors.toList());
				Collections.sort(movies, Comparator.comparing(Movies::getRating));
				Collections.reverse(movies);
				movies = new ArrayList<>(movies.subList((movies.size() > 5 ? movies.size() - 5 : 0),movies.size()));
				ViewOperator.pushView(new StaffTop5MovieView(movies, StaffTop5MovieView.Top5MoviesOption.RATINGS));
			}
		}else if (input == 3){
			ViewOperator.pushView(new StaffSystemSettingsView());
		}
	}

	/**
	 * This method is responsible for getting username and password input, and return true if the user is authorised
	 * @return Returns true if the user is authorised, else returns false
	 */
	private boolean authorisedUser(){
		String username = FileInputOutputOperator.getUserInputString("Please input your username: ");
		String password = FileInputOutputOperator.getUserInputString("Please input your password: ");
		ArrayList<Staff> users = DataOperator.retrieveAllAdminUsers();
		boolean userIsAuthorised = false;

		for (Staff user: users){
			if (user.equals(new Staff(username,password))){
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