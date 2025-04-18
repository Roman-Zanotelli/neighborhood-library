package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        boolean running;
        do { //main lifecycle
            running = Menu.open(
                new Menu.Option.ShowCheckedOut(),
                new Menu.Option.ShowAvailable(),
                new Menu.Option.ExitProgram()
            );
        } while (running);
    }
}
