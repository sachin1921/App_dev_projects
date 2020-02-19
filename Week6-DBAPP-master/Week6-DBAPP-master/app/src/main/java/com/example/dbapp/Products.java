package com.example.dbapp;

public class Products {
    private String productName;
    private double productPrice;

    public Products(String name,double price){
        this.productName = name;
        this.productPrice = price;
    }

    public  Products(){

    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
