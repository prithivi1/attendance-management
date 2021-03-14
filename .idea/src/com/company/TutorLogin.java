package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TutorLogin implements ActionListener {
    JFrame frame;
    JLabel title_label, user_icon, user_label, pass_icon, password_label;
    JTextArea username_text;
    JPasswordField password_text;
    JButton login_button, back_back;

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    String tablename;

    public TutorLogin(){
        Login();
        con = Sqlconn.getconnection();
    }

    public void Login() {
        frame = new JFrame("LOGIN");
        ImageIcon icon = new ImageIcon("E:\\skct.jpeg");
        frame.setIconImage(icon.getImage());

        title_label = new JLabel("     TUTOR LOGIN");
        frame.add(title_label);
        title_label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        title_label.setOpaque(true);
        title_label.setBackground(new Color(0xE28A3F));
        title_label.setBounds(0, 50, 800, 60);


        ImageIcon userIcon = new ImageIcon(new ImageIcon("E:\\us.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        user_icon = new JLabel();
        user_icon.setIcon(userIcon);
        frame.add(user_icon);
        user_icon.setFont(new Font("Times New Roman", Font.BOLD, 15));
        user_icon.setBounds(120, 100, 300, 200);

        user_label = new JLabel("USERNAME");
        frame.add(user_label);
        user_label.setFont(new Font("Times New Roman", Font.BOLD, 15));
        user_label.setBounds(200, 180, 300, 20);

        username_text = new JTextArea();
        frame.add(username_text);
        username_text.setFont(new Font("Times New Roman", Font.BOLD, 15));
        username_text.setBounds(200, 210, 300, 20);

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("E:\\ps.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        pass_icon = new JLabel();
        pass_icon.setIcon(imageIcon);
        frame.add(pass_icon);
        pass_icon.setFont(new Font("Times New Roman", Font.BOLD, 15));
        pass_icon.setBounds(120, 185, 300, 200);

        password_label = new JLabel("PASSWORD");
        frame.add(password_label);
        password_label.setFont(new Font("Times New Roman", Font.BOLD, 15));
        password_label.setBounds(200, 260, 300, 20);

        password_text = new JPasswordField();
        frame.add(password_text);
        password_text.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        password_text.setBounds(200, 290, 300, 20);

        ImageIcon login = new ImageIcon(new ImageIcon("E:\\login.png").getImage().getScaledInstance(80, 25, Image.SCALE_DEFAULT));
        login_button = new JButton();
        login_button.setIcon(login);
        frame.add(login_button);
        login_button.setFont(new Font("Times New Roman", Font.BOLD, 15));
        login_button.setBounds(400, 340, 100, 30);
        login_button.addActionListener(this);

        ImageIcon back = new ImageIcon(new ImageIcon("E:\\back.jpg").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        back_back = new JButton();
        back_back.setIcon(back);
        frame.add(back_back);
        back_back.setFont(new Font("Times New Roman", Font.BOLD, 15));
        back_back.setBounds(360, 340, 30, 30);
        back_back.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public boolean checkVaildCrendentials(String user,String pass) {
        try
        {
            if (user!= null && pass!=null)
            {
                String sql = "Select * from tutor where id = '"+user+"' and contact = '"+pass+"'";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                if(rs.next())
                    return true;
                System.out.println("EXECUTED!!");
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

        username_text.setText("");
        password_text.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource().equals(login_button)) {
           if (checkVaildCrendentials(username_text.getText(),password_text.getText())) {
                frame.dispose();
                new TutorPage(username_text.getText());
            }else {
                error();
            }
        }

        if(e.getSource().equals(back_back)){
            frame.dispose();
            new MainForm();
        }
    }
}
