/**
 * Created by Dan Conradt on 7/9/2016.
 */


import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import commands.*;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import java.util.Scanner;

import java.io.IOException;


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
    static CommandRmDir removeDir = new CommandRmDir();
    static CommandDeleteFile delFile = new CommandDeleteFile();
    static CommandLogoff logOff = new CommandLogoff();
    static DeleteDirectoryonRemoteServer delDirRemote = new DeleteDirectoryonRemoteServer();
    static CommandGetFile getFile = new CommandGetFile();

    static String testLogin = "anonymous";
    static String testPassword = "sms8@pdcx.edu";
    static String testHost = "ftp.ed.ac.uk";
    static int testPort = 21;
    static String login = "";
    static String password = "";
    static String host ="";
    static int port = 0;

    /**
     *  Here are some FTP sites we can use to test:
     *  ftp.ed.ac.uk; email is the password, port = 21
     *  ftp.microsoft.com; user = anonymous, password = doesn't matter what you use, port = 21
     *
     */

    /**
     * Processes the initial user menu and user input.
     * @param args  list of command line arguments
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

            System.out.println("- - - | Select an option | - - -");
            System.out.println("List directories . . . . . . . .   (1)");
            System.out.println("Get file . . . . . . . . . . . .   (2)");
            System.out.println("Delete file remotely . .  . . . .  (3)");
            System.out.println("Make Directory .  . . . . . . . .  (4)");
            System.out.println("Remove Directory locally . . . . . (5)");
            System.out.println("Remove Directory remotely . . . .  (6)");
            System.out.println("Add a file to remote . . . . . . . (7)");
            System.out.println("Log off .  . . . . . . . . . . . . (8)");

            choice = usrChoice.next();

            switch (choice) {

                case "1": //usrChoice = "1";
                    try {
                        listDir.execute(client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2": //usrChoice = "2";
                    //NOT WORKING
                    System.out.println("What file do you want to download?");
                    String path = usrChoice.next();
                    System.out.println("Where do you want to save the downloaded file?");
                    String save = usrChoice.next();
                    try {
                        getFile.execute(client, path, save);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3": //usrChoice = "3";
                    System.out.println("Would you like to view the files on the remote server first?");
                    System.out.println("Yes . . . . . . . . . . . (1)");
                    System.out.println("No . . . . . . . . . . .  (2)");

                    choice = usrChoice.next();

                    if (choice.equals("1")) {
                        try {
                            listDir.execute(client);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        delFile.execute(client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                case "4": //usrChoice = "4";
                    try {
                        makeDir.execute(client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "5":
                    //THIS IS ACTUALLY REMOTE
                    try {
                        removeDir.execute(client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "6":
                    //WORKING REMOVES REMOTE
                    try {
                        delDirRemote.execute(client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "7":
                    try {
                        addFile.execute(client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "8":
                    try {
                        logOff.execute(client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default: //usrChoice = "Invalid Match";
                    System.out.println("Invalid option.  Please select a valid option");
                    break;
            }

        }while(!choice.equals("8"));


        System.out.println("Exiting the program");


        //try {
        //    addFile.execute(client);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        //try {
        //    delFile.execute(client);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}

        /*
        try {
            makeDir.execute(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if(!client.isConnected()){
                client.connect(host, port);
            }
            listDir.execute(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            client.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }

*/
    }


    public static boolean inputServer(){
        boolean result = false;

        // do some stuff
        return result;
    }

    public static boolean openSocket(String h, int p) {
        boolean result = false;

        try {
            client.connect(h, p);
            System.out.println("Connected to " + h);
            //System.out.print(client.getReplyString());

            int reply = client.getReplyCode(); // check reply code.
            if(!FTPReply.isPositiveCompletion(reply)) {
                client.disconnect();
                System.err.println("FTP server refused connection.");
                System.exit(1);
            }
            result = true;
        }
        catch(IOException e){
            System.err.println("Caught IOException: " + e.getMessage() + "\n");
            e.printStackTrace();
        }

        return result;
    }

    public static void login(){
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



    public String getCommand(){
        String command = "";

        return command;
    }

    public void processComand(String op){

    }

    public void showOptions(){

    }

    public void quit(){
        // disconnect
    }



    // **************   TESTING ************** //

    private static void run_Tests() {
        System.out.println("Starting tests....  \n");

        test_LoginSuccess();    // command -- CommandLogin()
        //test_LoginSuccess();   // command -- CommandLogin()
        //test_LoginFail();    // command -- CommandLogin()

        test_listDirectories();

        //TODO - mk dir code puts in current directory user is in on the server
        //test_mkDir();    // command -- CommandMkDir();

        //TODO - rm dir code puts in current directory user is in on the server
        test_removeDir();   // command -- CommandRmDir();  //(String pathname)

        //TODO - Simone added a file "DeleteDirectoryOnRemoteServer"
        // TODO      -- which asks the user for a specific path and file name...


        test_listDirectories();

       //test_addFile();    // command -- AddFileToRemoteServer();

        test_Logoff();    // command -- CommandLogoff()

    }

    private static void test_LoginSuccess() {
        System.out.println("\nloginSuccess... ");

        try {
            connectFtp.execute(client, new String[]{testHost, String.valueOf(testPort), testLogin, testPassword});
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    private static void test_LoginFail() {
        System.out.println("\nloginFail... \n");
        String badLogin = "aaa";

        try {
            connectFtp.execute(client, new String[]{testHost, String.valueOf(testPort), badLogin, testPassword});
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    private static void test_Logoff() {
        System.out.println("\ntest_Logoff...");

        try {
            disconnectFTP.execute(client);
        } catch (IOException e) {
            System.err.println("failed logout " + e.getMessage() + "\n");
            //e.printStackTrace();
        }
    }

    private static void test_listDirectories() {
        System.out.println("\ntest_listDirectories...");

        try {
            listDir.execute(client);
        } catch (IOException e) {
            System.err.println("failed list directories " + e.getMessage() + "\n");
            //e.printStackTrace();
        }
    }

    private static void test_addFile() {
        System.out.println("\ntest_add file...");   // command -- AddFileToRemoteServer();

        try {
            addFile.execute(client);
        } catch (IOException e) {
            System.err.println("failed addfile " + e.getMessage() + "\n");
            //e.printStackTrace();
        }

    }

    private static void test_mkDir() {
        System.out.println("\ntest_make directory...");   // command -- CommandMkDir();

        String dirName = "myDirectory";  // TODO - How will we handle the user input for mk dir?

        try {
            makeDir.execute(client, dirName);
        } catch (IOException e) {
            System.err.println("failed mkDir " + e.getMessage() + "\n");
            //e.printStackTrace();
        }

    }

    private static void test_removeDir() {
        System.out.println("\ntest_remove directory...");   // command -- CommandRmDir();

        String dirName = "myDirectory";  // TODO - How will we handle the user input for REMOVE dir?
                                         // TODO - How do we handle removing non-empty directories?

        try {
            removeDir.execute(client, dirName);
        } catch (IOException e) {
            System.err.println("test_remove directory - Failed " + e.getMessage() + "\n");
            //e.printStackTrace();
        }

    }

}
