/**
 * Created by Dan Conradt on 7/9/2016.
 */


import commands.*;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Client {

    // Application variables
    static FTPClient client = new FTPClient();
    static User user = new User();
    static ConnectionInformation connInfo = new ConnectionInformation();
    static CommandLogin connectFtp = new CommandLogin();
    static CommandLogoff disconnectFTP = new CommandLogoff();
    static AddFileToRemoteServer addFile = new AddFileToRemoteServer();
    static CommandListDirectories listDir = new CommandListDirectories();
    static CommandMkDir makeDir = new CommandMkDir();
    //static CommandRmDir removeDir = new CommandRmDir();
    static CommandDeleteFile delFile = new CommandDeleteFile();
    static CommandLogoff logOff = new CommandLogoff();
    static DeleteDirectoryonRemoteServer delDirRemote = new DeleteDirectoryonRemoteServer();
    static CommandGetFile getFile = new CommandGetFile();
    static CommandBatchUploadFiles batchUpload = new CommandBatchUploadFiles();

    static String testLogin = "lou-ftp";
    static String testPassword = "lou-ftp";
    static String testHost = "ftp.drivehq.com";
    static int testPort = 21;
    static String login = "";
    static String password = "";
    static String host = "";
    static int port = 0;

    private static final Pattern STRING_SPLIT_PATTERN = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'\n");


    /**
     * Processes the initial user menu and user input.
     *
     * @param args list of command line arguments
     */
    public static void main(String[] args) {

        Scanner usrChoice = new Scanner(System.in);
        String choice;

        testLogin();
        System.out.println("You have been auto logged in.");
        System.out.println("Would you like to login under a different user?");
        System.out.println("Yes . . . . . . . . . . . (1)");
        System.out.println("No . . . . . . . . . . .  (2)");

        choice = usrChoice.next();

        if (choice.equals("1")) {
            try {
                logOff.execute(client);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (user.inputLogin())
                login = user.getLogin();
            else
                System.out.println("Invalid Data");

            if (user.inputPass())
                password = user.getPassword();
            else
                System.out.println("Invalid Data");

            if (connInfo.inputHost())
                host = connInfo.getHost();
            else
                System.out.println("Invalid Data");

            if (connInfo.inputPort())
                port = connInfo.getPort();
            else
                System.out.println("Invalid Data");
            login();
        }

        do {
            System.out.println("- - - - | Select an option | - - - - -");
            System.out.println("Launch Console . . . . . . . . . . (0)");
            System.out.println("List directories . . . . . . . .   (1)");
            System.out.println("Get file . . . . . . . . . . . .   (2)");
            System.out.println("Delete file remotely . .  . . . .  (3)");
            System.out.println("Make Directory .  . . . . . . . .  (4)");
            System.out.println("List files locally . . . . . . . . (5)");
            System.out.println("Remove Directory remotely . . . .  (6)");
            System.out.println("Add a file to remote . . . . . . . (7)");
            System.out.println("Add multiple file to remote  . . . (8)");
            System.out.println("Change file permission . . . . . . (9)");
            System.out.println("Retrieve multiple files . . . . . .(10)");
            System.out.println("Log off .  . . . . . . . . . . . . (11)");

            choice = usrChoice.next();

            switch (choice) {
                case "0":
                    try {
                        new CommandConsole().execute(client);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Unable to launch console, please try again. ");

                    }
                    break;
                case "1":
                    try {
                        listDir.execute(client);
                    } catch (IOException e) {
                        //e.printStackTrace();
                        System.out.println("Unable to list directories, please try again. ");

                    }
                    break;
                case "2":
                    System.out.println("What file do you want to download?");
                    String path = usrChoice.next();
                    System.out.println("Where do you want to save the downloaded file?");
                    String save = usrChoice.next();
                    try {
                        getFile.execute(client, path, save);
                    } catch (IOException e) {
                        //e.printStackTrace();
                        System.out.println("Unable to get file, please try again. ");
                    }
                    break;
                case "3":
                    System.out.println("Would you like to view the files on the remote server first?");
                    System.out.println("Yes . . . . . . . . . . . (1)");
                    System.out.println("No . . . . . . . . . . .  (2)");

                    choice = usrChoice.next();

                    if (choice.equals("1")) {
                        try {
                            listDir.execute(client);
                        } catch (IOException e) {
                            //e.printStackTrace();
                            System.out.println("Unable to list file, please try again. ");
                        }
                    }
                    try {
                        delFile.execute(client);
                    } catch (IOException e) {
                        //e.printStackTrace();
                        System.out.println("Unable to delete file remotely, please try again. ");

                    }

                    break;
                case "4":
                    try {
                        makeDir.execute(client);
                    } catch (IOException e) {
                        //e.printStackTrace();
                        System.out.println("Unable to make a directory, please try again. ");

                    }
                    break;
                case "5": {
                    try {
                        System.out.println("What directory do you want to list?");
                        String dirPath = usrChoice.next();
                        new CommandListLocal().execute(client, dirPath);
                    } catch (IOException e) {
                        System.out.println("Unable to list local directory, please try again");
                    }
                }
                case "6":
                    try {
                        delDirRemote.execute(client);
                    } catch (IOException e) {
                        //e.printStackTrace();
                        System.out.println("Unable to remove directory remotely, please try again. ");
                    }
                    break;
                case "7":
                    try {
                        addFile.execute(client);
                    } catch (IOException e) {
                        //e.printStackTrace();
                        System.out.println("Unable to add file to remote, please try again. ");

                    }
                    break;
                case "8":
                    try {
                        System.out.println("Please supply a list of files you wish to upload separated by spaces (use quotes for paths containing spaces)");
                        String paths = usrChoice.next();
                        Matcher m = STRING_SPLIT_PATTERN.matcher(paths);
                        LinkedList<String> matches = new LinkedList<>();
                        while (m.find()) {
                            if (m.group(1) != null) {
                                matches.add(m.group(1));
                            } else if (m.group(2) != null) {
                                matches.add(m.group(2));
                            } else {
                                // Add unquoted word
                                matches.add(m.group());
                            }
                        }
                        batchUpload.execute(client, matches.toArray(new String[matches.size()]));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "9":
                    try {
                        System.out.println("What file do you want to change permissions on?");
                        String trgetPath = usrChoice.next();
                        System.out.println("What permission mask do you want to use (e.g. 777)");
                        String mod = usrChoice.next();
                        new CommandChangePermissions().execute(client, trgetPath, mod);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "10":
                    try {
                        new CommandGetMultiple().execute(client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "11":
                    try {
                        logOff.execute(client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Invalid option.  Please select a valid option");
                    break;
            }

        } while (!choice.equals("11"));
        System.out.println("Exiting the program");
    }


    public static void login() {
        try {
            connectFtp.execute(client, new String[]{host, String.valueOf(port), login, password});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void testLogin(){
        try {
            connectFtp.execute(client, new String[]{testHost, String.valueOf(testPort), testLogin, testPassword});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



