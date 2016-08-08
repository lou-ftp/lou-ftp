package commands;



import org.apache.commons.net.ftp.FTPClient;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class CommandGetMultiple implements Command {
    public void execute(FTPClient client, String... args) throws IOException {

        Scanner userChoice = new Scanner(System.in);
        String yourChoice = null;
        AddFileToRemoteServer addFile = new AddFileToRemoteServer();
        FileOutputStream stream;
        do {
            addFile.execute(client, args);
            System.out.println("Would you like to get another file?");
            System.out.println("Yes . . . . . . . . . . . (1)");
            System.out.println("No . . . . . . . . . . .  (2)");
            yourChoice = userChoice.next();
        }while(!yourChoice.equals("2"));
    }
}
