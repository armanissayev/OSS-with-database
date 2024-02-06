package controllers;
import entities.User;
import repositories.*;
import java.util.Scanner;

public class OrderController {
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
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
        double price = productRepository.getPriceById(productId);
        int productQuantity = productRepository.getQuantityById(productId);

        double cost = price * quantity;
        if (userBalance < cost) {
            System.out.println("Not enough money! Please try again.");
            return;
        } else if (productQuantity < quantity) {
            System.out.println(productQuantity);
            System.out.println("Not enough products! Please try again.");
            return;
        } else {
            userRepository.setBalanceById(userId, userBalance - cost);
            productRepository.setQuantityById(productId, productQuantity - quantity);
            orderRepository.addOrder(userId, productId, quantity, cost);
        }
    }

    public void getAllOrders() {
        orderRepository.getAllOrders(userRepository, productRepository);
    }
}
