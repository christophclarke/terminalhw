package objects;

import java.io.Console;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

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
    public void add(Console console) {

        semesterList.add(new Semester(console));
        Collections.sort(semesterList);

    }

    @Override
    public void remove(Console console) {

        console.format("--- Removing Semester ---%n");
        String rmTerm = console.readLine("Semester Term > ").trim();
        int rmYear = Integer.parseInt(console.readLine("Semester Year > "));

        remove(rmTerm, rmYear, console);

    }

    @Override
    public void remove(String rmTerm, int rmYear, Console console) {

        for (int i = 0; i < semesterList.size(); i++) {

            // If a given semester term and year match the entered term and year, remove it
            if (semesterList.get(i).getYear() == rmYear) {
                if(semesterList.get(i).getTermString().equalsIgnoreCase(rmTerm)) {
                    semesterList.remove(i);
                    console.format("--- Semester Removed ---%n");
                    return;
                }
            }

        }
        console.format("Semester Not Found%n");
    }

    @Override
    public Component open(Component component) {
        return null;
    }

    @Override
    public String toDisplayName() {
        return firstName + " " + lastName;
    }
}
