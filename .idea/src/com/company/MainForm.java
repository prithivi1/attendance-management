package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm implements ActionListener {

    //Fields used in form

    JFrame frame;
    JLabel title_label, color1_label, color2_label, admin_label, tutor_label, student_label, title1_label,l5,l6,l7,l8,l9,l10;
    JButton admin_button, tutor_button, student_button;

    public MainForm(){

        //Form creation and Fields postioning.

        frame = new JFrame("SELECT USER");
        ImageIcon icon = new ImageIcon("E:\\skct.jpeg");
        frame.setIconImage(icon.getImage());

        color1_label = new JLabel();
        frame.add(color1_label);
        color1_label.setOpaque(true);
        color1_label.setBackground(new Color(0xE27F26));
        color1_label.setBounds(0,0,900,60);


        title_label = new JLabel("       SELECT LOGIN");
        frame.add(title_label);
        title_label.setFont(new Font("Times New Roman",Font.BOLD,20));
        title_label.setOpaque(true);
        title_label.setBackground(new Color(0xE27F26));
        title_label.setBounds(0,60,228,60);

        color2_label = new JLabel();
        frame.add(color2_label);
        color2_label.setOpaque(true);
        color2_label.setBackground(new Color(0xE27F26));
        color2_label.setBounds(0,60,228,510);

        student_button = new JButton("STUDENT");
        color2_label.add(student_button);
        student_button.setFont(new Font("Times New Roman",Font.BOLD,15));
        student_button.setBounds(100,100,120,30);
        student_button.addActionListener(this);


        ImageIcon studentIcon = new ImageIcon(new ImageIcon("E:\\admin.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        student_label = new JLabel();
        student_label.setIcon(studentIcon);
        color2_label.add(student_label);
        student_label.setBounds(30,355,60,60);

        admin_button = new JButton("ADMIN");
        color2_label.add(admin_button);
        admin_button.setFont(new Font("Times New Roman",Font.BOLD,15));
        admin_button.setBounds(100,360,120,30);
        admin_button.addActionListener(this);

        ImageIcon userIcon = new ImageIcon(new ImageIcon("E:\\admin.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        admin_label = new JLabel();
        admin_label.setIcon(userIcon);
        color2_label.add(admin_label);
        admin_label.setBounds(30,85,60,60);

        tutor_button = new JButton("TUTOR");
        color2_label.add(tutor_button);
        tutor_button.setFont(new Font("Times New Roman",Font.BOLD,15));
        tutor_button.setBounds(100,230,120,30);
        tutor_button.addActionListener(this);

        ImageIcon tutorIcon = new ImageIcon(new ImageIcon("E:\\admin.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        tutor_label = new JLabel();
        tutor_label.setIcon(tutorIcon);
        color2_label.add(tutor_label);
        tutor_label.setBounds(30,215,60,60);

        title1_label = new JLabel("<html><font color=white> STUDENT ATTENDANCE MANAGEMENT SYSTEM</font>");
        title1_label.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(title1_label);
        title1_label.setFont(new Font("Times New Roman",Font.BOLD,20));
        title1_label.setOpaque(true);
        title1_label.setBackground(new Color(0x12439E));
        title1_label.setBounds(228,60,672,60);

        ImageIcon pic1 = new ImageIcon(new ImageIcon("E:\\c.jpeg").getImage().getScaledInstance(90, 80, Image.SCALE_DEFAULT));
        l5 = new JLabel(pic1);
        frame.add(l5);
        l5.setOpaque(true);
        l5.setBackground(new Color(0xD0C7BE));
        l5.setBounds(280,180,160,110);

        l6 = new JLabel("<html><head><title><center><b><i>Positive<br>Work<br>Environment</b></i><br></center></title></head><p><br>Timely<br> attendance updates<br> and error free<br> payroll leads to <br>happier workforce<br></p></html>");
        l6.setHorizontalAlignment(SwingConstants.CENTER);
        l6.setFont(new Font("Times New Roman",Font.ITALIC,15));
        frame.add(l6);
        l6.setOpaque(true);
        l6.setBackground(new Color(0xD0C7BE));
        l6.setBounds(280,180,160,400);

        ImageIcon pic2 = new ImageIcon(new ImageIcon("E:\\b.jpeg").getImage().getScaledInstance(90, 80, Image.SCALE_DEFAULT));
        l7 = new JLabel(pic2);
        frame.add(l7);
        l7.setOpaque(true);
        l7.setBackground(new Color(0xD0C7BE));
        l7.setBounds(490,180,160,110);

        l8 = new JLabel("<html><head><title><center><b><i>Intelligent <br>Reports</b></i><br></center></title></head><p><br>Export and import<br> information rich<br> reports to <br>avoid errors <br>or disputes</p></html>");
        l8.setHorizontalAlignment(SwingConstants.CENTER);
        l8.setFont(new Font("Times New Roman",Font.ITALIC,15));
        frame.add(l8);
        l8.setOpaque(true);
        l8.setBackground(new Color(0xD0C7BE));
        l8.setBounds(490,180,160,400);

        ImageIcon pic3 = new ImageIcon(new ImageIcon("E:\\f.jpeg").getImage().getScaledInstance(90, 80, Image.SCALE_DEFAULT));
        l9 = new JLabel(pic3);
        frame.add(l9);
        l9.setOpaque(true);
        l9.setBackground(new Color(0xD0C7BE));
        l9.setBounds(700,180,160,110);

        l10 = new JLabel("<html><head><title><center><b><i>Attendance<br>updates</b></i><br></center></title></head><p><br>Sit back and <br>enjoy the <br>auto-calculation of<br> worked and <br>OT (overtime)<br> hours</p></html>");
        l10.setHorizontalAlignment(SwingConstants.CENTER);
        l10.setFont(new Font("Times New Roman",Font.ITALIC,15));
        frame.add(l10);
        l10.setOpaque(true);
        l10.setBackground(new Color(0xD0C7BE));
        l10.setBounds(700,180,160,400);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    //Actionevent for Buttons

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(student_button)){
            frame.dispose();
            new StudentLogin();
        }
        if(e.getSource().equals(tutor_button)){
            frame.dispose();
            new TutorLogin();
        }
        if(e.getSource().equals(admin_button)){
            frame.dispose();
            new AdminLogin();
        }

    }
}
