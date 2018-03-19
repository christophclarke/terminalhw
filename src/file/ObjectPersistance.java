package file;

import objects.Student;

import java.io.*;

public class ObjectPersistance {

    static void saveStudent(Student student, File rootDir) {

        //Objectify .student file of given student, whether it exists or not.
        File studentFile = new File(rootDir, student.toFileName());

        //Delete file of given .student
        studentFile.delete();

        //New File and Object output writers for student serialization
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(studentFile));
            //Serialize
            oos.writeObject(student);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    static Student loadStudent(String studentName, File rootDir) {

        //Load correct .student file
        File studentFile = new File(rootDir, studentName + ".student");

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(studentFile));
            Student returnStudent = (Student)ois.readObject();
            return returnStudent;
        } catch (Exception ex) {
            System.err.println("------");
            System.err.println(".student file not found");
            System.err.println(ex.getStackTrace());
            return null;
        }
    }
}
