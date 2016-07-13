import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;

public class CommandListDirectories implements Command {
    public void execute(FTPClient client, String... args) throws IOException {
        FTPFile[] files = args.length > 0 ? client.listFiles(args[0]) : client.listFiles();
        System.out.println("Items in Directory " + (args.length > 0 ? args[0] : client.printWorkingDirectory()));
        for (FTPFile file : files) {
            System.out.println(file.getName() + " Size:" + file.getSize() + " Last Modified:" + file.getTimestamp());
        }
    }
}
