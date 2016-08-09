package commands;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Simone on 07/23/16.
 * Refactored by Sara
 * Dan - Modified filepath to be entire path including file. Did some refactoring
 * Andrew - aded ability to pass file path in as argument
 */
public class AddFileToRemoteServer implements Command {

   // make sure to change the permission to the files

    public void execute(FTPClient client, String... args) throws IOException {
        Scanner input = new Scanner(System.in);
        client.enterLocalPassiveMode();
        client.setFileType(client.BINARY_FILE_TYPE);
        String filepath = null;
        String name_file = null;
        boolean uploaded = false;
        if (args[0] == null) {
            System.out.println("Enter the path of the file, include file name :");
            filepath = input.nextLine();
        } else {
            filepath = args[0];
        }

        File new_file = new File(filepath);

        try
        {
            InputStream inputStream = new FileInputStream(new_file);
            uploaded = client.storeFile(new_file.getName(), inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!new_file.exists()) {
            System.out.println("Invalid filename");
            return;
        }

        if (uploaded) {
            System.out.println("Success");
        }
        else {
            System.out.println("failure");
        }
    }
}

