package view;

import file.ObjectPersistance;
import objects.Semester;
import objects.*;

import java.io.Console;
import java.io.File;
import java.util.ArrayList;

public class Entry {

    public static void main(String args[]) {

        Student currentStudent = null;

        Console console = System.console();

        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        new Client(console);

    }

}