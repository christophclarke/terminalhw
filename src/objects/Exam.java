package objects;

import exceptions.ComponentDoesNotExistException;
import exceptions.ImproperFormatException;

import java.io.Console;
import java.io.Serializable;

public class Exam implements Serializable, Component, Comparable<Exam> {

    private Course course;
    private String date;
    private String material;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Exam o) {
        return 0;
    }

    @Override
    public void render(Console console) {

    }

    @Override
    public void add(Console console) throws ImproperFormatException {

    }

    @Override
    public void add(String arg1, String arg2, Console console) throws ImproperFormatException {

    }

    @Override
    public void remove(Console console) throws ComponentDoesNotExistException, ImproperFormatException {

    }

    @Override
    public void remove(String arg1, String arg2, Console console) throws ComponentDoesNotExistException, ImproperFormatException {

    }

    @Override
    public Component open(Console console) throws ComponentDoesNotExistException, ImproperFormatException {
        return null;
    }

    @Override
    public Component open(String arg1, String arg2) throws ComponentDoesNotExistException, ImproperFormatException {
        return null;
    }

    @Override
    public Component out() {
        return null;
    }

    @Override
    public String toDisplayName() {
        return null;
    }
}
