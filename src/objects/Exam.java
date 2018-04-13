package objects;

import exceptions.ComponentDoesNotExistException;
import exceptions.ImproperFormatException;

import java.io.Console;
import java.io.Serializable;

public class Exam implements Serializable, Component, Comparable<Exam> {

    private Course parentCourse;
    private String date;
    private String material;

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
