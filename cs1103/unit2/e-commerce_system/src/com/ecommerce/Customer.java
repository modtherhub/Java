package com.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String customerID;
    private String name;
    private List<Product> shoppingCart;

    public Customer(String customerID, String name) {
        this.customerID = customerID;
        this.name = name;
        this.shoppingCart = new ArrayList<>();
    }
    public String getCustomerID() {
        return customerID;
    }
    public String getName() {
        return name;
    }
    public void addProductToCart(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (product.getStockQuantity() <= 0) {
            throw new IllegalArgumentException("Product is out of stock.");
        }
        shoppingCart.add(product);
        product.reduceStock(1);
    }
    public void removeProductFromCart(Product product) {
        if (shoppingCart.remove(product)) {
            product.increaseStock(1);
        } else {
            throw new IllegalArgumentException("Product not found in cart.");
        }
    }
    public double calculateTotal() {
        double total = 0;
        for (Product product : shoppingCart) {
            total += product.getPrice();
        }
        return total;
    }
    public List<Product> getShoppingCart() {
        return shoppingCart;
    }
    public void clearCart() {
        shoppingCart.clear();
    }
    public void displayCart() {
        System.out.println("Shopping Cart for " + name + ":");
        for (Product product : shoppingCart) {
            System.out.println(product.getName() + " - $" + product.getPrice());
        }
        System.out.println("Total: $" + calculateTotal());
        System.out.println("----------------------");
    }
}