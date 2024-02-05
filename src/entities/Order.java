package entities;

public class Order {

    private int userId;
    private int productId;
    private int quantity;
    private double cost;

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
