package commands;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class CommandChangePermissions implements Command {
    @Override public void execute(FTPClient client, String... args) throws IOException {
        if (client.sendSiteCommand("chmod " + args[0] + " " + args[1])) {
            System.out.println(client.getReplyString());
        } else {
            System.out.println(client.getReplyString());
        }
    }
}
