package file;

import objects.Student;

import java.io.Console;
import java.io.File;

public class Directory {

    public static void initialize(Console console, File rootDir) {

        boolean loop = true;

        console.format("%nLooks like this is your first time; ");

        while (loop) {

            //Prompt user to initialize
            switch (console.readLine("Would you like to initialize? (y/n/quit) > ")) {

                // if user gives positive response
                case "y":

                    //catch write permission error
                    try {
                        rootDir.mkdirs();
                    } catch (SecurityException se) {
                        System.err.println("%n%nSecurity Exception -> Check write permissions");
                        System.exit(77);
                    }

                    //if dir written, create student file
                    if (rootDir.exists()) {
                        Student currentStudent = new Student(console);
                        ObjectPersistance.saveStudent(currentStudent, rootDir);
                        loop = false;
                        break;
                    }

                //if negative response given, prompt user to respond positively or quit
                case "n":
                    console.format("Must initialize directory to continue, respond with 'y' to continue, or 'quit' to quit%n%n");
                    break;
                case "q":
                case "quit":
                    console.format("Hope to see you soon!%n%n");
                    System.exit(0);
                default:
                    console.format("Response not recognized, must initialize directory to continue%n%n");
                    break;
            }
        }
    }
}
