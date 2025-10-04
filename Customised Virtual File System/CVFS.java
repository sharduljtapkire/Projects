/////////////////////////////////////////////////////////////////////////////
//
//  File Name   : CVFS.java
//  Description : Marvellous CVFS - Custom Virtual File System in Java
//                Supports commands: exit, help, clear, man, creat, unlink, ls, stat, write, read
//                Implements file system simulation with statistical display.
//  Author      : Shardul Tapkire
//  Date        : 04/10/2025
//
/////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;
import java.util.Arrays;
import java.io.Console;

///////////////////////////////////////////////////////////////////////
//
//  User defined Constants 
//
///////////////////////////////////////////////////////////////////////
class MarvellousConstants
{
    // Max file size
    public static final int MAXFILESIZE = 100;
    // Maximum number of files that we can open
    public static final int MAXOPENEDFILES = 20;
    // Maximum number of files that we can create
    public static final int MAXINODE = 5;

    public static final int READ = 1;
    public static final int WRITE = 2;
    public static final int EXECUTE = 4;

    public static final int REGULARFILE = 1;
    public static final int SPECIALFILE = 2;

    public static final int START = 0;
    public static final int CURRENT = 1;
    public static final int END = 2;

    public static final int EXECUTE_SUCCESS = 0;

    ///////////////////////////////////////////////////////////////////////
    //
    //  User defined Constants for error handling
    //
    ///////////////////////////////////////////////////////////////////////

    public static final int ERR_INVALID_PARAMETER = -1;
    public static final int ERR_NO_INODES = -2;
    public static final int ERR_FILE_ALREADY_EXIST = -3;
    public static final int ERR_FILE_NOT_EXIST = -4;
    public static final int ERR_PERMISSION_DENIED = -5;
    public static final int ERR_INSUFFICIENT_SPACE = -6;
    public static final int ERR_INSUFFICIENT_DATA = -7;
}

///////////////////////////////////////////////////////////////////////
//
//  Class Name :    BootBlock
//  Description :   Holds information to boot the operating system
//
///////////////////////////////////////////////////////////////////////
class BootBlock
{
    public String Information = new String(new char[100]); // Fixed size array of chars in C++ is a String in Java
}

///////////////////////////////////////////////////////////////////////
//
//  Class Name :    SuperBlock
//  Description :   Holds information about the file system
//
///////////////////////////////////////////////////////////////////////
class SuperBlock
{
    public int TotalInodes;
    public int FreeInodes;
}

///////////////////////////////////////////////////////////////////////
//
//  Class Name :    Inode
//  Description :   Holds information about the file
//
///////////////////////////////////////////////////////////////////////
class Inode
{
    public String FileName; // Used String for FileName
    public int InodeNumber;
    public int FileSize;
    public int ActualFileSize;
    public int FileType;
    public int ReferenceCount;
    public int LinkCount;
    public int Permission;
    public char[] Buffer; // Used char array for Buffer
    public Inode next;

    public Inode()
    {
        FileName = "";
        InodeNumber = 0;
        FileSize = 0;
        ActualFileSize = 0;
        FileType = 0;
        ReferenceCount = 0;
        LinkCount = 0;
        Permission = 0;
        Buffer = null;
        next = null;
    }
}

///////////////////////////////////////////////////////////////////////
//
//  Class Name :    FileTable
//  Description :   Holds information about the opened file
//
///////////////////////////////////////////////////////////////////////
class FileTable
{
    public int ReadOffset;
    public int WriteOffset;
    public int Count;
    public int Mode;
    public Inode ptrinode;

    public FileTable()
    {
        ReadOffset = 0;
        WriteOffset = 0;
        Count = 0;
        Mode = 0;
        ptrinode = null;
    }
}

///////////////////////////////////////////////////////////////////////
//
//  Class Name :    UAREA
//  Description :   Holds information about the process
//
///////////////////////////////////////////////////////////////////////
class UAREA
{
    public String ProcessName;
    // Array of references to FileTable objects
    public FileTable[] UFDT = new FileTable[MarvellousConstants.MAXOPENEDFILES];
}

///////////////////////////////////////////////////////////////////////
//
//  Class Name :    MarvellousCVFS
//  Description :   Core logic for the Custom Virtual File System
//
///////////////////////////////////////////////////////////////////////
class MarvellousCVFS
{
    // Global variables/objects
    private BootBlock bootobj = new BootBlock();
    private SuperBlock superobj = new SuperBlock();
    private Inode head = null;
    private UAREA uareaobj = new UAREA();

    // Scanner for console input
    private Scanner scanner = new Scanner(System.in);

    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     InitialiseUAREA
    //  Description :       It is used to intialise the contents UAREA
    //
    ///////////////////////////////////////////////////////////////////////
    private void InitialiseUAREA()
    {
        uareaobj.ProcessName = "Myexe";
        // UFDT is already initialised to null by Java for object arrays

        System.out.println("Marvellous CVFS : UAREA initialised succesfully");
    }

    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     InitialiseSuperblock
    //  Description :       It is used to intialise the contents of super block
    //
    ///////////////////////////////////////////////////////////////////////
    private void InitialiseSuperblock()
    {
        superobj.TotalInodes = MarvellousConstants.MAXINODE;
        superobj.FreeInodes = MarvellousConstants.MAXINODE;

        System.out.println("Marvellous CVFS : Superblock initialised succesfully");
    }

    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     CreateDILB
    //  Description :       It is used to create Linked List of Inodes
    //
    ///////////////////////////////////////////////////////////////////////
    private void CreateDILB()
    {
        Inode newn = null;
        Inode temp = head;

        for (int i = 1; i <= MarvellousConstants.MAXINODE; i++)
        {
            newn = new Inode(); // Allocate memory for a new Inode object

            newn.InodeNumber = i;
            newn.FileSize = 0;
            newn.ActualFileSize = 0;
            newn.LinkCount = 0;
            newn.Permission = 0;
            newn.FileType = 0;
            newn.ReferenceCount = 0;
            newn.Buffer = null;
            newn.next = null;

            if (temp == null)
            {
                head = newn;
                temp = head;
            }
            else
            {
                temp.next = newn;
                temp = temp.next;
            }
        }

        System.out.println("Marvellous CVFS : DILB created succesfully");
    }

    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     StartAuxilaryDataInitialisation
    //  Description :       It is used to intialise the Auxilary data
    //
    ///////////////////////////////////////////////////////////////////////
    private void StartAuxilaryDataInitialisation()
    {
        bootobj.Information = "Boot process of Opertaing System done";
        System.out.println(bootobj.Information);

        InitialiseSuperblock();
        CreateDILB();
        InitialiseUAREA();

        System.out.println("Marvellous CVFS : Auxilary data initalised succesfully");
    }

    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     DisplayHelp
    //  Description :       It is used to Display the information about commands
    //
    ///////////////////////////////////////////////////////////////////////
    private void DisplayHelp()
    {
        System.out.println("---------------------------------------------------------");
        System.out.println("----------- Command Manual of Marvellous CVFS -----------");
        System.out.println("---------------------------------------------------------");

        System.out.println("man : It is used to display the specific manual page of command");
        System.out.println("exit : It is used to terminate the shell of Marvellous CVFS");
        System.out.println("clear : It is used to clear the console of Marvellous CVFS (Requires 'clear' shell command to be available)");
        System.out.println("creat : It is used to create new regular file");
        System.out.println("unlink : It is used to delete existing file");
        System.out.println("stat : It is used to display statistical information about file");
        System.out.println("ls : It is used to list out all files from the directory");
        System.out.println("write : It is used to write the data into the file");
        System.out.println("read : It is used to read the data from the file");

        System.out.println("---------------------------------------------------------");
    }

    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     ManPage
    //  Description :       It is used to Display the manual page of the command
    //  Input :             It accepts the command name
    //  Output :            Displays the manual details of the command
    //
    ///////////////////////////////////////////////////////////////////////
    private void ManPage(String name)
    {
        if (name.equals("creat"))
        {
            System.out.println("Description : This command is used to create new regular file on our file system");
            System.out.println("Usage : creat File_name Permissions");
            System.out.println("File_name : The name of file that you want to create");
            System.out.println("Permissions : \n" + MarvellousConstants.READ + " : Read \n" + MarvellousConstants.WRITE + " : Write \n" + (MarvellousConstants.READ + MarvellousConstants.WRITE) + " : Read + Write");
        }
        else if (name.equals("exit"))
        {
            System.out.println("Description : This command is used to terminate the Marvellous CVFS");
            System.out.println("Usage : exit");
        }
        else if (name.equals("unlink"))
        {
            System.out.println("Description : This command is used to delete regular file from our file system");
            System.out.println("Usage : unlink File_name");
            System.out.println("File_name : The name of file that you want to delete");
        }
        else if (name.equals("stat"))
        {
            System.out.println("Description : This command is used to display statistical information about the file");
            System.out.println("Usage : stat File_name");
            System.out.println("File_name : The name of file whose information you want to display");
        }
        else if (name.equals("ls"))
        {
            System.out.println("Description : This command is used to list all file names form directory");
            System.out.println("Usage : ls");
        }
        else if (name.equals("write"))
        {
            System.out.println("Description : This command is used to write the data into the file");
            System.out.println("Usage : write File_Descriptor");
        }
        else if (name.equals("read"))
        {
            System.out.println("Description : This command is used to read the data from the file");
            System.out.println("Usage : read File_Descriptor Size");
            System.out.println("File_Descriptor : Its a value returned by create system call");
            System.out.println("Size : Number of bytes that you want to read");
        }
        else
        {
            System.out.println("No manual entry for " + name);
        }
    }

    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     IsFileExists
    //  Description :       It is used to check whether the given file name is exist or not
    //  Input :             It accepts the file name
    //  Output :            It returns boolean value (True : if present False : if not present)
    //
    ///////////////////////////////////////////////////////////////////////
    private boolean IsFileExists(String name)
    {
        Inode temp = head;
        while (temp != null)
        {
            if (temp.FileName.equals(name) && temp.FileType == MarvellousConstants.REGULARFILE)
            {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     CreateFile
    //  Description :       It is used to create a new regular file
    //  Input :             It accepts the file name and permission
    //  Output :            It returns the file descriptor
    //
    ///////////////////////////////////////////////////////////////////////
    private int CreateFile(String name, int permission)
    {
        Inode temp = head;
        int i = 0;

        System.out.println("Current Inodes remaining : " + superobj.FreeInodes);

        // Filters
        if (name == null || name.isEmpty())
        {
            return MarvellousConstants.ERR_INVALID_PARAMETER;
        }

        if (permission < MarvellousConstants.READ || permission > (MarvellousConstants.READ + MarvellousConstants.WRITE))
        {
            return MarvellousConstants.ERR_INVALID_PARAMETER;
        }

        if (superobj.FreeInodes == 0)
        {
            return MarvellousConstants.ERR_NO_INODES;
        }

        if (IsFileExists(name))
        {
            return MarvellousConstants.ERR_FILE_ALREADY_EXIST;
        }

        // Loop to search free Inode
        while (temp != null)
        {
            if (temp.FileType == 0)
            {
                break;
            }
            temp = temp.next;
        }

        if (temp == null)
        {
            System.out.println("Inode not found");
            return MarvellousConstants.ERR_NO_INODES;
        }

        // Search first non-NULL (non-initialised) value from UFDT
        for (i = 0; i < MarvellousConstants.MAXOPENEDFILES; i++)
        {
            if (uareaobj.UFDT[i] == null)
            {
                break;
            }
        }

        if (i == MarvellousConstants.MAXOPENEDFILES)
        {
            System.out.println("Unable to create file as MAX OPENED FILE LIMIT REACHED");
            return MarvellousConstants.ERR_INVALID_PARAMETER; // Using -1 as in C++ code
        }

        // Allocate memory for file table
        uareaobj.UFDT[i] = new FileTable();

        // Initialise elements of File table
        uareaobj.UFDT[i].ReadOffset = 0;
        uareaobj.UFDT[i].WriteOffset = 0;
        uareaobj.UFDT[i].Count = 1;
        uareaobj.UFDT[i].Mode = permission;

        // Connect file table with Inode
        uareaobj.UFDT[i].ptrinode = temp;

        uareaobj.UFDT[i].ptrinode.FileName = name;
        uareaobj.UFDT[i].ptrinode.FileSize = MarvellousConstants.MAXFILESIZE;
        uareaobj.UFDT[i].ptrinode.ActualFileSize = 0;
        uareaobj.UFDT[i].ptrinode.FileType = MarvellousConstants.REGULARFILE;
        uareaobj.UFDT[i].ptrinode.ReferenceCount = 1;
        uareaobj.UFDT[i].ptrinode.LinkCount = 1;
        uareaobj.UFDT[i].ptrinode.Permission = permission;

        // Allocate memory for Buffer
        uareaobj.UFDT[i].ptrinode.Buffer = new char[MarvellousConstants.MAXFILESIZE];
        Arrays.fill(uareaobj.UFDT[i].ptrinode.Buffer, '\0');

        // Decrement the number of free inodes by 1
        superobj.FreeInodes--;

        return i; // Return file descriptor (index in UFDT)
    }

    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     UnlinkFile
    //  Description :       It is used to delete a regular file
    //  Input :             It accepts the file name
    //  Output :            It returns success or error code
    //
    ///////////////////////////////////////////////////////////////////////
    private int UnlinkFile(String name)
    {
        int i = 0;

        if (name == null || name.isEmpty())
        {
            return MarvellousConstants.ERR_INVALID_PARAMETER;
        }

        if (!IsFileExists(name))
        {
            return MarvellousConstants.ERR_FILE_NOT_EXIST;
        }

        for (i = 0; i < MarvellousConstants.MAXOPENEDFILES; i++)
        {
            if (uareaobj.UFDT[i] != null)
            {
                if (uareaobj.UFDT[i].ptrinode.FileName.equals(name))
                {
                    // Deallocate the memory of buffer (Java handles this, but we'll reset the reference)
                    uareaobj.UFDT[i].ptrinode.Buffer = null;

                    // Reset all values of Inode
                    uareaobj.UFDT[i].ptrinode.FileName = "";
                    uareaobj.UFDT[i].ptrinode.FileSize = 0;
                    uareaobj.UFDT[i].ptrinode.ActualFileSize = 0;
                    uareaobj.UFDT[i].ptrinode.LinkCount = 0;
                    uareaobj.UFDT[i].ptrinode.Permission = 0;
                    uareaobj.UFDT[i].ptrinode.FileType = 0;
                    uareaobj.UFDT[i].ptrinode.ReferenceCount = 0;

                    // Deallocate memory of file table (Java handles this, but we'll set to null)
                    uareaobj.UFDT[i] = null;

                    // Increment the value of free inodes count
                    superobj.FreeInodes++;

                    return MarvellousConstants.EXECUTE_SUCCESS;
                }
            }
        }

        // If the file exists but no open file descriptor was found, we still delete it by resetting the Inode directly.
        // This is a common simplification in simulated file systems when 'unlink' implies a complete removal.
        Inode temp = head;
        while (temp != null) {
            if (temp.FileName.equals(name) && temp.FileType == MarvellousConstants.REGULARFILE) {
                temp.FileName = "";
                temp.FileSize = 0;
                temp.ActualFileSize = 0;
                temp.LinkCount = 0;
                temp.Permission = 0;
                temp.FileType = 0;
                temp.ReferenceCount = 0;
                temp.Buffer = null;
                superobj.FreeInodes++;
                return MarvellousConstants.EXECUTE_SUCCESS;
            }
            temp = temp.next;
        }
        
        return MarvellousConstants.EXECUTE_SUCCESS;
    }

    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     ls_file
    //  Description :       It is used to display the information about all files in the directory
    //
    ///////////////////////////////////////////////////////////////////////
    private void ls_file()
    {
        Inode temp = head;
        int count = 0;

        while (temp != null)
        {
            if (temp.FileType != 0)
            {
                System.out.println(temp.FileName);
                count++;
            }
            temp = temp.next;
        }
        if (count == 0) {
            System.out.println("No files found.");
        }
    }

    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     stat_file
    //  Description :       It is used to display the information about the given file
    //  Input :             file name
    //  Output :            Success or error code
    //
    ///////////////////////////////////////////////////////////////////////
    private int stat_file(String name)
    {
        Inode temp = head;

        if (name == null || name.isEmpty())
        {
            return MarvellousConstants.ERR_INVALID_PARAMETER;
        }

        if (!IsFileExists(name))
        {
            return MarvellousConstants.ERR_FILE_NOT_EXIST;
        }

        while (temp != null)
        {
            if (temp.FileName.equals(name) && temp.FileType != 0)
            {
                System.out.println("------------ Statistical Information of file -----------");
                System.out.println("File name : " + temp.FileName);
                System.out.println("File size on Disk : " + temp.FileSize);
                System.out.println("Actual File size : " + temp.ActualFileSize);
                System.out.println("Link count : " + temp.LinkCount);

                System.out.print("File permission : ");
                if (temp.Permission == MarvellousConstants.READ)
                {
                    System.out.println("Read");
                }
                else if (temp.Permission == MarvellousConstants.WRITE)
                {
                    System.out.println("Write");
                }
                else if (temp.Permission == (MarvellousConstants.READ + MarvellousConstants.WRITE))
                {
                    System.out.println("Read + Write");
                }

                System.out.print("File type : ");
                if (temp.FileType == MarvellousConstants.REGULARFILE)
                {
                    System.out.println("Regular file");
                }
                else if (temp.FileType == MarvellousConstants.SPECIALFILE)
                {
                    System.out.println("Special file");
                }

                System.out.println("--------------------------------------------------------");
                return MarvellousConstants.EXECUTE_SUCCESS;
            }

            temp = temp.next;
        }

        return MarvellousConstants.ERR_FILE_NOT_EXIST; // Should not reach here due to IsFileExists check
    }

    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     write_file
    //  Description :       It is used to write the contents into the file
    //  Input :             File descriptor, Address of buffer which contains data, Size of data
    //  Output :            Number of bytes succesfully written into the file
    //
    ///////////////////////////////////////////////////////////////////////
    private int write_file(int fd, String data)
    {
        if (data == null) {
            return MarvellousConstants.ERR_INVALID_PARAMETER;
        }

        int size = data.length();

        System.out.println("File descriptor is : " + fd);
        System.out.println("Data that we want to write : " + data);
        System.out.println("Number of bytes that we want to write : " + size);

        // Invalid value of fd
        if (fd < 0 || fd >= MarvellousConstants.MAXOPENEDFILES)
        {
            return MarvellousConstants.ERR_INVALID_PARAMETER;
        }

        // File is not opened or created with the given fd
        if (uareaobj.UFDT[fd] == null)
        {
            return MarvellousConstants.ERR_FILE_NOT_EXIST;
        }

        // If there is no permission to write the data into the file
        if (uareaobj.UFDT[fd].ptrinode.Permission < MarvellousConstants.WRITE)
        {
            return MarvellousConstants.ERR_PERMISSION_DENIED;
        }

        // Unable to write as there is no sufficient space
        if ((MarvellousConstants.MAXFILESIZE - uareaobj.UFDT[fd].WriteOffset) < size)
        {
            return MarvellousConstants.ERR_INSUFFICIENT_SPACE;
        }

        // Write the actual data (copy string to char buffer)
        char[] dataChars = data.toCharArray();
        for (int i = 0; i < size; i++)
        {
            uareaobj.UFDT[fd].ptrinode.Buffer[uareaobj.UFDT[fd].WriteOffset + i] = dataChars[i];
        }

        // Update the write offset
        uareaobj.UFDT[fd].WriteOffset += size;

        // Update the actual size of file after writing the new data
        uareaobj.UFDT[fd].ptrinode.ActualFileSize += size;

        return size;
    }

    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     read_file
    //  Description :       It is used to read the contents from the file
    //  Input :             File descriptor, Size of data that we want to read
    //  Output :            String containing read data, or null on error.
    //
    ///////////////////////////////////////////////////////////////////////
    private String read_file(int fd, int size)
    {
        // Invalid value of fd
        if (fd < 0 || fd >= MarvellousConstants.MAXOPENEDFILES)
        {
            return String.valueOf(MarvellousConstants.ERR_INVALID_PARAMETER);
        }

        if (size <= 0)
        {
            return String.valueOf(MarvellousConstants.ERR_INVALID_PARAMETER);
        }

        // File is not opened or created with the given fd
        if (uareaobj.UFDT[fd] == null)
        {
            return String.valueOf(MarvellousConstants.ERR_FILE_NOT_EXIST);
        }

        // If there is no permission to read the data from the file
        if (uareaobj.UFDT[fd].ptrinode.Permission < MarvellousConstants.READ)
        {
            return String.valueOf(MarvellousConstants.ERR_PERMISSION_DENIED);
        }

        // The remaining data from the current read offset to the actual file size
        int dataRemaining = uareaobj.UFDT[fd].ptrinode.ActualFileSize - uareaobj.UFDT[fd].ReadOffset;

        // Number of bytes to actually read (min of requested size and remaining data)
        int bytesToRead = Math.min(size, dataRemaining);

        // Unable to read as there is no sufficient data
        if (bytesToRead <= 0)
        {
            return String.valueOf(MarvellousConstants.ERR_INSUFFICIENT_DATA);
        }

        // Read the data
        char[] readChars = new char[bytesToRead];
        for (int i = 0; i < bytesToRead; i++)
        {
            readChars[i] = uareaobj.UFDT[fd].ptrinode.Buffer[uareaobj.UFDT[fd].ReadOffset + i];
        }

        // Update the read offset
        uareaobj.UFDT[fd].ReadOffset += bytesToRead;

        // Return the read data as a string
        return new String(readChars);
    }

    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     MainShell
    //  Description :       Custom shell for CVFS
    //
    ///////////////////////////////////////////////////////////////////////
    public void MainShell()
    {
        StartAuxilaryDataInitialisation();

        System.out.println("---------------------------------------------------------");
        System.out.println("--------- Marvellous CVFS Started Succesfully ------------");
        System.out.println("----------------------------------------------------------");

        String str;
        int iRet;

        while (true)
        {
            System.out.print("\nMarvellous CVFS > ");

            if (scanner.hasNextLine()) {
                str = scanner.nextLine().trim();
            } else {
                break; // End of input stream
            }

            if (str.isEmpty()) {
                continue;
            }

            // Split the command line by space
            String[] Command = str.split("\\s+");
            int iCount = Command.length;

            if (iCount == 0) {
                continue;
            }

            // Handle commands with 1 parameter
            if (iCount == 1)
            {
                if (Command[0].equals("exit"))
                {
                    System.out.println("Thank you for using Marvellous CVFS");
                    System.out.println("Deallocating all resources...");
                    break;
                }
                else if (Command[0].equals("help"))
                {
                    DisplayHelp();
                }
                else if (Command[0].equals("clear"))
                {
                    try {
                        // Attempt to execute the 'clear' command on the shell
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Windows
                        new ProcessBuilder("clear").inheritIO().start().waitFor(); // Unix-like
                    } catch (Exception e) {
                        // Fallback or ignore if not supported
                    }
                }
                else if (Command[0].equals("ls"))
                {
                    ls_file();
                }
                else
                {
                    System.out.println("Command not found...");
                    System.out.println("Please refer Help option or use man command");
                }
            }

            // Handle commands with 2 parameters
            else if (iCount == 2)
            {
                if (Command[0].equals("man"))
                {
                    ManPage(Command[1]);
                }
                else if (Command[0].equals("unlink"))
                {
                    iRet = UnlinkFile(Command[1]);

                    if (iRet == MarvellousConstants.EXECUTE_SUCCESS)
                    {
                        System.out.println("Unlink Operation is succesfully performed");
                    }
                    else if (iRet == MarvellousConstants.ERR_FILE_NOT_EXIST)
                    {
                        System.out.println("Error : Unable to do unlink activity as file is not present");
                    }
                    else if (iRet == MarvellousConstants.ERR_INVALID_PARAMETER)
                    {
                        System.out.println("Error : Invalid parameters for the function");
                        System.out.println("Please check Man page for more details");
                    }
                }
                else if (Command[0].equals("stat"))
                {
                    iRet = stat_file(Command[1]);

                    if (iRet == MarvellousConstants.ERR_FILE_NOT_EXIST)
                    {
                        System.out.println("Error : Unable to display statistics as file is not present");
                    }
                    else if (iRet == MarvellousConstants.ERR_INVALID_PARAMETER)
                    {
                        System.out.println("Error : Invalid parameters for the function");
                        System.out.println("Please check Man page for more details");
                    }
                }
                else if (Command[0].equals("write"))
                {
                    try {
                        int fd = Integer.parseInt(Command[1]);
                        System.out.println("Please enter the data that you want to write into the file : ");

                        String InputBuffer = "";
                        // Read the entire line of data to be written
                        if (scanner.hasNextLine()) {
                            InputBuffer = scanner.nextLine();
                        }

                        iRet = write_file(fd, InputBuffer);

                        if (iRet == MarvellousConstants.ERR_INSUFFICIENT_SPACE)
                        {
                            System.out.println("Error : Insufficient space in the data block for the file");
                        }
                        else if (iRet == MarvellousConstants.ERR_PERMISSION_DENIED)
                        {
                            System.out.println("Error : Unable to write as there is no write permission");
                        }
                        else if (iRet == MarvellousConstants.ERR_INVALID_PARAMETER)
                        {
                            System.out.println("Error : Invalid parameters for the function");
                            System.out.println("Please check Man page for more details");
                        }
                        else if (iRet == MarvellousConstants.ERR_FILE_NOT_EXIST)
                        {
                            System.out.println("Error : FD is invalid or file not open");
                        }
                        else
                        {
                            System.out.println(iRet + " bytes gets succesfully written into the file");
                            // Note: uareaobj.UFDT[fd] might be null if fd is not valid. The check in write_file handles this.
                            if (uareaobj.UFDT[fd] != null) {
                                System.out.println("Data from file is : " + new String(uareaobj.UFDT[fd].ptrinode.Buffer).trim());
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: File Descriptor must be a valid number.");
                    }
                }
                else
                {
                    System.out.println("Command not found...");
                    System.out.println("Please refer Help option or use man command");
                }
            }

            // Handle commands with 3 parameters
            else if (iCount == 3)
            {
                if (Command[0].equals("creat"))
                {
                    try {
                        int permission = Integer.parseInt(Command[2]);
                        iRet = CreateFile(Command[1], permission);

                        if (iRet == MarvellousConstants.ERR_INVALID_PARAMETER)
                        {
                            System.out.println("Error : Invalid parameters for the function");
                            System.out.println("Please check Man page for more details");
                        }
                        else if (iRet == MarvellousConstants.ERR_NO_INODES)
                        {
                            System.out.println("Error : Unable to create file as there are no Inodes");
                        }
                        else if (iRet == MarvellousConstants.ERR_FILE_ALREADY_EXIST)
                        {
                            System.out.println("Error : Unable to create file as file is already existing");
                        }
                        else
                        {
                            System.out.println("File is succesfully created with FD : " + iRet);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Permission must be a valid number.");
                    }
                }
                else if (Command[0].equals("read"))
                {
                    try {
                        int fd = Integer.parseInt(Command[1]);
                        int size = Integer.parseInt(Command[2]);

                        String result = read_file(fd, size);
                        int errCode = 0;

                        try {
                            // Check if the result is an error code
                            errCode = Integer.parseInt(result);
                        } catch (NumberFormatException e) {
                            errCode = MarvellousConstants.EXECUTE_SUCCESS; // Not an error code, successful read
                        }

                        if (errCode == MarvellousConstants.ERR_INSUFFICIENT_DATA)
                        {
                            System.out.println("Error : Insufficient data in the data block of the file");
                        }
                        else if (errCode == MarvellousConstants.ERR_PERMISSION_DENIED)
                        {
                            System.out.println("Error : Unable to read as there is no read permission");
                        }
                        else if (errCode == MarvellousConstants.ERR_INVALID_PARAMETER)
                        {
                            System.out.println("Error : Invalid parameters for the function");
                            System.out.println("Please check Man page for more details");
                        }
                        else if (errCode == MarvellousConstants.ERR_FILE_NOT_EXIST)
                        {
                            System.out.println("Error : FD is invalid or file not open");
                        }
                        else
                        {
                            System.out.println("Read operation is successfull");
                            System.out.println("Data from file is : " + result);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: File Descriptor and Size must be valid numbers.");
                    }
                }
                else
                {
                    System.out.println("Command not found...");
                    System.out.println("Please refer Help option or use man command");
                }
            }

            // Handle commands with 4 parameters
            else if (iCount == 4)
            {
                // No commands defined for 4 parameters in the C++ logic provided
                System.out.println("Command not found...");
                System.out.println("Please refer Help option or use man command");
            }
            else
            {
                System.out.println("Command not found...");
                System.out.println("Please refer Help option or use man command");
            }
        }
    }
}

///////////////////////////////////////////////////////////////////////
//
//  Entry point class and function of project (main)
//
///////////////////////////////////////////////////////////////////////
public class CVFS
{
    public static void main(String[] args)
    {
        MarvellousCVFS cvfs = new MarvellousCVFS();
        cvfs.MainShell();
    }
}