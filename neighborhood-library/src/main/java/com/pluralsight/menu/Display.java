package com.pluralsight.menu;

import com.pluralsight.books.Book;

//todo: in general it might be useful to create a "pretty string class" or use an external lib to format the string colors in a more readable/maintainable way (currently though I just do raw ANSI codes inside the strings)
class Display { //used for displaying information to the user
    static class MainMenu {
        private static String build(Menu.Option[] modes) { //builds the menu string
            final StringBuilder internal_builder = new StringBuilder();
            internal_builder.append("\u001B[1m").append("Main Menu:").append("\u001B[0m\n");
            for (int i = 0; i < modes.length; i++) {
                internal_builder
                        .append(String.format("\t\u001B[30m\u001B[47m%d", i)) //black text & white highlight index
                        .append("\u001B[0m - ") //reset -
                        .append(String.format("\u001B[1m\u001B[4m%s", modes[i].getDescription())) //underline & bold description
                        .append("\u001B[0m\n");//reset newline
            }
            return internal_builder.toString();
        }

        static void print(Menu.Option[] modes) { //prints the menu string from buildMenu()
            System.out.print(build(modes));
        }
    }

    static class Prompt {

        static void enterSelection() {//generic enter selection prompt
            System.out.print("Enter Selection: ");
        }

        static void checkExit(String message) {
            //this prints the formatted prompt for the C/X menu for both chek in and check out, the message is changed through the message variable allowing any function that need C/X options to use this function the same
            System.out.printf("\t\u001B[30m\u001B[47mC\u001B[0m - \u001B[1m\u001B[4m%s\u001B[0m\n\t\u001B[30m\u001B[47mX\u001B[0m - \u001B[1m\u001B[4mMain Menu\u001B[0m\n", message);
        }

        public static void bookID() {
            System.out.println("Please Enter a Book ID");
        }

        public static void name() {
            System.out.println("Please Enter a Name");
        }

        public static void enterToContinue() {
            System.out.print("\n\t\u001B[1m\u001B[4mPress Enter to Continue\u001B[0m\n");
        }
    }

    static class Alert{ //non-interactive functions used to display updates and information to the user that does not require response (such as Headers, Success/Failure, and other non-interactive UI elements)
        private static void printCheckAlertFormatted(String message){
            System.out.printf("\nYou Have Entered \u001B[1m\u001B[4m\u001B[33m%s\u001B[0m Mode!\n\n", message);
        }
        public static void checkIn() {
            printCheckAlertFormatted("CHECK IN");
        }

        public static void checkOut() {
            printCheckAlertFormatted("CHECK OUT");
        }

        public static void successOrFail(boolean success) { //prints Success!/Failed! based on the input bool
            if(success) System.out.println("\u001B[32mSuccess!\u001B[0m"); else  System.out.println("\u001B[31mFailed!\u001B[0m");
        }

        public static void listBooks(Book[] books){
            for(Book book : books){ //loop through each
                System.out.println(book.toString()); // print them out using the toString() method
            }
        }
        public static boolean noBooksFound(int len){
            if (len == 0){
                System.out.println("NO BOOKS FOUND"); //todo: replace with a Display.Alert
                Scan.Prompt.enterToContinueAndClear();
                return true; //exit back to main menu early
            }
            return false;
        }
        public static void loading(){
            try {
                System.out.print("\n\t\u001B[1m\u001B[4mLoading\u001B[0m ");
                System.out.print(". ");
                Thread.sleep(333);
                System.out.print(". ");
                Thread.sleep(666);
                System.out.print(".");
                Thread.sleep(999);
            }catch (Exception ignored){}
            System.out.print("\n\n");



        }
    }

    static void clear(){
        System.out.print("\033[H\033[2J\n\n");
        System.out.flush();
    }
}