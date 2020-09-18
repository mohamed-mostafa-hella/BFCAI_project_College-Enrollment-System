package data;
public class ScheduleData {
String course;
String time;
String date;

    public ScheduleData(String course, String time, String date) {
        this.course = course;
        this.time = time;
        this.date = date;
    }

    public ScheduleData() {
    }
    

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }





}
