package View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import Controller.DatabaseManager;
import Controller.IOManager;
import Controller.ViewsManager;
import Model.Movie;
import Model.MovieStatus;
import Model.Order;
import Model.Ticket;

/**
 * This View class presents for admin the various options, each representing a method of listing ore searching movies
 */

public class AdminMovieBrowseOptionsView extends BaseMovieBrowseOptionsView {

	/**
	 * This method helps to manage execution of code based on the user put choice on the View options.
	 * @param input the index of the options
	 */
	protected void processUserInput(int input) {
		switch (input) {
			case 1:
				this.handleOptionSearchMovie();
				break;
			case 2:
				this.handleListCurrentShowingMovies();
				break;
			case 3:
				this.handleListPreviewMovies();
				break;
			case 4:
				this.handleListUpcomingMoviesOption();
				break;
			case 5:
				this.handleListPastMoviesOption();
				break;
			case 6:
				this.handleListAllMoviesOption();
				break;
			case 7:
				this.handleListCurrentTop5Movies();
				break;
			case 8:
				this.handleBackToPreviousView();
		}
	}


	/**
	 * This method handles the case when the admin chooses to return to the previous BaseView
	 */
	private void handleBackToPreviousView(){
		ViewsManager.popView();
	}


	/**
	 * This method handles the case when the admin chooses to search for movies
	 */
	private void handleOptionSearchMovie(){
		ArrayList<Movie> movies = new ArrayList<>();
		movies = DatabaseManager.retrieveAllMovies();
		String userInput = IOManager.getUserInputString("Please write the name of the movie: ");
		
		ArrayList<Movie> filteredMovieList= (ArrayList<Movie>) movies.stream().filter(movie -> movie.getTitle().matches("(?i).*" + userInput + ".*")).collect(Collectors.toList());
		ViewsManager.pushView(new AdminListMoviesView(filteredMovieList));
	}


	/**
	 * This method handles the case when the admin chooses to list only the preview movies
	 */
	private void handleListPreviewMovies(){
		ArrayList<Movie> movies = new ArrayList<>();
		movies = DatabaseManager.retrieveAllMovies();
		ArrayList<Movie> filteredMovieList= (ArrayList<Movie>) movies.stream().filter(movie -> movie.getStatus().equals(MovieStatus.PREVIEW)).collect(Collectors.toList());
		ViewsManager.pushView(new AdminListMoviesView(filteredMovieList));
	}

	/**
	 * This method handles the case when the admin chooses to list all the movies in the system including the movies that have ended
	 */
	private void handleListAllMoviesOption(){
		ArrayList<Movie> movies = new ArrayList<>();
		movies = DatabaseManager.retrieveAllMovies();
		ViewsManager.pushView(new AdminListMoviesView(movies));
	}


	/**
	 * This method handles the case when the admin chooses to list only movies that have ended
	 */
	private void handleListPastMoviesOption() {
		ArrayList<Movie> movies = new ArrayList<>();
		movies = DatabaseManager.retrieveAllMovies();
		ArrayList<Movie> filteredMovieList= (ArrayList<Movie>) movies.stream().filter(movie -> movie.getStatus().equals(MovieStatus.ENDED)).collect(Collectors.toList());
		ViewsManager.pushView(new AdminListMoviesView(filteredMovieList));
	}


	/**
	 * This method handles the case when the admin chooses to list only movies that are coming soon
	 */
	private void handleListUpcomingMoviesOption() {
		ArrayList<Movie> movies = new ArrayList<>();
		movies = DatabaseManager.retrieveAllMovies();
		ArrayList<Movie> filteredMovieList= (ArrayList<Movie>) movies.stream().filter(movie -> movie.getStatus().equals(MovieStatus.COMINGSOON)).collect(Collectors.toList());
		ViewsManager.pushView(new AdminListMoviesView(filteredMovieList));
	}


	/**
	 * This method handles the case when the admin chooses to list only movies that are currently showing
	 */
	private void handleListCurrentShowingMovies() {
		ArrayList<Movie> movies = new ArrayList<>();
		movies = DatabaseManager.retrieveAllMovies();
		ArrayList<Movie> filteredMovieList= (ArrayList<Movie>) movies.stream().filter(movie -> movie.getStatus().equals(MovieStatus.NOWSHOWING)).collect(Collectors.toList());
		ViewsManager.pushView(new AdminListMoviesView(filteredMovieList));
	}


	/**
	 * This method handles the case when the admin chooses to list the currently top 5 movies
	 */
	private void handleListCurrentTop5Movies() {
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
	}

}