package view;

import file.ObjectPersistance;

import objects.Component;
import exceptions.ComponentDoesNotExistException;
import exceptions.ImproperFormatException;
import objects.Student;

import java.io.Console;
import java.io.File;
import java.io.IOException;

class CLIent {

    private Component currentObj;
    private Console console;
    private File rootDir;

    CLIent(Console consoleIn, File passedDir) {

        runCLI(consoleIn, passedDir);

    }

    private void runCLI(Console consoleIn, File passedDir) {

        console = consoleIn;
        rootDir = passedDir;

        loadStudent();
        clearScreen();
        currentObj.render(console);

        boolean loop = true;

        while (loop) {

            String[] input = console.readLine("(%s) How can I help? > ", currentObj.toDisplayName())
                    .toLowerCase()
                    .trim()
                    .split(" ");
            // Clear the screen
            clearScreen();

            switch (input[0]) {
                case "show":
                    currentObj.render(console);
                    break;

                case "add":
                    if (input.length == 3) {
                        try {
                            currentObj.add(input[1], input[2], console);
                        } catch (ImproperFormatException ife) {
                            console.format(ife.toString());
                        }
                    }

                    else {
                        try {
                            currentObj.add(console);
                        } catch (ImproperFormatException ife) {
                            console.format(ife.toString());
                        }
                    }
                    currentObj.render(console);
                    break;

                case "remove":
                    if (input.length == 3) {
                        try {
                            currentObj.remove(input[1], input[2], console);
                        } catch (ComponentDoesNotExistException | ImproperFormatException ex) {
                            console.format(ex.toString());
                        }
                    }

                    else {
                        try {
                            currentObj.remove(console);
                        } catch (ComponentDoesNotExistException | ImproperFormatException ex) {
                            console.format(ex.toString());
                        }
                    }
                    currentObj.render(console);
                    break;

                case "open":

                    if (input.length == 3) {

                        try {
                            currentObj = currentObj.open(input[1], input[2], console);
                        } catch (ComponentDoesNotExistException | ImproperFormatException ex) {
                            console.format(ex.toString());
                        }

                    } else {
                        try {
                            currentObj = currentObj.open(console);
                        } catch (ComponentDoesNotExistException | ImproperFormatException ex) {
                            console.format(ex.toString());
                        }
                    }
                    currentObj.render(console);
                    break;

                case "out":

                    if (currentObj.out() == null) {
                        save();
                        loadStudent();
                    } else {
                        currentObj = currentObj.out();
                        currentObj.render(console);
                    }

                    break;

                case "help":
                    console.format("%n<<this is an interactive terminal application>>%n%n");
                    console.format("commands: %n%n");
                    console.format("    'add': add a sub-component to the current component%n%n");
                    console.format("    'remove': remove a sub-component from the current component%n%n");
                    console.format("    'open': enter a sub-component%n%n");
                    console.format("    'out': enter the parent of the current component%n%n");
                    console.format("    'show': shows data within current object%n%n");
                    console.format("    'quit': exits the application%n%n");
                    console.format("    'help': shows this prompt%n%n");

                    break;

                case "quit":
                    save();
                    quit();
                    break;

                case "save":
                    //TODO implement session save
                    save();
                    break;

                default: console.format("unknown command, type 'help' for help menu%n");

            }

        }

    }

    private void quit() {

        console.format("Hope to see you soon!%n%n");
        System.exit(0);

    }

    private void save() {

        Student saveObj = null;
        Component originalObj = currentObj;
        boolean notStudent = true;
        do {
            try {
                saveObj = (Student)currentObj;
                notStudent = false;
                break;
            } catch (ClassCastException cce) {
                currentObj = currentObj.out();
            }
        } while (notStudent);
        ObjectPersistance.saveStudent(saveObj, rootDir);
        currentObj = originalObj;

    }

    private void loadStudent() {

        console.format("╞═════════════════ Student List ═════════════════╡%n");
        String[] studentList = rootDir.list();
        for (String file : studentList) {
            console.format("%" + (25 + file.length()/2) + "s%n", file);
        }

        boolean loop = true;

        while (loop) {

            String[] arguments = console.readLine("Select Student (full filename): ").trim().split(" ");

            if (arguments.length > 1) {

                String load = arguments[0] + "_" +arguments[1];

                try {
                    currentObj = ObjectPersistance.loadStudent(load, rootDir);
                    loop = false;
                    break;
                } catch (IOException e) {
                    System.err.println("------");
                    System.err.printf("file not found (%s.student)%n", load);
                    continue;
                }

            }

            switch (arguments[0].toLowerCase()) {

                case "q":
                case "quit":
                    quit();
                    break;

                case "n":
                case "new":
                    ObjectPersistance.saveStudent(new Student(console), rootDir);
                    continue;

                default:
                    try {
                        currentObj = ObjectPersistance.loadStudent(arguments[0], rootDir);
                        loop = false;
                        break;
                    } catch (IOException e) {
                        System.err.println("------");
                        System.err.printf("file not found (%s.student)%n", arguments[0]);
                    }

            }

        }

    }

    private void clearScreen() {

        console.format("\033[H\033[2J");
        console.flush();

    }

}
