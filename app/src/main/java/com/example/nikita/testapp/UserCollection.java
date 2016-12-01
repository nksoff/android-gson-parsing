package com.example.nikita.testapp;


public class UserCollection {

    private int id;

    private String name;

    private int size;

    private User[] users = new User[0];

    String getName() {
        return name;
    }

    int getId() {
        return id;
    }

    int getSize() {
        return size;
    }

    User[] getUsers() {
        return users;
    }
}
