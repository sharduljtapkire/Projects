### Marvellous Study Tracker

#### Overview

Marvellous Study Tracker is a simple desktop application built with Java Swing for logging and tracking study sessions. It allows users to record study logs, view all their logs, generate reports summarizing study time by date and subject, and export their data to a CSV file.

#### Features

  * **Add Study Logs**: Easily add new study sessions with details like subject, duration (in hours), and a description.
  * **Display All Logs**: View a complete list of all recorded study sessions in a separate window.
  * **Reports**: Generate two types of summary reports:
      * Total study hours per day.
      * Total study hours per subject.
  * **Export to CSV**: Save all your study log data to a CSV file for external analysis or backup.

#### Technologies Used

  * **Java**: The core programming language.
  * **Swing**: A Java Foundation Class library used for building the graphical user interface (GUI).
  * **java.time.LocalDate**: Used for handling dates of the study logs.
  * **Collections Framework**: Utilizes `ArrayList` and `TreeMap` for data storage and retrieval.

#### Getting Started

**Prerequisites**

  * Java Development Kit (JDK) 8 or higher.

**How to Run**

1.  **Compile the Java file:**
    ```bash
    javac MarvellousStudyTracker.java
    ```
2.  **Run the application:**
    ```bash
    java MarvellousStudyTracker
    ```

#### Code Structure

  * `StudyLog.java`: A simple class to represent a single study session, containing fields for date, subject, duration, and description.
  * `StudyTracker.java`: The core logic class that manages a collection of `StudyLog` objects. It includes methods for inserting logs, generating summary reports by date and subject, and exporting data to a CSV file.
  * `MarvellousStudyTracker.java`: The main class that sets up and manages the GUI using Swing components. It handles user interactions and calls methods from the `StudyTracker` class to perform actions.

#### How to Use the Application

  * **Add Study Log**: Click the "Add Study Log" button to open a dialog. Enter the subject, duration in hours, and a description, then click "OK".
  * **Display All Logs**: Click "Display All Logs" to see a table of all your recorded sessions.
  * **Reports**: Click the "Reports" button to view two tables showing your study time summarized by date and subject.
  * **Export to CSV**: Click "Export to CSV" to choose a location and filename to save your data in a comma-separated value format.