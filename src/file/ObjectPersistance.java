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

    public static Student loadStudent(String studentName, File rootDir) throws IOException {

        //Load correct .student file
        File studentFile = new File(rootDir, studentName + ".student");

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(studentFile));
            return (Student)ois.readObject();
        } catch (Exception ex) {
            throw new IOException();
        }
    }

}
