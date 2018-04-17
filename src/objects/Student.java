package objects;

import exceptions.ComponentDoesNotExistException;
import exceptions.ImproperFormatException;

import java.io.Console;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Student implements Serializable, Component {

    private String firstName;
    private String lastName;
    private ArrayList<Semester> semesterList = new ArrayList<>();

    public Student(Console console) {

        console.format("%n--- Creating Student ---%n");
        this.firstName = console.readLine("First Name > ");
        this.lastName = console.readLine("Last Name > ");
        console.format("--- Student Initialized ---%n%n");

    }

    public String toFileName() {

        return firstName + "_" + lastName + ".student";

    }

    @Override
    public void render(Console console) {

        console.format("Current Student: %s%n%n", toDisplayName());
        console.format("Semesters Saved:");

        if (semesterList.isEmpty()) {
            console.format("\u001B[36m No Semesters Saved\u001B[0m%n%n");
        } else {
            for (Semester sem : semesterList) {
                console.format("%n├%s", sem.toDisplayName());
            }
            console.format("%n┘%n%n");

        }

    }

    @Override
    public void add(Console console) throws ImproperFormatException {

        semesterList.add(new Semester(console, this));
        Collections.sort(semesterList);

    }

    @Override
    public void add(String arg1, String arg2, Console console) throws ImproperFormatException {

        semesterList.add(new Semester(arg1, arg2, console, this));
        Collections.sort(semesterList);

    }

    @Override
    public void remove(Console console) throws ComponentDoesNotExistException, ImproperFormatException {

        console.format("--- Removing Semester ---%n");
        String rmTerm = console.readLine("Semester Term > ").trim();
        String rmYear = console.readLine("Semester Year > ").trim();

        remove(rmTerm, rmYear, console);

    }

    @Override
    public void remove(String rmTerm, String rmYearStringIn, Console console) throws ComponentDoesNotExistException, ImproperFormatException {

        int rmYear;
        try {
            rmYear = Integer.parseInt(rmYearStringIn);
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }

        for (int i = 0; i < semesterList.size(); i++) {

            // If a given semester term and year match the entered term and year, remove it
            if (semesterList.get(i).getYear() == rmYear) {
                if(semesterList.get(i).getTermString().equalsIgnoreCase(rmTerm)) {
                    semesterList.remove(i);
                    console.format("--- Semester Removed ---%n");
                    return;
                }
            }

        }
        throw new ComponentDoesNotExistException();

    }

    @Override
    public Component open(Console console) throws ComponentDoesNotExistException, ImproperFormatException {

        console.format("--- Entering Semester ---");
        String termString = console.readLine("Semester Term > ").trim();
        String yearString = console.readLine("Semester Year > ").trim();

        return open(termString, yearString, console);

    }

    @Override
    public Component open(String arg1, String arg2, Console console) throws ComponentDoesNotExistException, ImproperFormatException {

        int year;

        try {
             year = Integer.parseInt(arg2);
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }

        for (Semester sem : semesterList) {

            if (sem.getYear() == year) {
                if (sem.getTermString().equalsIgnoreCase(arg1))
                    return sem;
            }

        }
        throw new ComponentDoesNotExistException();

    }

    @Override
    public Component out() {
        return null;
    }

    @Override
    public String toDisplayName() {
        return firstName + " " + lastName;
    }
}
