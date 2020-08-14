// 
// Decompiled by Procyon v0.5.36
// 

package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class UserCreate extends JFrame
{
    JPanel n;
    JButton btn_save;
    JButton btn_close;
    JComboBox cboType;
    JButton btn1;
    JButton btn2;
    JLabel label;
    JTextField txtField;
    int typeID;
    DB obj;
    
    public UserCreate() {
        this.n = new JPanel();
        this.btn_save = new JButton("Save");
        this.btn_close = new JButton("Close");
        this.cboType = new JComboBox();
        this.btn1 = new JButton("btn1");
        this.btn2 = new JButton("btn2");
        this.label = new JLabel("Type");
        this.txtField = new JTextField();
        this.typeID = 0;
        this.obj = new DB();
        this.setSize(300, 300);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setTitle("Item");
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        gbc.fill = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        final JLabel jl = new JLabel("Name");
        this.add(jl, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        this.add(this.txtField, gbc);
        gbc.fill = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        final JLabel jlc = new JLabel("Password");
        this.add(jlc, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        final JPasswordField txtPass = new JPasswordField();
        gbc.gridwidth = 1;
        this.add(txtPass, gbc);
        gbc.fill = 2;
        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(this.btn_save, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.btn_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                UserCreate.this.dispose();
            }
        });
        this.add(this.btn_close, gbc);
        this.btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (UserCreate.this.txtField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Name");
                }
                else if (txtPass.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Password");
                }
                else {
                    String sql = "select * from user where name = '" + UserCreate.this.txtField.getText() + "' AND password = '" + txtPass.getText() + "'";
                    final ResultSet rs = UserCreate.this.obj.selectData(sql);
                    try {
                        if (rs.next()) {
                            JOptionPane.showMessageDialog(null, "Duplicate Data");
                        }
                        else {
                            sql = "insert into user(name, password) values('" + UserCreate.this.txtField.getText() + "','" + txtPass.getText() + "')";
                            UserCreate.this.obj.saveData(sql);
                            JOptionPane.showMessageDialog(null, "Save Successfully");
                            UserCreate.this.txtField.setText("");
                            txtPass.setText("");
                        }
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
        this.setPreferredSize(this.getSize());
        this.setVisible(true);
    }
    
    public static void main(final String[] args) {
        final UserCreate uc = new UserCreate();
    }
}
