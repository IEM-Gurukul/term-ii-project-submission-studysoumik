package com.attendance.main;

import com.attendance.exception.DuplicateAttendanceException;
import com.attendance.model.AttendanceStatus;
import com.attendance.model.Course;
import com.attendance.model.Student;
import com.attendance.persistence.FileManager;
import com.attendance.service.AttendanceService;
import com.attendance.service.CourseService;
import com.attendance.service.StudentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AttendanceApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        AttendanceService attendanceService = new AttendanceService();
        FileManager fileManager = new FileManager();

        while (true) {
            System.out.println("\n===== Student Attendance Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Add Course");
            System.out.println("4. View Courses");
            System.out.println("5. Mark Attendance");
            System.out.println("6. View Attendance Percentage");
            System.out.println("7. Show Low Attendance Students");
            System.out.println("8. Save Attendance to File");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Roll Number: ");
                    String roll = scanner.nextLine();

                    studentService.addStudent(new Student(id, name, roll));
                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    List<Student> students = studentService.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Course ID: ");
                    String courseId = scanner.nextLine();

                    System.out.print("Enter Course Name: ");
                    String courseName = scanner.nextLine();

                    System.out.print("Enter Minimum Attendance Percentage: ");
                    double minAttendance = Double.parseDouble(scanner.nextLine());

                    courseService.addCourse(new Course(courseId, courseName, minAttendance));
                    System.out.println("Course added successfully.");
                    break;

                case 4:
                    List<Course> courses = courseService.getAllCourses();
                    if (courses.isEmpty()) {
                        System.out.println("No courses found.");
                    } else {
                        for (Course c : courses) {
                            System.out.println(c);
                        }
                    }
                    break;

                case 5:
                    System.out.print("Enter Student ID: ");
                    int studentId = Integer.parseInt(scanner.nextLine());
                    Student student = studentService.findStudentById(studentId);

                    if (student == null) {
                        System.out.println("Student not found.");
                        break;
                    }

                    System.out.print("Enter Course ID: ");
                    String cid = scanner.nextLine();
                    Course course = courseService.findCourseById(cid);

                    if (course == null) {
                        System.out.println("Course not found.");
                        break;
                    }

                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());

                    System.out.print("Enter Status (PRESENT / ABSENT / LATE): ");
                    AttendanceStatus status = AttendanceStatus.valueOf(scanner.nextLine().toUpperCase());

                    try {
                        attendanceService.markAttendance(student, course, date, status);
                        System.out.println("Attendance marked successfully.");
                    } catch (DuplicateAttendanceException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    System.out.print("Enter Student ID: ");
                    int sid = Integer.parseInt(scanner.nextLine());
                    Student st = studentService.findStudentById(sid);

                    if (st == null) {
                        System.out.println("Student not found.");
                        break;
                    }

                    System.out.print("Enter Course ID: ");
                    String coid = scanner.nextLine();
                    Course co = courseService.findCourseById(coid);

                    if (co == null) {
                        System.out.println("Course not found.");
                        break;
                    }

                    double percentage = attendanceService.calculateAttendancePercentage(st, co);
                    System.out.println("Attendance Percentage: " + percentage + "%");
                    break;

                case 7:
                    System.out.print("Enter Course ID: ");
                    String lowCid = scanner.nextLine();
                    Course lowCourse = courseService.findCourseById(lowCid);

                    if (lowCourse == null) {
                        System.out.println("Course not found.");
                        break;
                    }

                    List<Student> lowAttendanceStudents =
                            attendanceService.getLowAttendanceStudents(studentService.getAllStudents(), lowCourse);

                    if (lowAttendanceStudents.isEmpty()) {
                        System.out.println("No low attendance students.");
                    } else {
                        System.out.println("Students below minimum attendance:");
                        for (Student s : lowAttendanceStudents) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 8:
                    fileManager.saveAttendanceToFile(attendanceService.getAllRecords(), "attendance.txt");
                    break;

                case 9:
                    System.out.println("Exiting program.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
