package controllers;

import entities.Products;

public class ProductFactory {
    private static ProductFactory instance;
    public Products createProduct(String name, double price, int quantity) {
        Products product = new Products();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        return product;
    }
    public static synchronized ProductFactory getInstance() {
        if (instance == null) {
            instance = new ProductFactory();
        }
        return instance;
    }
}
