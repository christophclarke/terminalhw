package objects;

import java.io.Console;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;

public class Semester implements Serializable, Renderable {

    private int term;

    private int year;

    private ArrayList<Course> courseList;

    //New semester constructor, must pass year and term
    public Semester(int termIn, int yearIn) {
        this.term = termIn;
        this.year = yearIn;
        this.courseList = new ArrayList<>();
    }

    //term 0 indicates spring, 1 indicates fall
    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    //year as int, need input check
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    //ArrayList of course objects
    public ArrayList<Course> getClassList() {
        return courseList;
    }

    public void setClassList(ArrayList<Course> classList) {
        this.courseList = classList;
    }

    //Method to add course to semester's course list
    public void addCourse(Course addedCourse) {
        courseList.add(addedCourse);
    }

    //returns true if element exists and was removed, else false
    public boolean removeCourse(Course removedCourse) {
        return courseList.remove(removedCourse);
    }

    //returns "Semester{Year: {year}, Term: {term}, {classList.toString()}}"

    String toDisplayName() {

        StringWriter sw = new StringWriter();
        switch (term) {
            case 0: sw.append("Spring"); break;
            case 1: sw.append("Fall"); break;
        }
        sw.append(Integer.toString(year));
        return sw.toString();

    }

    @Override
    public void render(Console c) {

    }
}