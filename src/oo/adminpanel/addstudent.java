package oo.adminpanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class addstudent extends JPanel {
    JLabel img =new JLabel();
   
    JTextField name=new JTextField();
    JTextField ssn=new JTextField();
    JComboBox<String> gender =new JComboBox<>();
    JTextField age=new JTextField();
    JTextField phone=new JTextField();
    JTextField mail=new JTextField();
    JTextField sec=new JTextField();
    JTextField addres=new JTextField();
    JTextField user=new JTextField();
    JTextField pass=new JTextField();
    
    String yeararr[]={"1th","2th","3th","4th"};
    JComboBox<String> year=new JComboBox<>(yeararr);
    
    
    JTextField txsearch=new JTextField();
    JButton butsearch=new JButton();
    
    JButton save=new JButton();
    JButton remove=new JButton();

    Connection c;
    public addstudent() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/oo","root","root");
            System.out.println("conecttion succes stu");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("conecttion fall 1");
        } catch (SQLException ex) {
            System.out.println("conecttion fail 2");
        }
        
        
        img.setIcon(new ImageIcon("admin_student.png"));
        gender.addItem("Male");
        gender.addItem("Female");
        
        add(img);
        img.add(name);
        img.add(ssn);
        img.add(age);
        img.add(phone);
        img.add(gender);
        img.add(addres);
        img.add(year);
        img.add(mail);
        img.add(user);
        img.add(pass);
        
        img.add(txsearch);
        img.add(butsearch);
        
        img.add(save);
        img.add(remove);
        
        setSize(1280,720);
        setLayout(null);
        
        img.setBounds(0, 0, 1280, 720);
        
        name.setBounds(210,80, 443, 37);
        ssn.setBounds(210,139,443,37);
        age.setBounds(210,198, 443, 37);
        phone.setBounds(210, 257,443, 37);
        gender.setBounds(210,315, 443, 37);
        addres.setBounds(210, 373, 443, 37);
        year.setBounds(210,432, 443, 37);
        mail.setBounds(210,490, 443, 37);
        user.setBounds(210,548, 443, 37);
        pass.setBounds(210,606, 443, 37);


        name.setBorder(null);
        ssn.setBorder(null);
        age.setBorder(null);
        phone.setBorder(null);
        addres.setBorder(null);
        year.setBorder(null);
        mail.setBorder(null);
        user.setBorder(null);
        pass.setBorder(null);
        txsearch.setBorder(null);
        txsearch.setBackground(new Color(251,243,134));
        butsearch.setContentAreaFilled(false);
        butsearch.setBorder(null);
        save.setContentAreaFilled(false);
        save.setBorder(null);
        remove.setContentAreaFilled(false);
        remove.setBorder(null);
       
        Font f=new Font("Comic Sans MS", ABORT, 18);
        name.setFont(f);
        ssn.setFont(f);
        age.setFont(f);
        phone.setFont(f);
        addres.setFont(f);
        mail.setFont(f);
        user.setFont(f);
        pass.setFont(f);
        txsearch.setFont(f);
        year.setFont(f);
        gender.setFont(f);
          
        txsearch.setBounds( 865 , 81 , 368, 35);
        butsearch.setBounds(1017, 155, 80, 30);
        
        
        save.setBounds(1120, 580 , 95 , 80);
        remove.setBounds(875, 585 , 95 , 80);
        
        butsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(iffounded(txsearch.getText())){
                        getdata();
                    }else  JOptionPane.showMessageDialog(null, 
                              "we can not found this student", 
                              "error", 
                              JOptionPane.INFORMATION_MESSAGE);
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!iswrite()){
                     JOptionPane.showMessageDialog(null, 
                                  "please compleat fildes", 
                                  "error", 
                                  JOptionPane.WARNING_MESSAGE);
                    return;
                } 
                if(iffounded(ssn.getText())){

                    int replay = (int) JOptionPane.showConfirmDialog(null, 
                            "this student is exist are you need to ubdate his data",
                            "worning",
                            JOptionPane.YES_NO_OPTION);
                    if(replay==JOptionPane.YES_OPTION){
                        delet();
                        addstu();
                    }
                }else addstu();
                clear();
            }
        });
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ssn.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, 
                      "please compleat fildes", 
                      "error", 
                      JOptionPane.WARNING_MESSAGE);      
                    return;
                }
                int replay = (int) JOptionPane.showConfirmDialog(null, 
                            "are you shour you want to delete this student",
                            "worning",
                            JOptionPane.YES_NO_OPTION);
                if(replay==JOptionPane.YES_OPTION){
                    delet();
                    JOptionPane.showMessageDialog(null, "delete has been done","info",JOptionPane.INFORMATION_MESSAGE);
                }
                clear();
            }
        });
        
    }
    private boolean iswrite(){
        return !(name.getText().isEmpty() || ssn.getText().isEmpty() ||age.getText().isEmpty() || phone.getText().isEmpty() || gender.getSelectedItem().toString().isEmpty()
                || addres.getText().isEmpty() || year.getSelectedItem().toString().isEmpty() || mail.getText().isEmpty() || user.getText().isEmpty() || pass.getText().isEmpty());
    }
    private void delet (){
        try {
            Statement statm=c.createStatement();
            String delet="delete from student where ssn='"+ssn.getText()+"';";
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
            String strse="SELECT count(1) FROM oo.student where ssn='"+comp+"' or user='"+user.getText()+"';";
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
    private void addstu (){
        try {
            Statement statm=c.createStatement();
            String str="insert into student values('"+name.getText()+"','"+ssn.getText()+"','"+age.getText()+"','"+phone.getText()+"','"+gender.getSelectedItem().toString()+"','"+addres.getText()+"','"+year.getSelectedItem().toString()+"','"+mail.getText()+"','"+user.getText()+"','"+pass.getText()+"');";
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
            String strse="SELECT * FROM oo.student where ssn='"+txsearch.getText()+"';";
            ResultSet set=statm.executeQuery(strse);
            while(set.next()){
                String ss=set.getString(7);
                name.setText(set.getString(1));
                ssn.setText(set.getString(2));
                age.setText(set.getString(3));
                phone.setText(set.getString(4));
                if("mail".equals(set.getString(5)))
                        gender.setSelectedIndex(0);
                else
                    gender.setSelectedIndex(1);
                addres.setText(set.getString(6));
                if(ss.equals("1th"))
                    year.setSelectedIndex(0);
                else if(ss.equals("2th"))
                    year.setSelectedIndex(1);
                else if(ss.equals("3th"))
                    year.setSelectedIndex(2);
                else 
                    year.setSelectedIndex(3);
                mail.setText(set.getString(8));
                user.setText(set.getString(9));
                pass.setText(set.getString(10));
                
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
        ssn.setText("");
        age.setText("");
        phone.setText("");
        gender.setSelectedIndex(0);
        addres.setText("");
        year.setSelectedIndex(0);
        mail.setText("");
        user.setText("");
        pass.setText("");
        txsearch.setText("");
    }
}
