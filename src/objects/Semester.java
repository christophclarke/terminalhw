package objects;

import java.io.Console;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;

public class Semester implements Serializable, Component, Comparable<Semester> {

    private int term;

    private int year;

    private ArrayList<Course> courseList;

    //New semester constructor
    Semester(Console console) throws ImproperFormatException {

        console.format("%n--- Creating Semester ---%n");
        this.term = StringToTermInt(console.readLine("Term (Spring, Fall, etc.) > "));
        try {
            this.year = Integer.parseInt(console.readLine("Year > "));
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }
        this.courseList = new ArrayList<>();
        console.format("--- Semester Added ---%n%n");
    }

    Semester(String term, String year, Console console) throws ImproperFormatException {

        int yearInt;

        try {
            yearInt = Integer.parseInt(year);
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }

        this.term = StringToTermInt(term);
        this.year = yearInt;
        this.courseList = new ArrayList<>();
        console.format("--- Semester Added ---%n%n");

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

    public int getYear() {
        return year;
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
    public Component open(String arg1, String arg2) {
        return null;
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