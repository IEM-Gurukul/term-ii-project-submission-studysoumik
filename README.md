# PCCCS495 – Term II Project

## Project Title
Student Attendance Management System

---

## Problem Statement 
Educational institutions often rely on manual or semi-digital methods to track student attendance, which leads to inefficiencies, errors, and lack of real-time insights. Managing attendance across multiple courses becomes difficult without a structured system. This project proposes an object-oriented solution to manage students, courses, and attendance records efficiently. The system allows marking attendance, storing records, and calculating attendance percentages dynamically. It demonstrates how proper design using OOP principles can improve maintainability, scalability, and clarity compared to basic CRUD-based systems.

---

## Target User
- College students  
- Faculty members  
- Academic administrators  

---

## Core Features

- Add and manage student details  
- Create and manage courses  
- Mark attendance (Present / Absent / Late)  
- Store attendance records  
- Calculate attendance percentage per student per course  
- Basic validation and structured data handling  

---

## OOP Concepts Used

- Abstraction: Abstract class `Person` defines common properties like id and name  
- Inheritance: `Student` class extends `Person`  
- Polymorphism: Method overriding using `toString()` in Student class  
- Exception Handling: Handles invalid cases (can be extended further)  
- Collections / Threads: `ArrayList` used to store attendance records  

---

## Proposed Architecture Description
The system follows a layered object-oriented architecture:
- Model Layer → Classes like Student, Course, AttendanceRecord represent data  
- Service Layer → AttendanceService handles business logic (marking and calculation)  
- Main Layer → Demonstrates interaction and execution flow  
This separation ensures modularity, readability, and maintainability.

---

## How to Run
1. Compile all Java files  
2. Run the Main class  
3. Observe console output for attendance operations  

---

## Git Discipline Notes
Project developed with multiple commits showing incremental progress:
- Model classes creation  
- Service layer implementation  
- Feature addition and refinement  
- Documentation updates  
