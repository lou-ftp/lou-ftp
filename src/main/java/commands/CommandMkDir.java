package commands;


import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.util.Scanner;

public class CommandMkDir implements Command {

 public void execute(FTPClient client, String... args) throws IOException {

     Scanner input = new Scanner(System.in);
     System.out.println("Enter the name of the directory you wish to add:");
     String dirName = input.nextLine();
     if(!client.makeDirectory(dirName))
         System.out.println("Error, unable to make directory.");
     else
         System.out.println("Added directory: " + dirName);
 }
}
