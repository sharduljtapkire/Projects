**README.md**

### Marvellous Packer Unpacker

This is a Java application for packing and unpacking files. The application has a graphical user interface (GUI) and is divided into several classes to handle different functionalities, such as the login screen, main menu, packing, and unpacking.

### File Descriptions

  * `MarvellousPackUnpackGUI.java`: This file contains the main class `MarvellousPackUnpackGUI`, which starts the application by creating a `LoginFrame`. It also defines the classes for the different GUI frames:

      * `LoginFrame`: Handles the user login with a hardcoded username ("Marvellous") and password ("Marvellous"). It also displays the live date and time using a separate thread.
      * `MainMenuFrame`: The main menu after successful login, providing options to "Pack" or "Unpack" files.
      * `PackFrame`: The GUI for the packing functionality, where the user can specify the source directory and destination packed file name.
      * `UnpackFrame`: The GUI for the unpacking functionality, where the user specifies the packed file to unpack.
      * `DateTimeThread`: A `Thread` class that continuously updates the date and time on the GUI labels.

  * `MarvellousPacker.java`: This class handles the file packing process.

      * It takes a destination packed file name and a source directory name as input.
      * It checks if the provided directory exists.
      * It creates a new packed file and writes a 100-byte header for each file in the directory. The header contains the filename and its size, followed by spaces to fill the 100-byte space.
      * The contents of each file are then written into the packed file.
      * After packing, it prints a statistical report to the console showing the total number of files packed.

  * `MarvellousUnpacker.java`: This class handles the file unpacking process.

      * It takes the name of a packed file as input.
      * It reads the packed file and extracts the 100-byte header for each file.
      * The header is parsed to get the original filename and file size.
      * A new file is created with the original filename, and its data is read from the packed file and written to the newly created file.
      * After unpacking, it prints a statistical report to the console showing the total number of files unpacked.

### How to Compile and Run

1.  **Compile the Java files:**
    Open a terminal or command prompt and navigate to the directory containing the files. Compile the Java files using the `javac` command:

    ```bash
    javac MarvellousPackUnpackGUI.java MarvellousPacker.java MarvellousUnpacker.java
    ```

2.  **Run the application:**
    After compiling, run the main class:

    ```bash
    java MarvellousPackUnpackGUI
    ```

3.  **Login:**
    Use the following credentials to log in:

      * **Username:** `Marvellous`
      * **Password:** `Marvellous`

4.  **Pack Files:**

      * In the "Pack" screen, enter the path of the directory you want to pack in the "Directory Name" field.
      * Enter the desired name for the output packed file in the "Destination File" field.
      * Click the "Pack" button to start the process. A message dialog will confirm completion.

5.  **Unpack Files:**

      * In the "Unpack" screen, enter the path to the packed file you want to unpack in the "Packed File" field.
      * Click the "Unpack" button to start the process. A message dialog will confirm completion. The unpacked files will be created in the same directory where you ran the application.