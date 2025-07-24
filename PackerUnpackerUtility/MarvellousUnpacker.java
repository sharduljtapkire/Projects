package MarvellousPackerUnpacker;

import java.util.*;
import java.io.*;

public class MarvellousUnpacker
{
    private String PackName;

    public MarvellousUnpacker(String A)
    {
        this.PackName = A;
    }

    public void UnpackingActivity()
    {
        try
        {
            String Header = null;
            File fobjnew = null;
            int FileSize = 0, iRet = 0, iCountFile = 0;

            File fobj = new File(PackName);

            // If Packed File Is Not Present
            if(! fobj.exists())
            {
                System.out.println("Unable To Access packed File");
                return;
            }
    
            System.out.println("Packed File Gets Successfully Opened...");

            FileInputStream fiobj = new FileInputStream(fobj);

            // Buffer To Read The Header
            byte HeaderBuffer[] = new byte[100];


            // Scanned The Packed File To Extract The Files From It (Baher kadna)
            while((iRet = fiobj.read(HeaderBuffer,0,100)) != -1)
            {
                // Convert Byte Array To String
                Header = new String(HeaderBuffer);

                Header = Header.trim();

                // Tokenised The Header Into 2 parts
                String Tokens[] = Header.split(" ");    // Space Disla ki toda

                fobjnew = new File(Tokens[0]);

                // Create New File To Extract
                fobjnew.createNewFile(); 

                FileSize = Integer.parseInt(Tokens[1]);

                // Create new Buffer To Store Files Data
                byte Buffer[] = new byte[FileSize];

                FileOutputStream foobj = new FileOutputStream(fobjnew);

                // Readd The data form Packed File
                fiobj.read(Buffer,0,FileSize);

                // Writes The Data Into Extracted File
                foobj.write(Buffer,0,FileSize);
                
                System.out.println("File unpacked With Named : "+Tokens[0]+" Having Size : "+Tokens[1]);

                iCountFile++;

                foobj.close();
            } // End of While
                
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("---------------------------Statiscal Report----------------------------");
            System.out.println("-----------------------------------------------------------------------");

            System.out.println("Total Number Of File Unpacked : "+iCountFile);

            System.out.println("-----------------------------------------------------------------------");
            System.out.println("------------------Thank You For using Our Application------------------");
            System.out.println("-----------------------------------------------------------------------");

            fiobj.close();
        }
        catch(Exception eobj)
        {}
    }
}
