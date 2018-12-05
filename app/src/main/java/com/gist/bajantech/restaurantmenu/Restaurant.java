package com.gist.bajantech.restaurantmenu;

import java.util.UUID;

public class Restaurant {

    public int id;
    public String name;
    public String phone;
    public String canteen;
    public String status;

    public Restaurant(int id, String name, String phone, String canteen, String status) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.canteen = canteen;
        this.status = status;

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String title) {
        name = title;
    }

    public String getNumber() {
        return phone;
    }
    public void setNumber(String number) {
        phone = number;
    }

    public String isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        status = status;
    }

    public String getCanteen() {
        return canteen;
    }
    public void setCanteen(String canteen) {
        canteen = canteen;
    }
}