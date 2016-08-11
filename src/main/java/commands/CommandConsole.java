package commands;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandConsole implements Command {

    private static final Pattern STRING_SPLIT_PATTERN = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'\n");

    private static Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        // Initialize commands here (lowercase)
        commands.put("upload", new AddFileToRemoteServer());
        commands.put("multiupload", new CommandBatchUploadFiles());
        commands.put("chmod", new CommandChangePermissions());
        commands.put("delete", new CommandDeleteFile());
        commands.put("get", new CommandGetFile());
        commands.put("multiget", new CommandGetMultiple());
        commands.put("ls", new CommandListDirectories());
        commands.put("list", new CommandListDirectories());
        commands.put("login", new CommandLogin());
        commands.put("logoff", new CommandLogoff());
        commands.put("mkdir", new CommandMkDir());
        commands.put("rmdir", new CommandRmDir());

    }

    @Override public void execute(FTPClient client, String... args) throws IOException {
        System.out.println("Starting up console...type :exit to exit or :help to view available commands");
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.print(">");
            String cmdString = s.nextLine();
            if (cmdString.equalsIgnoreCase(":exit")) {
                break;
            }
            if (cmdString.equalsIgnoreCase(":help")) {
                System.out.println("Available Commands: ");
                for (String s1 : commands.keySet()) {
                    System.out.println(" - " + s1);
                }
                continue;
            }
            LinkedList<String> matches = new LinkedList<>();
            Matcher m = STRING_SPLIT_PATTERN.matcher(cmdString);
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
            String cmd = matches.pop();
            String[] delegatedArgs = matches.toArray(new String[matches.size()]);
            if (!commands.containsKey(cmd.toLowerCase())) {
                System.out.println("Invalid command!");
            } else {
                try {
                    commands.get(cmd.toLowerCase()).execute(client, delegatedArgs);
                } catch (Exception e) {
                    System.out.println("Error in command, are you sure you supplied the correct arguments?");
                }
            }
        }
    }
}
