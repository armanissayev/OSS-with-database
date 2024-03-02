package entities;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String username;
    private Type type;

    public User(int id, String username, int age, double balance) {
        Type type = LightWeight.getType(age, balance);
        this.id=  id;
        this.username = username;
        this.type = type;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return type.getAge();
    }

    public double getBalance() {
        return type.getBalance();
    }
}

class Type {
    private int age;
    private double balance;

    public Type(int age, double balance) {
        this.age = age;
        this.balance = balance;
    }

    public int getAge() { return age; }
    public double getBalance() { return balance; }
}

class LightWeight {
    private static List<Type> typesList = new ArrayList<>();

    public static Type getType(int age, double balance) {
        Type type = findInList(age, balance);
        if (type == null) {
            type = new Type(age, balance);
            typesList.add(type);
        }
        return type;
    }

    private static Type findInList(int age, double balance) {
        for (Type curType : typesList) {
            if (curType.getAge() == age && curType.getBalance() == balance) {
                return curType;
            }
        }
        return null;
    }
}