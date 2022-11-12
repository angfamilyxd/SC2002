package view;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import operator.DataOperator;
import operator.FileInputOutputOperator;
import operator.ViewOperator;
import model.*;
import enums.*;

/**
 * This View class is responsible for admin to change the information of a movie in the database
 */
public class StaffEditMovieDetailsView extends StartView {

	/**
	 * This is the selected movie to be modified
	 */
	private Movies selectedMovie;

	/**
	 * This is the options for the BaseView
	 */
	private ArrayList<String> options = new ArrayList<>(Arrays.asList(
			"Status",
			"Director",
			"Synopsis",
			"Title",
			"Casts",
			"Censorship Rating",
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
	private String viewContent = "Choose the following options to change the information of the movie";


	/**
	 * This is the constructor of this class which takes in a Movie object as the argument
	 * @param movie The selected Movie object
	 */
	public StaffEditMovieDetailsView(Movies movie) {
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
		
		int userInput = FileInputOutputOperator.getUserInputInt("Please input a choice",1,options.size());
		processUserInput(userInput);
	}

	/**
	 * This method helps to manage execution of code based on the user put choice on the View options.
	 * @param input the index of the options
	 */
	
	@Override
	protected void processUserInput(int input) {
		
		if (input == options.size()) {
			ViewOperator.popTillView(StaffMainView.class);
		
		}else if (input == 1) {
			System.out.println("Current Status: " + this.selectedMovie.getStatus().displayName());
			System.out.println("What would you like to status to? ");
			ArrayList<MovieStatus> movieStatusList = new ArrayList<MovieStatus>(EnumSet.allOf(MovieStatus.class));
			ArrayList<String> movieStatusListStrings = new ArrayList<String>();
			for (int x = 0 ; x < movieStatusList.size() ; x++) {
				movieStatusListStrings.add(movieStatusList.get(x).displayName());
			}
			FileInputOutputOperator.printMenuOptions(movieStatusListStrings);
			int statusChoice = FileInputOutputOperator.getUserInputInt("Input the choice: ",1,movieStatusListStrings.size());
			MovieStatus statusChosen = movieStatusList.get(statusChoice - 1);
			this.selectedMovie.setStatus(statusChosen);
			DataOperator.modifyMovieWithNewValues(selectedMovie);
			System.out.println("Saved");
		
		}else if (input == 2) {
			System.out.println("Current Director: " + this.selectedMovie.getDirector());
			System.out.println("What would you like to change the director to? ");
			String newDirectorName = FileInputOutputOperator.getUserInputString("Input Name: ");
			selectedMovie.setDirector(newDirectorName);
			DataOperator.modifyMovieWithNewValues(selectedMovie);
			System.out.println("Saved");
		
		}else if (input == 3) {
			System.out.println("Current Synopsis: " + this.selectedMovie.getSynopsis());
			System.out.println("What would you like to change the synopsis to? ");
			String newSynopsis = FileInputOutputOperator.getUserInputString("Input Synopsis: ");
			selectedMovie.setSynopsis(newSynopsis);
			DataOperator.modifyMovieWithNewValues(selectedMovie);
			System.out.println("Saved");
		
		}else if (input == 4) {
			System.out.println("Current Title: " + this.selectedMovie.getTitle());
			System.out.println("What would you like to change the title to? ");
			String newTitle = FileInputOutputOperator.getUserInputString("Input New Title: ");
			DataOperator.deleteMovieByName(selectedMovie.getTitle());
			selectedMovie.setTitle(newTitle);
			DataOperator.saveMovieToDataBase(selectedMovie);
			System.out.println("Saved");
		}else if (input == 5) {
			this.handleChangeCastInformation();
		}else if (input == 6){
			System.out.println("Current Rating: " + this.selectedMovie.getMovieCensorshipRating().displayName());
			System.out.println("What would you like to rating to? ");
			ArrayList<MovieRating> movieCensorshipList = new ArrayList<MovieRating>(EnumSet.allOf(MovieRating.class));
			ArrayList<String> movieCensorshipListStrings = new ArrayList<String>();
			for (int x = 0 ; x < movieCensorshipList.size() ; x++) {
				movieCensorshipListStrings.add(movieCensorshipList.get(x).displayName());
			}
			FileInputOutputOperator.printMenuOptions(movieCensorshipListStrings);
			int censorChoice = FileInputOutputOperator.getUserInputInt("Input the choice: ",1,movieCensorshipListStrings.size());
			MovieRating censorChosen = movieCensorshipList.get(censorChoice - 1);
			this.selectedMovie.setMovieCensorshipRating(censorChosen);
			DataOperator.modifyMovieWithNewValues(selectedMovie);
			System.out.println("Saved");
		}

		else if (input == 7){
			ViewOperator.pushView(new StaffShowTimeView(this.selectedMovie));
			//this.handleAddShowTime();
		}
		
		ArrayList<String> choices = new ArrayList<>();
		choices.add("Continue Editing other information");
		choices.add("Quit Editing");
		FileInputOutputOperator.printMenuOptions(choices);
		
		int userChoice = FileInputOutputOperator.getUserInputInt("",1,2);
		if (userChoice == 1) {
			this.activate();
		}else {
			ViewOperator.popViews(3);
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
			choices.add("Add Cast");
			choices.add("Delete Cast");
			choices.add("Edit Existing Cast");
			choices.add("Done editing");
			FileInputOutputOperator.printMenuOptions(choices);
			int userChoice = FileInputOutputOperator.getUserInputInt("\nInput choice: ", 1, choices.size());

			if (userChoice == 1) {
				String newCastName = FileInputOutputOperator.getUserInputString("Input the name of the cast");
				this.selectedMovie.addCast(newCastName);
				DataOperator.modifyMovieWithNewValues(this.selectedMovie);
				System.out.println("Saved!");
			}

			else if (userChoice == 2){
				ArrayList<String> choices2 = new ArrayList<>();
				for (String cast:this.selectedMovie.getCasts()){
					choices2.add(cast);
				}
				FileInputOutputOperator.printMenuOptions(choices2);
				int castChoice = FileInputOutputOperator.getUserInputInt("Choose the cast to delete: ",1,choices2.size());
				choices2.remove(castChoice);
				this.selectedMovie.setCast(choices2);
				DataOperator.modifyMovieWithNewValues(this.selectedMovie);
				System.out.println("Removed!");
			}

			else if (userChoice == 3){
				ArrayList<String> choices3 = new ArrayList<>();
				for (String cast:this.selectedMovie.getCasts()){
					choices3.add(cast);
				}
				FileInputOutputOperator.printMenuOptions(choices3);
				int castChoice = FileInputOutputOperator.getUserInputInt("Choose the cast to Edit: ",1,choices3.size());
				String newName2 = FileInputOutputOperator.getUserInputString("Please input the new cast name: ");
				choices3.set(castChoice - 1,newName2);
				this.selectedMovie.setCast(choices3);
				DataOperator.modifyMovieWithNewValues(this.selectedMovie);
				System.out.println("Edited!");
			}

			else if (userChoice == 4){
				break;
			}
		}


	}

}