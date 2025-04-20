package com.pluralsight;

import com.pluralsight.books.BookStore;
import com.pluralsight.menu.Menu;

public class Main {
    private static final BookStore store = new BookStore();
    public static void main(String[] args) {
        boolean running;
        do { //main lifecycle
            running = Menu.open(
                new Menu.Option.ExitProgram(),
                new Menu.Option.ShowAvailable(),
                new Menu.Option.ShowCheckedOut()

            );
        } while (running);
    }
    public static BookStore store(){
        return store;
    }
}
