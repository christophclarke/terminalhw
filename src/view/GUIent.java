package view;

import basicgraphics.BasicFrame;
import exceptions.ComponentDoesNotExistException;
import exceptions.ImproperFormatException;
import file.Directory;
import file.ObjectPersistance;
import objects.Component;
import objects.Student;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class GUIent {

    private BasicFrame f;
    private Component currentObj;
    private File rootDir;

    GUIent(File rDir) {

        f = new BasicFrame("terminalhw");
        f.jf.setSize(400, 800);

        rootDir = rDir;

        welcome();

    }

    private void welcome() {

        String[][] welcomeLayout = {
                {"lbuf1", "lbuf1", "lbuf1", "rbuf1", "rbuf1"},
                {"lbuf2", "lbuf2", "midd2", "rbuf2", "rbuf2"},
                {"lbuf3", "lbuf3", "midd3", "rbuf3", "rbuf3"},
                {"lbuf4", "lbuf4", "midd4", "rbuf4", "rbuf4"},
                {"lbuf5", "lbuf5", "midd5", "rbuf5", "rbuf5"},
                {"lbuf6", "left6", "midd6", "righ6", "rbuf6"},
                {"lbuf7", "lbuf7", "rbuf7", "rbuf7", "rbuf7"},
        };


        JLabel title = new JLabel("TERMINALHW", JLabel.CENTER);

        JLabel space = new JLabel("          ");

        for (int i = 1; i <= welcomeLayout.length; i++) {
            f.add(welcomeLayout, "lbuf" + i, space);
            f.add(welcomeLayout, "rbuf" + i, space);
        }
        f.add(welcomeLayout, "midd2", new JLabel("welcome to", JLabel.CENTER));
        f.add(welcomeLayout, "midd3", title);
        f.add(welcomeLayout, "midd4", new JLabel("(in a GUI)", JLabel.CENTER));
        f.add(welcomeLayout, "midd5", space);

        boolean dataExists = rootDir.exists();
        if (!dataExists)
            f.add(welcomeLayout, "midd6", new JLabel("initialize?", JLabel.CENTER));
        else
            f.add(welcomeLayout, "midd6", new JLabel("continue?", JLabel.CENTER));

        JButton yes = new JButton("yes");
        yes.addActionListener(event -> {
            if (!dataExists) {
                newStudent();
            } else {
                studentSelect();
            }
            mainWindow();
        });

        JButton no =  new JButton("no");
        no.addActionListener(event -> System.exit(6));

        f.add(welcomeLayout, "left6", yes);
        f.add(welcomeLayout, "righ6", no);

        f.setAllFonts(new Font("Arial", Font.PLAIN, 15));
        title.setFont(new Font("Arial", Font.BOLD, 50));
        render();

    }

    private void render() {

        f.show();

    }

    private void newStudent() {

        String [] nameArray = JOptionPane.showInputDialog("Student Name [first last]").split(" ");
        Directory.GUInitialize(nameArray, rootDir);
        studentSelect();

    }

    private void studentSelect() {

        String studentList = "";
        for (String filename : rootDir.list()) {

            filename = filename.substring(0, filename.length() - 8);
            String[] filenameArray = filename.split("_");
            studentList = studentList + filenameArray[0] + " " + filenameArray[1] + "\n";

        }

        while (true) {

            String selectedStudent = JOptionPane.showInputDialog(studentList + "Select Student: ");
            if (selectedStudent.equalsIgnoreCase("new")) {
                newStudent();
                break;
            }
            String[] nameArray = selectedStudent.split(" ");
            String studentFilename = nameArray[0] + "_" + nameArray[1];
            try {
                currentObj = ObjectPersistance.loadStudent(studentFilename, rootDir);
                break;
            } catch (IOException ioe) {
                System.err.println("User did not select Student");
            }
        }

    }

    private void mainWindow() {

        f.jf.setVisible(false);
        f.dispose();

        BasicFrame bf = new BasicFrame("main window");

        String[][] mainLayout = {
            {"topl", "topl", "topr", "topr"},
            {"texta", "texta", "texta", "texta"},
            {"texta", "texta", "texta", "texta"},
            {"texta", "texta", "texta", "texta"},
            {"lbutton", "lmbutton", "rmbutton", "rbutton"}
        };

        JTextArea jta = new JTextArea(25, 75);
        jta.setEditable(false);

        bf.add(mainLayout, "topl", new JLabel("Current Student: ", JLabel.RIGHT));
        bf.add(mainLayout, "topr", new JLabel(currentObj.toDisplayName(), JLabel.LEFT));
        bf.add(mainLayout, "texta", jta);

//      'Open' Command Handling
        JButton open = new JButton("open");
        open.addActionListener(e -> {
            while (true) {
                String inputArray[] = JOptionPane.showInputDialog("What to Open?").split(" ");

                if (inputArray[0].equalsIgnoreCase("save")) {save(); break;}
                try {
                    currentObj = currentObj.open(inputArray[0], inputArray[1], null);
                    break;
                } catch (ComponentDoesNotExistException | ImproperFormatException ex) {
                    System.err.println(ex);
                }
            }
            updateTA(jta);
            render();
        });

//      'Back' Command Handling
        JButton back = new JButton("back");
        back.addActionListener(e -> {
            if (currentObj.out() == null) {
                save();
                studentSelect();
                mainWindow();
            } else {
                currentObj = currentObj.out();
                updateTA(jta);
            }
        });

//      'Add' Command Handling
        JButton add = new JButton("add");
        add.addActionListener(e -> {
            while (true) {
                String[] inputArray = JOptionPane.showInputDialog("New Component -> ").split(" ");

                if (inputArray[0].equalsIgnoreCase("save")) {save(); break;}

                try {
                    currentObj.add(inputArray[0], inputArray[1], null);
                    break;
                } catch (ImproperFormatException ife) {
                    System.err.println(ife);
                }
            }
            updateTA(jta);
            render();
        });

//      'Remove' Command Handling
        JButton remove = new JButton("remove");
        remove.addActionListener(e -> {
            while (true) {
                String[] inputArray = JOptionPane.showInputDialog("Remove Component -> ").split(" ");

                if (inputArray[0].equalsIgnoreCase("save")) {save(); break;}

                try {
                    currentObj.remove(inputArray[0], inputArray[1], null);
                    break;
                } catch (ComponentDoesNotExistException | ImproperFormatException ex) {
                    System.err.println(ex);
                }
            }
            updateTA(jta);
            render();
        });

        bf.add(mainLayout, "lbutton", open);
        bf.add(mainLayout, "lmbutton", back);
        bf.add(mainLayout, "rmbutton", add);
        bf.add(mainLayout, "rbutton", remove);

        updateTA(jta);


        f = bf;
        render();

    }

    private void updateTA(JTextArea jta) {

        jta.setText(currentObj.render());

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

}
