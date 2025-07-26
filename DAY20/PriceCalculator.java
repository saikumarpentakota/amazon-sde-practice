package DAY20;

public class PriceCalculator {
    public double calculateDiscountedPrice(Book book, double discountPercentage) {
        return book.getPrice() * (1 - discountPercentage);
    }

    public double calculateTax(Book book, double taxRate) {
        return book.getPrice() * taxRate;
    }

    public double calculateFinalPrice(Book book, double discountPercentage, double taxRate) {
        double discountedPrice = calculateDiscountedPrice(book, discountPercentage);
        return discountedPrice * (1 + taxRate);
    }
}
