package Moblima;
import Controller.ViewsManager;
import View.MainMenuView;

/**
 * This is the main application class, the entry point of our Application
 */

public class Moblima {

	public static void main(String[] args) {

		//admin username : admin
		//admin password : admin

		ViewsManager.pushView(new MainMenuView());
	}

}