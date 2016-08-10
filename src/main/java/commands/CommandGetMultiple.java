package commands;



import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.util.Scanner;

public class CommandGetMultiple implements Command {
    public void execute(FTPClient client, String... args) throws IOException {

        String yourChoice;
        do {
            Scanner userChoice = new Scanner(System.in);
            System.out.println("Please input the path of the file you wish to download");
            String source = userChoice.nextLine();
            System.out.println("Please input the path to save the file");
            String target = userChoice.nextLine();
            CommandGetFile getFile = new CommandGetFile();
            getFile.execute(client, source, target);
            System.out.println("Would you like to get another file?");
            System.out.println("Yes . . . . . . . . . . . (1)");
            System.out.println("No . . . . . . . . . . .  (2)");
            yourChoice = userChoice.next();
        } while (!yourChoice.equals("2"));
    }
}
