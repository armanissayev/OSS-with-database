package controllers;

import entities.Products;
import repositories.ProductRepository;

import java.util.List;
import java.util.Scanner;

public class ProductController {
    private ProductRepository productRepository;
    private AddProduct addProduct;
    private GetAllProducts getAllProducts;
    private DeleteProduct deleteProduct;
    private UpdateProduct updateProduct;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.addProduct = addProduct;
    }

    public void addProduct(Scanner scanner) {
        addProduct.addProduct(scanner, productRepository);
    }

    public void getAllProducts() {
        getAllProducts.getAllProducts(productRepository);
    }

    public void updateProduct(Scanner scanner) {
        updateProduct.updateProduct(scanner, productRepository);
    }

    public void deleteProduct(Scanner scanner) {
        deleteProduct.deleteProduct(scanner, productRepository);
    }
}

class AddProduct {
    public void addProduct(Scanner scanner, ProductRepository productRepository) {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        productRepository.addProduct(name, price, quantity);
    }
}

class GetAllProducts {
    public void getAllProducts(ProductRepository productRepository) {
        List<Products> productsList = productRepository.getAllProducts();
        for (Products product : productsList) {
            System.out.println("ID: " + product.getId() +
                    ", Name: " + product.getName() +
                    ", Price: " + product.getPrice() +
                    ", Quantity: " + product.getQuantity());
        }
    }
}

class UpdateProduct {
    public void updateProduct(Scanner scanner, ProductRepository productRepository) {
        System.out.print("Enter product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter new product quantity: ");
        int quantity = scanner.nextInt();
        productRepository.updateProduct(id, name, price, quantity);
    }
}

class DeleteProduct {
    public void deleteProduct(Scanner scanner, ProductRepository productRepository) {
        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();
        productRepository.deleteProduct(id);
    }
}
