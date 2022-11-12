package View;

import java.util.ArrayList;
import java.util.EnumSet;
import Controller.DatabaseManager;
import Controller.IOManager;
import Controller.ViewsManager;
import Model.Movie;
import Model.MovieCensorshipRating;
import Model.MovieStatus;


/**
 * This View class is responsible for facilitating the process of admin creating new movie into the movie database
 */

public class AdminCreateNewMovieView extends BaseView {

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
			String movieName = IOManager.getUserInputString("Name of the movie: ");
			String directorName = IOManager.getUserInputString("Director name of the movie: ");
			String synopsis = IOManager.getUserInputString("Synopsis of the movie: ");
			ArrayList<String> casts = new ArrayList<>();
			
			int castNum;
			castNum = IOManager.getUserInputInt("How many casts are you inserting? ",0,10000);
			while (castNum < 2) {
				System.out.println("There must be at least two casts");
				castNum = IOManager.getUserInputInt("How many casts are you inserting? ",0,10000);
			}
			for (int x = 0 ; x < castNum ; x++) {
				String castName = IOManager.getUserInputString("Please input the name of cast " + (x + 1) + ":  ");
				casts.add(castName);
			}
			System.out.println("Status of the movie? ");
			ArrayList<MovieStatus> movieStatusList = new ArrayList<MovieStatus>(EnumSet.allOf(MovieStatus.class));
			ArrayList<String> movieStatusListStrings = new ArrayList<String>();
			for (int x = 0 ; x < movieStatusList.size() ; x++) {
				movieStatusListStrings.add(movieStatusList.get(x).displayName());
			}
			IOManager.printMenuOptions(movieStatusListStrings);
			int statusChoice = IOManager.getUserInputInt("Input the choice: ",1,movieStatusListStrings.size());
			MovieStatus statusChosen = movieStatusList.get(statusChoice - 1);

			System.out.println("Ratings of the movie? ");
			ArrayList<MovieCensorshipRating> movieCensorShipList = new ArrayList<MovieCensorshipRating>(EnumSet.allOf(MovieCensorshipRating.class));
			ArrayList<String> movieCensorShipListStrings = new ArrayList<String>();
			for (int x = 0 ; x < movieCensorShipList.size() ; x++) {
				movieCensorShipListStrings.add(movieCensorShipList.get(x).displayName());
			}
			IOManager.printMenuOptions(movieCensorShipListStrings);
			int censorChoice = IOManager.getUserInputInt("Input the choice: ",1,movieCensorShipList.size());
			MovieCensorshipRating censorshipChosen = movieCensorShipList.get(censorChoice - 1);

			Movie newMovie = new Movie(movieName,statusChosen,censorshipChosen);
			newMovie.setDirector(directorName);
			newMovie.setSynopsis(synopsis);
			for (String cast : casts) {
				newMovie.addCast(cast);
			}
			
			IOManager.printMenuContent(newMovie.toString() + "\n");
			
			ArrayList<String> choices = new ArrayList<>();
			choices.add("Save");
			choices.add("Re-enter information");
			choices.add("Cancel without saving");
			IOManager.printMenuOptions(choices);
			int continueChoice = IOManager.getUserInputInt("Choice: ",1,choices.size());
			
			if (continueChoice == 3) {
				break;
			}
			
		//	int continueChoice = IOManager.getUserInputInt("Input 1 to proceed to save, 2 to reinput the information",1,2);
			if (continueChoice == 1) {
				DatabaseManager.saveMovieToDataBase(newMovie);
				System.out.println("Successfully Saved!\n");
				System.out.println("Add more movies? ");
				ArrayList<String> choices2 = new ArrayList<>();
				choices2.add("Yes");
				choices2.add("No");
				IOManager.printMenuOptions(choices2);
				int continueChoice2 = IOManager.getUserInputInt("Choice: ",1,2);
				if (continueChoice2 == 2) {
					break;
				}
			}
			
		}
		
		ViewsManager.popView();
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