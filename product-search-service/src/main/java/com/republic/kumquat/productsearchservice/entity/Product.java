package com.republic.kumquat.productsearchservice.entity;

import org.springframework.context.annotation.Bean;


public class Product {

    private String brand;

    private String color;

    private int price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
