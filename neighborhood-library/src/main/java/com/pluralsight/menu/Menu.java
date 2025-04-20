package com.pluralsight.menu;

import com.pluralsight.books.Book;

import static com.pluralsight.Main.store;

public class Menu {
    public static boolean open(Option... modes){ //dynamic mode selection that increases as more modes are added
        Display.MainMenu.print(modes);
        int selection; //selection variable
        do {
            selection = Scan.Prompt.nextInt(); //Prompts user to enter value and handles invalid input types
        } while(!(0 <= selection && selection < modes.length)); //catch out of bounds by looping
        Display.Alert.loading();
        return modes[selection].run(); //run the selected mode
    }


    //available modes
    public static abstract class Option{
        String menu_desc; //the description of the mode used by the main menu
        Option(String menu_desc){ //constructor used to set the menu_desc (instances of this class use parameterless constructors that super this with preset value)
            this.menu_desc = menu_desc;
        }
        String getDescription(){ //default method used to automatically retrieve descriptions
            return menu_desc;
        }
        abstract boolean run(); //actual logic implemented by inherits


        public final static class ShowAvailable extends Option{ //shows all checked in books and options for interacting with them
            public ShowAvailable() {
                super("Show Available Books");
            }

            @Override
            boolean run() {

                //gets all available books
                Book[] currently_available = store().getAllAvailable();

                //exit early if no books are found
                if (Display.Alert.noBooksFound(currently_available.length)) return true;

                //Display books found
                Display.Alert.listBooks(currently_available);

                //handles optional check-out
                Scan.Check.out();

                //Asks the user to press enter before returning to the menu
                //Scan.Prompt.enterToContinueAndClear();
                return true; //tell main loop to continue
            }
        }


        public final static class ShowCheckedOut extends Option { //shows all checked out books and options for interacting with them
            public ShowCheckedOut() {
                super("Show Checked Out Books");
            }

            @Override
            public boolean run() {

                //get all checked out books
                Book[] checked_out = store().getAllCheckedOut();

                //exit early if no books are found
                if (Display.Alert.noBooksFound(checked_out.length)) return true;

                //list found books
                Display.Alert.listBooks(checked_out);

                //handles optional check-in
                Scan.Check.in();

                //Asks the user to press enter before returning to the menu
                //Scan.Prompt.enterToContinueAndClear();
                return true; //tell main loop to continue
            }
        }

        public final static class ExitProgram extends Option { //exits the program
            public ExitProgram() {
                super("Exit Program");
            }

            @Override
            public boolean run() {
                System.out.println("GoodBye!");
                return false; //tell main loop to stop
            }
        }

        public final static class AddBook extends Option{

            public AddBook() {
                super("Add New Book");
            }

            @Override
            boolean run() {
                System.out.println("Currently WIP");
                Scan.Prompt.enterToContinue();
                return true;
            }
        }
    }

}
