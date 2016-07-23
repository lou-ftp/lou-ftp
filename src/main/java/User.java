/**
 * Created by Dan Conradt on 7/9/2016.
 */

import java.util.Scanner;

public class User  {
    Scanner input = new Scanner(System.in);
    String  login;
    String password;

    // Initialize User class variables
    public User(){
        this.login = "";
        this.password = "";
    }

    //Constructor to get username and password
    public User(String username, String password){
        this.login = username;
        this.password = password;
    }

    // method to return login information
    public String getLogin() {
        return login;
    }

    public String getPassword(){
        return password;
    }
    //
    public boolean inputLogin(){
        boolean result = false;
        System.out.println("User Login:");
        this.login = input.nextLine();
        if(login != "")
            result = true;
        return result;
    }

    public boolean inputPass(){
        boolean result = false;
        System.out.println("User Password:");
        this.password = input.nextLine();
        if(password != "")
            result = true;
        return result;
    }
}
