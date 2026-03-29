package com.attendance;

import com.attendance.model.*;
import com.attendance.service.AttendanceService;

public class Main {
    public static void main(String[] args) {

        // Create objects
        Student s1 = new Student(1, "Rahul", "CSE");
        Course c1 = new Course("CS101", "Data Structures", 75);

        AttendanceService service = new AttendanceService();

        // Mark attendance
        service.markAttendance(s1, c1, AttendanceStatus.PRESENT);
        service.markAttendance(s1, c1, AttendanceStatus.ABSENT);
        service.markAttendance(s1, c1, AttendanceStatus.PRESENT);

        // Calculate attendance
        double percentage = service.calculateAttendance(s1, c1);

        // Output
        System.out.println("Student: " + s1.getName());
        System.out.println("Course: " + c1.getCourseName());
        System.out.println("Attendance: " + percentage + "%");

        // Basic validation
        if (percentage < c1.getMinimumAttendance()) {
            System.out.println("Warning: Attendance below required minimum!");
        } else {
            System.out.println("Attendance requirement satisfied.");
        }
    }
}
