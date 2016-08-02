package commands;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.util.Scanner;
/**
 * Edits Dan on 8/2/2016.
 * Gets the new directory name from the user.
 * Creates a directory on the remote server.
 * Checks to make sure directory does not already exist.
 * Message displayed on success or failure to add directory.
 */
public class CommandMkDir implements Command {

    public void execute(FTPClient client, String... args) throws IOException {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the directory you wish to add:");
        String dirName = input.nextLine();
        if(!client.makeDirectory(dirName) && checkExists(client,dirName) )
            System.out.println("Error, unable to make directory.");
        else
            System.out.println("Added directory: " + dirName);
 }
    // Checks to see if directory already exists
    private boolean checkExists(FTPClient client, String dir){
        FTPFile[] files = new FTPFile[0];
        try {
            files = client.listFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (FTPFile file : files) {
            if( file.getName().endsWith(dir)) return true;
        }
        return false;
    }
}
