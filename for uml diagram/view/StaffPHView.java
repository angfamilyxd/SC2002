package view;

import operator.FileInputOutputOperator;
import operator.ViewOperator	;
import model.PriceCalculator;
import model.PublicHoliday;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * This class is responsible for handling the setting of public holidays as part of the system settings.
 * For example, the admin can add new public holiday into the system, or remove existing public holidays from the system
 */
public class StaffPHView extends StartView {

    /**
     * This is the options for the BaseView
     */
    private ArrayList<String> options = new ArrayList<>(Arrays.asList(
            "Add New Public Holiday",
            "Delete Existing Public Holiday",
            "Go back to previous menu"
    ));

    /**
     * This is the title for the BaseView
     */
    private String title = "Configure Public Holiday";

    /**
     * This is the content for the BaseView
     */
    private String viewContent = "";

    /**
     * This is an array consisting of the existing public holidays in the system
     */
    private ArrayList<PublicHoliday> publicHolidays = PriceCalculator.getInstance().getPublicHolidays();


    /**
     * This method transforms the state of this View to be in active state
     */
    @Override
    public void activate() {
        viewContent = "\n\n";
        int count = 1;
        for (PublicHoliday publicHoliday : publicHolidays){
            viewContent += "     " + count + ". " + publicHoliday.getName() + "  (" + publicHoliday.getDate().format(DateTimeFormatter.ofPattern("d/M/yyyy")) + ") \n";
            count++;
        }

        if (this.publicHolidays.size() == 0){
            viewContent += "There is currently no public holiday set in the system";
        }

        viewContent += "\n";

        super.setOptions(options);
        super.setTitle(this.title);
        super.printViewTitle();
        System.out.println(viewContent);
        super.printOptions();

        int userInput = FileInputOutputOperator.getUserInputInt("Please input a choice",1,options.size());
        processUserInput(userInput);
    }


    /**
     * This method helps to manage execution of code based on the user put choice on the View options.
     * @param input the index of the options
     */

    @Override
    protected void processUserInput(int input) {
        if (input == options.size()){
        	ViewOperator.popView();
        }

        else if (input == 1){
            this.handleAddNewPublicHoliday();
        }

        else if (input == 2){
            this.handleDeletePublicHoliday();
        }
    }

    /**
     * This method helps to handle the process of adding new public holiday into the system
     */
    private void handleAddNewPublicHoliday(){
        String name = FileInputOutputOperator.getUserInputString("Please input the name of the public holiday");
        LocalDate date = FileInputOutputOperator.getUserInputDate("Please input the date of the public holiday");
        PublicHoliday publicHoliday = new PublicHoliday(name,date);
        this.publicHolidays.add(publicHoliday);
        PriceCalculator.getInstance().setPublicHolidays(this.publicHolidays);
        FileInputOutputOperator.getUserInputString("Successful! Please input any key to continue");
        this.activate();
    }

    /**
     * This method helps handle the process of deleting existing public holidays from the system
     */
    private void handleDeletePublicHoliday(){
        if (this.publicHolidays.size() == 0){
            System.out.println("There is currently no items available for deletion");
            FileInputOutputOperator.getUserInputString("Press any key to continue");
            this.activate();
        }else{
            int count = 1;
            for (PublicHoliday publicHoliday : publicHolidays){
                if (count == 1){
                    System.out.printf("");
                }
                String string = "     " + count + ". " + publicHoliday.getName() + "  (" + publicHoliday.getDate().format(DateTimeFormatter.ofPattern("d/M/yyyy")) + ") ";
                System.out.println(string);
                count++;
            }
            System.out.println("\n\n");
            int userChoice = FileInputOutputOperator.getUserInputInt("Please select the public holiday that you wish to delete: ",1,this.publicHolidays.size());
            this.publicHolidays.remove(userChoice - 1);
            PriceCalculator.getInstance().setPublicHolidays(this.publicHolidays);
            FileInputOutputOperator.getUserInputString("Successful! Please input any key to continue");
            this.activate();
        }
    }
}