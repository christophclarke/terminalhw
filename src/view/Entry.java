package view;

import java.io.Console;
import java.io.File;

public class Entry {

    // SYSTEMS CHECK (Console, RootDir, etc.)

    public static void main(String args[]) {

        File rootDir = new File(System.getProperty("user.home") + "/.hwdata/");

        if (args.length > 0) {

            if (args[0].equalsIgnoreCase("gui")) {

                new GUIent(rootDir);

            }
        } else {

            // Make sure it's running in a console.

            Console console = System.console();

            if (console == null) {
                System.err.println("No console.");
                System.exit(1);
            }

            // Welcome message

            console.format("\033[H\033[2J");
            console.flush();

            console.printf("Hello \u001B[31m%s\u001B[0m, welcome to termhw!%n", System.getProperty("user.name"));

            // Make sure hidden root directory exists

            if (!rootDir.exists()) {

                file.Directory.CLInitialize(console, rootDir);

            }

            // Once system check is complete, CLInitialize client

            new CLIent(console, rootDir);

        }

    }



}