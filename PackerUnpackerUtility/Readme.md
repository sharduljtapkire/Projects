## Marvellous Packer Unpacker Utility

The Marvellous Packer Unpacker Utility is a Java-based application designed to pack multiple files from a directory into a single archive file and unpack files from such an archive back into individual files.

### Features

  * **Packing**: Consolidates multiple files from a specified directory into a single ".pack" file. Each packed file includes a 100-byte header containing its name and size.
  * **Unpacking**: Extracts individual files from a ".pack" archive, recreating the original files.
  * **Graphical User Interface (GUI)**: Provides a user-friendly Swing-based interface for easy interaction.
  * **Output Display**: Shows packing and unpacking activity details, including progress and statistical reports, directly within the application's text areas.
  * **Error Handling**: Includes basic validation for directory and file existence and provides informative error messages through dialogs.

### How it Works

The utility consists of three main Java classes:

1.  **`MarvellousPacker.java`**: This class handles the packing logic.
      * It takes a directory name and a pack file name as input.
      * It iterates through all files in the specified directory.
      * For each file, it creates a 100-byte header containing the file's name and size.
      * It writes this header, followed by the actual file content, into the new pack file.
2.  **`MarvellousUnpacker.java`**: This class handles the unpacking logic.
      * It takes a pack file name as input.
      * It reads the pack file, extracting the 100-byte header for each packed file.
      * From the header, it parses the original file name and size.
      * It then reads the corresponding file content and writes it to a new file with the original name.
3.  **`PackerUnpackerUtility.java`**: This is the main class that sets up the GUI using Java Swing.
      * It provides two tabs: "Pack Files" and "Unpack Files."
      * **Pack Files Tab**:
          * Allows users to browse and select a directory to pack.
          * Allows users to specify the name of the output pack file.
          * Includes a "Pack" button to initiate the packing process.
          * Displays the output of the packing activity in a dedicated text area.
      * **Unpack Files Tab**:
          * Allows users to browse and select a pack file to unpack.
          * Includes an "Unpack" button to initiate the unpacking process.
          * Displays the output of the unpacking activity in a dedicated text area.
      * It redirects `System.out` to the respective text areas in the GUI, allowing users to see the console output within the application.

### Usage

#### Prerequisites

  * Java Development Kit (JDK) 8 or higher.

#### Compilation

To compile the Java files, navigate to the directory containing the source files in your terminal or command prompt and run the following commands:

```bash
javac MarvellousPackerUnpacker/*.java
javac PackerUnpackerUtility.java
```

#### Running the Application

After successful compilation, run the application using the following command from the root directory:

```bash
java PackerUnpackerUtility
```

#### Instructions for Use

1.  **Launch the Application**: Run the `PackerUnpackerUtility.java` file as described above. A window titled "Marvellous Packer Unpacker Utility" will appear.
2.  **Packing Files**:
      * Click on the "Pack Files" tab.
      * **Input Directory Name**: Click the "Browse" button next to "Input Directory Name" to select the directory containing the files you wish to pack.
      * **Pack File Name**: Enter the desired name for your packed file (e.g., `myarchive.pack`) in the "Pack File Name" field.
      * Click the "Pack" button. The packing progress and a statistical report will be displayed in the text area below.
3.  **Unpacking Files**:
      * Click on the "Unpack Files" tab.
      * **Pack File Name**: Click the "Browse" button next to "Pack File Name" to select the `.pack` file you wish to unpack.
      * Click the "Unpack" button. The unpacking progress and a statistical report will be displayed in the text area below. The unpacked files will be created in the same directory as the `.pack` file.

### Error Messages

  * "Please enter both directory and pack file names." (Packing): Appears if either the directory name or pack file name is left empty during packing.
  * "The specified directory does not exist or is not a valid directory." (Packing): Appears if the entered directory path is invalid or does not exist.
  * "Unable To Create Pack File": Printed to the output area if the packing process cannot create the specified pack file.
  * "Please enter the pack file name." (Unpacking): Appears if the pack file name is left empty during unpacking.
  * "The specified pack file does not exist or is not a valid file." (Unpacking): Appears if the entered pack file path is invalid or does not exist, or if it's a directory.
  * "Unable To Access packed File": Printed to the output area if the unpacking process cannot access the specified pack file.
  * "An error occurred during packing/unpacking: [error message]": A generic error message displayed in a dialog box if an unexpected exception occurs during the packing or unpacking process.