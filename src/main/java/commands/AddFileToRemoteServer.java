package commands;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Simone on 07/23/16.
 * Refactored by Sara
 */
public class AddFileToRemoteServer implements Command {

   // make sure to change the permission to the files

    public void execute(FTPClient client, String... args) throws IOException {
        Scanner input = new Scanner(System.in);
        client.enterLocalPassiveMode();
        client.setFileType(client.BINARY_FILE_TYPE);
        String filepath = null;
        String name_file = null;

        System.out.println("Enter the path of the file :");
        filepath = input.next();

        System.out.println("please enter the name of the new file:");
        name_file = input.next();
        File new_file = new File(filepath);

        if (!new_file.exists()) {
            System.out.println("Invalid filename");
            return;
        }

        FileInputStream inputStream = new FileInputStream(new_file);
        boolean uploaded = client.storeFile(name_file, inputStream);
/*
        String serverReply = client.getReplyString();
        System.out.println(serverReply);
*/
        inputStream.close();

        if (uploaded) {
            System.out.println(" Success");
        }
        else {
            System.out.println("failure");
        }

    }
}

