/**
 * Created by Dan Conradt on 7/9/2016.
 */


import commands.CommandLogin;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;


public class Client {

    // Application variables
    static FTPClient client = new FTPClient();
    static User user = new User();
    static ConnectionInformation connInfo = new ConnectionInformation();
    static CommandLogin connectFtp = new CommandLogin();
    static String login = "";
    static String password = "";
    static String host ="";
    static int port = 0;

    /**
     *  Here are some FTP sites we can use to test:
     *  ftp.ed.ac.uk; email is the password, port = 21
     *  ftp.microsoft.com; user = anonymous, password = doesn't matter what you use, port = 21
     */


    /**
     * Processes the initial user menu and user input.
     * @param args  list of command line arguments
     */
    public static void main(String[] args) {

        if(user.inputLogin())
            login = user.getLogin();
        else
            System.out.println("Invalid Data");

        if(user.inputPass())
            password = user.getPassword();
        else
            System.out.println("Invalid Data");

        if(connInfo.inputHost())
            host = connInfo.getHost();
        else
            System.out.println("Invalid Data");

        if(connInfo.inputPort())
            port = connInfo.getPort();
        else
            System.out.println("Invalid Data");
        login();
    }

    public static boolean inputServer(){
        boolean result = false;

        // do some stuff
        return result;
    }

    public static boolean openSocket() {
        boolean result = false;

        try {
            client.connect(host, port);
            System.out.println("Connected to " + host);
            System.out.print(client.getReplyString());

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
}
