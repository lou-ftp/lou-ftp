package commands;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.IOException;

public class CommandListLocal implements Command {
    @Override public void execute(FTPClient client, String... args) throws IOException {
        File dir = new File(args[0]);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Not a directory");
            return;
        }
        for (File file : dir.listFiles()) {
            System.out.println(file.getName());
        }
    }
}
