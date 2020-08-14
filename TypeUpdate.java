// 
// Decompiled by Procyon v0.5.36
// 

package test;

import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TypeUpdate extends JFrame
{
    JButton btn_update;
    JButton btn_close;
    JComboBox cbo_id;
    DB obj;
    
    public TypeUpdate() {
        this.btn_update = new JButton("Update");
        this.btn_close = new JButton("Close");
        this.cbo_id = new JComboBox();
        this.obj = new DB();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setTitle("Update Type");
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        gbc.fill = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        final JLabel jli = new JLabel("ID");
        this.add(jli, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        this.add(this.cbo_id, gbc);
        gbc.fill = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        final JLabel jl = new JLabel("Type");
        this.add(jl, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        final JTextField txtField = new JTextField();
        gbc.gridwidth = 1;
        this.add(txtField, gbc);
        gbc.fill = 2;
        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(this.btn_update, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.btn_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                TypeUpdate.this.dispose();
            }
        });
        this.add(this.btn_close, gbc);
        final String sql = "select * from type";
        final ResultSet rs = this.obj.selectData(sql);
        try {
            this.cbo_id.addItem("--Select--");
            while (rs.next()) {
                this.cbo_id.addItem(rs.getString(1));
            }
        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
        this.cbo_id.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (TypeUpdate.this.cbo_id.getSelectedIndex() > 0) {
                    final String sql = "select * from type where id = " + TypeUpdate.this.cbo_id.getSelectedItem();
                    final ResultSet rs = TypeUpdate.this.obj.selectData(sql);
                    try {
                        rs.next();
                        txtField.setText(rs.getString(2));
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
                else {
                    txtField.setText("");
                }
            }
        });
        this.btn_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (TypeUpdate.this.cbo_id.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Choose ID");
                }
                else if (txtField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Type :-P");
                }
                else {
                    String sql = "Select * from type where name = '" + txtField.getText() + "'";
                    final ResultSet rs = TypeUpdate.this.obj.selectData(sql);
                    try {
                        rs.next();
                        final int id = rs.getInt(1);
                        rs.last();
                        final int size = rs.getRow();
                        if (size > 0 && id != Integer.parseInt(TypeUpdate.this.cbo_id.getSelectedItem().toString())) {
                            JOptionPane.showMessageDialog(null, "Duplicate Data");
                        }
                        else {
                            sql = "update type set name = '" + txtField.getText() + "' where id =" + TypeUpdate.this.cbo_id.getSelectedItem();
                            TypeUpdate.this.obj.saveData(sql);
                            JOptionPane.showMessageDialog(null, "Successfully Update");
                            txtField.setText("");
                            TypeUpdate.this.cbo_id.setSelectedIndex(0);
                        }
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
        this.setSize(300, 300);
        this.setPreferredSize(this.getSize());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
    }
    
    public static void main(final String[] args) {
        final TypeUpdate update = new TypeUpdate();
    }
}
