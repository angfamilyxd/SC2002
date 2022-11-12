package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import operator.FileInputOutputOperator;
import operator.ViewOperator;
import operator.DataOperator;
import model.Movies;
import model.Transaction;
import model.Ticket;
import enums.*;

/**
 * This View class presents for movie-goer the various options, each representing a method of listing ore searching movies
 */

public class MovieGoerSearchMovieView extends MainSearchMovieView {


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
		ViewOperator.popView();
	}

	/**
	 * This method handles the case where the user wants to search for movie by typing the movie name
	 */
	private void handleOptionSearchMovie(){
		ArrayList<Movies> movies = new ArrayList<>();
		movies = DataOperator.retrieveAllMovies();
		String userInput = FileInputOutputOperator.getUserInputString("Please write the name of the movie: ");
		
		ArrayList<Movies> filteredMovieList= (ArrayList<Movies>) movies.stream().filter(movie -> movie.getTitle().matches("(?i).*" + userInput + ".*")).collect(Collectors.toList());
		ViewOperator.pushView(new MovieGoerMovieListingView(filteredMovieList));
	}

	/**
	 * This method handles the case where the user wants to list only the movies that are in preview
	 */
	private void handleListPreviewMovies(){
		ArrayList<Movies> movies = new ArrayList<>();
		movies = DataOperator.retrieveAllMovies();
		ArrayList<Movies> filteredMovieList= (ArrayList<Movies>) movies.stream().filter(movie -> movie.getStatus().equals(MovieStatus.PREVIEW)).collect(Collectors.toList());
		ViewOperator.pushView(new MovieGoerMovieListingView(filteredMovieList));
	}


	/**
	 * This method handles the case where the user wants to list all movies
	 */
	private void handleListAllMoviesOption(){
		ArrayList<Movies> movies = new ArrayList<>();
		movies = DataOperator.retrieveAllMovies();
		ViewOperator.pushView(new MovieGoerMovieListingView(movies));
	}

	/**
	 * This method handles the case where the user wants to list only movies that have already ended
	 */
	private void handleListPastMoviesOption() {
		ArrayList<Movies> movies = new ArrayList<>();
		movies = DataOperator.retrieveAllMovies();
		ArrayList<Movies> filteredMovieList= (ArrayList<Movies>) movies.stream().filter(movie -> movie.getStatus().equals(MovieStatus.ENDED)).collect(Collectors.toList());
		ViewOperator.pushView(new MovieGoerMovieListingView(filteredMovieList));
	}

	/**
	 * This method handles the case where the user wants to list only movies which are coming soon
	 */
	private void handleListUpcomingMoviesOption() {
		ArrayList<Movies> movies = new ArrayList<>();
		movies = DataOperator.retrieveAllMovies();
		ArrayList<Movies> filteredMovieList= (ArrayList<Movies>) movies.stream().filter(movie -> movie.getStatus().equals(MovieStatus.COMINGSOON)).collect(Collectors.toList());
		ViewOperator.pushView(new MovieGoerMovieListingView(filteredMovieList));
	}

	/**
	 * This method handles the case where the user wants to list only movies which are currently showing
	 */
	private  void handleListCurrentShowingMovies(){
		ArrayList<Movies> movies = new ArrayList<>();
		movies = DataOperator.retrieveAllMovies();
		ArrayList<Movies> filteredMovieList= (ArrayList<Movies>) movies.stream().filter(movie -> movie.getStatus().equals(MovieStatus.NOWSHOWING)).collect(Collectors.toList());
		ViewOperator.pushView(new MovieGoerMovieListingView(filteredMovieList));
	}

	/**
	 * This method handles the case where the user wants to list currently top 5 movies, either by ticket sales or ratings
	 */
	private void handleListCurrentTop5Movies(){
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

			ViewOperator.pushView(new MovieGoerTop5MovieView(resultMovies, MovieGoerTop5MovieView.Top5MoviesOption.TICKETSALES));


		}else{
			ArrayList<Movies> movies = DataOperator.retrieveAllMovies();
			movies= (ArrayList<Movies>) movies.stream().filter(movie -> movie.getRating() != -1.0).collect(Collectors.toList());
			movies= (ArrayList<Movies>) movies.stream().filter(movie -> (movie.getStatus() == MovieStatus.PREVIEW) || (movie.getStatus() == MovieStatus.NOWSHOWING)).collect(Collectors.toList());
			Collections.sort(movies, Comparator.comparing(Movies::getRating));
			Collections.reverse(movies);
			movies = new ArrayList<>(movies.subList((movies.size() > 5 ? movies.size() - 5 : 0),movies.size()));
			ViewOperator.pushView(new MovieGoerTop5MovieView(movies, MovieGoerTop5MovieView.Top5MoviesOption.RATINGS));
		}
	}



}