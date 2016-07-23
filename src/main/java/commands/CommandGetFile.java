package commands;

import org.apache.commons.net.ftp.FTPClient;

import java.io.FileOutputStream;
import java.io.IOException;

public class CommandGetFile implements Command {
    public void execute(FTPClient client, String... args) throws IOException {
        FileOutputStream stream = new FileOutputStream(args[1]);
        if (!client.retrieveFile(args[0], stream)) {
            System.out.println("Could not find file " + args[0] + " or failed to retrieve for other reasons");
        }
        stream.flush();
        stream.close();
    }
}
