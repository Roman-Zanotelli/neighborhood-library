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

        static void cOrX(String header, String messageC, String messageX) {
            //this prints the formatted prompt for the C/X menu for both chek in and check out, the message is changed through the message variable allowing any function that need C/X options to use this function the same
            System.out.printf("\n\u001B[1m%s\u001B[0m\n\t\u001B[30m\u001B[47mC\u001B[0m - \u001B[1m\u001B[4m%s\u001B[0m\n\t\u001B[30m\u001B[47mX\u001B[0m - \u001B[1m\u001B[4m%s\u001B[0m\n", header, messageC, messageX);
        }

        public static void bookID() {
            System.out.println("Please Enter a Book ID");
        }

        public static void name() {
            System.out.println("Please Enter a Name");
        }

        public static void enterToContinue() {
            System.out.print("\n\u001B[1m\u001B[4mPress Enter to Continue\u001B[0m\n");
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

        public static void successOrFail(boolean success, String successMessage, String failMessage) { //prints Success!/Failed! based on the input bool
            if(success) System.out.printf("\u001B[32m%s\u001B[0m\n", successMessage); else  System.out.printf("\u001B[33m%s\u001B[0m\n", failMessage);
        }

        public static void listBooks(Book[] books){
            for(Book book : books){ //loop through each
                 printBook(book);
            }
        }

        public static void printBook(Book book){
            System.out.println(book.toString());
        }

        public static boolean noBooksFound(int len){
            if (len == 0){
                System.out.println("\u001B[33mNO BOOKS FOUND\u001B[0m");
                Scan.Prompt.enterToContinueAndClear();
                return true; //exit back to main menu early
            }
            return false;
        }

        public static void loading(){ //todo: experiment with load times for a smoother feel

            try {
                System.out.print("\n\t\u001B[1m\u001B[4mLoading\u001B[0m ");
                Thread.sleep(111);
                System.out.print(". ");
                Thread.sleep(444);
                System.out.print(". ");
                Thread.sleep(777);
                System.out.print(".");
                Thread.sleep(111);

            }catch (Exception ignored){}

            System.out.print("\n\n");

        }

        public static void bookFound() {
            System.out.println("Book Found: ");
        }
    }

    static void clear(){
        System.out.print("\033[H\033[2J\n\n");
        System.out.flush();
    }
}