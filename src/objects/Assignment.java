package objects;

import exceptions.ComponentDoesNotExistException;
import exceptions.ImproperFormatException;

import java.io.Console;
import java.io.Serializable;

public class Assignment implements Serializable, Component, Comparable<Assignment> {

    private Course parentCourse;
    private String dueMonth;
    private int dueDay;
    private String assignmentType;
    private String moreInfo = null;

    Assignment(Console console, Course caller) throws ImproperFormatException {

        console.format("%n--- Creating Assignment ---%n");
        this.dueMonth = console.readLine("Due Month > ").toLowerCase();
        try {
            this.dueDay = Integer.parseInt(console.readLine("Due Day > "));
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }

        this.assignmentType = console.readLine("Assignment Type > ");

        this.parentCourse = caller;

        console.format("--- Course Created ---%n%n");

    }

    Assignment(String dueMonth, String dueDay, String assignmentType, Console console, Course caller) throws ImproperFormatException {

        this.dueMonth = dueMonth;
        try {
            this.dueDay = Integer.parseInt(console.readLine(dueDay));
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }
        this.assignmentType = assignmentType;
        this.parentCourse = caller;
        console.format("--- Course Created ---%n%n");

    }

    @Override
    public int compareTo(Assignment that) {
        int diff;
        diff = monthStringToInt(dueMonth) - monthStringToInt(that.dueMonth);
        if (diff != 0) {return diff;}
        diff = dueDay - that.dueDay;
        if (diff != 0) {return diff;}
        diff = assignmentType.compareToIgnoreCase(that.assignmentType);
        return diff;
    }

    @Override
    public void render(Console console) {

        console.format("Current Student: %s%n%n", parentCourse.parentSemester.parentStudent.toDisplayName());
        console.format("%s %s:%n%n", parentCourse.parentSemester.toDisplayName(), parentCourse.toDisplayName());
        console.format("%s due %s %d", assignmentType, dueMonth, dueDay);
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
                console.format("--- Info Removed ---%n%n");
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
        console.format("--- Info Removed ---%n%n");

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
        return dueMonth + " " + dueDay + " " + assignmentType;
    }

    private int monthStringToInt(String month) {

        switch(month.toLowerCase()) {

            case "january":
                return 1;
            case "february":
                return 2;
            case "march":
                return 3;
            case "april":
                return 4;
            case "may":
                return 5;
            case "june":
                return 6;
            case "july":
                return 7;
            case "august":
                return 8;
            case "september":
                return 9;
            case "october":
                return 10;
            case "november":
                return 11;
            case "december":
                return 12;
        }

        return -1;

    }

    public String getDueMonth() {
        return dueMonth;
    }

    public int getDueDay() {
        return dueDay;
    }

    public String getAssignmentType() {
        return assignmentType;
    }
}
