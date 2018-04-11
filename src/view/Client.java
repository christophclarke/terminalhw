package view;

import file.ObjectPersistance;
import objects.Component;
import objects.Student;

import java.io.Console;
import java.io.File;
import java.io.IOException;

class Client {

    private Component currentObj;
    private Console console;
    private File rootDir;

    Client(Console consoleIn, File passedDir) {

        console = consoleIn;
        rootDir = passedDir;

        loadStudent();

        boolean loop = true;

        while (loop) {

            String[] input = console.readLine("(%s) How can I help? > ", currentObj.toDisplayName())
                                                                                                    .toLowerCase()
                                                                                                    .trim()
                                                                                                    .split(" ");

            switch (input[0]) {
                case "show":
                    currentObj.render(console);
                    break;
                case "add":
                    currentObj.add(console);
                    currentObj.render(console);
                    break;
                case "remove":
                    if (input.length > 1) {
                        try {
                            currentObj.remove(input[1], Integer.parseInt(input[2]), console);
                        } catch (Exception e) {
                            System.err.println(e.toString());
                            currentObj.remove(console);
                        }
                    }
                    else {currentObj.remove(console);}
                    break;
                case "open":
                    break;
                case "help":
                    console.format("%n<<this is an interactive terminal application>>%n%n");
                    console.format("commands: %n%n");
                    console.format("    'show': shows data within current object%n%n");
                    console.format("    'quit': exits the application%n%n");
                    console.format("    'help': shows this prompt%n%n");
                    break;
                case "quit":
                    quit();
                    break;
                case "save":
                    //TODO implement session save
                    save();
                    quit();
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
        ObjectPersistance.saveStudent((Student)currentObj, rootDir);
    }

    private void loadStudent() {

        console.format("╞═════════════════ Student List ═════════════════╡%n");
        String[] studentList = rootDir.list();
        for (String file : studentList) {
            console.format("%" + (25 + file.length()/2) + "s%n", file);
        }

        while (true) {
            String load = console.readLine("Select Student (full filename): ");

            if (load.toLowerCase().equals("quit")) {
                quit();
            }

            try {
                currentObj = ObjectPersistance.loadStudent(load, rootDir);
                break;
            } catch (IOException e) {
                System.err.println("------");
                System.err.printf("file not found (%s.student)%n", load);
            }
        }

    }

}
