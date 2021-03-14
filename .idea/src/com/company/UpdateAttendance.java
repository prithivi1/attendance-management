package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class UpdateAttendance implements ActionListener {

    JFrame frame;
    JLabel l_title, l_search, l_name, l_attendance,l_date;
    JTextArea t_search, t_name,t_date;
    JButton b_search, b_update,b_back;
    JCheckBox c_mark;

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    String username;
    String tablename;
    String att_table;

    public UpdateAttendance(String date,String username,String tablename,String att_table){
        attendance(date);
        this.username=username;
        this.tablename = tablename;
        this.att_table = att_table;
        con = Sqlconn.getconnection();
    }

    public void attendance(String date) {
        frame = new JFrame("ATTENDANCE PORTAL");
        ImageIcon icon = new ImageIcon("E:\\skct.jpeg");
        frame.setIconImage(icon.getImage());

        l_title = new JLabel("   UPDATE ATTENDANCE");
        frame.add(l_title);
        l_title.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l_title.setOpaque(true);
        l_title.setBackground(new Color(0xE28A3F));
        l_title.setBounds(0, 50, 800, 60);

        l_search = new JLabel("REGNO");
        frame.add(l_search);
        l_search.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l_search.setBounds(50, 170, 100, 20);

        t_search = new JTextArea();
        frame.add(t_search);
        t_search.setFont(new Font("Times New Roman", Font.BOLD, 17));
        t_search.setBounds(220, 170, 200, 20);

        b_search = new JButton("SEARCH");
        frame.add(b_search);
        b_search.setFont(new Font("Times New Roman", Font.BOLD, 15));
        b_search.setBounds(310, 210, 110, 20);
        b_search.addActionListener(this);

        l_name = new JLabel("NAME");
        frame.add(l_name);
        l_name.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l_name.setBounds(50, 280, 100, 20);

        t_name = new JTextArea();
        frame.add(t_name);
        t_name.setFont(new Font("Times New Roman", Font.BOLD, 17));
        t_name.setBounds(220, 280, 200, 20);
        t_name.setEditable(false);

        l_date = new JLabel("DATE");
        frame.add(l_date);
        l_date.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l_date.setBounds(50, 330, 100, 20);

        t_date = new JTextArea(date);
        frame.add(t_date);
        t_date.setFont(new Font("Times New Roman", Font.BOLD, 17));
        t_date.setBounds(220, 330, 200, 20);
        t_date.setEditable(false);

        l_attendance = new JLabel("ATTENDANCE");
        frame.add(l_attendance);
        l_attendance.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l_attendance.setBounds(50, 380, 140, 20);

        c_mark = new JCheckBox("ABSENT");
        frame.add(c_mark);
        c_mark.setFont(new Font("Times New roman", Font.ITALIC, 15));
        c_mark.setBounds(220, 375, 200, 30);
        c_mark.setSelected(true);

        b_update = new JButton("UPDATE");
        frame.add(b_update);
        b_update.setFont(new Font("Times New Roman", Font.BOLD, 15));
        b_update.setBounds(310, 420, 110, 20);
        b_update.addActionListener(this);

        b_back = new JButton("BACK");
        frame.add(b_back);
        b_back.setFont(new Font("Times New Roman", Font.BOLD, 15));
        b_back.setBounds(310, 460, 110, 20);
        b_back.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public boolean checkValidCredentials(String reg) {
            try{
                if(reg!=null){
                    String sql = "Select * from "+tablename+" where Regno = '"+reg+"'";
                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    if(rs.next()) {
                        return true;
                    }
                    else {
                        error();
                    }
                }
            }catch (Exception e1){
                System.out.println("error"+e1);
            }

        return false;
    }
    
    public void error(){
        JDialog dialog = new JDialog();
        dialog.setSize(400,100);
        dialog.setLayout(null);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        JLabel message = new JLabel("STUDENT NOT FOUND!!!");
        dialog.add(message);
        message.setFont(new Font("Times New Roman",Font.BOLD,15));
        message.setBounds(70,20,200,20);

        t_name.setText(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(b_search)) {
            if (checkValidCredentials(t_search.getText())) {
                try {
                    t_name.setText(rs.getString("name"));
                } catch (SQLException throwables) {
                    error();
                }
            }
        }

        if (e.getSource().equals(b_back)) {
            frame.dispose();
            new TutorPage(username);
        }

        if(e.getSource().equals(b_update)){
            try{
                if(c_mark.isSelected()){
                    Sqlconn.getconnection().createStatement().execute("UPDATE "+att_table+" SET "+t_search.getText()+" = 0 WHERE Att_Date = '"+t_date.getText()+"'");
                }else {
                    Sqlconn.getconnection().createStatement().execute("UPDATE "+att_table+" SET "+t_search.getText()+" = 1 WHERE Att_Date = '"+t_date.getText()+"'");
                }
            }catch (Exception er){
                System.out.println(er);
            }
        }
    }
}

