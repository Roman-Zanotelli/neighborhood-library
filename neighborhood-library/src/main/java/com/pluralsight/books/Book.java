package com.pluralsight.books;

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
        this.id = (int)(Math.random() * 999999999) + 1;
        this.isbn = ""+(int)(Math.random() * 999999999) + 1;
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
        return String.format("\u001B[1mTitle:\u001B[0m %s - \u001B[1mBook ID:\u001B[0m \u001B[36m\u001B[4m%d\u001B[0m, \u001B[1mISBN:\u001B[0m \u001B[33m%s\u001B[0m%s", title, id, isbn, isCheckedOut ? ", \u001B[1mChecked Out By:\u001B[0m \u001B[36m\u001B[4m" + checkedOutTo + "\u001B[0m" : "");
    }

    public boolean checkInIf(int id) {
        if (this.id == id && isCheckedOut) { //check if the book id exists and if it's checked out (a book cant be checked in twice)
            this.isCheckedOut = false;
            this.checkedOutTo = "";
            return true; //return success
        }
        return false; //return fail
    }

    public boolean checkOutIf(int id, String checkOutTo) {
        if (this.id == id && !isCheckedOut) { //check if the book id exists and if it's checked in (a book cant be checked out twice)
            this.isCheckedOut = true;
            this.checkedOutTo = checkOutTo;
            return true; //return success
        }
        return false; //return fail
    }
}
