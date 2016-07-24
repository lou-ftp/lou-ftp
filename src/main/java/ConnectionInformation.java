import java.util.Scanner;

/**
 * Created by Dan Conradton 7/10/2016.
 */
public class ConnectionInformation {

    Scanner input = new Scanner(System.in);
    String  host;
    String port;

    // Initialize User class variables
    public ConnectionInformation(){
        this.host= "";
        this.port = "";
    }

    //Constructor to set host and port
    public ConnectionInformation(String host, String port){
        this.host = host;
        this.port = port;
    }

    // Provide the host information
    public String getHost() {
        return host;
    }

    // Provide the port information
    public int getPort(){
        try {
            return Integer.parseInt(port);
        } catch(NumberFormatException e) {
            System.err.println("Error with input for Port - NAN: " + e.getMessage() );
            return -1;
        }
    }

    // Get the host information from the user.
    public boolean inputHost(){
        boolean result = false;
        System.out.println("Host Name :");
        this.host = input.nextLine();
        if(host != null)
            result = true;
        return result;
    }

    // Get the port information from the user
    public boolean inputPort(){
        boolean result = false;
        System.out.println("Port:");
        this.port = input.nextLine();
        if(port != "")
            result = true;
        return result;
    }
}
