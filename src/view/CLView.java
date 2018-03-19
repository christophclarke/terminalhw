package view;

import objects.Semester;
import objects.*;

import java.io.Console;
import java.io.File;
import java.util.ArrayList;

public class CLView {

    public static void main(String args[]) {

        Student currentStudent = null;

        Console console = System.console();

        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        console.printf("Hello \u001B[31m%s\u001B[0m, welcome to termhw!%n", System.getProperty("user.name"));

        File rootDir = new File(System.getProperty("user.home") + "/.hwdata/");

        if (!rootDir.exists()) {

            file.Directory.initialize(console, rootDir);

        }

        console.format("╞═════════════════ Student List ═════════════════╡%n");
        String[] studentList = rootDir.list();
        for (String file : studentList) {
            console.format("%" + (25 + file.length()/2) + "s%n", file);
        }

//        switch (console.readLine("Select Student"));
//
//        boolean loop = true;
//
//        while (loop) {
//
//            String input = console.readLine("How can I help?");
//
//            switch (input.toLowerCase()) {
//                case "show":
//
//                case "help":
//                    console.format("%n%n<<this is an interactive terminal application>>%n%n");
//                    console.format("commands: %n%n");
//                    console.format("    'show': shows data within current object");
//            }
//
//        }

    }

}