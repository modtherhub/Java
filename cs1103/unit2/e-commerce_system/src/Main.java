import com.ecommerce.Product;
import com.ecommerce.Customer;
import com.ecommerce.orders.Order;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {

            // Create Products
            Product laptop = new Product("P101", "Laptop", 1200.00, 5);
            Product phone = new Product("P102", "Smartphone", 800.00, 10);
            Product headphones = new Product("P103", "Headphones", 150.00, 15);

            // Display Products
            System.out.println("Available Products:");
            System.out.println(laptop);
            System.out.println(phone);
            System.out.println(headphones);

            // Create Customer
            Customer customer = new Customer("C001", "Modther");

            // Add products to cart
            customer.addProductToCart(laptop);
            customer.addProductToCart(phone);
            customer.addProductToCart(headphones);

            // Display cart
            customer.displayCart();

            // Place Order
            Order order = new Order("O1001", customer, new ArrayList<>(customer.getShoppingCart()));
            order.generateOrderSummary();

            // Update Order Status
            order.updateStatus("Completed");
            order.generateOrderSummary();

            customer.clearCart();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}