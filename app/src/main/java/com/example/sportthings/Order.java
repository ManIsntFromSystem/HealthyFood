package com.example.sportthings;

public class Order {
    private String name;
    private String nameProduct;
    private int Quantity;
    private double totalPrice;

    /*public Order(String name, String nameProduct, int quantity, double totalPrice) {
        this.name = name;
        this.nameProduct = nameProduct;
        Quantity = quantity;
        this.totalPrice = totalPrice;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
