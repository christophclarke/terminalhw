package objects;

import exceptions.ComponentDoesNotExistException;
import exceptions.ImproperFormatException;

import java.io.Console;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Semester implements Serializable, Component, Comparable<Semester> {

    private int term;

    private int year;

    private ArrayList<Course> courseList;

    Student parentStudent;

    //New semester constructor
    Semester(Console console, Student caller) throws ImproperFormatException {

        console.format("%n--- Creating Semester ---%n");
        this.term = StringToTermInt(console.readLine("Term (Spring, Fall, etc.) > "));
        try {
            this.year = Integer.parseInt(console.readLine("Year > "));
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }
        this.courseList = new ArrayList<>();
        this.parentStudent = caller;
        console.format("--- Semester Added ---%n%n");
    }

    Semester(String term, String year, Console console, Student caller) throws ImproperFormatException {

        int yearInt;

        try {
            yearInt = Integer.parseInt(year);
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }

        this.term = StringToTermInt(term);
        this.year = yearInt;
        this.courseList = new ArrayList<>();
        this.parentStudent = caller;
        if (console != null) {console.format("--- Semester Added ---%n%n");}
    }

    public String toDisplayName() {

        StringWriter sw = new StringWriter();
        sw.append(getTermString());
        sw.append(" ");
        sw.append(Integer.toString(year));
        return sw.toString();

    }

    String getTermString() {

        switch (term) {
            case 0: return "Spring";
            case 1: return "Fall";
        }
        return null;

    }

    int getYear() {
        return year;
    }

    @Override
    public void render(Console console) {

        console.format("Current Student: %s%n", parentStudent.toDisplayName());
        console.format("Classes saved in %s:", toDisplayName());
        if (courseList.isEmpty()) {
            console.format("\u001B[36m No Courses Saved\u001B[0m%n%n");
        } else {

            for (Course course : courseList) {
                console.format("%n├%s", course.toDisplayName());
            }
            console.format("%n┘%n%n");

        }

    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        if (courseList.isEmpty()) {
            sb.append("No Saved Courses");
            return sb.toString();
        } else {
            for (Course course : courseList) {
                sb.append(String.format("%n├%s", course.toDisplayName()));
            }
            return sb.toString();
        }
    }

    @Override
    public void add(Console console) throws ImproperFormatException {

        courseList.add(new Course(console, this));
        Collections.sort(courseList);

    }

    @Override
    public void add(String arg1, String arg2, Console console) throws ImproperFormatException {
        courseList.add(new Course(arg1, arg2, console, this));
        Collections.sort(courseList);
    }

    @Override
    public void remove(Console console) throws ComponentDoesNotExistException, ImproperFormatException {

        console.format("--- Removing Course ---%n");
        String rmDept = console.readLine("Course Department > ").trim();
        String rmNum = console.readLine("Course Number > ").trim();

        remove(rmDept, rmNum, console);

    }

    @Override
    public void remove(String arg1, String arg2, Console console) throws ComponentDoesNotExistException, ImproperFormatException {

        int rmNum;
        try {
            rmNum = Integer.parseInt(arg2);
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }

        for (int i = 0; i < courseList.size(); i++) {

            // If a given course department and number match the entered department and number, remove it
            if (courseList.get(i).getCourseNumber() == rmNum) {
                if(courseList.get(i).getDepartment().equalsIgnoreCase(arg1)) {
                    courseList.remove(i);
                    if (console != null) {console.format("--- Semester Removed ---%n");}
                    return;
                }
            }

        }
        throw new ComponentDoesNotExistException();
    }

    @Override
    public Component open(Console console) throws ComponentDoesNotExistException, ImproperFormatException {

        console.format("--- Entering Semester ---");
        String deptString = console.readLine("Semester Term > ").trim();
        String classNumString = console.readLine("Semester Year > ").trim();

        return open(deptString, classNumString, console);

    }

    @Override
    public Component open(String arg1, String arg2, Console console) throws ComponentDoesNotExistException, ImproperFormatException {

        int classNum;

        try {
            classNum = Integer.parseInt(arg2);
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }

        for (Course course : courseList) {

            if (course.getCourseNumber() == classNum) {
                if (course.getDepartment().equalsIgnoreCase(arg1))
                    return course;
            }

        }
        throw new ComponentDoesNotExistException();
    }

    @Override
    public Component out() {
        return parentStudent;
    }

    @Override
    public int compareTo(Semester that) {

        int diff;
        diff = this.year - that.year;
        if (diff != 0) {return diff;}
        diff = this.term - that.term;
        return diff;

    }

    private int StringToTermInt(String termtext) throws ImproperFormatException {

        switch (termtext.trim().toLowerCase()) {

            case "spring":
                return 0;

            case "fall":
                return 1;

        }

        throw new ImproperFormatException();

    }
}