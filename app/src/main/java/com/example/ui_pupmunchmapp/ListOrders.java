package com.example.ui_pupmunchmapp;

public class ListOrders {
    String stallName, orderRefNum, customerName, date, time; // itemName;
    //int itemNum, quantity, price, total;

    public ListOrders(String stallName, String orderRefNum, String customerName, String date, String time) {
        this.stallName = stallName;
        this.orderRefNum = orderRefNum;
        this.customerName = customerName;
        this.date = date;
        this.time = time;
        //this.itemName = itemName;
        //this.itemNum = itemNum;
        //this.quantity = quantity;
        //this.price = price;
        //this.total = total;
    }
}
