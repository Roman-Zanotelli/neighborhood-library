package com.pluralsight.menu;

import java.util.Scanner;

import static com.pluralsight.Main.store;

class Scan {
    private static final Scanner scanner = new Scanner(System.in);
    static class Prompt {

        static int nextInt() {
            Display.Prompt.enterSelection();

            while (!scanner.hasNextInt()) { //checks if valid int
                scanner.nextLine();
                Display.Prompt.enterSelection();
            }

            int res = scanner.nextInt();
            scanner.nextLine();

            return res;
        }

        static String nextStringStripped() { //todo: check the result to make sure its not empty
            Display.Prompt.enterSelection();
            return scanner.nextLine().trim();
        }

        static void enterToContinue(){ //prompt the user to press enter to continue
            Display.Prompt.enterToContinue(); //actual display for the user
            scanner.nextLine();
        }
        static void enterToContinueAndClear(){ //same as above but calls Display.clear() immediately after to try and clear the terminal if allowed
            enterToContinue();
            Display.clear();
        }

    }

    static class Check{ //logic for checking in/out from scanner
        //Check in
        static void in(){
            //Handle further interaction
            if (orReturn("Check In")){ //checks length first to short circuit prompt, handles initial check out selection
                Display.Alert.checkIn(); //Alerts the user they are in check in mode

                boolean success;
                do {
                    success = store().checkInBook(inInfo()); //handles check in
                    Display.Alert.successOrFail(success); //Alerts if the interaction was a success or a fail
                } while (!success && orReturn("Try Again")); //checks if the input failed and asks if they want to try again if so
            }
        }
        //INFO scanner used by check in
        private static int inInfo(){
            Display.Prompt.bookID(); //Visual prompt for user to enter id info (Without input field display)
            return Scan.Prompt.nextInt(); //scans the next int with error handling (includes input field display)
        }

        //Check out
        static void out(){
            //Handle further interactions
            if (orReturn("Check Out")){ //checks length first to short circuit prompt, handles initial check out selection
                Display.Alert.checkOut(); //Alerts the user they are in check out mode

                boolean success;
                do {
                    OutResult result = outInfo(); //handles display and getting the information from the user
                    success = store().checkOutBook(result.id(), result.outTo()); //handles check out
                    Display.Alert.successOrFail(success); //Alerts if interaction was a success or fail

                } while (!success && orReturn("Try Again")); //checks if the input failed and asks if they want to try again if so
            }
        }
        //INFO scanner used by check out
        private static OutResult outInfo(){ //todo: add an earlier check to see if the id is valid before prompting for a name
            Display.Prompt.bookID(); //Visual prompt for the desired book ID (Without input field)
            int id = Scan.Prompt.nextInt(); //scans next int input (Includes visual input field)

            Display.Prompt.name();//Visual prompt for their name (Without input field)
            String checkedOutTo = Scan.Prompt.nextStringStripped(); //scans next string and trims in (including visual input field)
            return new OutResult(id, checkedOutTo);
        }
        private record OutResult(int id, String outTo){} //record for the checkOut scan


        //dynamic prompt for both check in, out & try again
        private static boolean orReturn(String message) { //false if you should return to main menu
            String res;
            Display.Prompt.checkExit(message); //displays the options with the appropriate message
            do {
                Display.Prompt.enterSelection(); //displays the selection area
                res = scanner.nextLine().trim(); //gets the selections
            } while (!(res.equalsIgnoreCase("c") || res.equalsIgnoreCase("x"))); //ensures the selection is vali
            Display.Alert.loading();
            return res.equalsIgnoreCase("c"); //returns if the user wants to check in/out/try-again
        }

    }

}