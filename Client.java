package com.enterprisedt.net.ftp.FTPClient;
/**
 * Created by Dan Conradt on 7/9/2016.
 */
import org.apache.commons.net.ftp.FTPClient;

public class Client {

    // Application variables
    static FTPClient client = new FTPClient();
    static User user = new User();
    static ConnectionInformation connInfo = new ConnectionInformation();
    static String login = "";
    static String password = "";
    static String host ="";
    static int port = 0;


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

    }

    public static boolean inputServer(){
        boolean result = false;

        // do some stuff
        return result;
    }

    public static boolean openSocket() {
       boolean result = false;
        // do some stuff
        return result;
    }

    public static boolean login(){
        boolean result = false;


        return result;
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




