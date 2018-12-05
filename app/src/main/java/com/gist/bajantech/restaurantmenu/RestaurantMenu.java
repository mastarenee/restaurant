package com.gist.bajantech.restaurantmenu;

public class RestaurantMenu {

    public int id;
    public String name;
    public String photo;
    public String restaurant;
    public String status;
    public String price;
    public String description;

    public RestaurantMenu(int id, String name, String photo, String restaurant, String description, String price, String status) {

        this.id = id;
        this.name = name;
        this.photo = photo;
        this.restaurant = restaurant;
        this.status = status;
        this.price = price;
        this.description = description;

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

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        price = price;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String desc) {
        description = desc;
    }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String number) {
        photo = photo;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        status = status;
    }

    public String getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(String restaurant) {
        restaurant = restaurant;
    }

}
