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

    public String getData() {
        return this.data;
    }
}
