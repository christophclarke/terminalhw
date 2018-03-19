package view;

import objects.Semester;
import objects.Student;

import java.io.Console;

public class Render {

    void printSemesters(Console c, Student student) {

        String leftAlign = "| %-4d | %-6s | %-7d |%n";

        c.format("+------+--------+---------+%n");
        c.format("| Year | Season | Classes |%n");
        c.format("+------+--------+---------+%n");

        for (Semester sem : student.getSemesterList()) {

            String semesterString = "debug";

            switch (sem.getTerm()){
                case 0: semesterString = "Spring";
                case 1: semesterString = "Fall";
            }

            c.format(leftAlign, sem.getYear(), semesterString, sem.getClassList().size());

        }

        c.format("+------+--------+---------+%n");

    }



}
