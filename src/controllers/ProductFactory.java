package controllers;

import entities.Products;

public class ProductFactory {
    public Products createProduct(String name, double price, int quantity) {
        Products product = new Products();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        return product;
    }
}
