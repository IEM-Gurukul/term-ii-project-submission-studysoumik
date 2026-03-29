package com.attendance.model;

import java.time.LocalDate;

public class AttendanceRecord {
    private Student student;
    private Course course;
    private LocalDate date;
    private AttendanceStatus status;

    public AttendanceRecord(Student student, Course course, LocalDate date, AttendanceStatus status) {
        this.student = student;
        this.course = course;
        this.date = date;
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDate getDate() {
        return date;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Student: " + student.getName() +
                ", Course: " + course.getCourseName() +
                ", Date: " + date +
                ", Status: " + status;
    }
}
