package objects;

import java.io.Console;
import java.io.Serializable;
import java.util.ArrayList;

class Course implements Serializable, Component {

    private String department;
    private int courseNumber;

    private ArrayList<Exam> examList = new ArrayList<>();
    private ArrayList<Assignment> assignmentList = new ArrayList<>();

    public Course(String dept, int courseNbr) {
        this.department = dept;
        this.courseNumber = courseNbr;
    }


    //returns "Course{{dept} {courseNbr} | Exams: {examList.toString()}, Assignments: {assignmentList.toString()}}"
    @Override
    public String toString() {
        return "Course{" + this.department + " " + this.courseNumber + " | " +
                "Exams: " + examList.toString() + ", Assignments: " + assignmentList.toString() + "}";
    }

    @Override
    public void render(Console c) {



    }

    @Override
    public void add(Console console) {

    }

    @Override
    public void add(String arg1, int arg2, Console console) {

    }

    @Override
    public void remove(Console console) {

    }

    @Override
    public void remove(String arg1, int arg2, Console console) {

    }

    @Override
    public Component open(String componentString) {
        return null;
    }

    @Override
    public String toDisplayName() {
        return null;
    }
}
