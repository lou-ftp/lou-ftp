package commands;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class CommandMock implements Command {

    @Override public void execute(FTPClient client, String... args) throws IOException {
        
    }
}
