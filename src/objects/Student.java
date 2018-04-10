package objects;

import java.io.Console;
import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable, Renderable {

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
    public void render(Console c) {

    }
}
