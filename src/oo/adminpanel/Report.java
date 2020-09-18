package oo.adminpanel;

import data.StdData;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.ABORT;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Report extends JPanel{
    
    JLabel img =new JLabel();
    String yeararr[]={"all","1th","2th","3th","4th"};
    String termarr[]={"all","1th","2th"};
    String scarr[]={"student","coursec","schadule"};
    JLabel numberofallstudent=new JLabel("num");
    JComboBox<String> year=new JComboBox<>(yeararr);
    JComboBox<String> term=new JComboBox<>(termarr);
    JComboBox<String> sc=new JComboBox<>(scarr);
    JTable stutable ;
    JTable coutable ;
    JTable schtable ;
    JLabel numofstudent=new JLabel("num of student");
    
    Connection c;
    JScrollPane stuscrollPane ;
    JScrollPane couscrollPane ;
    JScrollPane schscrollPane ;
    public Report() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/oo","root","root");
            System.out.println("conecttion succes stu");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("conecttion fall 1");
        } catch (SQLException ex) {
            System.out.println("conecttion fail 2");
        }
        
        
        //////////////////student table///////////////
        String []stucolom={"name","ssn","age","phone","gender","mail","user","year"};
        Object [][]studata={};
        stutable=new JTable(new DefaultTableModel(studata,stucolom));
        stutable.setPreferredScrollableViewportSize(new Dimension(508, 512));
        stutable.setFillsViewportHeight(true);
        stuscrollPane = new JScrollPane(stutable);
        //////////////////////////////////////////////
        //////////////////courses table///////////////
        String []coucolom={"name","lecturer","lec-num","duration","mark","year","term"};
        Object [][]coudata={};
        coutable=new JTable(new DefaultTableModel(coudata,coucolom));
        coutable.setPreferredScrollableViewportSize(new Dimension(508, 512));
        coutable.setFillsViewportHeight(true);
        couscrollPane = new JScrollPane(coutable);
        //////////////////////////////////////////////
        //////////////////schadule table///////////////
        String []schcolom={"name","date","time","year","term",};
        Object [][]schdata={};
        schtable=new JTable(new DefaultTableModel(schdata,schcolom));
        schtable.setPreferredScrollableViewportSize(new Dimension(508, 512));
        schtable.setFillsViewportHeight(true);
        schscrollPane = new JScrollPane(schtable);
        ////////////////////////////////////////////////////
        
        img.setIcon(new ImageIcon("Report.png"));
        img.setBounds(0, 0, 1280, 720);
        add(img);
        img.add(couscrollPane);
        img.add(stuscrollPane);
        img.add(schscrollPane);
        img.add(year);
        img.add(term);
        img.add(sc);
        img.add(numofstudent);
        
        couscrollPane.setBounds(553, 36, 700, 618);
        stuscrollPane.setBounds(553, 36, 700, 618);
        schscrollPane.setBounds(553, 36, 700, 618);
        
        year.setBounds(136, 54, 361, 38);
        term.setBounds(136, 114, 326, 38);
        sc.setBounds(136 , 174, 275, 38);
        numofstudent.setBounds(150, 615 , 150 , 35);
        
        
        numofstudent.setFont(new Font("Comic Sans MS", ABORT, 28));
        numofstudent.setForeground(Color.ORANGE);
        
        loodtable(sc.getSelectedItem().toString());
        loodnumofstu(year.getSelectedItem().toString());
        
        year.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loodtable(sc.getSelectedItem().toString());
                //loodnumofstu(year.getSelectedItem().toString());
                DefaultTableModel model=(DefaultTableModel)stutable.getModel();
                numofstudent.setText(""+model.getRowCount());
                
            }
        });
        term.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loodtable(sc.getSelectedItem().toString());
                DefaultTableModel model=(DefaultTableModel)stutable.getModel();
                numofstudent.setText(""+model.getRowCount());
            }
        });
        
        sc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loodtable(sc.getSelectedItem().toString());
                String s=sc.getSelectedItem().toString();
                if(s.equalsIgnoreCase("student")){
                DefaultTableModel model=(DefaultTableModel)stutable.getModel();
                numofstudent.setText(""+model.getRowCount());
                }else if(s.equalsIgnoreCase("coursec")){
                DefaultTableModel model=(DefaultTableModel)coutable.getModel();
                numofstudent.setText(""+model.getRowCount());
                }else if(s.equalsIgnoreCase("schadule")){
                DefaultTableModel model=(DefaultTableModel)schtable.getModel();
                numofstudent.setText(""+model.getRowCount());
                }
               
            }
        });
        
       
    }
    
    private void loodnumofstu(String s1){
        String ret="error";
        try {
            Statement statm=c.createStatement();
            String s="";
            if(s1.equals("all")){
                s="SELECT count(1) FROM oo.student ;";
            }
            else {
                s="SELECT count(1) FROM oo.student where year='"+s1+"' ;";
            }
            ResultSet set=statm.executeQuery(s);
            while(set.next()){
                ret=set.getString(1);
            }            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                              "sorry we can not calculate number of stu", 
                              "error", 
                              JOptionPane.ERROR_MESSAGE);
        }
        numofstudent.setText(ret);
    }
    
    private void lodstudata(String y){
        DefaultTableModel model=(DefaultTableModel)stutable.getModel();
        for(int i= model.getRowCount()-1;i>=0;i--){
            model.removeRow(i);
        }
        String s="";
        
        if(y.equals("all") ){
            s="SELECT * FROM oo.student ;";
        }
        else {
            s="SELECT * FROM oo.student where year='"+y+"';";
        }
        try {
            StdData studata=new StdData();
            Statement statm=c.createStatement();
            ResultSet set=statm.executeQuery(s);
            while(set.next()){
                String arr[]={set.getString(1),set.getString(2),set.getString(3),set.getString(4)
                        ,set.getString(5),set.getString(8),set.getString(9),set.getString(7)};
                
                model.addRow(arr);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                     "sorry we can not lood student data", 
                     "error", 
                     JOptionPane.WARNING_MESSAGE);
        }
        couscrollPane.setVisible(false);
        schscrollPane.setVisible(false);
        stuscrollPane.setVisible(true);
    }
    
     private void lodcoudata(String y,String t){
        DefaultTableModel model=(DefaultTableModel)coutable.getModel();
        for(int i= model.getRowCount()-1;i>=0;i--){
            model.removeRow(i);
        }
        String s="";
        
        if(y.equals("all") && t.equals("all")){
            s="SELECT * FROM oo.course ;";
        }
        else if(y.equals("all")){
            s="SELECT * FROM oo.course where term='"+t+"';";
        }
        else if( t.equals("all") ){
            s="SELECT * FROM oo.course where year='"+y+"';";
        }
        else {
            s="SELECT * FROM oo.course where year='"+y+"' and term='"+t+"';";
        }
        try {
            Statement statm=c.createStatement();
            ResultSet set=statm.executeQuery(s);
            while(set.next()){
                String arr[]={set.getString(1),set.getString(2),set.getString(3),set.getString(4)
                        ,set.getString(5),set.getString(7),set.getString(6)};
                
                model.addRow(arr);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                     "sorry we can not lood course data", 
                     "error", 
                     JOptionPane.WARNING_MESSAGE);
        }
        couscrollPane.setVisible(true);
        schscrollPane.setVisible(false);
        stuscrollPane.setVisible(false);
    }
     
    private void lodschdata(String y,String t){
        DefaultTableModel model=(DefaultTableModel)schtable.getModel();
        for(int i= model.getRowCount()-1;i>=0;i--){
            model.removeRow(i);
        }
        String s="";
        
        if(y.equals("all") && t.equals("all")){
            s="SELECT * FROM oo.schedule ;";
        }
        else if(y.equals("all")){
            s="SELECT * FROM oo.schedule where term='"+t+"';";
        }
        else if( t.equals("all") ){
            s="SELECT * FROM oo.schedule where year='"+y+"';";
        }
        else {
            s="SELECT * FROM oo.schedule where year='"+y+"' and term='"+t+"';";
        }
        try {
            Statement statm=c.createStatement();
            ResultSet set=statm.executeQuery(s);
            while(set.next()){
                String arr[]={set.getString(1),set.getString(2),set.getString(3),set.getString(5)
                        ,set.getString(4)};
                
                model.addRow(arr);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                     "sorry we can not lood course data", 
                     "error", 
                     JOptionPane.WARNING_MESSAGE);
        }
        couscrollPane.setVisible(false);
        schscrollPane.setVisible(true);
        stuscrollPane.setVisible(false);
    }
    
    private void loodtable(String var){
        String y=year.getSelectedItem().toString(),t=term.getSelectedItem().toString();
        switch (var) {
            case "coursec":
                lodcoudata(y, t);
                break;
            case "student":
                lodstudata(y);    
                break;
            default:
                lodschdata(y, t);
                break;
        }
    }
}
