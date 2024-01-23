package com.example.ui_pupmunchmapp;

public class ListOrders {
    String stallName, orderRefNum, customerName, date, time;

    public ListOrders(String stallName, String orderRefNum, String customerName, String date, String time) {
        this.stallName = stallName;
        this.orderRefNum = orderRefNum;
        this.customerName = customerName;
        this.date = date;
        this.time = time;
    }
}
