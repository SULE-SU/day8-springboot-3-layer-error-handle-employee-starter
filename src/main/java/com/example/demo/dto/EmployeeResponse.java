package com.example.demo.dto;


public class EmployeeResponse {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Double salary;
    private boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeResponse(Integer id, String name, Integer age, String gender, Double salary, boolean status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.status = status;
    }

    public EmployeeResponse() {

    }

    public boolean getStatus() {
        return status;
    }
}
