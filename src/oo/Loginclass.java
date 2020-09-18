/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oo;

import data.StdData;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.ABORT;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author mohamed
 */
public class Loginclass extends JFrame{

    //JFrame Login=new JFrame("Log In");
    
    JTextField User =new JTextField();
    JTextField Bassword =new JTextField();
    JButton loginbutton =new JButton();
    Font f=new Font("Comic Sans MS", ABORT, 18);

    
    JLabel img=new JLabel();
    Connection c;
    public Loginclass() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/oo","root","root");
            System.out.println("conecttion succes stu");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("conecttion fall 1");
        } catch (SQLException ex) {
            System.out.println("conecttion fail 2");
        }
        
        
        
        setTitle("Log IN");
        setLayout(null);
        setSize(1280,720);
        setResizable(false);
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        img.setIcon(new ImageIcon("Log in.png"));
        add(img);
        img.add(User);
        img.add(Bassword);
        img.add(loginbutton);
        User.setBounds(736, 201,449,50);
        Bassword.setBounds(736, 361 , 449 , 50);
        loginbutton.setBounds(910,560,170,63);
        img.setBounds(0, 0, 1280, 720);
        
        User.setFont(f);
        Bassword.setFont(f);
        
        
        
        User.setBorder(null);
        Bassword.setBorder(null);
        loginbutton.setContentAreaFilled(false);
        loginbutton.setBorder(null);
        
        setVisible(true);
        
        loginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             String u="",p="";
             u=User.getText();
             p=Bassword.getText();
             if(u.equals("1") && p.equals("1")){
                 Adminclass adm=new Adminclass();
             }else if( iffounded()){
                DateFormat dateFormat = new SimpleDateFormat("MM");
                Calendar cal = Calendar.getInstance();
                String time=(dateFormat.format(cal.getTime()));
                 try {
                     StdData studata=new StdData();
                     Statement statm=c.createStatement();
                     String s="SELECT * FROM oo.student where pass='"+Bassword.getText()+"' and user='"+User.getText()+"';";
                     ResultSet set=statm.executeQuery(s);
                     while(set.next()){
                         studata=new StdData(set.getString(1),set.getString(2),set.getString(3),set.getString(4)
                                 ,set.getString(5),set.getString(6),set.getString(7),set.getString(8),set.getString(9)
                                 ,set.getString(10));
                     }
                     Studentclass stu=new Studentclass(studata,time);
                 } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null, 
                              "sorry we can not lood your data", 
                              "error", 
                              JOptionPane.WARNING_MESSAGE);
                 }
             }
             else {
                 JOptionPane.showMessageDialog(null, 
                              "sorry your user or bassword is rong you can try agen", 
                              "error", 
                              JOptionPane.WARNING_MESSAGE);

                 return;
             }
             dispose();
            }
        });
       
    }
    
    boolean iffounded(){
        int number=0;
        try {
            Statement statm=c.createStatement();
            String strse="SELECT count(1) FROM oo.student where user='"+User.getText()+"' and pass='"+Bassword.getText()+"';";
            ResultSet set=statm.executeQuery(strse);
            while(set.next()){
                number=set.getInt(1);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, 
                              "sorry we can not cheack if you are student", 
                              "error", 
                              JOptionPane.WARNING_MESSAGE);
        }
        return number != 0;
    }
   
    
}
