package com.example.dietconsultantapp;

public class User {
    public String fullname;
    public String email;
    public String cell;
    public String height;
    public String weight;
    public String age;

    public String activity;
    public String Gender;




    public User(String fullname, String email, String cell, String height, String weight, String age, String activity, String Gender) {
        this.fullname = fullname;
        this.email = email;
        this.cell = cell;
        this.height =height;
        this.weight = weight;
        this.age = age;
        this.activity = activity;
        this.Gender =Gender;

    }
    public User() {
    }

}