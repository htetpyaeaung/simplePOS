// 
// Decompiled by Procyon v0.5.36
// 

package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class TypeCreate extends JFrame
{
    JPanel n;
    JButton btn_save;
    JButton btn_close;
    
    public TypeCreate() {
        this.n = new JPanel();
        this.btn_save = new JButton("Save");
        this.btn_close = new JButton("Close");
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setTitle("Type");
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        gbc.fill = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        final JLabel jl = new JLabel("Type");
        this.add(jl, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        final JTextField txtField = new JTextField();
        gbc.gridwidth = 1;
        this.add(txtField, gbc);
        gbc.fill = 2;
        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(this.btn_save, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.btn_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                TypeCreate.this.dispose();
            }
        });
        this.btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (txtField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter type");
                    txtField.requestFocus();
                }
                else {
                    final DB obj = new DB();
                    String sql = "select * from type where name = '" + txtField.getText() + "'";
                    final ResultSet rs = obj.selectData(sql);
                    try {
                        rs.last();
                        final int size = rs.getRow();
                        if (size > 0) {
                            JOptionPane.showMessageDialog(null, "Duplicate Data");
                        }
                        else {
                            sql = "insert into type(name) values('" + txtField.getText() + "')";
                            obj.saveData(sql);
                            JOptionPane.showMessageDialog(null, "Save Successfully");
                            txtField.setText("");
                            txtField.requestFocus();
                        }
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
        this.add(this.btn_close, gbc);
        this.setSize(300, 300);
        this.setPreferredSize(this.getSize());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
    }
    
    public static void main(final String[] args) {
        final TypeCreate p = new TypeCreate();
    }
}
