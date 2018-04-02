package view;

import objects.*;
import java.io.Console;
import java.io.File;

public class Entry {

    // SYSTEMS CHECK (Console, RootDir, etc.)

    public static void main(String args[]) {

        // Make sure it's running in a console.

        Console console = System.console();

        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        // Welcome message

        console.printf("Hello \u001B[31m%s\u001B[0m, welcome to termhw!%n", System.getProperty("user.name"));

        File rootDir = new File(System.getProperty("user.home") + "/.hwdata/");

        // Make sure hidden root directory exists

        if (!rootDir.exists()) {

            file.Directory.initialize(console, rootDir);

        }

        // Once system check is complete, initialize client

        new Client(console, rootDir);

    }

}