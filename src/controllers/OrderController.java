package controllers;
import entities.User;
import repositories.*;
import java.util.Scanner;

public class OrderController {
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository
    public OrderController(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public void createOrder(Scanner scanner) {
        System.out.print("Enter ID of the user: ");
        int userId = scanner.nextInt();
        System.out.print("Enter ID of the product: ");
        int productId = scanner.nextInt();
        System.out.print("Enter the quantity: ");
        int quantity = scanner.nextInt();

        double userBalance = userRepository.getBalanceById(userId);
        double price = ProductRepository.getPriceById(productId);

        double cost = price * quantity;
        if (userBalance < cost) {
            System.out.println("Not enough money! Please try again.");
            return;
        } else {
            userRepository.deductBalanceById(userId, cost);
            productRepository.deductQuantityById(productId, cost);
            orderRepository.addOrder(userId, productId, quantity, cost);
        }
    }
}
