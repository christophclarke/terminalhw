package objects;

import exceptions.ComponentDoesNotExistException;
import exceptions.ImproperFormatException;

import java.io.Console;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;

class Course implements Serializable, Component, Comparable<Course> {

    private String department;
    private int courseNumber;

    private ArrayList<Exam> examList;
    private ArrayList<Assignment> assignmentList;

    Semester parentSemester;

    Course(Console console, Semester caller) throws ImproperFormatException {

        console.format("%n--- Creating Course ---%n");
        department = console.readLine("Department > ");
        try {
            this.courseNumber = Integer.parseInt(console.readLine("Course Number > "));
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }

        this.examList = new ArrayList<>();
        this.assignmentList = new ArrayList<>();
        this.parentSemester = caller;

        console.format("--- Course Created ---%n%n");

    }

    Course(String dept, String courseNumStr, Console console, Semester caller) throws ImproperFormatException {

        try {
            this.courseNumber = Integer.parseInt(courseNumStr);
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }
        this.department = dept;
        this.parentSemester = caller;
        this.examList = new ArrayList<>();
        this.assignmentList = new ArrayList<>();

        console.format("--- Course Created ---%n%n");
    }

    int getCourseNumber() {
        return courseNumber;
    }

    String getDepartment() {
        return department;
    }

    //returns "Course{{dept} {courseNbr} | Exams: {examList.toString()}, Assignments: {assignmentList.toString()}}"
    @Override
    public String toString() {
        return "Course{" + this.department + " " + this.courseNumber + " | " +
                "Exams: " + examList.toString() + ", Assignments: " + assignmentList.toString() + "}";
    }

    @Override
    public void render(Console console) {

        console.format("Current Student: %s%n%n", parentSemester.parentStudent.toDisplayName());
        console.format("%s %s:%n%n", parentSemester.toDisplayName(), toDisplayName());
        if (assignmentList.isEmpty()) {
            console.format("\u001B[36m No Assignments Saved\u001B[0m%n%n");
        } else {

            console.format("Assignments:");
            for (Assignment assignment : assignmentList) {
                console.format("%n├%s", assignment.toDisplayName());
            }
            console.format("%n┘%n%n");

        }

        if (examList.isEmpty()) {
            console.format("\u001B[36m No Exams Saved\u001B[0m%n%n");
        } else {

            console.format("Exams:");
            for (Exam exam : examList) {
                console.format("%n├%s", exam.toDisplayName());
            }
            console.format("%n┘%n%n");

        }

    }

    @Override
    public void add(Console console) {

    }

    @Override
    public void add(String arg1, String arg2, Console console) {

    }

    @Override
    public void remove(Console console) throws ComponentDoesNotExistException {

    }

    @Override
    public void remove(String arg1, String arg2, Console console) throws ComponentDoesNotExistException {

    }

    @Override
    public Component open(Console console) throws ComponentDoesNotExistException, ImproperFormatException {
        return null;
    }

    @Override
    public Component open(String arg1, String arg2) {
        return null;
    }

    @Override
    public Component out() {
        return parentSemester;
    }

    @Override
    public String toDisplayName() {

        StringWriter sw = new StringWriter();
        sw.append(department.toUpperCase());
        sw.append(" ");
        sw.append(Integer.toString(courseNumber));

        return sw.toString();
    }

    @Override
    public int compareTo(Course that) {
        int diff;
        diff = department.compareToIgnoreCase(that.department);
        if (diff != 0) {return diff;}
        return courseNumber - that.courseNumber;
    }
}
