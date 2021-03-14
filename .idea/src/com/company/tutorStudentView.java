package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class tutorStudentView implements ActionListener {
    //Fields used in this form

    JFrame frame;
    JLabel l_title, l_name, l_regno, l_date, l_no_days_leave, l_depatment, l_year, l_section, l_mode, l_mailID, l_contact;
    JTextArea t_name, t_regno, t_department, t_section, t_leaveDate, t_no_days_leave, t_contact, t_mode, t_year, t_mailID;
    JButton b_logout, b_summary;

    Connection con = Sqlconn.getconnection();
    PreparedStatement pst = null;
    ResultSet rs = null;

    int absent = 0;
    int present = 0;
    double percentage = 0;
    String username;
    String tablename;
    String att_table;

    ArrayList<String> list = new ArrayList<String>();

    public tutorStudentView(String user, String tutorName,String tablename,String att_table) {

        //Frame creation

        this.username = tutorName;
        this.tablename = tablename;
        this.att_table = att_table;

        frame = new JFrame("ATTENDANCE VIEW PAGE");
        ImageIcon icon = new ImageIcon("E:\\skct.jpeg");
        frame.setIconImage(icon.getImage());

        l_title = new JLabel();
        frame.add(l_title);
        l_title.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l_title.setOpaque(true);
        l_title.setBackground(new Color(0xE28A3F));
        l_title.setBounds(0, 20, 800, 60);


        l_name = new JLabel("NAME");
        frame.add(l_name);
        l_name.setFont(new Font("Times New Roman", Font.BOLD, 15));
        l_name.setBounds(80, 100, 100, 20);

        t_name = new JTextArea();
        frame.add(t_name);
        t_name.setFont(new Font("Times New Roman", Font.BOLD, 15));
        t_name.setBounds(300, 100, 200, 20);
        t_name.setEditable(false);


        l_regno = new JLabel("REGNO");
        frame.add(l_regno);
        l_regno.setFont(new Font("Times New Roman", Font.BOLD, 15));
        l_regno.setBounds(80, 140, 100, 20);

        t_regno = new JTextArea();
        frame.add(t_regno);
        t_regno.setFont(new Font("Times New Roman", Font.BOLD, 15));
        t_regno.setBounds(300, 140, 200, 20);
        t_regno.setEditable(false);

        l_depatment = new JLabel("DEPARTMENT");
        frame.add(l_depatment);
        l_depatment.setFont(new Font("Times New Roman", Font.BOLD, 15));
        l_depatment.setBounds(80, 180, 150, 20);

        t_department = new JTextArea();
        frame.add(t_department);
        t_department.setFont(new Font("Times New Roman", Font.BOLD, 15));
        t_department.setBounds(300, 180, 200, 20);
        t_department.setEditable(false);

        l_year = new JLabel("YEAR");
        frame.add(l_year);
        l_year.setFont(new Font("Times New Roman", Font.BOLD, 15));
        l_year.setBounds(80, 220, 150, 20);

        t_year = new JTextArea();
        frame.add(t_year);
        t_year.setFont(new Font("Times New Roman", Font.BOLD, 15));
        t_year.setBounds(300, 220, 200, 20);
        t_year.setEditable(false);
        l_section = new JLabel("SECTION");
        frame.add(l_section);
        l_section.setFont(new Font("Times New Roman", Font.BOLD, 15));
        l_section.setBounds(80, 260, 150, 20);

        t_section = new JTextArea();
        frame.add(t_section);
        t_section.setFont(new Font("Times New Roman", Font.BOLD, 15));
        t_section.setBounds(300, 260, 200, 20);
        t_section.setEditable(false);

        l_mode = new JLabel("DAYSCHOLAR/HOSTEL");
        frame.add(l_mode);
        l_mode.setFont(new Font("Times New Roman", Font.BOLD, 15));
        l_mode.setBounds(80, 300, 200, 20);

        t_mode = new JTextArea();
        frame.add(t_mode);
        t_mode.setFont(new Font("Times New Roman", Font.BOLD, 15));
        t_mode.setBounds(300, 300, 200, 20);
        t_mode.setEditable(false);

        l_mailID = new JLabel("MAILID");
        frame.add(l_mailID);
        l_mailID.setFont(new Font("Times New Roman", Font.BOLD, 15));
        l_mailID.setBounds(80, 340, 100, 20);

        t_mailID = new JTextArea();
        frame.add(t_mailID);
        t_mailID.setFont(new Font("Times New Roman", Font.BOLD, 15));
        t_mailID.setBounds(300, 340, 200, 20);
        t_mailID.setEditable(false);

        l_contact = new JLabel("CONTACT");
        frame.add(l_contact);
        l_contact.setFont(new Font("Times New Roman", Font.BOLD, 15));
        l_contact.setBounds(80, 380, 100, 20);

        t_contact = new JTextArea();
        frame.add(t_contact);
        t_contact.setFont(new Font("Times New Roman", Font.BOLD, 15));
        t_contact.setBounds(300, 380, 200, 20);
        t_contact.setEditable(false);

        l_no_days_leave = new JLabel("NO OF DAYS ON LEAVE");
        frame.add(l_no_days_leave);
        l_no_days_leave.setFont(new Font("Times New Roman", Font.BOLD, 15));
        l_no_days_leave.setBounds(80, 420, 200, 20);

        t_no_days_leave = new JTextArea();
        frame.add(t_no_days_leave);
        t_no_days_leave.setFont(new Font("Times New Roman", Font.BOLD, 15));
        t_no_days_leave.setBounds(300, 420, 200, 20);
        t_no_days_leave.setEditable(false);

        l_date = new JLabel("PERCENTAGE");
        frame.add(l_date);
        l_date.setFont(new Font("Times New Roman", Font.BOLD, 15));
        l_date.setBounds(80, 460, 200, 20);

        t_leaveDate = new JTextArea();
        frame.add(t_leaveDate);
        t_leaveDate.setFont(new Font("Times New Roman", Font.BOLD, 15));
        t_leaveDate.setBounds(300, 460, 200, 20);
        t_leaveDate.setEditable(false);

        b_summary = new JButton("SUMMARY");
        frame.add(b_summary);
        b_summary.setFont(new Font("Times New Roman", Font.BOLD, 15));
        b_summary.setBounds(560, 410, 150, 20);
        b_summary.addActionListener(this);

        b_logout = new JButton("BACK");
        frame.add(b_logout);
        b_logout.setFont(new Font("Times New Roman", Font.BOLD, 15));
        b_logout.setBounds(560, 450, 150, 20);
        b_logout.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        //MY_SQL database connection

            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?zeroDateTimeBehaviour=convertToNull", "user", "useruser");
                PreparedStatement pst = null;

                String sql = "SELECT * FROM "+tablename+" WHERE regno = '" + user + "'";
                pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery(sql);

                while (rs.next()) {
                    l_title.setText("    HELLO " + rs.getString("regno").toUpperCase() + " !");
                    t_name.setText(rs.getString("name"));
                    t_regno.setText(rs.getString("regno"));
                    t_department.setText(rs.getString("department"));
                    t_year.setText(rs.getString("year"));
                    t_section.setText(rs.getString("section"));
                    t_mode.setText(rs.getString("mode"));
                    t_mailID.setText(rs.getString("mailid"));
                    t_contact.setText(rs.getString("contact"));
                }
                conn.setAutoCommit(true);
                conn.close();

            } catch (Exception e) {
                System.out.println(e);
            }

            try {
                String sql = "Select " + t_regno.getText() + " from "+att_table;
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    list.add(rs.getString(1));
                }
                for (int i = 0; i < list.size(); i++) {
                    if (Integer.parseInt(list.get(i)) == 0)
                        absent++;
                    else
                        present++;
                }

                t_no_days_leave.setText(String.valueOf(absent));
                percentage = ((present * 100) / (present + absent));
                t_leaveDate.setText(String.valueOf(percentage) + "%");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        //Actionevent for buttons

        @Override
        public void actionPerformed(ActionEvent ev){

            if (ev.getSource().equals(b_summary)) {
                new Summary("ATTENDANCE", absent, present);
            }

            if (ev.getSource().equals(b_logout)) {
                frame.dispose();
                new ViewAttendance(username,tablename,att_table);
            }
        }
    }
