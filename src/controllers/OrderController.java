package controllers;
import entities.User;
import repositories.*;
import java.util.Scanner;
import entities.*;
import java.sql.Connection;

public class OrderController {
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private CreateOrder createOrder;
    private CancelOrder cancelOrder;
    public OrderController() {
        orderRepository = OrderRepository.getInstance();
        userRepository = UserRepository.getInstance();
        productRepository = ProductRepository.getInstance();
        createOrder = new CreateOrder();
        cancelOrder = new CancelOrder();
    }

    public void createOrder(Scanner scanner) {
        this.createOrder.createOrder(scanner, userRepository, productRepository, orderRepository);
    }

    public void getAllOrders() {
        orderRepository.getAllOrders(userRepository, productRepository);
    }

    public void cancelOrder(Scanner scanner) {
        this.cancelOrder.cancelOrder(scanner, userRepository, productRepository, orderRepository);
    }
}

class CreateOrder {
    public void createOrder(Scanner scanner, UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository) {
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
            System.out.printf("%d %f %f\n", productQuantity, userBalance, price);
            System.out.println("Not enough products! Please try again.");
            return;
        } else {
            userRepository.setBalanceById(userId, userBalance - cost);
            productRepository.setQuantityById(productId, productQuantity - quantity);
            orderRepository.addOrder(userId, productId, quantity, cost);
        }
    }
}

class CancelOrder {
    public void cancelOrder(Scanner scanner, UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        System.out.print("Enter ID of the order to cancel: ");
        int id = scanner.nextInt();

        Order order = orderRepository.getOrderById(id);
        double userBalance = userRepository.getBalanceById(id);
        int productQuantity = productRepository.getQuantityById(id);

        userRepository.setBalanceById(order.getUserId(), userBalance + order.getCost());
        productRepository.setQuantityById(order.getProductId(), productQuantity + order.getQuantity());
        orderRepository.deleteOrderById(id);
    }
}
