package commands;

import org.apache.commons.net.ftp.FTPClient;

import java.util.Scanner;
import java.io.IOException;

/**
 * Created by Simone on 07/26/16.
 */
public class DeleteDirectoryonRemoteServer implements Command {
    public void execute(FTPClient client, String... args) throws IOException {

        String directoryPath = null;
        Scanner input = new Scanner(System.in);
        System.out.println ( " Enter the path of the directory you will like to delete \n");
        directoryPath = input.next();
        FTPClient ftpClient = new FTPClient();
        boolean deleted = ftpClient.removeDirectory(directoryPath);
        if (deleted) {
            System.out.println(" Success removing the directory.");
        } else {
            System.out.println("could not remove directory");
        }

        }
}
