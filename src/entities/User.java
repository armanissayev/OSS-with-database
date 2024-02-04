package entities;

public class User {

    private int id;
    private String username;
    private int age;
    private double balance;

    // Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public double getBalance() {
        return balance;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
