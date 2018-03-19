package objects;

import java.io.Console;
import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {

    private String firstName;
    private String lastName;
    private ArrayList<Semester> semesterList = new ArrayList<>();

    public Student(Console console) {

        console.format("%n--- Creating Student ---");
        this.firstName = console.readLine("%n%nFirst Name > ");
        this.lastName = console.readLine("%nLast Name > ");
        console.format("%n%n--- Student Initialized ---%n%n");

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

    public ArrayList<Semester> getSemesterList() {
        return semesterList;
    }

    public void setSemesterList(ArrayList<Semester> semesterList) {
        this.semesterList = semesterList;
    }

    public String toFileName() {

        return firstName + "_" + lastName + ".student";

    }



}