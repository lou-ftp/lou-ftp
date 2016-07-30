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




        System.out.println("Enter the path of the file, include file name :");
        filepath = input.nextLine();
/*
        System.out.println("please enter the name of the new file:");
        name_file = input.nextLine();
*/

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
/*
        FileInputStream inputStream = new FileInputStream(new_file);
        boolean uploaded = client.storeFile(fname_file, inputStream);

        String serverReply = client.getReplyString();
        System.out.println(serverReply);
*/

        if (uploaded) {
            System.out.println(" Success");
        }
        else {
            System.out.println("failure");
        }
    }
}

