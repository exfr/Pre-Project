package model;

public class Car {
    private String model;  // оформить как константы?
    private int price;
    private int maxSpeed;

    public Car(String model, int price, int maxSpeed) {
        this.model = model;
        this.price = price;
        this.maxSpeed = maxSpeed;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }
}
