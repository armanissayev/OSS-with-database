package controllers;
import repositories.*;

public class ProductController {
    private ProductRepository productRepository;
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
