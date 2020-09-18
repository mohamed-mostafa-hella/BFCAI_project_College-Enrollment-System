package data;
public class CourseData {
String name; 
String lecturer;
String num;
String duration;
String marks;
String term;
String year;
int stdNum;

    public CourseData(String name, String lecturer, String num, String duration, String marks, String term, String year, int stdNum) {
        this.name = name;
        this.lecturer = lecturer;
        this.num = num;
        this.duration = duration;
        this.marks = marks;
        this.term = term;
        this.year = year;
        this.stdNum = stdNum;
    }

    public CourseData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getStdNum() {
        return stdNum;
    }

    public void setStdNum(int stdNum) {
        this.stdNum = stdNum;
    }

    public void increment()
    {
    this.stdNum++;
    }
    public void Decrement()
    {
    this.stdNum--;
    }
    
}
