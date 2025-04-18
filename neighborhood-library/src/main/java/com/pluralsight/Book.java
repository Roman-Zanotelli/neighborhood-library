package com.pluralsight;

public class Book {
    private int id;
    private String isbn, title, checkedOutTo;
    boolean isCheckedOut;

    public Book(int id, String isbn, String title){
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.checkedOutTo = "";
        this.isCheckedOut = false;
    }
    public Book(String title){
        this.title = title;
        this.id = (int)(Math.random() * 999999) + 1;
        this.isbn = ""+(int)(Math.random() * 999999) + 1;
        this.checkedOutTo = "";
        this.isCheckedOut = false;
    }

    public boolean checkOut(String name){ //returns false if already checked out
        if (isCheckedOut){ //checks if book is already checked out
            return false; //exit early
        }
        this.isCheckedOut = true;
        checkedOutTo = name;
        return true;
    }
    public String checkIn(){ //returns checkedOutTo if it was checked out
        isCheckedOut = false;
        String old = checkedOutTo;
        checkedOutTo = "";
        return old;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getId() {
        return id;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public boolean isAvailable() {
        return !isCheckedOut;
    }

    @Override
    public String toString(){
        return String.format("Title: %s - Book ID: %d ISBN: %s%s", title, id, isbn, isCheckedOut ? checkedOutTo : "");
    }

    public boolean checkInIf(int i) {
        return false;
    }
}
