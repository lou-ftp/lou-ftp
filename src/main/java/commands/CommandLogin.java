package commands;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

/**
 * Created by Dan on 7/23/2016.
 * Takes args[0]-host, arg[1]-port, arg[2]-username, arg[3]-pw
 * Connects to server
 * Logs in
 * Sets the mode to passive mode.
 */
public class CommandLogin implements Command {

    @Override
    public void execute(FTPClient client, String... args) throws IOException {
        try {
            client.connect(args[0], Integer.parseInt(args[1]));
            int replyCode = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Connection failed. Server reply code: " + replyCode);
                return;
            }
            boolean success = client.login(args[2], args[3]);
            if (!success) {
                System.out.println("Could not login to server host: " + args[0]);
                return;
            } else {
                System.out.println("Logged into server host: " + args[0]);
            }
            client.enterLocalPassiveMode();
        } catch (IOException ex) {
            System.out.println("Login Failed!");
            ex.printStackTrace();
        }
    }
}
