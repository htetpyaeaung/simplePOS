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
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class ItemCreate extends JFrame
{
    JPanel n;
    JButton btn_save;
    JButton btn_close;
    JComboBox cboType;
    JButton btn1;
    JButton btn2;
    JLabel label;
    int typeID;
    DB obj;
    
    public ItemCreate() {
        this.n = new JPanel();
        this.btn_save = new JButton("Save");
        this.btn_close = new JButton("Close");
        this.cboType = new JComboBox();
        this.btn1 = new JButton("btn1");
        this.btn2 = new JButton("btn2");
        this.label = new JLabel("Type");
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
        final JLabel jl = new JLabel("Type");
        this.add(jl, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        this.add(this.cboType, gbc);
        gbc.fill = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        final JLabel jlb = new JLabel("Name");
        this.add(jlb, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        final JTextField txtField = new JTextField();
        gbc.gridwidth = 1;
        this.add(txtField, gbc);
        gbc.fill = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        final JLabel jlc = new JLabel("Price");
        this.add(jlc, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        final JTextField txtPrice = new JTextField();
        gbc.gridwidth = 1;
        this.add(txtPrice, gbc);
        gbc.fill = 2;
        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(this.btn_save, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.btn_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                ItemCreate.this.dispose();
            }
        });
        this.add(this.btn_close, gbc);
        this.cboType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (ItemCreate.this.cboType.getSelectedIndex() > 0) {
                    final String sql = "select * from type where name = '" + ItemCreate.this.cboType.getSelectedItem() + "'";
                    final ResultSet rs = ItemCreate.this.obj.selectData(sql);
                    try {
                        rs.next();
                        ItemCreate.this.typeID = rs.getInt(1);
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
                else {
                    ItemCreate.this.typeID = 0;
                }
            }
        });
        this.showType();
        this.btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (ItemCreate.this.cboType.getSelectedIndex() > 0) {
                    if (!txtField.getText().equals("")) {
                        if (!txtPrice.getText().equals("")) {
                            boolean numeric = true;
                            numeric = txtPrice.getText().matches("-?\\d+(\\.\\d+)?");
                            if (numeric) {
                                String sql = "select * from item where name = '" + txtField.getText() + "'";
                                final ResultSet rs = ItemCreate.this.obj.selectData(sql);
                                try {
                                    rs.last();
                                    final int size = rs.getRow();
                                    if (size > 0) {
                                        JOptionPane.showMessageDialog(null, "Duplicate Data");
                                    }
                                    else {
                                        sql = "insert into item(name,type_id,price) values('" + txtField.getText() + "'," + ItemCreate.this.typeID + "," + txtPrice.getText() + ")";
                                        ItemCreate.this.obj.saveData(sql);
                                        txtField.setText("");
                                        txtPrice.setText("");
                                        ItemCreate.this.cboType.setSelectedIndex(0);
                                        ItemCreate.this.typeID = 0;
                                        JOptionPane.showMessageDialog(null, "Save Successfully");
                                    }
                                }
                                catch (SQLException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Enter Number");
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Enter Price");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Enter Name");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Choose ID");
                }
            }
        });
        this.setPreferredSize(this.getSize());
        this.setVisible(true);
    }
    
    public void showType() {
        final String sql = "select * from type";
        final ResultSet rs = this.obj.selectData(sql);
        this.cboType.removeAllItems();
        this.cboType.addItem("--Select--");
        try {
            while (rs.next()) {
                this.cboType.addItem(rs.getString(2));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(final String[] args) {
        final ItemCreate cre = new ItemCreate();
    }
}
