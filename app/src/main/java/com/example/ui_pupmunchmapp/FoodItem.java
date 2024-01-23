package com.example.ui_pupmunchmapp;

public class FoodItem {

    String itemName, stallName;
    int foodImage, foodPrice;

    public FoodItem(String itemName, String stallName, int foodImage, int foodPrice){
        this.itemName = itemName;
        this.stallName = stallName;
        this.foodImage = foodImage;
        this.foodPrice = foodPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getStallName() {
        return stallName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(int foodImage) {
        this.foodImage = foodImage;
    }

    public int getFoodPrice() { return foodPrice; }

    public void setFoodPrice(int foodPrice) { this.foodPrice = foodPrice; }
}
