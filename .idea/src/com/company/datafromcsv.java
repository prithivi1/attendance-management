package com.company;

import java.sql.*;
import java.io.*;
import javax.swing.JFileChooser;

public class datafromcsv {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String query;
    String Path;
    int batchsize = 10;

    public datafromcsv(String table_name,String att_table) {

        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("choosertitle");
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                Path = chooser.getSelectedFile().toString();
            } else {
                System.out.println("No Selection ");
            }
            conn = Sqlconn.getconnection();
            conn.setAutoCommit(false);
            query = "insert into " + table_name + " (Regno,Name,Gender,Department,Year,Section,Mode,MailID,Contact,Password) values (?,?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(query);
            BufferedReader lineReader = new BufferedReader(new FileReader(Path));
            String lineText = null;
            int count = 0;
            lineReader.readLine();

            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String Regno = data[0];
                String Name = data[1];
                String Gender = data[2];
                String Department = data[3];
                String Year = data[4];
                String Section = data[5];
                String Mode = data[6];
                String MailID = data[7];
                String Contact = data[8];

                pst.setString(1, Regno);
                pst.setString(2, Name);
                pst.setString(3, Gender);
                pst.setString(4, Department);
                pst.setString(5, Year);
                pst.setString(6, Section);
                pst.setString(7, Mode);
                pst.setString(8, MailID);
                pst.setString(9, Contact);
                pst.setString(10,Contact);

                String sql = "ALTER TABLE " + att_table + " ADD " + Regno + " Boolean DEFAULT 1";
                Sqlconn.getconnection().createStatement().execute(sql);

                pst.addBatch();
                if (count % batchsize == 0) {
                    pst.executeBatch();
                }
            }

            lineReader.close();
            conn.commit();
            conn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
