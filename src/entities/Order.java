package entities;

public class Order {
    private int id;

    private int userId;
    private int productId;
    private int quantity;
    private double cost;

    public Order(int id, int userId, int productId, int quantity, double cost) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.cost = cost;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCost() {
        return cost;
    }

    // Setters
    public void setUser(int userId) {
        this.userId = userId;
    }

    public void setProduct(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCost(double price) {
        this.cost = price;
    }

}
