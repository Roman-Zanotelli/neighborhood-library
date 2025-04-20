package com.pluralsight.menu;

import com.pluralsight.books.Book;

import java.util.Scanner;

import static com.pluralsight.Main.store;

public class Scan {
    private static final Scanner scanner = new Scanner(System.in);


    public static class Prompt {

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
        private static boolean cOrX(String header, String messageC, String messageX) { //false if you should return to main menu
            String res;
            Display.Prompt.cOrX(header, messageC, messageX); //displays the options with the appropriate message
            do {
                Display.Prompt.enterSelection(); //displays the selection area
                res = scanner.nextLine().trim(); //gets the selections
            } while (!(res.equalsIgnoreCase("c") || res.equalsIgnoreCase("x"))); //ensures the selection is vali
            Display.Alert.loading();
            return res.equalsIgnoreCase("c"); //returns if the user wants to check in/out/try-again
        }

        public static String checkOutName(){
            String name;
            do {
                Display.Prompt.name();
                name = Prompt.nextStringStripped();
            }while((name.isBlank() || !Prompt.cOrX("\u001B[33mConfirm Name?\u001B[0m " + name,"\u001B[32mConfirm", "\u001B[31mCancel")) && Prompt.cOrX("Retry Options","Retry Name", "Cancel to Check-Out"));
            return name;
        }

        public static boolean confirmBook(Book book) {
            Display.Alert.loading();
            Display.Alert.bookFound();
            return cOrX("\u001B[33mConfirm Book?\u001B[0m " + book.toString(),"\u001B[32mConfirm", "\u001B[31mCancel");
        }
    }

    static class Check{ //logic for checking in/out from scanner
        //Check in
        static void in(){
            //Handle further interaction
            if (Prompt.cOrX("Check-In Options","Check-In", "Return to Menu")){ //checks length first to short circuit prompt, handles initial check out selection
                Display.Alert.checkIn(); //Alerts the user they are in check in mode

                boolean success;
                do {
                    Display.Prompt.bookID(); //Visual prompt for user to enter id info (Without input field display)
                    int id = Scan.Prompt.nextInt(); //scans the next int with error handling (includes input field display)
                    success = store().checkInBook(id); //handles check in
                    Display.Alert.successOrFail(success, "Book Checked In!", "No Book Checked In!"); //Alerts if the interaction was a success or a fail
                } while (!success && Prompt.cOrX("Check-In Options","Try Again", "Return to Menu")); //checks if the input failed and asks if they want to try again if so
            }
        }

        //Check out
        static void out(){
            //Handle further interactions
            if (Prompt.cOrX("Check-Out Options","Check-Out", "Return to Menu")){ //checks length first to short circuit prompt, handles initial check out selection
                Display.Alert.checkOut(); //Alerts the user they are in check out mode

                boolean success;
                do {

                    Display.Prompt.bookID(); //Visual prompt for the desired book ID (Without input field)
                    int id = Scan.Prompt.nextInt(); //scans next int input (Includes visual input field)
                    success = store().checkOutBook(id); //handles check out
                    Display.Alert.successOrFail(success, "Book Checked Out!", "No Book Checked Out!"); //Alerts if interaction was a success or fail

                } while (!success && Prompt.cOrX("Check-Out Options","Try Again", "Return to Menu")); //checks if the input failed and asks if they want to try again if so
            }
        }

        //dynamic prompt for both check in, out & try again


    }

}