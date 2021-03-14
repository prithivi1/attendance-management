package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAttendance implements ActionListener {

    JFrame frame;
    JLabel l_title, l_search;
    JTextArea t_search;
    JButton b_search, b_update,b_back;
    String username;

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    String tablename;
    String att_table;

    public ViewAttendance(String name,String tablename,String att_table){
        this.username = name;
        this.tablename = tablename;
        this.att_table = att_table;
        attendance();
        con = Sqlconn.getconnection();
    }

    public void attendance() {
        frame = new JFrame("ATTENDANCE");
        ImageIcon icon = new ImageIcon("E:\\skct.jpeg");
        frame.setIconImage(icon.getImage());

        l_title = new JLabel("   VIEW ATTENDANCE");
        frame.add(l_title);
        l_title.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l_title.setOpaque(true);
        l_title.setBackground(new Color(0xE28A3F));
        l_title.setBounds(0, 50, 800, 60);

        l_search = new JLabel("SEARCH BY REGNO");
        frame.add(l_search);
        l_search.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l_search.setBounds(50, 170, 200, 20);

        t_search = new JTextArea();
        frame.add(t_search);
        t_search.setFont(new Font("Times New Roman", Font.BOLD, 17));
        t_search.setBounds(140, 210, 200, 20);

        b_search = new JButton("REPORT");
        frame.add(b_search);
        b_search.setFont(new Font("Times New Roman", Font.BOLD, 15));
        b_search.setBounds(230, 250, 110, 20);
        b_search.addActionListener(this);

        b_update = new JButton("VIEW");
        frame.add(b_update);
        b_update.setFont(new Font("Times New Roman", Font.BOLD, 15));
        b_update.setBounds(230, 290, 110, 20);
        b_update.addActionListener(this);

        b_back = new JButton("BACK");
        frame.add(b_back);
        b_back.setFont(new Font("Times New Roman", Font.BOLD, 15));
        b_back.setBounds(230, 330, 110, 20);
        b_back.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void view(){
        JFrame f1;

        Connection con;
        PreparedStatement pst = null;
        ResultSet rs = null;

        con = Sqlconn.getconnection();

        f1 = new JFrame("ATTENDANCE DETAILS ");

        String[] columnNames = {"DATE","Record"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        JTable table=new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        table.setBounds(30,40,200,300);
        JScrollPane scroll=new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        f1.add(scroll);

        f1.setSize(800,600);
        f1.setVisible(true);
        f1.setLocationRelativeTo(null);


        try {
            pst = (PreparedStatement)con.prepareStatement("select * from "+att_table);
            rs = pst.executeQuery();
            while (rs.next()) {

                String Date = rs.getString("Att_Date");
                String Record= rs.getString(t_search.getText());

                model.addRow(new Object[]{Date,Record});
            }
        } catch (SQLException ex) {
            error();
            System.out.println(ex);
        }
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(b_search)){
            try{
                String sql = "SELECT * FROM "+tablename+" WHERE regno = '"+t_search.getText()+"'";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                if(rs.next()) {
                    frame.dispose();
                    new tutorStudentView(t_search.getText(),username,tablename,att_table);
                }else
                    error();
            }catch (Exception ex){
                System.out.println(ex);
            }
        }
        if (e.getSource().equals(b_back)){
            frame.dispose();
            new TutorPage(username);
        }

        if(e.getSource().equals(b_update)){
            try{
                String sql = "SELECT * FROM "+tablename+" WHERE regno = '"+t_search.getText()+"'";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                if(rs.next()) {
                    view();
                }else
                    error();
            }catch (Exception ex){
                System.out.println(ex);
            }
        }
    }
}
