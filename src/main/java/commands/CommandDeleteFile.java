package commands;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

/**
 * Created by Dan Conradt on 7/24/2016.
 */
public class CommandDeleteFile implements Command {
    @Override
    public void execute(FTPClient client, String... args) throws IOException {
        // Set path to file for deletion
        String filename = args[0];
        // Set deletion confirmation
        boolean exist = false;
        // try to delete the file and communicate result
        try {
            exist = client.deleteFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (exist)
            System.out.println("File "+ filename + " was deleted.");
        else
            System.out.println("File "+ filename + " doesn't exist...");
    }
}
