package oo.adminpanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class AddSchedule extends JPanel {

    JLabel img = new JLabel();
    
    JTextField Name = new JTextField();
    JTextField Time = new JTextField();
    JTextField Date = new JTextField();
    JTable Schedule ;
    JButton Add = new JButton();
    JButton Remove = new JButton();
    JButton UpDate = new JButton();
    JButton Save = new JButton();
    Font f = new Font("Comic Sans MS", ABORT, 18);
    Connection c;
    public AddSchedule() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/oo","root","root");
            System.out.println("conecttion succes sca");
        } catch (ClassNotFoundException ex) {
            System.out.println("conecttion fall 1");
        } catch (SQLException ex) {
            System.out.println("conecttion fail 2");
        }
        ///////////////////
        String []row={"name","date","time","year","term"};
        Object [][]data={};
        Schedule=new JTable(new DefaultTableModel(data,row));
        Schedule.setPreferredScrollableViewportSize(new Dimension(610,610));
        Schedule.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(Schedule);
        //////////////////////////
        img.setIcon(new ImageIcon("Add Schedule.png"));
        add(img);
        img.add(Name);
        img.add(Time);
        img.add(Date);
        img.add(scrollPane);
        img.add(Add);
        img.add(Remove);
        img.add(UpDate);
        img.add(Save);

        setSize(1280, 720);
        setLayout(null);

        img.setBounds(0, 0, 1280, 720);
        Name.setBounds(148, 65, 375, 38);
        Date.setBounds(148, 125, 335, 38);
        Time.setBounds(148, 185, 285, 38);
        UpDate.setBounds(63, 464, 150, 55);
        Remove.setBounds(82, 435, 150, 55);
        Add.setBounds(82, 355, 150, 55);
        Save.setBounds(112,602,72,72);
        scrollPane.setBounds(615,49,610,610);
        
    // Schedule.setBackground(Color.yellow);

        img.setBorder(null);
        Name.setBorder(null);
        Date.setBorder(null);
        Time.setBorder(null);
        Add.setBorder(null);
        UpDate.setBorder(null);
        Remove.setBorder(null);
        Save.setBorder(null);

        Name.setFont(f);
        Date.setFont(f);
        Time.setFont(f);

        Add.setContentAreaFilled(false);
        UpDate.setContentAreaFilled(false);
        Remove.setContentAreaFilled(false);
        Save.setContentAreaFilled(false);
        
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean found=false;
                String s1="",s2="";
                if(isful()){
                    try {
                        Statement statm=c.createStatement();
                        String find="SELECT year,term FROM oo.course where name='"+Name.getText()+"';";
                        ResultSet set=statm.executeQuery(find);
                        while(set.next()){
                            found=true;
                            s1=set.getString(1);
                            s2=set.getString(2);
                        }
                        if(found)
                            addnewrow(Name.getText() , Date.getText(), Time.getText() , s1 , s2 );
                        else 
                            JOptionPane.showMessageDialog(null, 
                              "we have not this course", 
                              "error", 
                              JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, 
                              "we can not cheack now if course is existe", 
                              "error", 
                              JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        Remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletselcted();
            }
        });
        
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model=(DefaultTableModel) Schedule.getModel();
                for(int i=0;i<model.getRowCount();i++){
                    addsch(model.getValueAt(i, 0).toString(),
                            model.getValueAt(i, 1).toString(),
                            model.getValueAt(i, 2).toString(),
                            model.getValueAt(i, 3).toString(),
                            model.getValueAt(i, 4).toString());
                }
            }
        });
        
        

    }
    
    private void addnewrow(String s1,String s2,String s3,String s4, String s5){
        DefaultTableModel model=(DefaultTableModel) Schedule.getModel();
        model.addRow(new Object[]{s1,s2,s3,s4,s5});
    }
    
    private boolean isful(){
        return !(Name.getText().isEmpty() || Date.getText().isEmpty() || Time.getText().isEmpty());
    }
    
    private void deletselcted(){
        int []selected=Schedule.getSelectedRows();
        DefaultTableModel model=(DefaultTableModel) Schedule.getModel();
        for(int i=0;i < selected.length;i++)model.removeRow(selected[i]);
    }
    private void addsch (String s1,String s2,String s3,String s4,String s5){
        try {
            Statement statm=c.createStatement();
            String str="insert into schedule values('"+s1+"','"+s2+"','"+s3+"','"+s5+"','"+s4+"');";
            statm.executeUpdate(str);
            JOptionPane.showMessageDialog(null, 
                              "insert succes", 
                              "error", 
                              JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                              "sorry we can not add this scadual", 
                              "error", 
                              JOptionPane.ERROR_MESSAGE);
            
        }
    }

}
