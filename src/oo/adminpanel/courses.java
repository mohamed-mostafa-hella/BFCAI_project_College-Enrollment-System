/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oo.adminpanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.ABORT;
import java.sql.*;
import javax.swing.*;

public class courses extends JPanel{
    JTextField name=new JTextField();
    JTextField lecturer=new JTextField();
    JTextField lecnum=new JTextField();
    JTextField mark =new JTextField();
    JTextField duration=new JTextField();
    JLabel img =new JLabel();
    
    String yeararr[]={"1th","2th","3th","4th"};
    String termarr[]={"1th","2th"};
    JComboBox<String> year=new JComboBox<>(yeararr);
    JComboBox<String> tearm=new JComboBox<>(termarr);
    
   
    JTextField txsearch=new JTextField();
    JButton butsearch=new JButton();
    
    JButton save=new JButton();
    JButton remove=new JButton();
    Connection c;
    public courses() {
        
         try {
            Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/oo","root","root");
            System.out.println("conecttion succes courses");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("conecttion fall 1");
        } catch (SQLException ex) {
            System.out.println("conecttion fail 2");
        }
        
        img.setIcon(new ImageIcon("Lecture.png"));
        add(img);
        img.add(name);
        img.add(lecturer);
        img.add(lecnum);
        img.add(duration);
        img.add(mark);
        img.add(tearm);
        img.add(year);
        
       
        img.add(txsearch);
        img.add(butsearch);
        
        img.add(save);
        img.add(remove);
        
        setSize(1280,720);
        setLayout(null);
        
        img.setBounds(0, 0, 1280, 720);
        
        name.setBounds(253,89, 400, 38);
        lecturer.setBounds(253,172, 400, 38);
        lecnum.setBounds(252,254, 400, 38);
        duration.setBounds(249,334, 405, 38);
        mark.setBounds(248,418, 406, 38);
        tearm.setBounds(248,500, 404, 38);
        year.setBounds(246,584 , 405 , 38);
              
        txsearch.setBounds( 868 , 87 , 360, 39);
        butsearch.setBounds(1030, 170, 60, 35);
        
        
        save.setBounds(1133, 585 , 70 , 67);
        remove.setBounds(920, 594 , 70 , 67);
        
        Font f=new Font("Comic Sans MS", ABORT, 18);
        name.setFont(f);
        lecturer.setFont(f);
        lecnum.setFont(f);
        duration.setFont(f);
        mark.setFont(f);
        tearm.setFont(f);
        year.setFont(f);
        txsearch.setFont(f);
        
       
        name.setBorder(null);
        lecturer.setBorder(null);
        lecnum.setBorder(null);
        duration.setBorder(null);
        mark.setBorder(null);
        tearm.setBorder(null);
        year.setBorder(null);
        save.setContentAreaFilled(false);
        save.setBorder(null);
        remove.setContentAreaFilled(false);
        remove.setBorder(null);
        butsearch.setContentAreaFilled(false);
        butsearch.setBorder(null);
        txsearch.setBorder(null);
        txsearch.setBackground(new Color(251,243,134));
        
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(!iswrite()){
                      JOptionPane.showMessageDialog(null, 
                              "please complete fildes", 
                              "error", 
                              JOptionPane.ERROR_MESSAGE);
                     return;
                    } 
                    if(iffounded(name.getText())){
                        int replay=JOptionPane.showConfirmDialog(null, "this course is exist are you need to update this","worning",JOptionPane.YES_NO_OPTION);
                        if(replay==JOptionPane.YES_OPTION){
                           delet();
                           addcousre();
                        }
                    }
                    else addcousre();
                    clear();
                 }
            });
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int replay=JOptionPane.showConfirmDialog(null,
                       "are you sure you need to delete this course",
                       "warning",
                       JOptionPane.YES_NO_OPTION);
               if(replay==JOptionPane.YES_OPTION){
                    delet();
                    JOptionPane.showMessageDialog(null,
                            "delete has been done",
                            "info",
                            JOptionPane.INFORMATION_MESSAGE);
               }
               clear();
            }
        });
        butsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(iffounded(txsearch.getText())){
                        getdata();
                }else   JOptionPane.showMessageDialog(null, 
                              "we can not found this Course", 
                              "error", 
                              JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    private boolean iswrite(){
        return !(name.getText().isEmpty() || lecturer.getText().isEmpty() ||lecnum.getText().isEmpty() || duration.getText().isEmpty() 
                || mark.getText().isEmpty() || mark.getText().isEmpty() || year.getSelectedItem().toString().isEmpty());
    }
    
    private void delet (){
        try {
            Statement statm=c.createStatement();
            String delet="delete from course where name='"+name.getText()+"';";
            statm.executeUpdate(delet);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                              "data is founded and can not delet", 
                              "error", 
                              JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean iffounded(String comp){
        int number=0;
        try {
            Statement statm=c.createStatement();
            String strse="SELECT count(1) FROM oo.course where name='"+comp+"';";
            ResultSet set=statm.executeQuery(strse);
            while(set.next()){
                number=set.getInt(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                              "sorry we can not cheack if it is founded befor", 
                              "error", 
                              JOptionPane.ERROR_MESSAGE);
        }
        return number != 0;
    }
    
    private void addcousre (){
        try {
            Statement statm=c.createStatement();
            String str="insert into course values('"+name.getText()+"','"+lecturer.getText()+"','"+lecnum.getText()+"','"+duration.getText()+"','"+mark.getText()+"','"+tearm.getSelectedItem().toString()+"','"+year.getSelectedItem().toString()+"');";
            statm.executeUpdate(str);
            JOptionPane.showMessageDialog(null, 
                              "insert succes", 
                              "error", 
                              JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                              "sorry we can not add this student", 
                              "error", 
                              JOptionPane.ERROR_MESSAGE);
        }
    
    }
    
    private void getdata(){
        try {
            Statement statm=c.createStatement();
            String strse="SELECT * FROM oo.course where name='"+txsearch.getText()+"';";
            ResultSet set=statm.executeQuery(strse);
            while(set.next()){
                String ss=set.getString(7);
                String sst=set.getString(6);
                name.setText(set.getString(1));
                lecturer.setText(set.getString(2));
                lecnum.setText(set.getString(3));
                duration.setText(set.getString(4));
                mark.setText(set.getString(5));
                
                if(sst.equals("1th"))
                    tearm.setSelectedIndex(0);
                else 
                    tearm.setSelectedIndex(1);
                
                if(ss.equals("1th"))
                    year.setSelectedIndex(0);
                else if(ss.equals("2th"))
                    year.setSelectedIndex(1);
                else if(ss.equals("3th"))
                    year.setSelectedIndex(2);
                else 
                    year.setSelectedIndex(3);
           }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                              "sorry we can not search now you can try latter", 
                              "error", 
                              JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clear (){
        name.setText("");
        lecturer.setText("");
        lecnum.setText("");
        duration.setText("");
        mark.setText("");
        tearm.setSelectedIndex(0);
        year.setSelectedIndex(0);
        txsearch.setText("");
    }
    
}
