package commands;

//hacked together a get multiple file by just constantly asking for additional files
//change this to place in an array and push all at once

import org.apache.commons.net.ftp.FTPClient;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class CommandGetMultiple implements Command {
    public void execute(FTPClient client, String... args) throws IOException {

        Scanner userChoice = new Scanner(System.in);
        String yourChoice = null;
        AddFileToRemoteServer addFile = new AddFileToRemoteServer();

        FileOutputStream stream;/* = new FileOutputStream(args[1]);
        if (!client.retrieveFile(args[0], stream)) {
            System.out.println("Could not find file " + args[0] + " or failed to retrieve for other reasons");
        }
        stream.flush();
        stream.close();*/

        do {

           /* stream = new FileOutputStream(args[1]);
            if (!client.retrieveFile(args[0], stream)) {
                System.out.println("Could not find file " + args[0] + " or failed to retrieve for other reasons");
            }
            stream.flush();
            stream.close();*/

            addFile.execute(client, args);

            System.out.println("Would you like to get another file?");
            System.out.println("Yes . . . . . . . . . . . (1)");
            System.out.println("No . . . . . . . . . . .  (2)");

            yourChoice = userChoice.next();


        }while(!yourChoice.equals("2"));

    }
}
