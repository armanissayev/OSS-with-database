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
        System.out.println("Adding a new user:");
        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter user name: ");
        String name = scanner.next();
        System.out.print("Enter user email: ");
        String email = scanner.next();

        userRepository.addUser(new User(id, name, email));
        System.out.println("User added successfully.");
    }

    public void getAllUsers() {
        System.out.println("Getting all users:");
        List<User> users = userRepository.getAllUsers();
        for (User user : users) {
            System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Email: " + user.getEmail());
        }
    }

    public void updateUser(Scanner scanner) {
        System.out.println("Updating a user:");
        System.out.print("Enter user ID to update: ");
        int id = scanner.nextInt();
        if (userRepository.isUserExists(id)) {
            System.out.print("Enter new user name: ");
            String newName = scanner.next();
            System.out.print("Enter new user email: ");
            String newEmail = scanner.next();

            userRepository.updateUser(id, newName, newEmail);
            System.out.println("User updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    public void deleteUser(Scanner scanner) {
        System.out.println("Deleting a user:");
        System.out.print("Enter user ID to delete: ");
        int id = scanner.nextInt();
        if (userRepository.isUserExists(id)) {
            userRepository.deleteUser(id);
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }
}
