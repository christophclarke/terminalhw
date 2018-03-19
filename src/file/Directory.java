package file;

import objects.Student;

import java.io.Console;
import java.io.File;

public class Directory {

    public static void initialize(Console console, File rootDir) {

        boolean loop = true;

        while (loop) {

            //Prompt user to initialize
            switch (console.readLine("Looks like this is your first time, would you like to initialize? (y/n/quit) >")) {

                // if user gives positive response
                case "y":

                    //catch write permission error
                    try {
                        rootDir.mkdirs();
                    } catch (SecurityException se) {
                        System.err.println("Security Exception -> Check write permissions");
                        System.exit(77);
                    }

                    //if dir written, create student file
                    if (rootDir.exists()) {
                        Student currentStudent = new Student(console);
                        ObjectPersistance.saveStudent(currentStudent, rootDir);

                    }

                //if negative response given, prompt user to respond positively or quit
                case "n":
                    console.format("Must initialize directory to continue, respond with 'quit' to quit");
                case "quit":
                    console.format("Hope to see you soon");
                    System.exit(0);

                default:
                    console.format("Response not recognized, must initialize directory to continue");
            }
        }
    }
}
