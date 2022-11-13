package Controller;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class is responsible for the direct interaction with the user.
 * This class deals with getting input from the user as well as printing text and options onto the screen for the user
 */

public class IOManager {


	/**
	 * This method prints the title of each menu view in a pretty format
	 * @param title The title String of the menu view
	 */
	public static void printMenuTitle(String title) {
		System.out.println("\n\n");
		String upperBorderLine = "=======================================================================";
		System.out.println(upperBorderLine);
		
		int borderLengthHalf = (upperBorderLine.length()) / 2;
		for (int x = 0 ; x < borderLengthHalf ; x++) {
			System.out.printf("+ ");
		}
		System.out.printf("\n");
		int frontSpacing = (upperBorderLine.length() - title.length()) / 2;
		for (int y = 0 ; y < frontSpacing ; y++) {
			System.out.printf(" ");
		}
		System.out.println(title);
		System.out.println(upperBorderLine + "\n");
	}


	/**
	 * This method helps to print the numbered options in a consistent format.
	 * Every menu view will display numbered options for users to choose the option they want.
	 * This method can be used for printing such options.
	 *
	 * @param options The ArrayList of strings to be used as the options
	 */
	public static void printMenuOptions(ArrayList<String> options) {
		int counter = 1;
		System.out.println("\n");
		for (String option : options) {
			System.out.println("  " + counter + ") " + option);
			counter++;
		}
		System.out.println("\n");
	}


	/**
	 * This method helps to print String content in any BaseView. This method ensures that the string will automatically go to the next line when it reaches the maximum width of the allowable screen width
	 * @param input The String content
	 */

	public static void printMenuContent(String input) {
		String upperBorderLine = "______________________________________________________________________________";
		int viewLength = upperBorderLine.length();
		StringTokenizer tok = new StringTokenizer(input, " ");
	    StringBuilder output = new StringBuilder(input.length());
	    int lineLen = 0;
	    while (tok.hasMoreTokens()) {
	        String word = tok.nextToken() + " ";

	        if (lineLen + word.length() > viewLength) {
	            output.append("\n");
	            lineLen = 0;
	        }
	        output.append(word);
	        lineLen += word.length();
	    }
		System.out.println(output);
	}

	/**
	 * Given an ArrayList of any type, this method helps to transform the list into multi-page options menu and manage the navigation of the pages.
	 * This method does not support the selection of the options.
	 * @param array Array containing objects to be listed as options in this menu
	 * @param numOfOptionsPerPage The maximum number of options allowed for each page
	 * @param endOptionsChoosingSessionMessage The option String that tells user to navigate back to previous BaseView.
	 * @param <E> Any type as long as the toString() method is properly configured.
	 */
	public static <E> void printMultipageOptions(ArrayList<E> array,int numOfOptionsPerPage,String endOptionsChoosingSessionMessage) {
		int numOfReviewsPerPage = numOfOptionsPerPage;
		int multiplier = 0;
		boolean isLastPage = false;
		boolean isFirstPage = true;
		
		while (true) {
			System.out.println("\nPage " + (multiplier + 1) + ": \n");
			isLastPage = false;
			isFirstPage = false;
			for (int x = 1 + (numOfReviewsPerPage*multiplier); x <= array.size() ; x++) {
				if ((x-1) % (numOfReviewsPerPage*(multiplier+1)) == 0 && (x != 1)) {
					break;
				}
				if (x <= numOfReviewsPerPage) {
					isFirstPage = true;
				}
				if (x == array.size()) {
					isLastPage = true;
				}
				System.out.println(x + ") " + array.get(x - 1).toString());
				System.out.println(" ");
			}
			
			ArrayList<String> userChoices = new ArrayList<>();
			userChoices.add(endOptionsChoosingSessionMessage);
			if (!(isLastPage && isFirstPage) && array.size() != 0) {
				if (!isFirstPage){userChoices.add("Previous page");}
				if (!isLastPage) {userChoices.add("Next Page");}
			}
			if (array.size() == 0){
				System.out.println("    " + "There is no available items");
			}
			IOManager.printMenuOptions(userChoices);
			int userChoice = IOManager.getUserInputInt("Please choose an option: ",1,userChoices.size());
			
			if (userChoice == 1) {
				return;
			}else if (userChoice == 2) {
				if (!isFirstPage) {
					multiplier--;
					continue;
				}
			}
			multiplier++;
		}
	}

	/**
	 * Given an ArrayList of any type, this method helps to transform the list into multi-page options menu and manage the navigation of the pages and selection of the element in the multi-page menu options.
	 * This method uses the toString() method for each element in the ArrayList to print each option.
	 * @param array Array containing objects to be listed as options in this menu
	 * @param numOfOptionsPerPage The maximum number of options allowed for each page
	 * @param <E> Any type as long as the toString() method is properly configured.
	 * @return The position of the selected option. (eg 4 if the 4th option is selected). Return -1 if user choose to close this menu to return to previous menu.
	 */
	public static <E> int printMultipageOptionsWithReturnedChoice(ArrayList<E> array,int numOfOptionsPerPage) {
		int numOfReviewsPerPage = numOfOptionsPerPage;
		int multiplier = 0;
		boolean isLastPage = false;
		boolean isFirstPage = true;
		
		while (true) {
			System.out.println("\nPage " + (multiplier + 1) + ": \n");
			isLastPage = false;
			isFirstPage = false;
			int x;
			for (x = 1 + (numOfReviewsPerPage*multiplier); x <= array.size() ; x++) {
				if ((x-1) % (numOfReviewsPerPage*(multiplier+1)) == 0 && (x != 1)) {
					break;
				}
				if (x <= numOfReviewsPerPage) {
					isFirstPage = true;
				}
				if (x == array.size()) {
					isLastPage = true;
				}
				System.out.println("    " + x + ") " + array.get(x - 1).toString());
			}
			
			if (((isFirstPage) && (isLastPage)) || array.size() == 0) {
				int start = 1;
				int end = array.size() + 1;
				if (array.size() == 0){
					System.out.println("    " + "There is no available items\n");
				}
				System.out.println("    " + x + ") " + "Return to previous menu");
				int userChoice = IOManager.getUserInputInt("\nInput your choice: ",start,end);
				if (userChoice == end) {return -1;} else {return userChoice;}
			}
			
			System.out.println("\n\nOptions: ");
			ArrayList<String> userChoices = new ArrayList<>();
			userChoices.add("Choose one of the item");
			userChoices.add("Return to previous menu");
			if (!isFirstPage){userChoices.add("Previous page");}
			if (!isLastPage) {userChoices.add("Next Page");}
			IOManager.printMenuOptions(userChoices);
			int userChoice = IOManager.getUserInputInt("Please choose an option: ",1,userChoices.size());
			
			if (userChoice == 1) {
				int start = 1;
				int end = array.size();
				return IOManager.getUserInputInt("Input your choice: ",start,end);
			}else if (userChoice == 2) {
				return -1;
			}
			else if (userChoice == 3) {
				if (!isFirstPage) {
					multiplier--;
					continue;
				}
			}
			multiplier++;
		}
	}


	/**
	 * This method helps to print double value with a specified number of decimal place.
	 * @param value the double value to be printed
	 * @param decimalPlace the number of decimal places
	 */

	public static void printDouble(double value,int decimalPlace){
		System.out.printf("%." + decimalPlace + "f",value);
	}

	/**
	 * This method is responsible for getting input of type String from the user.
	 * @param message The message to print on the console when asking for user input
	 * @return The String value input by user
	 */
	public static String getUserInputString(String message) {
		System.out.println(message);
		Scanner sc = new Scanner(System.in);
		String userInput = sc.nextLine();
		return userInput;
	}

	/**
	 * This method is responsible for getting input of type int from the user.
	 * Invalid input exception handling is automatically handled in this function.
	 * @param message The message to print on the console when asking for user input
	 * @return The int value input by user
	 */
	public static int getUserInputInt(String message) {
		System.out.println(message);
		int userInput;
		
		try {
			Scanner sc = new Scanner(System.in);
			userInput = sc.nextInt();
		}catch (InputMismatchException err) {
			System.out.println("You have input the wrong type, please input only integers");
			userInput = getUserInputInt(message);
		}
		return userInput;
	}


	/**
	 * This method is responsible for getting user to input a date value
	 * @param message The message to print on the console when asking for user input
	 * @return The date value input by user, LocalDate object
	 */

	public static LocalDate getUserInputDate(String message){

		System.out.println(message);

		try{
			System.out.println("Format: day/month/year, example: 24/2/2022");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
			String userDate = IOManager.getUserInputString("");
			LocalDate localDate = LocalDate.parse(userDate, formatter);
			return localDate;
		}catch (Exception e){
			System.out.println("Wrong format, try again");
			return getUserInputDate(message);
		}
	}

	 /**
	 * This method is responsible for getting input of type int from the user.
	 * This method limits the range of acceptable from the user to be between the parameter values, start and end.
	 * Invalid input exception handling is automatically handled in this function.
	 * @param message The message to print on the console when asking for user input
	 * @param start The start of the allowed range of values (inclusive)
	 * @param end The end of the allowed range of values (inclusive)
	 * @return The int value input by user
	 */

	public static int getUserInputInt(String message, int start, int end) {
		System.out.println(message);
		int userInput;
		
		try {
			Scanner sc = new Scanner(System.in);
			userInput = sc.nextInt();

			if (userInput < start || userInput > end) {
				throw new Exception("Invalid input. This input is not among the choices");
			}
		}catch (InputMismatchException err) {
			System.out.println("You have input the wrong type, please input only integers");
			userInput = getUserInputInt(message,start,end);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			userInput = getUserInputInt(message,start,end);
		}
		
		return userInput;
	}


	/**
	 * This method is responsible for getting input of type double from the user.
	 * This method limits the range of acceptable from the user to be between the parameter values, start and end.
	 * Invalid input exception handling is automatically handled in this function.
	 * @param message The message to print on the console when asking for user input
	 * @param start The start of the allowed range of values (inclusive)
	 * @param end The end of the allowed range of values (inclusive)
	 * @return The double value input by user
	 */
	
	public static double getUserInputDouble(String message,double start,double end) {
		System.out.println(message);
		double userInput;
		
		try {
			Scanner sc = new Scanner(System.in);
			userInput = sc.nextDouble();
			
			if (userInput < start || userInput > end) {
				throw new Exception("Invalid input. This input is not in the specified range");
			}
		}catch (InputMismatchException err) {
			System.out.println("You have input the wrong type, please input only numbers numbers");
			userInput = getUserInputDouble(message,start,end);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			userInput = getUserInputDouble(message,start,end);
		}
		return userInput;
	}


}