package com.attendance.model;

public class Course {
    private String courseId;
    private String courseName;
    private double minimumAttendance;

    public Course(String courseId, String courseName, double minimumAttendance) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.minimumAttendance = minimumAttendance;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getMinimumAttendance() {
        return minimumAttendance;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseId + ", Course Name: " + courseName +
                ", Minimum Attendance: " + minimumAttendance + "%";
    }
}
