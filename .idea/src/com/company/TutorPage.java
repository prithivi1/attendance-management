package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TutorPage implements ActionListener {
    JFrame frame;
    JLabel title_label, update_label, view_label, add_label,l_viewStudent;
    JButton update_button, view_button, add_button, logout_button, viewstudent_button;
    String id;
    String tablename;
    String att_table;

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    JDialog dialog_date;
    JLabel label_attadance,label_date;
    JButton button_create;
    JTextArea text_date;


    public TutorPage(String id){
        this.id = id;

        frame = new JFrame("TUTOR PORTAL");
        ImageIcon icon = new ImageIcon("E:\\skct.jpeg");
        frame.setIconImage(icon.getImage());

        title_label = new JLabel("   WELCOME " +id.toUpperCase());
        frame.add(title_label);
        title_label.setFont(new Font("Times New Roman",Font.BOLD,20));
        title_label.setOpaque(true);
        title_label.setBackground(new Color(0xE28A3F));
        title_label.setBounds(0,50,800,60);

        ImageIcon update = new ImageIcon(new ImageIcon("E:\\att.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        update_button = new JButton();
        update_button.setIcon(update);
        frame.add(update_button);
        update_button.setFont(new Font("Times New Roman",Font.BOLD,15));
        update_button.setBounds(130,150,100,100);
        update_button.addActionListener(this);

        update_label = new JLabel("UPDATE ATTENDANCE");
        frame.add(update_label);
        update_label.setFont(new Font("Times New Roman",Font.BOLD,15));
        update_label.setBounds(100,280,200,20);

        ImageIcon view = new ImageIcon(new ImageIcon("E:\\pic.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        view_button = new JButton();
        view_button.setIcon(view);
        frame.add(view_button);
        view_button.setBounds(130,340,100,100);
        view_button.addActionListener(this);

        view_label = new JLabel("VIEW ATTENDANCE");
        frame.add(view_label);
        view_label.setFont(new Font("Times New Roman",Font.BOLD,15));
        view_label.setBounds(110,470,200,20);

        ImageIcon add = new ImageIcon(new ImageIcon("E:\\pic1.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        add_button = new JButton();
        add_button.setIcon(add);
        frame.add(add_button);
        add_button.setBounds(450,150,100,100);
        add_button.addActionListener(this);

        add_label = new JLabel("ADD/EDIT STUDENT");
        frame.add(add_label);
        add_label.setFont(new Font("Times New Roman",Font.BOLD,15));
        add_label.setBounds(430,280,200,20);

        ImageIcon viewImg = new ImageIcon(new ImageIcon("E:\\view.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        viewstudent_button = new JButton();
        viewstudent_button.setIcon(viewImg);
        frame.add(viewstudent_button);
        viewstudent_button.setFont(new Font("Times New Roman",Font.BOLD,15));
        viewstudent_button.setBounds(450,340,100,100);
        viewstudent_button.addActionListener(this);

        l_viewStudent = new JLabel("VIEW STUDENTS");
        frame.add(l_viewStudent);
        l_viewStudent.setFont(new Font("Times New Roman",Font.BOLD,15));
        l_viewStudent.setBounds(440,470,200,20);

        ImageIcon log = new ImageIcon(new ImageIcon("E:\\log.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        logout_button = new JButton();
        logout_button.setIcon(log);
        frame.add(logout_button);
        logout_button.setFont(new Font("Times New Roman",Font.BOLD,15));
        logout_button.setBounds(750,0,30,30);
        logout_button.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void CreateDate()
    {
        dialog_date = new JDialog();

        dialog_date.setVisible(true);
        dialog_date.setSize(300,250);
        dialog_date.setLayout(null);
        dialog_date.setLocationRelativeTo(null);

        label_attadance = new JLabel("ATTENDANCE DATE");
        dialog_date.add(label_attadance);
        label_attadance.setBounds(26,30,250,20);
        label_attadance.setFont(new Font("Times New Roman",Font.BOLD,16));

        label_date = new JLabel("DATE");
        dialog_date.add(label_date);
        label_date.setBounds(35,90,70,20);
        label_attadance.setFont(new Font("Times New Roman",Font.ITALIC,24));

        Date currentdate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        text_date = new JTextArea(dateFormat.format(currentdate));
        dialog_date.add(text_date);
        text_date.setBounds(85,90,135,20);
        text_date.setEditable(false);

        button_create = new JButton("SELECT");
        dialog_date.add(button_create);
        button_create.setBounds(105,145,90,20);
        button_create.addActionListener(this);

    }

    public void dateError(){
        JDialog dialog = new JDialog();
        dialog.setSize(300,100);
        dialog.setLayout(null);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        JLabel message = new JLabel("ENTER DATE!!!");
        dialog.add(message);
        message.setFont(new Font("Times New Roman",Font.BOLD,20));
        message.setBounds(70,20,200,25);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(update_button)){
           CreateDate();
        }

        if(e.getSource().equals(view_button)){
            String sql = "SELECT * FROM tutor WHERE id = '"+id+"'";
            try {
                rs = Sqlconn.getconnection().createStatement().executeQuery(sql);
                if(rs.next()){
                    tablename = rs.getString("classname");
                    att_table = tablename+"_att";
                }
            }catch (Exception ex){
                System.out.println(ex);
            }
            frame.dispose();
            new ViewAttendance(id,tablename,att_table);
        }

        if(e.getSource().equals(add_button)){
            String sql = "SELECT * FROM tutor WHERE id = '"+id+"'";
            try {
                rs = Sqlconn.getconnection().createStatement().executeQuery(sql);
                if(rs.next()){
                    tablename = rs.getString("classname");
                    att_table = tablename+"_att";
                }
            }catch (Exception ex){
                System.out.println(ex);
            }
            frame.dispose();
            new StudentRegister(id,tablename,att_table);
        }

        if(e.getSource().equals(logout_button)){
            frame.dispose();
            new TutorLogin();
        }

        if(e.getSource().equals(button_create)){
            if (text_date.getText().isEmpty()) {
                dateError();
            } else {
                String sql = "SELECT * FROM tutor WHERE id = '"+id+"'";
                try {
                    rs = Sqlconn.getconnection().createStatement().executeQuery(sql);
                    if(rs.next()){
                        tablename = rs.getString("classname");
                        att_table = tablename+"_att";

                    }
                }catch (Exception ex){
                    System.out.println(ex);
                }
                try {
                    Date currentdate = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Sqlconn.getconnection().createStatement().execute("INSERT INTO "+att_table+" (`Att_Date`) VALUES('"+dateFormat.format(currentdate)+"')");
                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
            dialog_date.dispose();
            frame.dispose();
            new UpdateAttendance(text_date.getText(),id,tablename,att_table);
        }

        if(e.getSource().equals(viewstudent_button)){
            String sql = "SELECT * FROM tutor WHERE id = '"+id+"'";
            try {
                rs = Sqlconn.getconnection().createStatement().executeQuery(sql);
                if(rs.next()){
                    tablename = rs.getString("classname");
                    att_table = tablename+"_att";

                }
            }catch (Exception ex){
                System.out.println(ex);
            }
            new ViewStudent(tablename);
        }
    }
}
