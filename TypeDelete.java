// 
// Decompiled by Procyon v0.5.36
// 

package test;

import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class TypeDelete extends JFrame
{
    JLabel lbl;
    JComboBox cbo_id;
    JLabel lbl1;
    JTextField txtType;
    JButton btn_delete;
    JButton btn_close;
    DB obj;
    
    public TypeDelete() {
        this.lbl = new JLabel("ID");
        this.cbo_id = new JComboBox();
        this.lbl1 = new JLabel("Type");
        this.txtType = new JTextField();
        this.btn_delete = new JButton("Delete");
        this.btn_close = new JButton("Close");
        this.obj = new DB();
        this.setSize(300, 300);
        this.setDefaultCloseOperation(3);
        this.setTitle("Delete");
        this.setLocationRelativeTo(null);
        final GridBagConstraints gbc = new GridBagConstraints();
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
        this.add(this.btn_delete, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.btn_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                TypeDelete.this.dispose();
            }
        });
        this.add(this.btn_close, gbc);
        this.showData();
        this.cbo_id.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (TypeDelete.this.cbo_id.getSelectedIndex() > 0) {
                    final String sql = "select * from type where id = " + TypeDelete.this.cbo_id.getSelectedItem();
                    final ResultSet rs = TypeDelete.this.obj.selectData(sql);
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
        this.btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (TypeDelete.this.cbo_id.getSelectedIndex() > 0) {
                    if (JOptionPane.showConfirmDialog(null, "Sure Delete?", "Delete", 0) == 0) {
                        final String sql = "delete from type where id = " + TypeDelete.this.cbo_id.getSelectedItem();
                        TypeDelete.this.obj.saveData(sql);
                        JOptionPane.showMessageDialog(null, "Delete Successfully");
                        TypeDelete.this.showData();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Choose IDDDD");
                }
            }
        });
        this.setVisible(true);
    }
    
    public void showData() {
        final String sql = "select * from type";
        final ResultSet rs = this.obj.selectData(sql);
        this.cbo_id.removeAllItems();
        this.cbo_id.addItem("--Select--");
        try {
            while (rs.next()) {
                this.cbo_id.addItem(rs.getInt(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(final String[] args) {
        final TypeDelete delete = new TypeDelete();
    }
}
