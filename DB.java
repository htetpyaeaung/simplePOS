// 
// Decompiled by Procyon v0.5.36
// 

package test;

import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.Connection;

public class DB
{
    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            final Connection con = DriverManager.getConnection("jdbc:mysql://192.168.64.2:3306/kfc", "hoho", "moko");
            return con;
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public void saveData(final String sql) {
        try {
            final Connection con = this.connect();
            final Statement stmt = con.createStatement();
            final int i = stmt.executeUpdate(sql);
            if (i <= 0) {
                JOptionPane.showMessageDialog(null, "Error in save data");
            }
            con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public ResultSet selectData(final String sql) {
        try {
            final Connection con = this.connect();
            final Statement stmt = con.createStatement();
            final ResultSet rs = stmt.executeQuery(sql);
            return rs;
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
