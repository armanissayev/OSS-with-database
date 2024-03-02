package controllers;

import entities.Products;
import repositories.ProductRepository;

import java.util.List;
import java.util.Scanner;

public class ProductController {
    private ProductRepository productRepository;
    private ProductFactory productFactory;

    public ProductController() {
        productRepository = ProductRepository.getInstance();
        productFactory = ProductFactory.getInstance();
    }

    public void addProduct(Scanner scanner) {
        Products product = createProductFromInput(scanner);
        productRepository.addProduct(product);
    }

    public void updateProduct(Scanner scanner) {
        System.out.print("Enter product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Products product = createProductFromInput(scanner);
        product.setId(id);
        productRepository.updateProduct(product);
    }

    public void deleteProduct(Scanner scanner) {
        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();
        productRepository.deleteProduct(id);
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

    private Products createProductFromInput(Scanner scanner) {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Чистим буфер

        // Создаем продукт с помощью фабричного метода
        return productFactory.createProduct(name, price, quantity);
    }
}
