package com.example.ui_pupmunchmapp;

public class OrderItem {
    private String title;
    private int numberInCart;
    private double totalPrice;
    private String imagePath;
    private String userName;

    public OrderItem() {
    }

    public OrderItem(String title, int numberInCart, double totalPrice, String imagePath, String userName) {
        this.title = title;
        this.numberInCart = numberInCart;
        this.totalPrice = totalPrice;
        this.imagePath = imagePath;
        this.userName = userName;
    }


    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
