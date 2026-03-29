package com.attendance.service;

import com.attendance.exception.DuplicateAttendanceException;
import com.attendance.model.AttendanceRecord;
import com.attendance.model.AttendanceStatus;
import com.attendance.model.Course;
import com.attendance.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceService {
    private List<AttendanceRecord> records = new ArrayList<>();

    public void markAttendance(Student student, Course course, LocalDate date, AttendanceStatus status)
            throws DuplicateAttendanceException {

        for (AttendanceRecord record : records) {
            if (record.getStudent().getId() == student.getId() &&
                record.getCourse().getCourseId().equalsIgnoreCase(course.getCourseId()) &&
                record.getDate().equals(date)) {
                throw new DuplicateAttendanceException("Attendance already marked for this student on this date.");
            }
        }

        AttendanceRecord record = new AttendanceRecord(student, course, date, status);
        records.add(record);
    }

    public double calculateAttendancePercentage(Student student, Course course) {
        int totalClasses = 0;
        int attendedClasses = 0;

        for (AttendanceRecord record : records) {
            if (record.getStudent().getId() == student.getId() &&
                record.getCourse().getCourseId().equalsIgnoreCase(course.getCourseId())) {

                totalClasses++;

                if (record.getStatus() == AttendanceStatus.PRESENT ||
                    record.getStatus() == AttendanceStatus.LATE) {
                    attendedClasses++;
                }
            }
        }

        if (totalClasses == 0) {
            return 0.0;
        }

        return (attendedClasses * 100.0) / totalClasses;
    }

    public List<Student> getLowAttendanceStudents(List<Student> students, Course course) {
        List<Student> lowAttendanceStudents = new ArrayList<>();

        for (Student student : students) {
            double percentage = calculateAttendancePercentage(student, course);
            if (percentage < course.getMinimumAttendance()) {
                lowAttendanceStudents.add(student);
            }
        }

        return lowAttendanceStudents;
    }

    public List<AttendanceRecord> getAllRecords() {
        return records;
    }
}
