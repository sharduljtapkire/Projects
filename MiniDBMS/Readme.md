# MiniDBMS - Mini Database Management System

## Overview
**MiniDBMS** is a Java-based Mini Database Management System with a graphical user interface (GUI) built using Java Swing. It allows users to manage employee records efficiently and includes essential DBMS functionalities such as insertion, update, deletion, searching, sorting, backup, and export.

This project demonstrates object-oriented programming, file handling (serialization), and GUI programming in Java. It can serve as a portfolio project or a learning tool for basic DBMS concepts.

---

## Features

- **Employee Record Management**
  - Insert new employee records
  - Update existing employee details
  - Delete employee records

- **Display and Sorting**
  - Display all employees in a table
  - Sort employees by **Name**, **Age**, or **Salary**

- **Searching**
  - Search by **Employee ID**
  - Search by **Name**
  - Search by **Age Range**
  - Search by **Salary Range**

- **Persistence**
  - Backup and restore the database using serialization
  - Export employee data to CSV for external use

- **Validation**
  - Prevents invalid inputs such as negative age or salary
  - Ensures all required fields are filled

---

## Technologies Used
- **Programming Language:** Java
- **GUI Framework:** Java Swing
- **Data Persistence:** Java Serialization
- **File Export:** CSV format

---

## How to Run
1. Clone or download the project files.
2. Ensure Java JDK is installed on your system.
3. Compile the project using:
   ```bash
   javac MiniDBMS.java
````

4. Run the application using:

   ```bash
   java MiniDBMS
   ```
5. The GUI will open, allowing you to manage employee records.

---

## File Structure

* `MiniDBMS.java` : Main program file containing all classes (`Employee`, `MarvellousDBMS`, and GUI).
* `MiniDBMS.ser` : Backup file created during runtime (generated automatically).

---

## Future Enhancements

* Add **column header click sorting** for dynamic sorting.
* Real-time validation for input fields.
* Integration with **SQL database** for larger data storage.
* Export reports in **PDF** or **Excel** format.
* Add **login system** for multiple users.

---

## Author

**Shardul Tapkire**
Date: 01/10/2025

---