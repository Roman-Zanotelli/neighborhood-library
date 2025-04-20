package com.pluralsight.books;

import java.util.ArrayList;

public class BookStore {
    private final ArrayList<Book> inventory = new ArrayList<>();
    public BookStore(){
        String[] suffix_pool = {"\u001B[31mRed\u001B[0m", "\u001B[34mBlue\u001B[0m", "\u001B[32mGreen\u001B[0m", "\u001B[33mYellow\u001B[0m", "\u001B[35mPurple\u001B[0m"};
        String[] prefix_pool = {"\u001B[31mRedish\u001B[0m", "\u001B[34mBluish\u001B[0m", "\u001B[32mGreenish\u001B[0m", "\u001B[33mYellowish\u001B[0m"};
        String[] size_pool = {"Large", "Small", "Medium", "Slightly Large", "Slightly Small", "Slightly Medium"};
        for (String size : size_pool) {
            for (String prefix : prefix_pool) {
                for (String suffix : suffix_pool) {
                    inventory.add(new Book(String.format("\u001B[36m%s\u001B[0m %s %s \u001B[36mBook\u001B[0m",size, prefix, suffix)));
                }
            }
        }
    }

    public Book[] getAllCheckedOut() {
        return inventory.stream().filter(Book::isCheckedOut).toArray(Book[]::new);
    }
    public Book[] getAllAvailable() {
        return inventory.stream().filter(Book::isAvailable).toArray(Book[]::new);
    }

    public boolean checkInBook(int id) {
        return inventory.stream().anyMatch(book -> book.checkInIf(id)); //this will short circuit on the first valid book (this would break if multiple books are allowed to share id, but could be fixed by checking the checkedOutTO for the book to match the person checking it in)
    }
    public boolean checkOutBook(int id){
        return inventory.stream().anyMatch(book -> book.checkOutIf(id)); //this will short circuit on the first valid book
    }
}
