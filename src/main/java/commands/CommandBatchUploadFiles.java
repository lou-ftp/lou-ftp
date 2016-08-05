package commands;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class CommandBatchUploadFiles implements Command {
    @Override public void execute(FTPClient client, String... args) throws IOException {
        AddFileToRemoteServer uploadCmd = new AddFileToRemoteServer();
        for (String file : args) {
            uploadCmd.execute(client, file);
        }
    }
}
