package objects;

import exceptions.ComponentDoesNotExistException;
import exceptions.ImproperFormatException;

import java.io.Console;
import java.io.Serializable;

public class Assignment implements Serializable, Component, Comparable<Assignment> {

    private Course parentCourse;
    private String month;
    private int date;
    private String assignmentType;
    private String moreInfo = null;

    Assignment(Console console, Course caller) throws ImproperFormatException {

        console.format("%n--- Creating Assignment ---%n");
        this.month = console.readLine("Due Month > ");
        try {
            this.date = Integer.parseInt(console.readLine("Due Day > "));
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }

        this.assignmentType = console.readLine("Assignment Type > ");

        this.parentCourse = caller;

        console.format("--- Assignment Created ---%n%n");

    }

    Assignment(String month, String date, String assignmentType, Console console, Course caller) throws ImproperFormatException {

        this.month = month;
        try {
            this.date = Integer.parseInt(console.readLine(date));
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }
        this.assignmentType = assignmentType;
        this.parentCourse = caller;
        console.format("--- Assignment Created ---%n%n");

    }

    @Override
    public int compareTo(Assignment that) {
        int diff;
        diff = Utilities.monthStringToInt(month) - Utilities.monthStringToInt(that.month);
        if (diff != 0) {return diff;}
        diff = date - that.date;
        if (diff != 0) {return diff;}
        diff = assignmentType.compareToIgnoreCase(that.assignmentType);
        return diff;
    }

    @Override
    public void render(Console console) {

        console.format("Current Student: %s%n%n", parentCourse.parentSemester.parentStudent.toDisplayName());
        console.format("%s %s:%n%n", parentCourse.parentSemester.toDisplayName(), parentCourse.toDisplayName());
        console.format("%s due %s %d", assignmentType, month, date);
        if (moreInfo != null) {
            console.format(moreInfo + "%n%n");
        }

    }

    @Override
    public void add(Console console) {

        String addInfo = console.readLine("Additional info for Assignment > ");
        if (addInfo.compareToIgnoreCase("quit") != 0) {
            this.moreInfo = addInfo;
        }

    }

    @Override
    public void add(String arg1, String arg2, Console console) {

        add(console);

    }

    @Override
    public void remove(Console console) {

        switch(console.readLine("Remove info? [y/n] > ").toLowerCase()) {

            case "y":
            case "yes":
                this.moreInfo = null;
                console.format("--- Assignment Info Removed ---%n%n");
                break;
            case "n":
            case "no":
                console.format("Assignment Unmodified%n%n");
                break;
            default:
                console.format("Input not recognized%nAssignment Unmodified%n%n");
                break;
        }
    }

    @Override
    public void remove(String arg1, String arg2, Console console) {

        if (arg1.compareToIgnoreCase("info") == 0) {
            this.moreInfo = null;
        }
        console.format("--- Assignment Info Removed ---%n%n");

    }

    @Override
    public Component open(Console console) throws ComponentDoesNotExistException {
        throw new ComponentDoesNotExistException();
    }

    @Override
    public Component open(String arg1, String arg2, Console console) throws ComponentDoesNotExistException {
        throw new ComponentDoesNotExistException();
    }

    @Override
    public Component out() {
        return parentCourse;
    }

    @Override
    public String toDisplayName() {
        return month + " " + date + " " + assignmentType;
    }

    public String getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public String getAssignmentType() {
        return assignmentType;
    }
}
