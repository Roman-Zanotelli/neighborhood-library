package com.pluralsight;

public class Display { //used for displaying information to the user
    //Menu
    private static String buildMenu(Menu.Option[] modes) { //builds the menu string
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
    static void printMenu(Menu.Option[] modes){ //prints the menu string from buildMenu()
        System.out.print(buildMenu(modes));
    }

    //other displays
    static void printSelectionPrompt(){
        System.out.print("Enter Selection: ");
    }

    static void printCheckExit(String message){
        System.out.printf("\t\u001B[30m\u001B[47mC\u001B[0m - \u001B[1m\u001B[4m%s\u001B[0m\n\t\u001B[30m\u001B[47mX\u001B[0m - \u001B[1m\u001B[4mReturn to Main Menu\u001B[0m\n", message);
    }

    public static void printCheckIn() {
    }

    public static void printSucess(boolean suc) {
        if(suc) System.out.println("\u001B[32mSuccess!\u001B[0m"); else  System.out.println("\u001B[31mFailed!\u001B[0m");
    }
}