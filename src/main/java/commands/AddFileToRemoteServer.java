package commands;
import org.apache.commons.net.ftp.FTPClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Simone on 07/23/16.
 */
public class AddFileToRemoteServer implements Command {
    public void execute(FTPClient client, String... args) throws IOException {
        client = new FTPClient();
        Scanner input = new Scanner(System.in);
        String pathName;
        String fileName;
        //get file location and file name
        System.out.println("Enter the path of the file you would like to upload");
        pathName = input.next();
        File fileExt = new File(pathName);

        System.out.println("Enter the name of the file you would like to upload");
        fileName = input.next();
        String file_name = fileName;// replace filename by the name of the file

        InputStream in_stream = new FileInputStream(fileExt);

        boolean check = client.storeFile(file_name, in_stream);
        // check for success
        if (check) System.out.println("upload successful");
    }


}

