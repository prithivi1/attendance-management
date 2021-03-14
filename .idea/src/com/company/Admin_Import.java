package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin_Import implements ActionListener {

    JFrame frame;
    JLabel ldepartment,lyear,lsection,l_title;
    JComboBox cdepartment,cyear,csection;
    JButton b_select,b_back;
    String tablename,dept,year,section;

    public Admin_Import()
    {
        frame = new JFrame("Select Class");
        frame.setResizable(false);

        l_title = new JLabel("  SELECT CLASS");
        frame.add(l_title);
        l_title.setFont(new Font("Times New Roman",Font.BOLD,20));
        l_title.setOpaque(true);
        l_title.setBackground(new Color(0xE28A3F));
        l_title.setBounds(0,50,800,60);

        ldepartment = new JLabel("DEPARTMENT");
        frame.add(ldepartment);
        ldepartment.setFont(new Font("Times New Roman",Font.BOLD,20));
        ldepartment.setBounds(150,200,170,20);

        String department[] = {"","MECH","ECE","EEE","ICE","CIVIL","IT","CSE"};
        cdepartment = new JComboBox(department);
        frame.add(cdepartment);
        cdepartment.setFont(new Font("Times New Roman",Font.BOLD,20));
        cdepartment.setBounds(400,200,170,20);

        lyear = new JLabel("YEAR");
        frame.add(lyear);
        lyear.setFont(new Font("Times New Roman",Font.BOLD,20));
        lyear.setBounds(150,250,170,20);

        String year[] = {"","I","II","III","IV"};
        cyear = new JComboBox(year);
        frame.add(cyear);
        cyear.setFont(new Font("Times New Roman",Font.BOLD,20));
        cyear.setBounds(400,250,170,20);

        lsection = new JLabel("SECTION");
        frame.add(lsection);
        lsection.setFont(new Font("Times New Roman",Font.BOLD,20));
        lsection.setBounds(150,300,170,20);

        String section[] = {"","A","B","C"};
        csection = new JComboBox(section);
        frame.add(csection);
        csection.setFont(new Font("Times New Roman",Font.BOLD,20));
        csection.setBounds(400,300,170,20);

        b_select = new JButton("SELECT");
        frame.add(b_select);
        b_select.setFont(new Font("Times New Roman",Font.BOLD,15));
        b_select.setBounds(470,340,100,20);
        b_select.addActionListener(this);

        b_back = new JButton("BACK");
        frame.add(b_back);
        b_back.setFont(new Font("Times New Roman",Font.BOLD,15));
        b_back.setBounds(470,380,100,20);
        b_back.addActionListener(this);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public boolean mandatory(){
        if(cdepartment.getSelectedItem().toString().isEmpty() || csection.getSelectedItem().toString().isEmpty() ||
                cyear.getSelectedItem().toString().isEmpty()){
            return false;
        }
        return true;
    }


    public void errorMandatory(){
        JDialog error = new JDialog();

        JLabel l2 = new JLabel("MANDATORY FIELD MISSING OR INCORRECT ENTRY");
        error.add(l2);
        l2.setFont(new Font("Times New Roman",Font.BOLD,15));
        l2.setBounds(30,20,480,20);

        error.setSize(480,100);
        error.setLocationRelativeTo(null);
        error.setLayout(null);
        error.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(b_select)) {
            if(mandatory()) {
                dept = cdepartment.getSelectedItem().toString();
                year = cyear.getSelectedItem().toString();
                section = csection.getSelectedItem().toString();
                tablename = year + "_" + dept + "_" + section;

                new ViewStudent(tablename);
            }else {
                errorMandatory();
            }
        }

        if(e.getSource().equals(b_back)){
            frame.dispose();
            new AdminPage();
        }
    }


}
