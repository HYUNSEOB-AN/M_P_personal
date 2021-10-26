package com.example.myproject;

// firebase에 데이터를 넣어주기 위한 info_class
public class info {
    public String email;
    public String name;
    public String phone;
    public String address;
    public info(){}

    public info(String email, String name, String phone, String address){
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
}
