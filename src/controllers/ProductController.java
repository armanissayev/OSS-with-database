package controllers;

import entities.Products;
import repositories.ProductRepository;

import java.util.List;
import java.util.Scanner;

public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Scanner scanner) {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        productRepository.addProduct(name, price, quantity);
    }

    public void getAllProducts() {
        List<Products> productsList = productRepository.getAllProducts();
        for (Products product : productsList) {
            System.out.println("ID: " + product.getId() +
                    ", Name: " + product.getName() +
                    ", Price: " + product.getPrice() +
                    ", Quantity: " + product.getQuantity());
        }
    }

    public void updateProduct(Scanner scanner) {
        System.out.print("Enter product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter new product quantity: ");
        int quantity = scanner.nextInt();
        productRepository.updateProduct(id, name, price, quantity);
    }

    public void deleteProduct(Scanner scanner) {
        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();
        productRepository.deleteProduct(id);
    }
}
