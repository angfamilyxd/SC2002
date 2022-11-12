package View;

import java.util.ArrayList;

import Controller.DatabaseManager;
import Controller.IOManager;
import Controller.ViewsManager;
import Model.Order;

/**
 * This class is responsible for showing the booking history that has been made by a movie-goer
 */
public class MovieGoerBookingHistoryView extends BaseView {

	/**
	 * This is the View options
	 */
	private ArrayList<String> options = new ArrayList<>();

	/**
	 * This is the View title
	 */
	private String title = "Booking History";

	/**
	 * This is the View content
	 */
	private String viewContent = "Here is the list of your past bookings: Choose any one of the order to show the detailed order information";

	/**
	 * This is the movie goer name which is used to filter the booking history in which the booking is made under this name
	 */
	private String username;

	/**
	 * This is the list of orders made by the movie-goer
	 */
	private ArrayList<Order> orders = new ArrayList<>();


	/**
	 * This is the constructor which takes the username as an argument.
	 * This username refers to the movie goer name, which is needed to show his booking histories
	 * @param username The name of the movie goer
	 */
	public MovieGoerBookingHistoryView(String username) {
		this.username = username;
		for (Order order : DatabaseManager.retrieveAllOrders()){
			if (order.getBuyerName().toLowerCase().equals(this.username.toLowerCase())){
				options.add(order.toSummarisedString());
				orders.add(order);
			}
		}

		this.options.add("Back to Previous Page");
	}

	/**
	 * This method helps to transform the View into active state
	 */
	public void activate() {
		super.setOptions(this.options);
		super.setTitle(this.title);
		super.setViewContent(this.viewContent);
		super.activate();
		
		int userInput = IOManager.getUserInputInt("Please input a choice",1,options.size());
		processUserInput(userInput);
	}

	/**
	 * This method helps to manage execution of code based on the user put choice on the View options.
	 * @param input the index of the options
	 */
	@Override
	protected void processUserInput(int input) {
		if (input == options.size()) {
			ViewsManager.popView();
		}else{
			String orderString = this.orders.get(input - 1).toString();
			System.out.println(orderString);
			IOManager.getUserInputString("Click any key to go back");
			this.activate();
		}

	}

}