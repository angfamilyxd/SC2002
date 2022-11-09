package view;

import java.util.ArrayList;
import java.util.EnumSet;
import operator.DataOperator;
import operator.FileInputOutputOperator;
import operator.ViewOperator;
import model.Movies;
import enums.*;



/**
 * This View class is responsible for facilitating the process of admin creating new movie into the movie database
 */

public class StaffAddMovieView extends StartView {

	/**
	 * This is the title for the BaseView
	 */
	private String title = "Create New Movie";


	/**
	 * This method transforms the View into active state.
	 */
	@Override
	public void activate() {
		
		super.setTitle(this.title);
		super.activate();
		
		while (true) {
			String movieName = FileInputOutputOperator.getUserInputString("Please input the name of the movie: ");
			String directorName = FileInputOutputOperator.getUserInputString("Please input the director name of the movie: ");
			String synopsis = FileInputOutputOperator.getUserInputString("Please input the synopsis of the movie: ");
			ArrayList<String> casts = new ArrayList<>();
			
			int castNum;
			castNum = FileInputOutputOperator.getUserInputInt("How many casts are you inserting? ",0,10000);
			while (castNum < 2) {
				System.out.println("There must be at least two casts");
				castNum = FileInputOutputOperator.getUserInputInt("How many casts are you inserting? ",0,10000);
			}
			for (int x = 0 ; x < castNum ; x++) {
				String castName = FileInputOutputOperator.getUserInputString("Please input the name of cast " + (x + 1) + ":  ");
				casts.add(castName);
			}
			System.out.println("What is the current status of the movie? ");
			ArrayList<MovieStatus> movieStatusList = new ArrayList<MovieStatus>(EnumSet.allOf(MovieStatus.class));
			ArrayList<String> movieStatusListStrings = new ArrayList<String>();
			for (int x = 0 ; x < movieStatusList.size() ; x++) {
				movieStatusListStrings.add(movieStatusList.get(x).displayName());
			}
			FileInputOutputOperator.printMenuOptions(movieStatusListStrings);
			int statusChoice = FileInputOutputOperator.getUserInputInt("Input the choice: ",1,movieStatusListStrings.size());
			MovieStatus statusChosen = movieStatusList.get(statusChoice - 1);

			System.out.println("What is the current censorship rating of the movie? ");
			ArrayList<MovieRating> movieCensorShipList = new ArrayList<MovieRating>(EnumSet.allOf(MovieRating.class));
			ArrayList<String> movieCensorShipListStrings = new ArrayList<String>();
			for (int x = 0 ; x < movieCensorShipList.size() ; x++) {
				movieCensorShipListStrings.add(movieCensorShipList.get(x).displayName());
			}
			FileInputOutputOperator.printMenuOptions(movieCensorShipListStrings);
			int censorChoice = FileInputOutputOperator.getUserInputInt("Input the choice: ",1,movieCensorShipList.size());
			MovieRating censorshipChosen = movieCensorShipList.get(censorChoice - 1);

			Movies newMovie = new Movies(movieName,statusChosen,censorshipChosen);
			newMovie.setDirector(directorName);
			newMovie.setSynopsis(synopsis);
			for (String cast : casts) {
				newMovie.addCast(cast);
			}
			
			FileInputOutputOperator.printMenuContent(newMovie.toString() + "\n");
			
			ArrayList<String> choices = new ArrayList<>();
			choices.add("Save");
			choices.add("Re-enter information");
			choices.add("Cancel without saving");
			FileInputOutputOperator.printMenuOptions(choices);
			int continueChoice = FileInputOutputOperator.getUserInputInt("Choice: ",1,choices.size());
			
			if (continueChoice == 3) {
				break;
			}
			
		//	int continueChoice = IOManager.getUserInputInt("Input 1 to proceed to save, 2 to reinput the information",1,2);
			if (continueChoice == 1) {
				DataOperator.saveMovieToDataBase(newMovie);
				System.out.println("Successfully Saved!\n");
				System.out.println("Do you want to create more movies? ");
				ArrayList<String> choices2 = new ArrayList<>();
				choices2.add("Yes");
				choices2.add("No");
				FileInputOutputOperator.printMenuOptions(choices2);
				int continueChoice2 = FileInputOutputOperator.getUserInputInt("Choice: ",1,2);
				if (continueChoice2 == 2) {
					break;
				}
			}
			
		}
		
		ViewOperator.popView();
	}

	/**
	 * This method helps to manage execution of code based on the user put choice on the View options.
	 * @param input the index of the options
	 */
	@Override
	protected void processUserInput(int input) {
		// TODO Auto-generated method stub

	}

}