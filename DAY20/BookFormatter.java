package DAY20;

public class BookFormatter {
    public String formatTitle(Book book) {
        return "Title: " + book.getTitle().toUpperCase();
    }

    public String formatAuthor(Book book) {
        return "Author: " + book.getAuthor();
    }

    public String formatPrice(Book book) {
        return String.format("Price: $%.2f", book.getPrice());
    }
}
