# Marvellous CVFS (Custom Virtual File System)

Marvellous CVFS is a simulated virtual file system implemented in Java. It provides a command-line interface to manage files within a custom file system, supporting basic file operations and system commands.

---

## Features

- Create, read, write, and delete files in a simulated file system.
- File system commands similar to UNIX shell commands: `exit`, `help`, `clear`, `man`, `creat`, `unlink`, `ls`, `stat`, `write`, `read`.
- Supports file permissions: Read, Write, and Read/Write.
- Simulated inode structure and file allocation.
- Provides statistical information about files.
- Custom shell interface for interactive file system management.
- Error handling for invalid parameters, file existence, permissions, and space constraints.

---

## Installation

1. Ensure you have Java Development Kit (JDK) installed (version 8 or above recommended).
2. Clone this repository or download the source file `CVFS.java`.
3. Compile the Java source file:
   ```
   javac CVFS.java
   ```
4. Run the program:
   ```
   java CVFS
   ```

---

## Usage

Once running, the CVFS shell accepts the following commands:

| Command | Description |
|---------|-------------|
| `creat <filename> <permissions>` | Create a new file with given permissions (1=Read, 2=Write, 3=Read/Write). |
| `unlink <filename>` | Delete a file. |
| `ls` | List all files in the directory. |
| `stat <filename>` | Display file statistics like size, type, permissions, and link count. |
| `write <file_descriptor>` | Write data into an opened file. After the command, input data when prompted. |
| `read <file_descriptor> <size>` | Read specified bytes from an opened file. |
| `man <command>` | Show detailed usage of the specified command. |
| `exit` | Terminate the CVFS shell. |
| `clear` | Clear the console screen. |
| `help` | Display a summary of available commands and usage. |

---

## Command Examples

- Create a file named `file1` with read/write permissions:
  ```
  creat file1 3
  ```
- Write to file descriptor 0:
  ```
  write 0
  ```
- Read 10 bytes from file descriptor 0:
  ```
  read 0 10
  ```
- List files:
  ```
  ls
  ```
- Delete a file:
  ```
  unlink file1
  ```

---

## Implementation Details

- Uses custom classes to simulate the components of a file system such as `BootBlock`, `SuperBlock`, `Inode`, `FileTable`, and `UAREA`.
- Limits on file size (100 bytes), number of files (5 inodes), and maximum opened files (20).
- Permissions and file types are managed via constants.
- Provides a simulated filesystem environment within a single Java process without actual disk operations.
- The shell parses and executes user commands in an interactive loop until exit.

---

## Author

Shardul Tapkire

---
For more information about specific commands, use the `man <command>` inside the CVFS shell after running the program.

```