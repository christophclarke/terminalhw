package objects;

import java.io.Console;
import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable, Renderable {

    private String firstName;
    private String lastName;
    private String displayName;
    private ArrayList<Semester> semesterList = new ArrayList<>();

    public Student(Console console) {

        console.format("%n--- Creating Student ---%n");
        this.firstName = console.readLine("First Name > ");
        this.lastName = console.readLine("Last Name > ");
        console.format("--- Student Initialized ---%n%n");
        this.displayName = this.firstName + "_" + this.lastName;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Renderable> getObjList() {
        return semesterList;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    public void setSemesterList(ArrayList<Semester> semesterList) {
        this.semesterList = semesterList;
    }

    public String toFileName() {

        return firstName + "_" + lastName + ".student";

    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
