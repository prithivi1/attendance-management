package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;

public class StudentLogin implements ActionListener {

    //Fields used in Form

    JFrame frame;
    JLabel l_title, l_userIcon, l_regno, l_passIcon, l_password,ldepartment,lyear,lsection;
    JTextField t_username;
    JPasswordField p_password;
    JButton b_login, b_exit;
    JComboBox cdepartment,cyear,csection;
    String username,tablename;
    String password,dept,year,section;
    

    public StudentLogin()
    {
        Login();
    }

    public void Login(){
        frame = new JFrame("LOGIN");
        ImageIcon icon = new ImageIcon("E:\\skct.jpg");
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);


        l_title = new JLabel("     STUDENT LOGIN");
        frame.add(l_title);
        l_title.setFont(new Font("Times New Roman",Font.BOLD,20));
        l_title.setOpaque(true);
        l_title.setBackground(new Color(0xE28A3F));
        l_title.setBounds(0,50,800,60);


        ImageIcon userIcon = new ImageIcon(new ImageIcon("E:\\us.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        l_userIcon = new JLabel();
        l_userIcon.setIcon(userIcon);
        frame.add(l_userIcon);
        l_userIcon.setFont(new Font("Times New Roman",Font.BOLD,15));
        l_userIcon.setBounds(120,100,300,200);

        l_regno = new JLabel("REGNO");
        frame.add(l_regno);
        l_regno.setFont(new Font("Times New Roman",Font.BOLD,15));
        l_regno.setBounds(200,180,300,20);

        t_username = new JTextField();
        frame.add(t_username);
        t_username.setFont(new Font("Times New Roman",Font.BOLD,15));
        t_username.setBounds(200,210,300,20);

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("E:\\ps.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        l_passIcon = new JLabel();
        l_passIcon.setIcon(imageIcon);
        frame.add(l_passIcon);
        l_passIcon.setFont(new Font("Times New Roman",Font.BOLD,15));
        l_passIcon.setBounds(120,185,300,200);

        l_password = new JLabel("PASSWORD");
        frame.add(l_password);
        l_password.setFont(new Font("Times New Roman",Font.BOLD,15));
        l_password.setBounds(200,260,300,20);

        p_password = new JPasswordField();
        frame.add(p_password);
        p_password.setFont(new Font("Times New Roman",Font.ITALIC,15));
        p_password.setBounds(200,290,300,20);
        
         ldepartment = new JLabel("DEPARTMENT");
        frame.add(ldepartment);
        ldepartment.setFont(new Font("Times New Roman",Font.BOLD,15));
        ldepartment.setBounds(120,340,170,20);

        String department[] = {"","mech","ece","eee","ice","civil","it","cse"};
        cdepartment = new JComboBox(department);
        frame.add(cdepartment);
        cdepartment.setFont(new Font("Times New Roman",Font.BOLD,15));
        cdepartment.setBounds(120,370,170,20);

        lyear = new JLabel("YEAR");
        frame.add(lyear);
        lyear.setFont(new Font("Times New Roman",Font.BOLD,15));
        lyear.setBounds(310,340,80,20);
       
        String year[] = {"","i","ii","iii","iv"};
        cyear = new JComboBox(year);
        frame.add(cyear);
        cyear.setFont(new Font("Times New Roman",Font.BOLD,15));
        cyear.setBounds(310,370,80,20);

        lsection = new JLabel("SECTION");
        frame.add(lsection);
        lsection.setFont(new Font("Times New Roman",Font.BOLD,15));
        lsection.setBounds(410,340,90,20);

        String section[] = {"","a","b","c"};
        csection = new JComboBox(section);
        frame.add(csection);
        csection.setFont(new Font("Times New Roman",Font.BOLD,15));
        csection.setBounds(410,370,90,20);


        ImageIcon login = new ImageIcon(new ImageIcon("E:\\login.png").getImage().getScaledInstance(80, 25, Image.SCALE_DEFAULT));
        b_login = new JButton();
        b_login.setIcon(login);
        frame.add(b_login);
        b_login.setFont(new Font("Times New Roman",Font.BOLD,15));
        b_login.setBounds(400,440,100,30);
        b_login.addActionListener(this);

        ImageIcon back = new ImageIcon(new ImageIcon("E:\\back.jpg").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        b_exit = new JButton();
        b_exit.setIcon(back);
        frame.add(b_exit);
        b_exit.setFont(new Font("Times New Roman",Font.BOLD,15));
        b_exit.setBounds(360,440,30,30);
        b_exit.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public boolean checkVaildCrendentials(String user,String pass,String tablename) {
        Connection con=Sqlconn.getconnection();
        try
        {
            if (user!= null && pass!=null && tablename!=null)
            {
                String sql = "Select * from "+tablename+" where regno='"+user+"'and contact='"+pass+"'";
                 PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery();
                System.out.println("EXECUTED!!");
                if(rs.next())
                    return true;
            }
        }
        catch(Exception e)
        {
            System.out.println("Validation Error "+e);
        }
        return false;
    }

    public void error(){
            JDialog dialog = new JDialog();
            dialog.setSize(400,100);
            dialog.setLayout(null);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);

            JLabel message = new JLabel("ENTER VALID CREDENTIALS !!!");
            dialog.add(message);
            message.setFont(new Font("Times New Roman",Font.BOLD,15));
            message.setBounds(70,20,400,20);

            t_username.setText(null);
            p_password.setText(null);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        username = t_username.getText();
        password = p_password.getText();
         dept=(String) cdepartment.getItemAt(cdepartment.getSelectedIndex());
         year=(String) cyear.getItemAt(cyear.getSelectedIndex());
         section=(String) csection.getItemAt(csection.getSelectedIndex());
         tablename = year+ "_"+dept+ "_"+section;
        if(e.getSource().equals(b_login)) {
            if (checkVaildCrendentials(username,password,tablename)) {
                frame.dispose();
                new StudentView(username,tablename);
            }else {
                error();
            }
        }
        if(e.getSource().equals(b_exit)){
            frame.dispose();
            new MainForm();
        }
    }

}