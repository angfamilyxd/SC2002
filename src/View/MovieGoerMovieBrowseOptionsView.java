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
 * This View class presents for movie-goer the various options, each representing a method of listing ore searching movies
 */

public class MovieGoerMovieBrowseOptionsView extends BaseMovieBrowseOptionsView {


	/**
	 * This method helps to manage execution of code based on the user put choice on the View options.
	 * @param input the index of the options
	 */
	@Override
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
	 * This method handles the case where the user wants to go back to the previous BaseView
	 */
	private void handleBackToPreviousView(){
		ViewsManager.popView();
	}

	/**
	 * This method handles the case where the user wants to search for movie by typing the movie name
	 */
	private void handleOptionSearchMovie(){
		ArrayList<Movie> movies = new ArrayList<>();
		movies = DatabaseManager.retrieveAllMovies();
		String userInput = IOManager.getUserInputString("Please write the name of the movie: ");
		
		ArrayList<Movie> filteredMovieList= (ArrayList<Movie>) movies.stream().filter(movie -> movie.getTitle().matches("(?i).*" + userInput + ".*")).collect(Collectors.toList());
		ViewsManager.pushView(new MovieGoerListMoviesView(filteredMovieList));
	}

	/**
	 * This method handles the case where the user wants to list only the movies that are in preview
	 */
	private void handleListPreviewMovies(){
		ArrayList<Movie> movies = new ArrayList<>();
		movies = DatabaseManager.retrieveAllMovies();
		ArrayList<Movie> filteredMovieList= (ArrayList<Movie>) movies.stream().filter(movie -> movie.getStatus().equals(MovieStatus.PREVIEW)).collect(Collectors.toList());
		ViewsManager.pushView(new MovieGoerListMoviesView(filteredMovieList));
	}


	/**
	 * This method handles the case where the user wants to list all movies
	 */
	private void handleListAllMoviesOption(){
		ArrayList<Movie> movies = new ArrayList<>();
		movies = DatabaseManager.retrieveAllMovies();
		ViewsManager.pushView(new MovieGoerListMoviesView(movies));
	}

	/**
	 * This method handles the case where the user wants to list only movies that have already ended
	 */
	private void handleListPastMoviesOption() {
		ArrayList<Movie> movies = new ArrayList<>();
		movies = DatabaseManager.retrieveAllMovies();
		ArrayList<Movie> filteredMovieList= (ArrayList<Movie>) movies.stream().filter(movie -> movie.getStatus().equals(MovieStatus.ENDED)).collect(Collectors.toList());
		ViewsManager.pushView(new MovieGoerListMoviesView(filteredMovieList));
	}

	/**
	 * This method handles the case where the user wants to list only movies which are coming soon
	 */
	private void handleListUpcomingMoviesOption() {
		ArrayList<Movie> movies = new ArrayList<>();
		movies = DatabaseManager.retrieveAllMovies();
		ArrayList<Movie> filteredMovieList= (ArrayList<Movie>) movies.stream().filter(movie -> movie.getStatus().equals(MovieStatus.COMINGSOON)).collect(Collectors.toList());
		ViewsManager.pushView(new MovieGoerListMoviesView(filteredMovieList));
	}

	/**
	 * This method handles the case where the user wants to list only movies which are currently showing
	 */
	private  void handleListCurrentShowingMovies(){
		ArrayList<Movie> movies = new ArrayList<>();
		movies = DatabaseManager.retrieveAllMovies();
		ArrayList<Movie> filteredMovieList= (ArrayList<Movie>) movies.stream().filter(movie -> movie.getStatus().equals(MovieStatus.NOWSHOWING)).collect(Collectors.toList());
		ViewsManager.pushView(new MovieGoerListMoviesView(filteredMovieList));
	}

	/**
	 * This method handles the case where the user wants to list currently top 5 movies, either by ticket sales or ratings
	 */
	private void handleListCurrentTop5Movies(){
		ArrayList<String> userChoices = new ArrayList<>();
		userChoices.add("By ticket sales");
		userChoices.add("By reviewers' ratings");
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

			ViewsManager.pushView(new MovieGoerListTop5MoviesView(resultMovies, MovieGoerListTop5MoviesView.Top5MoviesOption.TICKETSALES));


		}else{
			ArrayList<Movie> movies = DatabaseManager.retrieveAllMovies();
			movies= (ArrayList<Movie>) movies.stream().filter(movie -> movie.getRating() != -1.0).collect(Collectors.toList());
			movies= (ArrayList<Movie>) movies.stream().filter(movie -> (movie.getStatus() == MovieStatus.PREVIEW) || (movie.getStatus() == MovieStatus.NOWSHOWING)).collect(Collectors.toList());
			Collections.sort(movies, Comparator.comparing(Movie::getRating));
			Collections.reverse(movies);
			movies = new ArrayList<>(movies.subList((movies.size() > 5 ? movies.size() - 5 : 0),movies.size()));
			ViewsManager.pushView(new MovieGoerListTop5MoviesView(movies, MovieGoerListTop5MoviesView.Top5MoviesOption.RATINGS));
		}
	}



}