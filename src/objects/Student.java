package objects;

import java.io.Console;
import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable, Component {

    private String firstName;
    private String lastName;
    private ArrayList<Semester> semesterList = new ArrayList<>();

    public Student(Console console) {

        console.format("%n--- Creating Student ---%n");
        this.firstName = console.readLine("First Name > ");
        this.lastName = console.readLine("Last Name > ");
        console.format("--- Student Initialized ---%n%n");

    }

    public String toFileName() {

        return firstName + "_" + lastName + ".student";

    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public void render(Console console) {

        console.format("Current Student: %s %s%n%n", firstName, lastName);
        console.format("Semesters Saved:");

        if (semesterList.isEmpty()) {
            console.format("\u001B[31m No Semesters Saved\u001B[0m%n%n");
        } else {

            for (Semester sem : semesterList) {
                console.format("%n├%s", sem.toDisplayName());
            }

            console.format("%n┴%n%n");
        }

    }

    @Override
    public void add() {

    }

    @Override
    public void remove() {

    }

    @Override
    public Component open(Component component) {
        return null;
    }
}
