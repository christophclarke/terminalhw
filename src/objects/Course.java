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

    public void addExam(Exam addedExam) {
        examList.add(addedExam);
    }

    //returns true if exam exists and is removed, else false
    public boolean removeExam(Exam removedExam) {
        return examList.remove(removedExam);
    }

    public void addAssignment(Assignment addedAssignment) {
        assignmentList.add(addedAssignment);
    }

    public boolean removeAssignment(Assignment removedAssignment) {
        return assignmentList.remove(removedAssignment);
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
