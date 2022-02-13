package com.example.myaddressbook.model;

public class EmployeeModel {
    public String id;
    public String employeeId;
    public String name;
    public String picture;
    public String city;
    public String register;
    public String email;
    public String phone;

    public EmployeeModel() {
    }

    public EmployeeModel(String employeeId, String name, String picture, String city, String register, String email, String phone) {
        this.employeeId = employeeId;
        this.name = name;
        this.picture = picture;
        this.city = city;
        this.register = register;
        this.email = email;
        this.phone = phone;
    }
}
