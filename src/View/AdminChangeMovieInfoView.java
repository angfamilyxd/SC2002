package View;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import Controller.DatabaseManager;
import Controller.IOManager;
import Controller.ViewsManager;
import Model.*;

/**
 * This View class is responsible for admin to change the information of a movie in the database
 */
public class AdminChangeMovieInfoView extends BaseView {

	/**
	 * This is the selected movie to be modified
	 */
	private Movie selectedMovie;

	/**
	 * This is the options for the BaseView
	 */
	private ArrayList<String> options = new ArrayList<>(Arrays.asList(
			"Status",
			"Director",
			"Synopsis",
			"Title",
			"Casts",
			"Ratings",
			"Add or Remove Showtime for this movie",
			"Go back to admin main menu"
	));

	/**
	 * This is the title for the BaseView
	 */
	private String title = "Movie Info Modification: ";

	/**
	 * This is the content for the BaseView
	 */
	private String viewContent = "Which content would you like to change?";


	/**
	 * This is the constructor of this class which takes in a Movie object as the argument
	 * @param movie The selected Movie object
	 */
	public AdminChangeMovieInfoView(Movie movie) {
		this.selectedMovie = movie;
		this.title += movie.getTitle();
	}

	/**
	 * This method transforms the View into active state
	 */
	public void activate() {
		super.setOptions(this.options);
		super.setTitle(this.title);
		super.setViewContent(this.viewContent);
		super.activate();
		
		int userInput = IOManager.getUserInputInt("Please enter a choice",1,options.size());
		processUserInput(userInput);
	}

	/**
	 * This method helps to manage execution of code based on the user put choice on the View options.
	 * @param input the index of the options
	 */
	
	@Override
	protected void processUserInput(int input) {
		
		if (input == options.size()) {
			ViewsManager.popTillView(AdminMainMenuView.class);
		
		}else if (input == 1) {
			System.out.println("Current Status: " + this.selectedMovie.getStatus().displayName());
			System.out.println("What would you like to status to? ");
			ArrayList<MovieStatus> movieStatusList = new ArrayList<MovieStatus>(EnumSet.allOf(MovieStatus.class));
			ArrayList<String> movieStatusListStrings = new ArrayList<String>();
			for (int x = 0 ; x < movieStatusList.size() ; x++) {
				movieStatusListStrings.add(movieStatusList.get(x).displayName());
			}
			IOManager.printMenuOptions(movieStatusListStrings);
			int statusChoice = IOManager.getUserInputInt("Input the choice: ",1,movieStatusListStrings.size());
			MovieStatus statusChosen = movieStatusList.get(statusChoice - 1);
			this.selectedMovie.setStatus(statusChosen);
			DatabaseManager.modifyMovieWithNewValues(selectedMovie);
			System.out.println("Saved");
		
		}else if (input == 2) {
			System.out.println("Current Director: " + this.selectedMovie.getDirector());
			System.out.println("What would you like to change the director to? ");
			String newDirectorName = IOManager.getUserInputString("Input Name: ");
			selectedMovie.setDirector(newDirectorName);
			DatabaseManager.modifyMovieWithNewValues(selectedMovie);
			System.out.println("Saved");
		
		}else if (input == 3) {
			System.out.println("Current Synopsis: " + this.selectedMovie.getSynopsis());
			System.out.println("What would you like to change the synopsis to? ");
			String newSynopsis = IOManager.getUserInputString("Input Synopsis: ");
			selectedMovie.setSynopsis(newSynopsis);
			DatabaseManager.modifyMovieWithNewValues(selectedMovie);
			System.out.println("Saved");
		
		}else if (input == 4) {
			System.out.println("Current Title: " + this.selectedMovie.getTitle());
			System.out.println("What would you like to change the title to? ");
			String newTitle = IOManager.getUserInputString("Input New Title: ");
			DatabaseManager.deleteMovieByName(selectedMovie.getTitle());
			selectedMovie.setTitle(newTitle);
			DatabaseManager.saveMovieToDataBase(selectedMovie);
			System.out.println("Saved");
		}else if (input == 5) {
			this.handleChangeCastInformation();
		}else if (input == 6){
			System.out.println("Current Rating: " + this.selectedMovie.getMovieCensorshipRating().displayName());
			System.out.println("What would you like to rating to? ");
			ArrayList<MovieCensorshipRating> movieCensorshipList = new ArrayList<MovieCensorshipRating>(EnumSet.allOf(MovieCensorshipRating.class));
			ArrayList<String> movieCensorshipListStrings = new ArrayList<String>();
			for (int x = 0 ; x < movieCensorshipList.size() ; x++) {
				movieCensorshipListStrings.add(movieCensorshipList.get(x).displayName());
			}
			IOManager.printMenuOptions(movieCensorshipListStrings);
			int censorChoice = IOManager.getUserInputInt("Input the choice: ",1,movieCensorshipListStrings.size());
			MovieCensorshipRating censorChosen = movieCensorshipList.get(censorChoice - 1);
			this.selectedMovie.setMovieCensorshipRating(censorChosen);
			DatabaseManager.modifyMovieWithNewValues(selectedMovie);
			System.out.println("Saved");
		}

		else if (input == 7){
			ViewsManager.pushView(new AdminAddShowTimeView(this.selectedMovie));
			//this.handleAddShowTime();
		}
		
		ArrayList<String> choices = new ArrayList<>();
		choices.add("Continue to edit other information?");
		choices.add("Quit Editing");
		IOManager.printMenuOptions(choices);
		
		int userChoice = IOManager.getUserInputInt("",1,2);
		if (userChoice == 1) {
			this.activate();
		}else {
			ViewsManager.popViews(3);
		}
		
	}


	/**
	 * This method is responsible for handling the process of changing casts information of a movie
	 */
	private void handleChangeCastInformation(){

		while (true) {
			System.out.println("\nHere is the current list of the casts: \n");
			for (String cast : this.selectedMovie.getCasts()) {
				System.out.println("   " + cast);
			}
			ArrayList<String> choices = new ArrayList<>();
			choices.add("Add New Cast");
			choices.add("Delete Cast");
			choices.add("Edit Existing Cast");
			choices.add("Done editing");
			IOManager.printMenuOptions(choices);
			int userChoice = IOManager.getUserInputInt("\nInput choice: ", 1, choices.size());

			if (userChoice == 1) {
				String newCastName = IOManager.getUserInputString("Input the name of the cast");
				this.selectedMovie.addCast(newCastName);
				DatabaseManager.modifyMovieWithNewValues(this.selectedMovie);
				System.out.println("Saved!");
			}

			else if (userChoice == 2){
				ArrayList<String> choices2 = new ArrayList<>();
				for (String cast:this.selectedMovie.getCasts()){
					choices2.add(cast);
				}
				IOManager.printMenuOptions(choices2);
				int castChoice = IOManager.getUserInputInt("Choose the cast to delete: ",1,choices2.size());
				choices2.remove(castChoice);
				this.selectedMovie.setCast(choices2);
				DatabaseManager.modifyMovieWithNewValues(this.selectedMovie);
				System.out.println("Removed!");
			}

			else if (userChoice == 3){
				ArrayList<String> choices3 = new ArrayList<>();
				for (String cast:this.selectedMovie.getCasts()){
					choices3.add(cast);
				}
				IOManager.printMenuOptions(choices3);
				int castChoice = IOManager.getUserInputInt("Choose the cast to Edit: ",1,choices3.size());
				String newName2 = IOManager.getUserInputString("Please input the new cast name: ");
				choices3.set(castChoice - 1,newName2);
				this.selectedMovie.setCast(choices3);
				DatabaseManager.modifyMovieWithNewValues(this.selectedMovie);
				System.out.println("Edited!");
			}

			else if (userChoice == 4){
				break;
			}
		}


	}

}