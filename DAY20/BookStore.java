package DAY20;

public class BookStore {
    public static void main(String[] args) {

        Book book = new Book("Java Programming", "John Doe", 29.99);
        BookFormatter formatter = new BookFormatter();
        PriceCalculator calculator = new PriceCalculator();


        System.out.println(formatter.formatTitle(book));
        System.out.println(formatter.formatAuthor(book));
        System.out.println(formatter.formatPrice(book));


        double discountPercentage = 0.1;
        double taxRate = 0.05;

        System.out.println("Discounted Price: $" +
                calculator.calculateDiscountedPrice(book, discountPercentage));
        System.out.println("Final Price with Tax: $" +
                calculator.calculateFinalPrice(book, discountPercentage, taxRate));
    }
}
