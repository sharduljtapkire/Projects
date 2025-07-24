package MarvellousPackerUnpacker;

import java.util.*;
import java.io.*;

public class MarvellousPacker
{
    private String PackName;
    private String DirName;

    public MarvellousPacker(String A , String B)
    {
        this.PackName = A;
        this.DirName = B;
    }

    public void PackingActivity()
    {
        try
        {
           
            int i = 0 , j = 0 , iRet = 0 , iCountFile = 0;
            
            File fobj = new File(DirName);

            //check the existance of directory
            if((fobj.exists()) && (fobj.isDirectory()))
            {
                System.out.println(DirName+"Directory Is Successfully Opened");

                File Packobj = new File(PackName);

                //Create Pack File
                boolean bRet = Packobj.createNewFile();
            
                if(bRet == false)
                {
                    System.out.println("Unable To Create Pack File");
                    return;
                }

                System.out.println("Packed File Gets Successfully Created With  Name : "+PackName);

                //Retrive All Files From Directory
                File Arr[] = fobj.listFiles();

                

                // packed file object
                FileOutputStream foobj = new FileOutputStream(Packobj);

                //buffer for read write and activity 
                byte Buffer[] = new byte[1024];
                

                String Header = null;

                //Directory Traversal
                for(i = 0 ; i < Arr.length ; i++)
                {
                    Header = Arr[i].getName() + " " + Arr[i].length();
                    

                    //loop to form 100 bytes header 
                    for(j = Header.length() ; j < 100 ; j++)
                    {
                        Header = Header + " ";
                    }

                    //write header into packed file
                    foobj.write(Header.getBytes());

                    //open file from directory for reading
                    FileInputStream fiobj = new FileInputStream(Arr[i]);

                    //write contents of file into packed file 
                    while((iRet = fiobj.read(Buffer)) != -1)
                    {
                        foobj.write(Buffer,0,iRet); 
                        System.out.println("file name scanned : "+Arr[i].getName());
                        System.out.println("File Size Read Is : "+iRet);                       
                    }
                    fiobj.close();
                    iCountFile++;
                }

                System.out.println("Packing Activity Done....");
                System.out.println("-----------------------------------------------------------------------------");
                System.out.println("------------------------Statistical Report-----------------------------------");
                System.out.println("-----------------------------------------------------------------------------");

                System.out.println("Total File Packed :"+iCountFile);

                System.out.println("-----------------------------------------------------------------------------");
                System.out.println("------------------------Thank You For Using Our Application------------------");
                System.out.println("-----------------------------------------------------------------------------");
            }
            else
            {
                System.out.println("There Is No Such Directory");
            }
        } // end of try  block 
        catch(Exception eobj)
        {}
    } // end of packing activity function
} // end of marvellouspacker class 
