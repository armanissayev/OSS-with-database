package controllers;

import repositories.UserRepository;
import entities.User;
import java.util.Scanner;
import java.util.List;

public class UserController {
    private UserRepository userRepository;
    private AddUser addUser;
    private UpdateUser updateUser;
    private DeleteUser deleteUser;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(Scanner scanner) {
        addUser.addUser(scanner, userRepository);
    }

    public void getAllUsers() {
        userRepository.getAllUsers();
    }

    public void updateUser(Scanner scanner) {
        updateUser.updateUser(scanner, userRepository);
    }

    public void deleteUser (Scanner scanner) {
        deleteUser.deleteUser(scanner, userRepository);
    }

}

class AddUser {
    public void addUser(Scanner scanner, UserRepository userRepository) {
        System.out.print("Enter username: ");
        String name = scanner.next();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        System.out.print("Enter balance: ");
        double balance = scanner.nextDouble();

        userRepository.addUser(name, age, balance);
    }
}

class UpdateUser {
    public void updateUser(Scanner scanner, UserRepository userRepository) {
        System.out.print("Enter user ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new username: ");
        String newName = scanner.next();
        System.out.print("Enter new age: ");
        int newAge = scanner.nextInt();
        System.out.println("Enter new balance: ");
        double newBalance = scanner.nextDouble();

        userRepository.updateUserById(id, newName, newAge, newBalance);
    }
}

class DeleteUser {
    public void deleteUser(Scanner scanner, UserRepository userRepository) {
        System.out.print("Enter user ID to delete: ");
        int id = scanner.nextInt();
        userRepository.deleteUserById(id);
    }
}