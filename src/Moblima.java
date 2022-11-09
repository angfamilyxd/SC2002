import operator.ViewOperator;
import view.MainMenuView;

/**
 * This is the main application class, the entry point of our Application
 */

public class Moblima {
	public static void main(String[] args) {

		//admin username : admin
		//admin password : admin

		ViewOperator.pushView(new MainMenuView());
	}
}