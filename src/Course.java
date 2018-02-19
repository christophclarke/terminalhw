import java.util.ArrayList;

class Course {

    private String department;
    private int courseNumber;
    ArrayList<Test> testList = new ArrayList<>();
    ArrayList<Assignment> assignmentList = new ArrayList<>();

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

    public ArrayList<Test> getTestList() {
        return testList;
    }

    public void setTestList(ArrayList<Test> testList) {
        this.testList = testList;
    }

    public ArrayList<Assignment> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(ArrayList<Assignment> assignmentList) {
        this.assignmentList = assignmentList;
    }

}
