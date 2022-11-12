package View;

import Controller.DatabaseManager;
import Controller.IOManager;
import Controller.ViewsManager;
import Model.PriceConfiguration;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * This View class is responsible for handling the configuration of system settings by the admin/
 */

public class AdminSystemSettingsView extends BaseView {


    /**
     * This is the options for the View
     */
    private ArrayList<String> options = new ArrayList<>(Arrays.asList(
            "Set Public Holidays",
            "Base Ticket Price",
            "Base Platinum Class Ticket Price",
            "Base Gold Class Ticket Price",
            "Additional price for Weekend Ticket",
            "Additional price for Public Holiday Ticket",
            "Additional price for 3D Movie Ticket",
            "Additional price for Blockbuster Movie Ticket",
            "Discount for Child Ticket",
            "Discount for Senior Citizen Ticket",
            "GST Percentage Increase",
            "Back to Previous Page"
    ));


    /**
     * This is the title for the View
     */
    private String title = "Admin Configuring System Settings";

    /**
     * This is the content for the View
     */
    private String viewContent = "Modify";


    /**
     * This method transforms the View into active state
     */
    @Override
    public void activate() {

        super.setOptions(this.options);
        super.setTitle(this.title);
        super.setViewContent(this.viewContent);
        super.activate();

        int userInput = IOManager.getUserInputInt("Please input a choice",1,options.size());
        processUserInput(userInput);
    }

    /**
     * This method helps to manage execution of code based on the user put choice on the BaseView options.
     * @param input the index of the options
     */
    @Override
    protected void processUserInput(int input) {

        switch (input){
            case 1:
                this.handleSetPublicHoliday();
            case 2:
                this.handleSetBaseTicketPrice();
            case 3:
                this.handleSetBasePlatinumTicketPrice();
            case 4:
                this.handleSetBaseGoldTicketPrice();
            case 5:
                this.handleSetWeekendPriceIncrement();
            case 6:
                this.handleSetPHPriceIncrement();
            case 7:
                this.handleSet3DPriceIncrement();
            case 8:
                this.handleSetBlockbusterPriceIncrement();
            case 9:
                this.handleSetChildTicketPercentageDiscount();
            case 10:
                this.handleSetSCTicketPercentageDiscount();
            case 11:
                this.handleSetGSTPercentageIncrease();
            default:
                ViewsManager.popView();
        }
    }

    /**
     * This method handles the action when admin choose to set public holidays into the system settings
     */
    private void handleSetPublicHoliday(){
        ViewsManager.pushView(new AdminConfigurePublicHolidayView());
    }

    /**
     * This method handles the setting of base price by the admin
     */
    private void handleSetBaseTicketPrice(){

        System.out.printf("Current Base Price: $");
        IOManager.printDouble(PriceConfiguration.getInstance().getBasePrice(),2);
        System.out.printf("\n");
        double userInput = IOManager.getUserInputDouble("New Base Class cinema ticket price: ",0,Double.POSITIVE_INFINITY);
        BigDecimal bd = new BigDecimal(userInput).setScale(2, RoundingMode.HALF_UP);
        double roundedInput = bd.doubleValue();
        PriceConfiguration.getInstance().setBasePrice(roundedInput);
        DatabaseManager.savePriceConfiguration(PriceConfiguration.getInstance());
        System.out.printf("Saved! New Base Class cinema ticket price: $");
        IOManager.printDouble(PriceConfiguration.getInstance().getBasePrice(),2);
        System.out.printf("\n");
        IOManager.getUserInputString("Press any key to continue: ");
        this.activate();

    }

    /**
     * This method handles the setting of base platinum price by the admin
     */
    private void handleSetBasePlatinumTicketPrice(){

        System.out.printf("Current Base Platinum Price: $");
        IOManager.printDouble(PriceConfiguration.getInstance().getPlatinumBasePrice(),2);
        System.out.printf(" \n");
        double userInput = IOManager.getUserInputDouble("New base Platinum Class cinema ticket price: ",0,Double.POSITIVE_INFINITY);
        BigDecimal bd = new BigDecimal(userInput).setScale(2, RoundingMode.HALF_UP);
        double roundedInput = bd.doubleValue();
        PriceConfiguration.getInstance().setPlatinumBasePrice(roundedInput);
        System.out.printf("Saved! New base Platinum Class cinema ticket price: $");
        IOManager.printDouble(PriceConfiguration.getInstance().getPlatinumBasePrice(),2);
        System.out.printf("\n");
        IOManager.getUserInputString("Press any key to continue: ");
        this.activate();
    }

    /**
     * This method handles the setting of base gold price by the admin
     */
    private void handleSetBaseGoldTicketPrice(){

        System.out.printf("Current Gold Platinum Price: $");
        IOManager.printDouble(PriceConfiguration.getInstance().getGoldBasePrice(),2);
        System.out.printf("\n");
        double userInput = IOManager.getUserInputDouble("New base Gold Class cinema ticket price: ",0,Double.POSITIVE_INFINITY);
        BigDecimal bd = new BigDecimal(userInput).setScale(2, RoundingMode.HALF_UP);
        double roundedInput = bd.doubleValue();
        PriceConfiguration.getInstance().setGoldBasePrice(roundedInput);
        System.out.printf("Saved! New base Gold Class cinema ticket price: $");
        IOManager.printDouble(PriceConfiguration.getInstance().getGoldBasePrice(),2);
        System.out.printf("\n");
        IOManager.getUserInputString("Press any key to continue: ");
        this.activate();
    }

    /**
     * This method handles the setting of weekend price increment by the admin
     */
    private void handleSetWeekendPriceIncrement(){
        System.out.printf("Current additional price for Weekend ticket: $");
        IOManager.printDouble(PriceConfiguration.getInstance().getWeekendIncrement(),2);
        System.out.printf("\n");
        double userInput = IOManager.getUserInputDouble("New additional price for weekend ticket: ",0,Double.POSITIVE_INFINITY);
        BigDecimal bd = new BigDecimal(userInput).setScale(2, RoundingMode.HALF_UP);
        double roundedInput = bd.doubleValue();
        PriceConfiguration.getInstance().setWeekendIncrement(roundedInput);
        System.out.printf("Saved! New additional price for Weekend ticket: $");
        IOManager.printDouble(PriceConfiguration.getInstance().getWeekendIncrement(),2);
        System.out.printf("\n");
        IOManager.getUserInputString("Press any key to continue: ");
        this.activate();
    }

    /**
     * This method handles the setting of price increment of public holiday by the admin
     */
    private void handleSetPHPriceIncrement(){
        System.out.printf("Current additional price for Public Holiday ticket: $");
        IOManager.printDouble(PriceConfiguration.getInstance().getPublicHolidayIncrement(),2);
        System.out.printf("\n");
        double userInput = IOManager.getUserInputDouble("New addtional price for public holiday ticket: ",0,Double.POSITIVE_INFINITY);
        BigDecimal bd = new BigDecimal(userInput).setScale(2, RoundingMode.HALF_UP);
        double roundedInput = bd.doubleValue();
        PriceConfiguration.getInstance().setPublicHolidayIncrement(roundedInput);
        System.out.printf("Saved! New additional price for Public Holiday ticket: $");
        IOManager.printDouble(PriceConfiguration.getInstance().getPublicHolidayIncrement(),2);
        System.out.printf("\n");
        IOManager.getUserInputString("Press any key to continue: ");
        this.activate();
    }

    /**
     * This method handles the setting of price increment of 3D movie by the admin
     */
    private void handleSet3DPriceIncrement(){
        System.out.printf("Current additional price for 3D movie ticket: $");
        IOManager.printDouble(PriceConfiguration.getInstance().getThreeDMovieIncrement(),2);
        System.out.printf("\n");
        double userInput = IOManager.getUserInputDouble("New addtional price for 3D movie ticket: ",0,Double.POSITIVE_INFINITY);
        BigDecimal bd = new BigDecimal(userInput).setScale(2, RoundingMode.HALF_UP);
        double roundedInput = bd.doubleValue();
        PriceConfiguration.getInstance().setThreeDMovieIncrement(roundedInput);
        System.out.printf("Saved! New additional price for 3D Movie ticket: $");
        IOManager.printDouble(PriceConfiguration.getInstance().getThreeDMovieIncrement(),2);
        System.out.printf("\n");
        IOManager.getUserInputString("Press any key to continue: ");
        this.activate();

    }

    /**
     * This method handles the setting of price increment of blockbuster movie by the admin
     */
    private void handleSetBlockbusterPriceIncrement(){
        System.out.printf("Current additional price for Blockbuster Movie ticket: $");
        IOManager.printDouble(PriceConfiguration.getInstance().getBlockbusterMovieIncrement(),2);
        System.out.printf("\n");
        double userInput = IOManager.getUserInputDouble("New addtional price for Blockbuster Movie ticket price",0,Double.POSITIVE_INFINITY);
        BigDecimal bd = new BigDecimal(userInput).setScale(2, RoundingMode.HALF_UP);
        double roundedInput = bd.doubleValue();
        PriceConfiguration.getInstance().setBlockbusterMovieIncrement(roundedInput);
        System.out.printf("Saved! New additional price for Blockbuster Movie ticket: $");
        IOManager.printDouble(PriceConfiguration.getInstance().getBlockbusterMovieIncrement(),2);
        System.out.printf("\n");
        IOManager.getUserInputString("Press any key to continue: ");
        this.activate();
    }

    /**
     * This method handles the setting of percentage price discount for child ticket
     */
    private void handleSetChildTicketPercentageDiscount(){
        System.out.printf("Current Children Ticket Discount: ");
        IOManager.printDouble((1 - PriceConfiguration.getInstance().getChildPercentageOff()) * 100,2);
        System.out.printf("%% \n");
        double userInput = IOManager.getUserInputDouble("New Discount for Children Ticket: ",0,100);
        BigDecimal bd = new BigDecimal((100 - userInput) / 100).setScale(2, RoundingMode.HALF_UP);
        double roundedInput = bd.doubleValue();
        PriceConfiguration.getInstance().setChildPercentageOff(roundedInput);
        System.out.printf("Saved! New Children Ticket Discount: ");
        IOManager.printDouble((1 - PriceConfiguration.getInstance().getChildPercentageOff()) * 100,2);
        System.out.printf("%%\n");
        IOManager.getUserInputString("Press any key to continue: ");
        this.activate();
    }

    /**
     * This method handles the setting of percentage price discount for senior citizen ticket
     */
    private void handleSetSCTicketPercentageDiscount(){
        System.out.printf("Current Senior Citizen Ticket Discount: ");
        IOManager.printDouble((1 - PriceConfiguration.getInstance().getSeniorCitizenPercentageOff()) * 100,2);
        System.out.printf("%% \n");
        double userInput = IOManager.getUserInputDouble("New Discount for Senior Citizen Ticket: ",0,100);
        BigDecimal bd = new BigDecimal((100 - userInput) / 100).setScale(2, RoundingMode.HALF_UP);
        double roundedInput = bd.doubleValue();
        PriceConfiguration.getInstance().setSeniorCitizenPercentageOff(roundedInput);
         System.out.printf("Saved! New Senior Citizen Ticket Discount: ");
        IOManager.printDouble((1 - PriceConfiguration.getInstance().getSeniorCitizenPercentageOff()) * 100,2);
        System.out.printf("%%\n");
        IOManager.getUserInputString("Press any key to continue: ");
        this.activate();
    }

    /**
     * This method handles the setting of gst rate
     */
    private void handleSetGSTPercentageIncrease(){
        System.out.printf("Current GST Percentage: ");
        IOManager.printDouble(PriceConfiguration.getInstance().getGstPercentageIncrease() * 100,2);
        System.out.printf("%%" +  "\n");
        double userInput = IOManager.getUserInputDouble("New GST percentage:",0,100);
        BigDecimal bd = new BigDecimal(userInput / 100).setScale(2, RoundingMode.HALF_UP);
        double roundedInput = bd.doubleValue();
        PriceConfiguration.getInstance().setGstPercentageIncrease(roundedInput);
        System.out.printf("Saved! New GST percentage: ");
        IOManager.printDouble(PriceConfiguration.getInstance().getGstPercentageIncrease() * 100,2);
        System.out.printf("%% \n");
        IOManager.getUserInputString("Press any key to continue: ");
        this.activate();
    }
}