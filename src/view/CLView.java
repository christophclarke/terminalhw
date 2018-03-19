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

        console.printf("Hello \u001B[31m%s\u001B[0m, welcome to termhw!\n", System.getProperty("user.name"));

        File rootDir = new File(System.getProperty("user.home") + "/.hwdata/");

        if (!rootDir.exists()) {

            file.Directory.initialize(console, rootDir);

        }

//        switch (console.readLine("Select Student")) ;
//
//        while (true) {
//
//            String input = console.readLine("How can I help?");
//
//            switch (input.toLowerCase()) {
//                case "show":
//
//
//            }
//
//        }

    }

}