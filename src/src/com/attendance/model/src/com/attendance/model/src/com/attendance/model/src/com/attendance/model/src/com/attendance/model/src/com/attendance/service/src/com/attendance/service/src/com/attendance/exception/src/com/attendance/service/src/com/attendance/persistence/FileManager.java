package com.attendance.persistence;

import com.attendance.model.AttendanceRecord;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileManager {

    public void saveAttendanceToFile(List<AttendanceRecord> records, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (AttendanceRecord record : records) {
                writer.write(record.toString());
                writer.newLine();
            }
            System.out.println("Attendance data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error while saving file: " + e.getMessage());
        }
    }
}
