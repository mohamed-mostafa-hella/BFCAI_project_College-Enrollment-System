/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oo.studentpanel;

import java.awt.Dimension;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class studentscadual extends JPanel {

    JLabel img = new JLabel();

    JTable table ;
    Connection c;
    public studentscadual(String year , String time) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/oo","root","root");
            System.out.println("conecttion succes sca");
        } catch (ClassNotFoundException ex) {
            System.out.println("conecttion fall 1");
        } catch (SQLException ex) {
            System.out.println("conecttion fail 2");
        }
        
        
        img.setIcon(new ImageIcon("Schedule.png"));
        
        //////////////////////////////////////////////
        String []colom={"name","date","time","term"};
        Object [][]data={};
        table=new JTable(new DefaultTableModel(data,colom));
        table.setPreferredScrollableViewportSize(new Dimension(990,640));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        //////////////////////////////////////////////////
        

        add(img);
        img.add(scrollPane);

        img.setBounds(0, 0, 1280, 720);
        scrollPane.setBounds(31, 28, 992, 642);
        
        if(time.equals("08") || time.equals("09") || time.equals("10") || time.equals("11")
                || time.equals("12") || time.equals("1"))
            lod1(year);
        else
            lod2(year);
        
    }
    
    private void lod1(String year){
        String []arr=new String[4];
        DefaultTableModel model=(DefaultTableModel) table.getModel();
        try {
            Statement statm=c.createStatement();
            String strse="SELECT * FROM oo.schedule where year='"+year+"' and term='1th' ;";
            ResultSet set=statm.executeQuery(strse);
            while(set.next()){
                for(int i=0;i<4;i++){
                    arr[i]=set.getString(i+1);
                }
                model.addRow(arr);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                              "sorry we can not get your schedule now", 
                              "error", 
                              JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void lod2(String year){
        String []arr=new String[4];
        DefaultTableModel model=(DefaultTableModel) table.getModel();
        try {
            Statement statm=c.createStatement();
            String strse="SELECT * FROM oo.schedule where year='"+year+"' and term='2th' ;";
            ResultSet set=statm.executeQuery(strse);
            while(set.next()){
                for(int i=0;i<4;i++){
                    arr[i]=set.getString(i+1);
                }
                model.addRow(arr);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                              "sorry we can not get your schedule now", 
                              "error", 
                              JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
}
