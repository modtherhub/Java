package com.ecommerce;

public class Product {

    private String productID;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(String productID, String name, double price, int stockQuantity) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative.");
        }
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    public String getProductID() {
        return productID;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStockQuantity() {
        return stockQuantity;
    }
    public void reduceStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (quantity > stockQuantity) {
            throw new IllegalArgumentException("Insufficient stock available.");
        }
        stockQuantity -= quantity;
    }
    public void increaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        stockQuantity += quantity;
    }
    @Override
    public String toString() {
        return "Product ID: " + productID +
                "\nName: " + name +
                "\nPrice: $" + price +
                "\nStock: " + stockQuantity +
                "\n----------------------";
    }
}