# 💻 Software Development Portfolio Summary

This portfolio showcases a collection of diverse software development projects primarily implemented in **Java** and leveraging modern frameworks like **Spring Boot**. The projects span various domains, including networking, file system simulation, database management, and web application development (RESTful APIs), demonstrating proficiency in object-oriented design, data structures, algorithms, and full-stack concepts.

***

## 1. Chat Application (Client-Server) 💬

### Description

This project implements a simple **client-server chat application** using **Java Swing** for the graphical user interface and standard Java networking. It enables real-time message exchange between a single client and a server. A key feature is the client's ability to log all chat history to a timestamped text file for record-keeping.

### Features
* **Real-time Messaging:** Exchange messages between client and server.
* **GUI Interface:** User-friendly Swing interfaces for both client and server.
* **Chat History Logging (Client-side):** All messages are logged to a timestamped file (`ChatHistory_Client_YYYYMMDD_HHmmss.txt`).
* **Graceful Termination:** Client can end the session via an "End Chat" button or by typing "bye."

### Technical Details
* **Server (`ChatServerX.java`):** Uses `ServerSocket` and `Socket` on port `12345`.
* **Client (`ChatClientX.java`):** Connects to `localhost:12345`.
* **Communication:** `PrintWriter` and `BufferedReader` for text exchange.

### Limitations & Enhancements
* **Limitation:** Single-client only (no multithreading).
* **Future:** Multi-client support, improved UI, usernames, and robust error handling.

***

## 2. Marvellous CVFS (Custom Virtual File System) 📁

### Description

Marvellous CVFS is a simulated **Virtual File System** implemented in Java. It provides a command-line interface to manage files within a custom, in-memory file system, supporting basic file operations and system-like commands.

### Features
* **File Operations:** Supports `creat`, `unlink`, `write`, and `read`.
* **System Commands:** Commands similar to UNIX shell: `exit`, `help`, `clear`, `man`, `ls`, `stat`.
* **File Permissions:** Supports Read (1), Write (2), and Read/Write (3) permissions.
* **Simulated Structure:** Uses custom classes to simulate file system components like `BootBlock`, `SuperBlock`, `Inode`, `FileTable`, and `UAREA`.
* **Constraints:** Limits on file size (100 bytes) and number of files (5 inodes).

### Usage (Shell Commands)

| Command | Description |
| :--- | :--- |
| `creat <filename> <permissions>` | Create a new file with specified permissions. |
| `unlink <filename>` | Delete a file. |
| `ls` | List all files in the directory. |
| `stat <filename>` | Display file statistics (size, permissions, link count). |
| `write <file_descriptor>` | Write data into an opened file. |
| `read <file_descriptor> <size>` | Read specified bytes from an opened file. |

***

## 3. Employee Management System (Spring Boot) 👥

### Description

The **Employee Management System (EMS)** is a **RESTful web application** built with **Spring Boot**. It provides essential CRUD (Create, Read, Update, Delete) operations for managing employee records via a scalable REST API.

### Technologies Used
* **Backend:** Java 17+, Spring Boot
* **Data:** Spring Data JPA, MySQL (or H2 for testing)
* **Build Tool:** Maven

### Project Structure Highlights
* `controller/EmployeeController.java`: Handles HTTP requests.
* `model/Employee.java`: JPA Entity for the employee data.
* `repository/EmployeeRepository.java`: Interface for database operations.
* `exception/ResourceNotFoundException.java`: Global exception handling.

### API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/v1/employees` | Fetch all employees. |
| `GET` | `/api/v1/employees/{id}` | Get employee by ID. |
| `POST` | `/api/v1/employees` | Create a new employee. |
| `PUT` | `/api/v1/employees/{id}` | Update an existing employee. |
| `DELETE` | `/api/v1/employees/{id}` | Delete an employee. |

***

## 4. Generalised Data Structures and Algorithms Library 📚

### Description

This project is a single-file, consolidated Java library (`Generalised_Data_Structures_library.java`) containing generic implementations of fundamental **Data Structures** and **Algorithms**.

### Key Data Structures (Generic)
| Class Name | Type | Key Features |
| :--- | :--- | :--- |
| `QueueX<T>`, `StackX<T>` | Queue (FIFO) & Stack (LIFO) | Linked List based implementations. |
| `SinglyLL<T>`, `DoublyLL<T>` | Linear Linked Lists | Supports number-specific operations (Prime, Perfect, Sum of Digits, Reverse Digits). |
| `SinglyCLL<T>`, `DoublyCLL<T>` | Circular Linked Lists | Also supports number-specific operations. |
| `BST<T>` | Binary Search Tree | Supports Inorder, Preorder, and Postorder traversals. |

### Key Algorithms
* **Sorting:** `Sorting` class implements **Bubble Sort** and **Selection Sort**.
* **Searching:** `Searching` class implements **Linear Search** and **Binary Search**.

***

## 5. Marvellous Portal - Backend API (MongoDB) 🔗

### Description

Marvellous Portal provides a **backend API** for managing batch entries, built with **Spring Boot** and utilizing **MongoDB** as its database. It offers a set of RESTful endpoints to perform CRUD operations on batch data (name and fees).

### Technologies Used
* **Framework:** Spring Boot
* **Database:** MongoDB (NoSQL)
* **Utility:** Lombok

### Project Structure Highlights
* `Entity.BatchEntry`: Data model mapping to the "BatchDetails" collection.
* `Repository.BatchEntryRepository`: Extends `MongoRepository`.
* `controller.BatchEntryController`: Exposes the CRUD endpoints.

### API Endpoints (on Port 8080)

| Method | Endpoint | Functionality |
| :--- | :--- | :--- |
| `GET` | `/batches` | Retrieve all batch entries. |
| `POST` | `/batches` | Create a new batch entry. |
| `GET` | `/batches/id/{myid}` | Retrieve by MongoDB ObjectId. |
| `PUT` | `/batches/id/{myid}` | Update by MongoDB ObjectId. |
| `DELETE` | `/batches/id/{myid}` | Delete by MongoDB ObjectId. |

***

## 6. Marvellous Study Tracker 📊

### Description

Marvellous Study Tracker is a simple **desktop application** built with **Java Swing** for logging and tracking study sessions. It focuses on logging, reporting, and exporting study hours.

### Features
* **Log Management:** Add new study sessions (subject, duration, description).
* **Display:** View all recorded logs in a table format.
* **Reports:** Generate summary reports:
    * Total study hours per day.
    * Total study hours per subject.
* **Data Export:** Save all logs to a **CSV** file.

### Technologies Used
* **GUI:** Java Swing
* **Data Handling:** Java Collections (`ArrayList`, `TreeMap`), `java.time.LocalDate`.

***

## 7. MiniDBMS - Mini Database Management System 💾

### Overview

**MiniDBMS** is a **Java Swing GUI** application that functions as a small-scale Database Management System for managing employee records. It demonstrates core DBMS concepts through a user-friendly interface.

### Features
* **CRUD Operations:** Insert, Update, and Delete employee records.
* **Display & Sorting:** Display all records and sort them by **Name**, **Age**, or **Salary**.
* **Advanced Searching:** Search by **ID**, **Name**, **Age Range**, or **Salary Range**.
* **Persistence:** Backup and restore data using **Java Serialization**.
* **Export:** Export data to **CSV** format.
* **Validation:** Input validation for age and salary.

### Technologies Used
* **GUI Framework:** Java Swing
* **Data Persistence:** Java Serialization (`MiniDBMS.ser` file).

***

## 8. Marvellous Packer Unpacker (Security Focus) 🔒

### Overview

This is a secure, platform-independent **Java application** for packing and unpacking files, featuring a **Swing GUI** and built-in **XOR Cipher** for encryption/decryption.

### Key Components
* **GUI (`MarvellousPackUnpackGUI.java`):** Contains `LoginFrame`, `MainMenuFrame`, `PackFrame`, and `UnpackFrame`. Includes a real-time date/time thread.
* **Security (`MarvellousSecurity.java`):** Provides static methods (`xorCipher`, `xorCipherHeader`) implementing the **Keyed XOR Cipher** for symmetric encryption/decryption.
* **Packing (`MarvellousPacker.java`):** Accepts an **Encryption Key**, encrypts the file header and content, and creates a packed file.
* **Unpacking (`MarvellousUnpacker.java`):** Accepts a **Decryption Key**, decrypts the header to read file metadata, and decrypts the content to restore original files.

### Security Mechanism
* A mandatory **Encryption Key** is required for both packing and unpacking.
* The 100-byte file header (containing filename and size) is **encrypted**.
* File contents are **encrypted** byte-by-byte using the key before writing to the packed file.

### Login Credentials
* **Username:** `Marvellous`
* **Password:** `Marvellous`