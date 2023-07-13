package com.example.SpringCRUD.dto;

public class StudentRequestBody {

    private String name;
    private String department;
    private int year;
    private float cgpa;

    public StudentRequestBody(String name, String department, int year, float cgpa) {
        this.name = name;
        this.department = department;
        this.year = year;
        this.cgpa = cgpa;
    }

    public StudentRequestBody() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }
}
