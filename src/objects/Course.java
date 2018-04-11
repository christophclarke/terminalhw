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

    private Semester parent;

    public Course(Console console, Semester caller) throws ImproperFormatException {

        console.format("%n--- Creating Course ---%n");
        department = console.readLine("Department > ");
        try {
            this.courseNumber = Integer.parseInt(console.readLine("Course Number > "));
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }

        this.examList = new ArrayList<>();
        this.assignmentList = new ArrayList<>();
        this.parent = caller;

        console.format("--- Course Created ---%n%n");

    }

    public Course(String dept, String courseNumStr, Console console, Semester caller) throws ImproperFormatException {

        try {
            this.courseNumber = Integer.parseInt(courseNumStr);
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }
        this.department = dept;
        this.parent = caller;
        this.examList = new ArrayList<>();
        this.assignmentList = new ArrayList<>();

        console.format("--- Course Created ---%n%n");
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public String getDepartment() {
        return department;
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
        return parent;
    }

    @Override
    public String toDisplayName() {

        StringWriter sw = new StringWriter();
        sw.append(department.toUpperCase());
        sw.append(" ");
        sw.append(Integer.toString(courseNumber));

        return sw.toString();
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     * <p>
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     * <p>
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     * <p>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p>
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param that the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Course that) {
        int diff;
        diff = department.compareToIgnoreCase(that.department);
        if (diff != 0) {return diff;}
        return courseNumber - that.courseNumber;
    }
}
