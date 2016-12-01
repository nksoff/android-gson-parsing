package com.example.nikita.testapp;


import com.google.gson.annotations.SerializedName;

class User {

    @SerializedName("fullName")
    private String name;

    private int age;

    private float weight;

    private UserGender gender;

    private Boolean married;

    @SerializedName("protected")
    private Boolean isProtected;


    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    float getWeight() {
        return weight;
    }

    UserGender getGender() {
        return gender;
    }

    Boolean getMarried() {
        return married;
    }

    Boolean getProtected() {
        return isProtected;
    }
}
