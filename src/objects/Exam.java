package objects;

import exceptions.ComponentDoesNotExistException;
import exceptions.ImproperFormatException;

import java.io.Console;
import java.io.Serializable;

public class Exam implements Serializable, Component, Comparable<Exam> {

    private Course parentCourse;
    private String month;
    private int date;
    private String material;
    private String moreInfo = null;

    Exam(Console console, Course caller) throws ImproperFormatException {

        console.format("%n--- Creating Exam ---%n");
        this.month = console.readLine("Exam Month > ");
        try {
            this.date = Integer.parseInt(console.readLine("Exam Date > "));
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }

        this.material = console.readLine("Exam Material > ");

        this.parentCourse = caller;

        console.format("--- Exam Created ---%n%n");

    }

    Exam(String month, String date, String examMaterial, Console console, Course caller) throws ImproperFormatException {

        this.month = month;
        try {
            this.date = Integer.parseInt(date);
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }
        this.material = examMaterial;
        this.parentCourse = caller;
        if (console != null) {console.format("--- Exam Created ---%n%n");}

    }

    @Override
    public int compareTo(Exam that) {
        int diff;
        diff = Utilities.monthStringToInt(month) - Utilities.monthStringToInt(that.month);
        if (diff != 0) {return diff;}
        diff = date - that.date;
        return diff;
    }

    @Override
    public void render(Console console) {

        console.format("Current Student: %s%n%n", parentCourse.parentSemester.parentStudent.toDisplayName());
        console.format("%s %s:%n%n", parentCourse.parentSemester.toDisplayName(), parentCourse.toDisplayName());
        console.format("%s exam on %s %d%n%n", material, month, date);
        if (moreInfo != null) {
            console.format(moreInfo + "%n%n");
        }

    }

    @Override
    public String render() {
        return null;
    }

    @Override
    public void add(Console console) {

        String addInfo = console.readLine("Additional info for Exam > ");
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
                console.format("--- Exam Info Removed ---%n%n");
                break;
            case "n":
            case "no":
                console.format("Exam Unmodified%n%n");
                break;
            default:
                console.format("Input not recognized%nExam Unmodified%n%n");
                break;
        }
    }

    @Override
    public void remove(String arg1, String arg2, Console console) {

        if (arg1.compareToIgnoreCase("info") == 0) {
            this.moreInfo = null;
        }
        console.format("--- Exam Info Removed ---%n%n");

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
        return month + " " + date + " " + material;
    }

    public String getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }


}
