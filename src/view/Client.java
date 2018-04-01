package view;

import file.ObjectPersistance;
import objects.Renderable;

import java.io.Console;
import java.io.File;
import java.io.IOException;

import static view.Render.render;

class Client {

    private Renderable currentObj;
    private Console console;
    private File rootDir;

    Client(Console consoleIn) {

        console = consoleIn;

        console.printf("Hello \u001B[31m%s\u001B[0m, welcome to termhw!%n", System.getProperty("user.name"));

        rootDir = new File(System.getProperty("user.home") + "/.hwdata/");

        if (!rootDir.exists()) {

            file.Directory.initialize(console, rootDir);

        }

        clientInit();

        boolean loop = true;

        while (loop) {

            String[] input = console.readLine("How can I help? > ").toLowerCase().split(" ");

            switch (input[0]) {
                case "show":
                    render(console, currentObj);
                    break;
                case "open":

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
                    loop = false;
                default: console.format("unknown command, type 'help' for help menu%n");
            }

        }



    }

    private void clientInit() {
        console.format("╞═════════════════ Student List ═════════════════╡%n");
        String[] studentList = rootDir.list();
        for (String file : studentList) {
            console.format("%" + (25 + file.length()/2) + "s%n", file);
        }

        loadStudent();

    }

    private void quit() {
        console.format("Hope to see you soon!%n%n");
        System.exit(0);
    }

    private void save() {

    }

    private void loadStudent() {

        String load = console.readLine("Select Student (full filename): ");

        if (load.toLowerCase().equals("quit")) {
            quit();
        }

        //TODO: allows session restoration
        try {
            currentObj = ObjectPersistance.loadStudent(load, rootDir);
        } catch (IOException e) {
            System.err.println("------");
            System.err.printf("file not found (%s.student)%n", load);
            e.printStackTrace();
            clientInit();
        }
    }

}
