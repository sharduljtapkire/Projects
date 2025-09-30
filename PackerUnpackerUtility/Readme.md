### Marvellous Packer Unpacker

This is a secure, platform-independent Java application for packing and unpacking files. The application features a graphical user interface (GUI) built with Swing and is structured into several classes to manage authentication, navigation, and core file operations, including encryption and decryption.

## File Descriptions

  * `MarvellousPackUnpackGUI.java`: This file contains the main class `MarvellousPackUnpackGUI`, which starts the application by creating a `LoginFrame`. It also defines the classes for the different GUI frames:

      * `LoginFrame`: Handles user login with a hardcoded username ("Marvellous") and password ("Marvellous"). It displays the live date and time using a separate thread.
      * `MainMenuFrame`: The main menu after successful login, providing options to "Pack" or "Unpack" files.
      * `PackFrame`: The GUI for packing, which now accepts the **Encryption Key**, source directory, and destination packed file name. The key is securely passed to the `MarvellousPacker`.
      * `UnpackFrame`: The GUI for unpacking, which now accepts the **Decryption Key** and the packed file name. The key is securely passed to the `MarvellousUnpacker`.
      * `DateTimeThread`: A `Thread` class that continuously updates the date and time on the GUI labels.

  * `MarvellousPacker.java` (in `MarvellousPackerUnpacker` package): This class handles the file packing and **encryption** process.

      * It now accepts a mandatory **Encryption Key** in its constructor.
      * It creates a new packed file and writes an **encrypted 100-byte header** (containing the file name and size).
      * The contents of each file are **encrypted** using the XOR cipher and written into the packed file.
      * It prints a statistical report to the console showing the total number of files packed.

  * `MarvellousUnpacker.java` (in `MarvellousPackerUnpacker` package): This class handles the file unpacking and **decryption** process.

      * It now accepts a mandatory **Decryption Key** in its constructor.
      * It reads the packed file, extracts the 100-byte header, and **decrypts it** using the provided key.
      * The decrypted header is parsed to get the original filename and file size.
      * A new file is created, and the file's data is read, **decrypted** using the key, and written to the newly created file.
      * It prints a statistical report to the console showing the total number of files unpacked.

  * `MarvellousSecurity.java` (in `MarvellousPackerUnpacker` package): **New File.** This class provides the static methods (`xorCipher` and `xorCipherHeader`) that implement the **Keyed XOR Cipher** for symmetric encryption and decryption of both the file headers and data.

-----

## How to Compile and Run 

Since your project uses the package `MarvellousPackerUnpacker`, you must compile all source files together using the correct package paths.

1.  **Project Structure:** Ensure your files are structured with the package folder:

    ```
    <Project Root>\
    ├── MarvellousPackUnpackGUI.java
    ├── MarvellousPacker.java
    ├── MarvellousUnpacker.java
    └── MarvellousSecurity.java
    ```

2.  **Compile the Java files (Windows CMD):**
    Open a Command Prompt (CMD) and navigate to the project root directory. Use the following command to compile all files and place the resulting `.class` files into the correct package folder:

    ```bash
    javac -d . MarvellousPackUnpackGUI.java MarvellousPacker.java MarvellousUnpacker.java MarvellousSecurity.java
    ```

3.  **Run the application:**
    After compiling, run the main class from the root directory:

    ```bash
    java MarvellousPackUnpackGUI
    ```

4.  **Login:**
    Use the following credentials to log in:

      * **Username:** `Marvellous`
      * **Password:** `Marvellous`

5.  **Pack Files (Key Required):**

      * In the "Pack" screen, enter the Directory Name, Destination File name, and a mandatory **Encryption Key** (e.g., `MySecret123`).
      * Click "Pack." A message dialog will confirm completion.

6.  **Unpack Files (Key Required):**

      * In the "Unpack" screen, enter the Packed File name.
      * **Crucially, enter the exact same Decryption Key** that was used during the packing process.
      * Click "Unpack." A message dialog will confirm completion. The decrypted files will be created in the same directory where you ran the application.