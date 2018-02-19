import java.util.ArrayList;

public class Semester {

    private int term;

    private int year;

    private ArrayList<Course> classList = new ArrayList<>();


    //term 0 indicates spring, 1 indicates fall
    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    //year as int, need input check
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    //ArrayList of course objects
    public ArrayList<Course> getClassList() {
        return classList;
    }

    public void setClassList(ArrayList<Course> classList) {
        this.classList = classList;
    }


}