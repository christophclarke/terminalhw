import java.util.ArrayList;

class Course {

    private String department;
    private int courseNumber;
    private ArrayList<Exam> examList = new ArrayList<>();
    private ArrayList<Assignment> assignmentList = new ArrayList<>();

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public ArrayList<Exam> getExamList() {
        return examList;
    }

    public void setTestList(ArrayList<Exam> testList) {
        this.examList = testList;
    }

    public ArrayList<Assignment> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(ArrayList<Assignment> assignmentList) {
        this.assignmentList = assignmentList;
    }

    public Course(String dept, int courseNbr) {
        this.department = dept;
        this.courseNumber = courseNbr;
    }

    public void addExam(Exam addedExam) {
        examList.add(addedExam);
    }

    //returns true if exam exists and is removed, else false
    public boolean removeExam(Exam removedExam) {
        return examList.remove(removedExam);
    }

    public void addAssignment(Assignment addedAssignment) {
        assignmentList.add(addedAssignment);
    }

    public boolean removeAssignment(Assignment removedAssignment) {
        return assignmentList.remove(removedAssignment);
    }
}
