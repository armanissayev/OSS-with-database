package controllers;

import repositories.UserRepository;
import entities.User;
import java.util.Scanner;
import java.util.List;

public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String name = scanner.next();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        System.out.print("Enter balance: ");
        double balance = scanner.nextDouble();

        userRepository.addUser(name, age, balance);
    }

    public void getAllUsers() {
        userRepository.getAllUsers();
    }

    public void updateUser(Scanner scanner) {
        System.out.print("Enter user ID to update: ");
        int id = scanner.nextInt();
        System.out.print("Enter new username: ");
        String newName = scanner.next();
        System.out.print("Enter new age: ");
        int newAge = scanner.nextInt();
        System.out.println("Enter new balance: ");
        double newBalance = scanner.nextDouble();

        userRepository.updateUserById(id, newName, newAge, newBalance);
    }

public void deleteUser(Scanner scanner) {
    System.out.print("Enter user ID to delete: ");
    int id = scanner.nextInt();
    userRepository.deleteUserById(id);
}

}
