// Generic Library Item Class
public class LibraryItem<T> {

    private String title;
    private String author;
    private T itemID;

    // Constructor
    public LibraryItem(String title, String author, T itemID) {
        this.title = title;
        this.author = author;
        this.itemID = itemID;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public T getItemID() {
        return itemID;
    }

    @Override
    public String toString() {
        return "Item ID: " + itemID +
               ", Title: " + title +
               ", Author: " + author;
    }
}