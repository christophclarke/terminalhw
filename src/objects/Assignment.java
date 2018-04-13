package objects;

import exceptions.ComponentDoesNotExistException;
import exceptions.ImproperFormatException;

import java.io.Console;
import java.io.Serializable;

public class Assignment implements Serializable, Component, Comparable<Assignment> {

    private Course parentCourse;
    private int dueDate;
    private String assignmentType;

    @Override
    public int compareTo(Assignment o) {
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
