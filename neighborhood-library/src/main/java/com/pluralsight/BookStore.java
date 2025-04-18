package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class BookStore {
    private final ArrayList<Book> inventory = new ArrayList<>();
    BookStore(){
        String[] suffix_pool = {"\u001B[31mRed\u001B[0m", "\u001B[34mBlue\u001B[0m", "\u001B[32mGreen\u001B[0m", "\u001B[33mYellow\u001B[0m", "\u001B[35mPurple\u001B[0m"};
        String[] prefix_pool = {"\u001B[31mRedish\u001B[0m", "\u001B[34mBluish\u001B[0m", "\u001B[32mGreenish\u001B[0m", "\u001B[33mYellowish\u001B[0m"};
        for (String prefix : prefix_pool){
            for(String suffix : suffix_pool){
                inventory.add(new Book(String.format("%s %s Book", prefix, suffix)));
            }
        }
    }

    public Book[] getAllCheckedOut() {
        return inventory.stream().filter(Book::isCheckedOut).toArray(Book[]::new);
    }
    public Book[] getAllAvailable() {
        return inventory.stream().filter(Book::isAvailable).toArray(Book[]::new);
    }

    public boolean checkInBook(int i) {
        return inventory.stream().anyMatch(book -> book.checkInIf(i));
    }
}
