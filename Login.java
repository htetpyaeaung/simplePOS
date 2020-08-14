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
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Login extends JFrame implements Runnable
{
    JPanel n;
    JButton btn_login;
    JButton btn_close;
    JComboBox cboType;
    static Login l;
    JPanel p1;
    JPanel p2;
    JButton btn1;
    JButton btn2;
    JProgressBar pb;
    JLabel label;
    JTextField txtField;
    int typeID;
    DB obj;
    
    public Login() {
        this.n = new JPanel();
        this.btn_login = new JButton("Login");
        this.btn_close = new JButton("Close");
        this.cboType = new JComboBox();
        this.p1 = new JPanel();
        this.p2 = new JPanel();
        this.btn1 = new JButton("btn1");
        this.btn2 = new JButton("btn2");
        this.pb = new JProgressBar();
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
        this.setLayout(new CardLayout());
        this.p1.setLayout(layout);
        gbc.fill = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        final JLabel jl = new JLabel("Name");
        this.p1.add(jl, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        this.p1.add(this.txtField, gbc);
        gbc.fill = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        final JLabel jlc = new JLabel("Password");
        this.p1.add(jlc, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        final JPasswordField txtPass = new JPasswordField();
        gbc.gridwidth = 1;
        this.p1.add(txtPass, gbc);
        gbc.fill = 2;
        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.p1.add(this.btn_login, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.btn_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                Login.this.dispose();
            }
        });
        this.p1.add(this.btn_close, gbc);
        this.add(this.p1);
        this.add(this.p2);
        this.p1.setVisible(false);
        this.p2.add(this.pb);
        final GridBagLayout playout = new GridBagLayout();
        final GridBagConstraints cons = new GridBagConstraints();
        playout.addLayoutComponent(this.pb, cons);
        this.p2.setLayout(playout);
        this.btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (Login.this.txtField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Name");
                }
                else if (txtPass.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Password");
                }
                else {
                    final String sql = "select * from user where name = '" + Login.this.txtField.getText() + "' AND password = '" + txtPass.getText() + "'";
                    final ResultSet rs = Login.this.obj.selectData(sql);
                    try {
                        if (rs.next()) {
                            Login.this.p1.setVisible(false);
                            Login.this.p2.setVisible(true);
                            final Thread tr = new Thread(Login.l);
                            tr.start();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Invalid User and password");
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
        Login.l = new Login();
    }
    
    @Override
    public void run() {
        for (int i = 0; i <= 100; ++i) {
            try {
                this.pb.setValue(i);
                Thread.sleep(100L);
                if (i == 100) {
                    final Main m = new Main();
                    this.dispose();
                }
            }
            catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }
}
