package oo;
import java.awt.Dimension;
import javax.swing.*;
import oo.adminpanel.addstudent;
import oo.adminpanel.courses;
import oo.adminpanel.AddSchedule;
import oo.adminpanel.Report;

public class Adminclass extends JFrame {

    JTabbedPane tabbedpane = new JTabbedPane();
    addstudent addstudent = new addstudent();
    courses lec = new courses();
    Report report = new Report();
    AddSchedule schedule = new AddSchedule();

    public Adminclass() {
        setLayout(null);
        setTitle("Admin");
        tabbedpane.add("New Student", addstudent);
        tabbedpane.add("New course", lec);
        tabbedpane.add("New Schedule", schedule);
        tabbedpane.add("Report", report);
        add(tabbedpane);
        tabbedpane.setSize(1280, 720);
        setSize(1280, 755);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
