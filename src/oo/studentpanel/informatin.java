/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oo.studentpanel;

import data.StdData;
import java.awt.Color;
import java.awt.Font;
import static java.awt.image.ImageObserver.ABORT;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mohamed
 */
public class informatin extends JPanel{
    JLabel img=new JLabel();
    
    JLabel name=new JLabel("name");
    JLabel ssn=new JLabel("ssn");
    JLabel gender =new JLabel("gender");
    JLabel age=new JLabel("age");
    JLabel phone=new JLabel("phone");
    JLabel mail=new JLabel("mail");
    JLabel year=new JLabel("year");
    JLabel sec=new JLabel("sec");
    JLabel addres=new JLabel("addres");
    JLabel user=new JLabel("user");
    JLabel pass=new JLabel("pass");
    
    public informatin(StdData studata) {
        img.setIcon(new ImageIcon("ShowStudent.png"));
        
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
        
        setSize(1280,720);
        setLayout(null);
        
        img.setBounds(0, 0, 1280, 720);
        
        name.setBounds(600,50, 443, 37);
        ssn.setBounds(600,115,443,37);
        age.setBounds(600,180, 443, 37);
        phone.setBounds(600, 246,443, 37);
        gender.setBounds(600,310, 443, 37);
        addres.setBounds(600, 380, 443, 37);
        year.setBounds(600,447, 443, 37);
        mail.setBounds(600,514, 443, 37);
        user.setBounds(600,572, 443, 37);
        pass.setBounds(600,636, 443, 37);
        
        
        Font f=new Font("Comic Sans MS", ABORT, 18);
        name.setFont(f);
        ssn.setFont(f);
        age.setFont(f);
        phone.setFont(f);
        gender.setFont(f);
        addres.setFont(f);
        year.setFont(f);
        mail.setFont(f);
        user.setFont(f);
        pass.setFont(f);
        
        name.setForeground(Color.WHITE);
        ssn.setForeground(Color.WHITE);
        age.setForeground(Color.WHITE);
        phone.setForeground(Color.WHITE);
        gender.setForeground(Color.WHITE);
        addres.setForeground(Color.WHITE);
        year.setForeground(Color.WHITE);
        mail.setForeground(Color.WHITE);
        user.setForeground(Color.WHITE);
        pass.setForeground(Color.WHITE);
        
        name.setText(studata.getName());
        ssn.setText(studata.getSsn());
        age.setText(studata.getAge());
        phone.setText(studata.getPhone());
        gender.setText(studata.getGender());
        addres.setText(studata.getAddress());
        year.setText(studata.getYear());
        mail.setText(studata.getMail());
        user.setText(studata.getUser());
        pass.setText(studata.getPass());
    }
    
}
