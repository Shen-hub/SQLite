package com.example.playlistsqlite;

public class Item {
    private String name;
    private String data;

    public Item() {
    }

    public Item(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return this.data;
    }

    public void setPrice(String price) {
        this.data = price;
    }
}
