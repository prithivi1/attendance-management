package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminLogin implements ActionListener
{
    JFrame frame;
    JLabel ltitle,lusername,luser,lpass,lpassword;
    JTextField tusername;
    JPasswordField p1;
    JButton b1,b2;

    String username;
    String password;

    public AdminLogin(){
        frame = new JFrame("LOGIN");
        ImageIcon icon = new ImageIcon("E:\\skct.jpeg");
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);
        
        ltitle = new JLabel("   ADMIN LOGIN");
        frame.add(ltitle);
        ltitle.setFont(new Font("Times New Roman",Font.BOLD,20));
        ltitle.setOpaque(true);
        ltitle.setBackground(new Color(0xE28A3F));
        ltitle.setBounds(0,50,800,60);


        ImageIcon userIcon = new ImageIcon(new ImageIcon("E:\\us.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        lusername = new JLabel();
        lusername.setIcon(userIcon);
        frame.add(lusername);
        lusername.setFont(new Font("Times New Roman",Font.BOLD,20));
        lusername.setBounds(120,100,300,200);

        luser = new JLabel("USERNAME");
        frame.add(luser);
        luser.setFont(new Font("Times New Roman",Font.BOLD,15));
        luser.setBounds(200,180,300,20);

        tusername = new JTextField();
        frame.add(tusername);
        tusername.setFont(new Font("Times New Roman",Font.BOLD,15));
        tusername.setBounds(200,210,300,20);

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("E:\\ps.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        lpass = new JLabel();
        lpass.setIcon(imageIcon);
        frame.add(lpass);
        lpass.setFont(new Font("Times New Roman",Font.BOLD,20));
        lpass.setBounds(120,185,300,200);

        lpassword = new JLabel("PASSWORD");
        frame.add(lpassword);
        lpassword.setFont(new Font("Times New Roman",Font.BOLD,15));
        lpassword.setBounds(200,260,300,20);

        p1 = new JPasswordField();
        frame.add(p1);
        p1.setFont(new Font("Times New Roman",Font.BOLD,15));
        p1.setBounds(200,290,300,20);

        ImageIcon login = new ImageIcon(new ImageIcon("E:\\login.png").getImage().getScaledInstance(80, 25, Image.SCALE_DEFAULT));
        b1 = new JButton();
        b1.setIcon(login);
        frame.add(b1);
        b1.setFont(new Font("Times New Roman",Font.BOLD,20));
        b1.setBounds(400,340,100,30);
        b1.addActionListener(this);

        ImageIcon back = new ImageIcon(new ImageIcon("E:\\back.jpg").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        b2 = new JButton();
        b2.setIcon(back);
        frame.add(b2);
        b2.setFont(new Font("Times New Roman",Font.BOLD,20));
        b2.setBounds(360,340,30,30);
        b2.addActionListener(this);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().setBackground(Color.GRAY);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource().equals(b1)) {
        try {
            username = tusername.getText();
            password = p1.getText();
            String id = "1";
            String username1,password1;
            Connection con = Sqlconn.getconnection();
            PreparedStatement ps=(PreparedStatement)con.prepareStatement("select * from admin");
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
               username1 = rs.getString(2);
               password1 = rs.getString(3);
               if(username.equals(username1) && password.equals(password1))
                {
                frame.dispose();
                new AdminPage();
                  }
               else
               {
                    if(username.trim().equals(""))
                    {
                          JOptionPane.showMessageDialog(null,"INVALID CREDENTIALS!!!TRY AGAIN");
                    }
                    else if(password.trim().equals(""))
                    {
                           JOptionPane.showMessageDialog(null,"INVALID CREDENTIALS!!!TRY AGAIN");
                    }
                    else
                    {
                         JOptionPane.showMessageDialog(null,"INVALID CREDENTIALS!!!TRY AGAIN");
                    }
                   
                }
             }
               
            }catch (SQLException ex)
                 {
            Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
      
        if(e.getSource().equals(b2)) {
             frame.dispose();
             new MainForm();
        }
    }
}
