package commands;


import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class CommandRmDir  implements Command {

    public void execute(FTPClient client, String... args) throws IOException {

        //Removes a directory on the FTP server (if empty!)
        if(!client.removeDirectory( args[0]))
            System.out.println("Error, unable to remove directory - it may contain files.");
        else
            System.out.println("Removed directory: " + args[0]);




    }

}
