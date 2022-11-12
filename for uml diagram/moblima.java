
import operator.ViewOperator;
import view.MainMenuView;
import model.*;

/**
 * This is the main application class, the entry point of our Application
 */

public class moblima {

	public static void main(String[] args) {

		//FirstStartUp initialise = new FirstStartUp();
		//initialise.initialise();
		
		ViewOperator.pushView(new MainMenuView());
	}

}