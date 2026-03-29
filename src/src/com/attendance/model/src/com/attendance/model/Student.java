package com.attendance.model;

public class Student extends Person {
    private String rollNumber;

    public Student(int id, String name, String rollNumber) {
        super(id, name);
        this.rollNumber = rollNumber;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name + ", Roll No: " + rollNumber;
    }
}
