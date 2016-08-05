package commands;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class CommandLogoff implements Command {
    public void execute(FTPClient client, String... args) throws IOException {

        client.logout();
        client.disconnect();

        System.out.println("\nYou have logged out!");
    }
}
