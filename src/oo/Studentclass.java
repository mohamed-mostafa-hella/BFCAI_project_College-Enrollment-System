package oo;

import data.StdData;
import javax.swing.*;
import oo.studentpanel.informatin;
import oo.studentpanel.studentcourses;
import oo.studentpanel.studentscadual;

public class Studentclass extends JFrame {
       JTabbedPane tabbedpane = new JTabbedPane();
    

    public Studentclass(StdData studata , String time) {
       informatin info =new informatin(studata);
        studentcourses mycourses = new studentcourses(studata.getYear(),time);
        studentscadual scadual = new studentscadual(studata.getYear(),time);
        setLayout(null);
        setTitle("Student");
        tabbedpane.add("My Info", info);
        tabbedpane.add("My Courses", mycourses);
        tabbedpane.add("My Schedule", scadual);
        add(tabbedpane);
        tabbedpane.setSize(1280, 720);
        setSize(1280, 755);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
