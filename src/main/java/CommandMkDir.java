package ftp;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

/**
 * Created by siggy on 7/16/16.
 */

public class CommandMkDir implements Command {

    public void execute(FTPClient client, String... args) throws IOException {

        if( !client.makeDirectory( args[0]) )
            System.out.println("Error, unable to make directory.");
        else
            System.out.println("Added " + args[0]);
    }


}

