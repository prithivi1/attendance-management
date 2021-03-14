package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class admin_StudentRegister implements ActionListener {

    JFrame frame;
    JLabel label_title, label_name, label_regno, label_gender, label_department, label_year, label_section, label_mode, label_mailid, label_contact;
    JTextArea text_name, text_regno, text_contact, text_mail;
    JComboBox combo_department, combo_year, combo_section, combo_gender, combo_mode;
    JButton button_save, button_back, button_search, button_update, button_delete,button_import,button_clear;

    Connection con;
    PreparedStatement pst = null;
    ResultSet rs = null;

    String tablename;
    String uname;
    String att_table;


    admin_StudentRegister(){

        this.uname=uname;
        register();
        con = Sqlconn.getconnection();
    }

    public void register(){

        frame = new JFrame("REGISTER");

        label_title = new JLabel("     STUDENT REGISTER");
        frame.add(label_title);
        label_title.setFont(new Font("Times New Roman",Font.BOLD,20));
        label_title.setOpaque(true);
        label_title.setBackground(new Color(0xE28A3F));
        label_title.setBounds(0,20,800,60);

        label_regno = new JLabel("REGNO");
        frame.add(label_regno);
        label_regno.setFont(new Font("Times New Roman",Font.BOLD,15));
        label_regno.setBounds(80,110,100,20);

        text_regno = new JTextArea();
        frame.add(text_regno);
        text_regno.setFont(new Font("Times New Roman",Font.BOLD,15));
        text_regno.setBounds(300,110,200,20);

        label_name = new JLabel("NAME");
        frame.add(label_name);
        label_name.setFont(new Font("Times New Roman",Font.BOLD,15));
        label_name.setBounds(80,150,100,20);

        text_name = new JTextArea();
        frame.add(text_name);
        text_name.setFont(new Font("Times New Roman",Font.BOLD,15));
        text_name.setBounds(300,150,200,20);

        label_gender = new JLabel("GENDER");
        frame.add(label_gender);
        label_gender.setFont(new Font("Times New Roman",Font.BOLD,15));
        label_gender.setBounds(80,190,150,20);

        String gender[] = {"","male","female"};
        combo_gender = new JComboBox(gender);
        frame.add(combo_gender);
        combo_gender.setFont(new Font("Times New Roman",Font.BOLD,15));
        combo_gender.setBounds(300,190,200,20);


        label_department = new JLabel("DEPARTMENT");
        frame.add(label_department);
        label_department.setFont(new Font("Times New Roman",Font.BOLD,15));
        label_department.setBounds(80,230,150,20);

        String department[] = {"","MECH","ECE","EEE","ICE","CIVIL","IT","CSE"};
        combo_department = new JComboBox(department);
        frame.add(combo_department);
        combo_department.setFont(new Font("Times New Roman",Font.BOLD,15));
        combo_department.setBounds(300,230,200,20);

        label_year = new JLabel("YEAR");
        frame.add(label_year);
        label_year.setFont(new Font("Times New Roman",Font.BOLD,15));
        label_year.setBounds(80,270,150,20);

        String year[] = {"","I","II","III","IV"};
        combo_year = new JComboBox(year);
        frame.add(combo_year);
        combo_year.setFont(new Font("Times New Roman",Font.BOLD,15));
        combo_year.setBounds(300,270,200,20);

        label_section = new JLabel("SECTION");
        frame.add(label_section);
        label_section.setFont(new Font("Times New Roman",Font.BOLD,15));
        label_section.setBounds(80,310,150,20);

        String section[] = {"","A","B","C"};
        combo_section = new JComboBox(section);
        frame.add(combo_section);
        combo_section.setFont(new Font("Times New Roman",Font.BOLD,15));
        combo_section.setBounds(300,310,200,20);

        label_mode = new JLabel("Dayscholar/Hostel");
        frame.add(label_mode);
        label_mode.setFont(new Font("Times New Roman",Font.BOLD,15));
        label_mode.setBounds(80,350,200,20);

        String mode[] = {"","Dayscholar","Hostel"};
        combo_mode = new JComboBox(mode);
        frame.add(combo_mode);
        combo_mode.setFont(new Font("Times New Roman",Font.BOLD,15));
        combo_mode.setBounds(300,350,200,20);

        label_mailid = new JLabel("MAILID");
        frame.add(label_mailid);
        label_mailid.setFont(new Font("Times New Roman",Font.BOLD,15));
        label_mailid.setBounds(80,390,100,20);

        text_mail = new JTextArea();
        frame.add(text_mail);
        text_mail.setFont(new Font("Times New Roman",Font.BOLD,15));
        text_mail.setBounds(300,390,200,20);

        label_contact = new JLabel("CONTACT");
        frame.add(label_contact);
        label_contact.setFont(new Font("Times New Roman",Font.BOLD,15));
        label_contact.setBounds(80,430,100,20);

        text_contact = new JTextArea();
        frame.add(text_contact);
        text_contact.setFont(new Font("Times New Roman",Font.BOLD,15));
        text_contact.setBounds(300,430,200,20);

        button_search = new JButton("SEARCH");
        frame.add(button_search);
        button_search.setFont(new Font("Times New Roman",Font.BOLD,15));
        button_search.setBounds(570,150,100,20);
        button_search.addActionListener(this);

        button_update = new JButton("UPDATE");
        frame.add(button_update);
        button_update.setFont(new Font("Times New Roman",Font.BOLD,15));
        button_update.setBounds(570,190,100,20);
        button_update.addActionListener(this);

        button_save = new JButton("SAVE");
        frame.add(button_save);
        button_save.setFont(new Font("Times New Roman",Font.BOLD,15));
        button_save.setBounds(570,230,100,20);
        button_save.addActionListener(this);

        button_delete = new JButton("DELETE");
        frame.add(button_delete);
        button_delete.setFont(new Font("Times New Roman",Font.BOLD,15));
        button_delete.setBounds(570,270,100,20);
        button_delete.addActionListener(this);

        button_import= new JButton("IMPORT");
        frame.add(button_import);
        button_import.setFont(new Font("Times New Roman",Font.BOLD,15));
        button_import.setBounds(570,310,100,20);
        button_import.addActionListener(this);

        button_clear = new JButton("CLEAR");
        frame.add(button_clear);
        button_clear.setFont(new Font("Times New Roman",Font.BOLD,15));
        button_clear.setBounds(570,350,100,20);
        button_clear.addActionListener(this);

        button_back = new JButton("BACK");
        frame.add(button_back);
        button_back.setFont(new Font("Times New Roman",Font.BOLD,15));
        button_back.setBounds(570,390,100,20);
        button_back.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void success(){
        JDialog success = new JDialog();

        JLabel l1 = new JLabel("SUCCESS");
        success.add(l1);
        l1.setFont(new Font("Times New Roman",Font.BOLD,15));
        l1.setBounds(50,20,100,20);

        success.setSize(200,100);
        success.setLocationRelativeTo(null);
        success.setLayout(null);
        success.setVisible(true);

        clearForm();
    }

    public void clearForm(){
        text_regno.setText(null);
        text_name.setText(null);
        combo_gender.setSelectedItem(null);
        combo_department.setSelectedItem(null);
        combo_year.setSelectedItem(null);
        combo_section.setSelectedItem(null);
        combo_mode.setSelectedItem(null);
        text_mail.setText(null);
        text_contact.setText(null);
    }


    public void errorRecord(){
        JDialog error = new JDialog();

        JLabel l2 = new JLabel("NO STUDENT RECORD FOUND");
        error.add(l2);
        l2.setFont(new Font("Times New Roman",Font.BOLD,15));
        l2.setBounds(30,20,280,20);

        error.setSize(280,100);
        error.setLocationRelativeTo(null);
        error.setLayout(null);
        error.setVisible(true);

        clearForm();
    }

    public void deleteSuccess() {
        JDialog error = new JDialog();

        JLabel l2 = new JLabel("DELETED SUCCESSFULLY");
        error.add(l2);
        l2.setFont(new Font("Times New Roman", Font.BOLD, 15));
        l2.setBounds(30, 20, 280, 20);

        error.setSize(280, 100);
        error.setLocationRelativeTo(null);
        error.setLayout(null);
        error.setVisible(true);

        clearForm();
    }
    public void errorMandatory(){
        JDialog error = new JDialog();

        JLabel l2 = new JLabel("MANDATORY FIELD MISSING OR INCORRECT ENTRY");
        error.add(l2);
        l2.setFont(new Font("Times New Roman",Font.BOLD,15));
        l2.setBounds(30,20,400,20);

        error.setSize(400,100);
        error.setLocationRelativeTo(null);
        error.setLayout(null);
        error.setVisible(true);
    }

    public boolean mandatory(){
        if(text_regno.getText().isEmpty()|| text_name.getText().isEmpty()|| combo_gender.getSelectedItem().equals(null)||
                combo_department.getSelectedItem().equals(null)|| combo_year.getSelectedItem().equals(null)|| combo_section.getSelectedItem().equals(null)
                || combo_mode.getSelectedItem().equals(null)|| text_mail.getText().isEmpty()|| text_contact.getText().isEmpty()){
            return false;
        }
        return true;
    }

    public void selectDetails(){
        JDialog error = new JDialog();

        JLabel l2 = new JLabel("SELECT DEPARTMENT , CLASS , SECTION TO SEARCH");
        error.add(l2);
        l2.setFont(new Font("Times New Roman",Font.BOLD,15));
        l2.setBounds(30,20,400,20);

        error.setSize(500,100);
        error.setLocationRelativeTo(null);
        error.setLayout(null);
        error.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {


        String NAME_REGEX ="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
        Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);

        String ID_REGEX = "^[a-zA-Z0-9_]+$";
        Pattern ID_PATTERN =Pattern.compile(ID_REGEX);

        String CONTACT_REGEX = "^\\D?(\\d{3})\\D?\\D?(\\d{3})\\D?(\\d{4})$";
        Pattern CONTACT_PATTERN = Pattern.compile(CONTACT_REGEX);

        String MAIL_REGEX ="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*"+"@skct.edu.in";
        String MAIL_REGEX1 ="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*"+"@skcet.edu.in";

        Pattern MAIL_PATTERN = Pattern.compile(MAIL_REGEX);
        Pattern MAIL_PATTERN1 = Pattern.compile(MAIL_REGEX1);

        if(e.getSource().equals(button_search)) {
            if (combo_department.getSelectedItem().toString().isEmpty() || combo_year.getSelectedItem().toString().isEmpty() || combo_section.getSelectedItem().toString().isEmpty()) {
                selectDetails();
            } else {
                String tablename = combo_year.getSelectedItem().toString() + "_" + combo_department.getSelectedItem().toString() + "_" + combo_section.getSelectedItem().toString();
                String att_table = tablename + "_att";

                String search = "select * from " + tablename + " where REGNO = '" + text_regno.getText() + "'";
                try {
                    ResultSet result = Sqlconn.getconnection().createStatement().executeQuery(search);
                    if (result.next()) {
                        text_name.setText(result.getString("Name"));
                        combo_gender.setSelectedItem(result.getObject("Gender"));
                        combo_department.setSelectedItem(result.getObject("Department"));
                        combo_year.setSelectedItem(result.getObject("Year"));
                        combo_section.setSelectedItem(result.getObject("Section"));
                        combo_mode.setSelectedItem(result.getObject("Mode"));
                        text_mail.setText(result.getString("MailID"));
                        text_contact.setText(result.getString("Contact"));
                    } else {
                        errorRecord();
                    }
                } catch (SQLException e1) {
                    System.out.println(e1);
                }

            }
        }


        if(e.getSource().equals(button_update)) {
            if (combo_department.getSelectedItem().toString().isEmpty() || combo_year.getSelectedItem().toString().isEmpty() || combo_section.getSelectedItem().toString().isEmpty()) {
                selectDetails();
            } else {

                String tablename = combo_year.getSelectedItem().toString() + "_" + combo_department.getSelectedItem().toString() + "_" + combo_section.getSelectedItem().toString();

                if (ID_PATTERN.matcher(text_regno.getText()).matches() && NAME_PATTERN.matcher(text_name.getText()).matches() &&

                        (MAIL_PATTERN.matcher(text_mail.getText()).matches() || MAIL_PATTERN1.matcher(text_mail.getText()).matches()) &&

                        CONTACT_PATTERN.matcher(text_contact.getText()).matches() && mandatory()) {
                    try {
                        PreparedStatement pstmt = Sqlconn.getconnection().prepareStatement("UPDATE `" + tablename + "` SET `Name`= ?,`Gender`= ? ,`Department`= ?,`Year`= ?,`Section` = ?,`Mode`= ?,`MailID`= ? ,`Contact`= ? WHERE `regno` = '" + text_regno.getText() + "'");
                        pstmt.setString(1, text_name.getText());
                        pstmt.setString(2, combo_gender.getSelectedItem().toString());
                        pstmt.setString(3, combo_department.getSelectedItem().toString());
                        pstmt.setString(4, combo_year.getSelectedItem().toString());
                        pstmt.setString(5, combo_section.getSelectedItem().toString());
                        pstmt.setString(6, combo_mode.getSelectedItem().toString());
                        pstmt.setString(7, combo_mode.getSelectedItem().toString());
                        pstmt.setString(8, text_contact.getText());
                        pstmt.executeUpdate();
                        success();
                        clearForm();
                    } catch (SQLException e1) {
                        System.out.println(e1);
                    }
                } else {
                    errorMandatory();
                }
            }
        }

        if(e.getSource().equals(button_save)) {
            if (combo_department.getSelectedItem().toString().isEmpty() || combo_year.getSelectedItem().toString().isEmpty() || combo_section.getSelectedItem().toString().isEmpty()) {
                selectDetails();
            } else {
                String tablename = combo_year.getSelectedItem().toString() + "_" + combo_department.getSelectedItem().toString() + "_" + combo_section.getSelectedItem().toString();
                String att_table = tablename + "_att";

                if (ID_PATTERN.matcher(text_regno.getText()).matches() && NAME_PATTERN.matcher(text_name.getText()).matches() &&

                        (MAIL_PATTERN.matcher(text_mail.getText()).matches() || MAIL_PATTERN1.matcher(text_mail.getText()).matches()) &&

                        CONTACT_PATTERN.matcher(text_contact.getText()).matches() && mandatory()) {
                    try {
                        PreparedStatement pstmt = con.prepareStatement("INSERT INTO `" + tablename + "`(`Regno`, `Name`, `Gender`,`Department`, `Year`, `Section`,`Mode` ,`MailID`, `Contact`) VALUES (?,?,?,?,?,?,?,?,?)");
                        pstmt.setString(1, text_regno.getText());
                        pstmt.setString(2, text_name.getText());
                        pstmt.setString(3, combo_gender.getSelectedItem().toString());
                        pstmt.setString(4, combo_department.getSelectedItem().toString());
                        pstmt.setString(5, combo_year.getSelectedItem().toString());
                        pstmt.setString(6, combo_section.getSelectedItem().toString());
                        pstmt.setString(7, combo_mode.getSelectedItem().toString());
                        pstmt.setString(8, text_mail.getText());
                        pstmt.setString(9, text_contact.getText());
                        pstmt.executeUpdate();

                        String sql = "ALTER TABLE " + att_table + " ADD " + text_regno.getText() + " Boolean DEFAULT 1";
                        Sqlconn.getconnection().createStatement().execute(sql);

                        success();
                        clearForm();
                    } catch (SQLException e1) {
                        System.out.println(e1);
                    }
                } else {
                    errorMandatory();
                }
            }
        }

        if(e.getSource().equals(button_delete)) {
            if (combo_department.getSelectedItem().toString().isEmpty() || combo_year.getSelectedItem().toString().isEmpty() || combo_section.getSelectedItem().toString().isEmpty()) {
                selectDetails();
            } else {
                String tablename = combo_year.getSelectedItem().toString() + "_" + combo_department.getSelectedItem().toString() + "_" + combo_section.getSelectedItem().toString();
                String att_table = tablename + "_att";

                try {
                    String delete = "DELETE FROM " + tablename + " WHERE REGNO = '" + text_regno.getText() + "'";
                    Sqlconn.getconnection().createStatement().execute(delete);
                    Sqlconn.getconnection().createStatement().execute("ALTER TABLE " + att_table + " drop " + text_regno.getText());
                    deleteSuccess();
                } catch (SQLException e1) {
                    System.out.println(e1);
                }
                clearForm();
            }
        }

        if(e.getSource().equals(button_import)) {
            if (combo_department.getSelectedItem().toString().isEmpty() || combo_year.getSelectedItem().toString().isEmpty() || combo_section.getSelectedItem().toString().isEmpty()) {
                selectDetails();
            } else {
                String tablename = combo_year.getSelectedItem().toString() + "_" + combo_department.getSelectedItem().toString() + "_" + combo_section.getSelectedItem().toString();
                String att_table = tablename + "_att";

                new datafromcsv(tablename,att_table);
            }
        }

        if(e.getSource().equals(button_clear)){
            clearForm();
        }

        if(e.getSource().equals(button_back)){
            frame.dispose();
            new AdminPage();
        }
    }
}
