import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public interface Command {
    void execute(FTPClient client, String... args) throws IOException;
}
