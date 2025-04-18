package com.pluralsight;

import java.util.Scanner;

public class Menu {
    private static final BookStore store = new BookStore();
    private static final Scanner scanner = new Scanner(System.in);

    static boolean open(Option... modes){ //dynamic mode selection that increases as more modes are added
        Display.printMenu(modes);
        int selection; //selection variable
        do {
            selection = Prompt.nextInt(); //Prompts user to enter value and handles invalid input types
        } while(!(0 <= selection && selection < modes.length)); //catch out of bounds by looping
        return modes[selection].run(); //run the selected mode
    }

    private static class Prompt{
        private static int nextInt(){
            Display.printSelectionPrompt();
            while (!scanner.hasNextInt()){ //checks if valid int
                scanner.nextLine();
                Display.printSelectionPrompt();
            }
            int res = scanner.nextInt();
            scanner.nextLine();
            return res;
        }
        private static boolean checkOrReturn(String message){ //false if you should return to main menu
            String res;
            Display.printCheckExit(message);
            do{
                Display.printSelectionPrompt();
                res = scanner.nextLine().trim();
            } while(!(res.equalsIgnoreCase("c") || res.equalsIgnoreCase("x")));
            return res.equalsIgnoreCase("c");
        }
    }



    //available modes
    public static abstract class Option{
        String menu_desc;
        Option(String menu_desc){
            this.menu_desc = menu_desc;
        }
        String getDescription(){
            return menu_desc;
        }
        abstract boolean run();


        final static class ShowAvailable extends Option{
            ShowAvailable() {
                super("Show Available Books");
            }

            @Override
            boolean run() {
                for(Book book : store.getAllAvailable()){
                    System.out.println(book.toString());
                }
                if (Prompt.checkOrReturn("Check Out A Book")){

                }
                return true;
            }
        }


        final static class ShowCheckedOut extends Option {
            ShowCheckedOut() {
                super("Show Checked Out Books");
            }

            @Override
            public boolean run() {
                for(Book book : store.getAllCheckedOut()){
                    System.out.println(book.toString());
                }
                if (Prompt.checkOrReturn("Check In A Book")){
                    Display.printCheckIn();
                    Display.printSucess(store.checkInBook(Prompt.nextInt()));
                }
                return true;
            }
        }

        final static class ExitProgram extends Option {
            ExitProgram() {
                super("Exit Program");
            }

            @Override
            public boolean run() {
                System.out.println("GoodBye!");
                return false;
            }
        }
    }

}
