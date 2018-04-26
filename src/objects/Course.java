package objects;

import exceptions.ComponentDoesNotExistException;
import exceptions.ImproperFormatException;

import javax.swing.*;
import java.io.Console;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;

class Course implements Serializable, Component, Comparable<Course> {

    private String department;
    private int courseNumber;

    private ArrayList<Exam> examList;
    private ArrayList<Assignment> assignmentList;

    Semester parentSemester;

    Course(Console console, Semester caller) throws ImproperFormatException {

        console.format("%n--- Creating Course ---%n");
        department = console.readLine("Department > ").trim();
        try {
            this.courseNumber = Integer.parseInt(console.readLine("Course Number > ").trim());
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }

        this.examList = new ArrayList<>();
        this.assignmentList = new ArrayList<>();
        this.parentSemester = caller;

        console.format("--- Course Created ---%n%n");

    }

    Course(String dept, String courseNumStr, Console console, Semester caller) throws ImproperFormatException {

        try {
            this.courseNumber = Integer.parseInt(courseNumStr.trim());
        } catch (NumberFormatException nfe) {
            throw new ImproperFormatException();
        }
        this.department = dept.trim();
        this.parentSemester = caller;
        this.examList = new ArrayList<>();
        this.assignmentList = new ArrayList<>();

        if (console != null) {console.format("--- Course Created ---%n%n");}
    }

    int getCourseNumber() {
        return courseNumber;
    }

    String getDepartment() {
        return department;
    }

    //returns "Course{{dept} {courseNbr} | Exams: {examList.toString()}, Assignments: {assignmentList.toString()}}"
    @Override
    public String toString() {
        return "Course{" + this.department + " " + this.courseNumber + " | " +
                "Exams: " + examList.toString() + ", Assignments: " + assignmentList.toString() + "}";
    }

    @Override
    public void render(Console console) {

        console.format("Current Student: %s%n%n", parentSemester.parentStudent.toDisplayName());
        console.format("%s %s:%n%n", parentSemester.toDisplayName(), toDisplayName());
        if (assignmentList.isEmpty()) {
            console.format("\u001B[36m No Assignments Saved\u001B[0m%n%n");
        } else {

            console.format("Assignments:");
            for (Assignment assignment : assignmentList) {
                console.format("%n├%s", assignment.toDisplayName());
            }
            console.format("%n┘%n%n");

        }

        if (examList.isEmpty()) {
            console.format("\u001B[36m No Exams Saved\u001B[0m%n%n");
        } else {

            console.format("Exams:");
            for (Exam exam : examList) {
                console.format("%n├%s", exam.toDisplayName());
            }
            console.format("%n┘%n%n");

        }

    }

    @Override
    public String render() {

        StringBuilder sb = new StringBuilder();
        if (assignmentList.isEmpty()) {
            sb.append(String.format("No Assignments Saved%n%n"));
        } else {

            sb.append("Assignments:");
            for (Assignment assignment : assignmentList) {
                sb.append(String.format("%n├%s", assignment.toDisplayName()));
            }
            sb.append(String.format("%n┘%n%n"));
        }

        if (examList.isEmpty()) {
            sb.append(String.format("No Exams Saved%n%n"));
        } else {
            sb.append("Exams:");
            for (Exam exam : examList) {
                sb.append(String.format("%n├%s", exam.toDisplayName()));
            }
            sb.append(String.format("%n┘%n%n"));
        }

        return sb.toString();
    }

    @Override
    public void add(Console console) throws ImproperFormatException {

        String addType = console.readLine("What would you like to add? [Assignment/Exam] > ");

        add(addType, null, console);

    }

    @Override
    public void add(String arg1, String arg2, Console console) throws ImproperFormatException {

        if (console == null) {

            switch (arg1.toLowerCase().trim()) {

                case "exam":
                    String[] examArray = JOptionPane.showInputDialog("Exam Date + Material:").split(" ");
                    examList.add(new Exam(examArray[0], examArray[1], examArray[2], null, this));
                    Collections.sort(examList);
                    break;
                case "assignment":
                    String[] assignmentArray = JOptionPane.showInputDialog("Assignment Date + Type:").split(" ");
                    assignmentList.add(new Assignment(assignmentArray[0], assignmentArray[1], assignmentArray[2], null, this));
                    Collections.sort(assignmentList);
                    break;
                default:
                    throw new ImproperFormatException();
            }

        } else {
            switch (arg1.toLowerCase().trim()) {

                case "exam":
                    examList.add(new Exam(console, this));
                    Collections.sort(examList);
                    break;
                case "assignment":
                    assignmentList.add(new Assignment(console, this));
                    Collections.sort(assignmentList);
                    break;
                default:
                    throw new ImproperFormatException();
            }
        }

    }

    @Override
    public void remove(Console console) throws ComponentDoesNotExistException, ImproperFormatException {

        String rmType = console.readLine("What would you like to remove? [Assignment/Exam] > ");

        remove(rmType, null, console);

    }

    @Override
    public void remove(String arg1, String arg2, Console console) throws ComponentDoesNotExistException, ImproperFormatException {

        if (console == null) {

            int rmDate;
            String rmMonth;

            switch (arg1.toLowerCase().trim()) {

                case "exam":
                    String[] examArray = JOptionPane.showInputDialog("Exam to remove: [Month  Day]").split(" ");
                    rmMonth = examArray[0];
                    try {
                        rmDate = Integer.parseInt(examArray[1]);
                    } catch (NumberFormatException nfe) {
                        throw new ImproperFormatException();
                    }

                    for (int i = 0; i < examList.size(); i++) {

                        if (examList.get(i).getDate() == rmDate) {
                            if (examList.get(i).getMonth().equalsIgnoreCase(rmMonth)) {
                                examList.remove(i);
                                return;
                            }
                        }

                    }
                    throw new ComponentDoesNotExistException();

                case "assignment":
                    String[] assignmentArray = JOptionPane.showInputDialog("Assignment to remove: [Month  Day Type]").split(" ");
                    rmMonth = assignmentArray[0];
                    try {
                        rmDate = Integer.parseInt(assignmentArray[1]);
                    } catch (NumberFormatException nfe) {
                        throw new ImproperFormatException();
                    }
                    String rmType = assignmentArray[2];
                    for (int i = 0; i < assignmentList.size(); i++) {

                        if (assignmentList.get(i).getDate() == rmDate) {
                            if (assignmentList.get(i).getMonth().equalsIgnoreCase(rmMonth)) {
                                if (assignmentList.get(i).getAssignmentType().equalsIgnoreCase(rmType)) {
                                    assignmentList.remove(i);
                                    return;
                                }
                            }
                        }

                    }
                    throw new ComponentDoesNotExistException();

                default:
                    throw new ImproperFormatException();
            }

        } else {

            console.format("--- Removing %s ---%n", arg1);
            int rmDate;
            String rmMonth;

            switch (arg1.toLowerCase().trim()) {

                case "exam":
                    rmMonth = console.readLine("Exam Month > ").trim();
                    try {
                        rmDate = Integer.parseInt(console.readLine("Exam Date > ").trim());
                    } catch (NumberFormatException nfe) {
                        throw new ImproperFormatException();
                    }
                    for (int i = 0; i < examList.size(); i++) {

                        // If a given course department and number match the entered department and number, remove it
                        if (examList.get(i).getDate() == rmDate) {
                            if (examList.get(i).getMonth().equalsIgnoreCase(rmMonth)) {
                                examList.remove(i);
                                console.format("--- Exam Removed ---%n");
                                return;
                            }
                        }

                    }
                    throw new ComponentDoesNotExistException();

                case "assignment":
                    rmMonth = console.readLine("Assignment Month > ").trim();
                    try {
                        rmDate = Integer.parseInt(console.readLine("Assignment Date > ").trim());
                    } catch (NumberFormatException nfe) {
                        throw new ImproperFormatException();
                    }
                    String rmType = console.readLine("Assignment Type > ").trim();
                    for (int i = 0; i < assignmentList.size(); i++) {

                        if (assignmentList.get(i).getDate() == rmDate) {
                            if (assignmentList.get(i).getMonth().equalsIgnoreCase(rmMonth)) {
                                if (assignmentList.get(i).getAssignmentType().equalsIgnoreCase(rmType)) {
                                    assignmentList.remove(i);
                                    console.format("--- Assignment Removed ---%n");
                                    return;
                                }
                            }
                        }

                    }
                    throw new ComponentDoesNotExistException();

                default:
                    throw new ImproperFormatException();
            }
        }

    }

    @Override
    public Component open(Console console) throws ComponentDoesNotExistException, ImproperFormatException {

        String openType = console.readLine("What would you like to open? [Assignment/Exam] > ").trim();

        return open(openType, null, console);
    }

    @Override
    public Component open(String arg1, String arg2, Console console) throws ImproperFormatException, ComponentDoesNotExistException {

        console.format("--- Opening %s ---%n", arg1);
        int opDate;
        String opMonth;

        switch (arg1.toLowerCase().trim()) {

            case "exam":
                opMonth = console.readLine("Exam Month > ").trim();
                try {
                    opDate = Integer.parseInt(console.readLine("Exam Date > ").trim());
                } catch (NumberFormatException nfe) {
                    throw new ImproperFormatException();
                }
                for (Exam exam : examList) {
                    if (exam.getMonth().compareToIgnoreCase(opMonth) == 0) {
                        if (exam.getDate() == opDate)
                            return exam;
                    }
                }
                throw new ComponentDoesNotExistException();

            case "assignment":
                opMonth = console.readLine("Assignment Month > ").trim();
                try {
                    opDate = Integer.parseInt(console.readLine("Assignment Date > ").trim());
                } catch (NumberFormatException nfe) {
                    throw new ImproperFormatException();
                }
                String opType = console.readLine("Assignment Type > ").trim();
                for (Assignment assignment : assignmentList) {
                    if (assignment.getMonth().compareToIgnoreCase(opMonth) == 0) {
                        if (assignment.getDate() == opDate) {
                            if (assignment.getAssignmentType().compareToIgnoreCase(opType) == 0)
                                return assignment;
                        }
                    }
                }
                throw new ComponentDoesNotExistException();

            default:
                throw new ImproperFormatException();
        }

    }

    @Override
    public Component out() {
        return parentSemester;
    }

    @Override
    public String toDisplayName() {

        StringWriter sw = new StringWriter();
        sw.append(department.toUpperCase());
        sw.append(" ");
        sw.append(Integer.toString(courseNumber));

        return sw.toString();
    }

    @Override
    public int compareTo(Course that) {
        int diff;
        diff = department.compareToIgnoreCase(that.department);
        if (diff != 0) {return diff;}
        return courseNumber - that.courseNumber;
    }
}
