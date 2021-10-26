package com.example.myproject;

//firebase로 부터 데이터를 받아오기 위한 User.class
public class Users {

    private String Name;
    private String Email;
    private String Phone;
    private String Address;

    public Users() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
    public String getAddress(){
        return Address;
    }
    public void setAddress(String address){
        Address = address;
    }

    public Users(String name, String email, String phone ,String address) {
        Name = name;
        Email = email;
        Phone = phone;
        Address = address;
    }

}
