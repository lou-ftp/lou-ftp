package commands;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Dan Conradt on 7/24/2016.
 */
public class CommandDeleteFile implements Command {
    @Override
    public void execute(FTPClient client, String... args) throws IOException {
        // Set path to file for deletion
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the path of the file you would like to delete");
        String filename = input.next();;
        // Set deletion confirmation
        boolean fileExists = false;
        // try to delete the file and communicate result
        try {
            fileExists = client.deleteFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileExists)
            System.out.println("File "+ filename + " was deleted.");
        else
            System.out.println("File "+ filename + " doesn't exist.");
    }
}
