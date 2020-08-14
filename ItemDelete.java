// 
// Decompiled by Procyon v0.5.36
// 

package test;

import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class ItemDelete extends JFrame
{
    JPanel n;
    JButton btn_delete;
    JButton btn_close;
    JTextField lblType;
    JComboBox cboItemID;
    DB obj;
    int typeID;
    
    public ItemDelete() {
        this.n = new JPanel();
        this.btn_delete = new JButton("Delete");
        this.btn_close = new JButton("Close");
        this.lblType = new JTextField();
        this.cboItemID = new JComboBox();
        this.obj = new DB();
        this.typeID = 0;
        this.setSize(300, 300);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setTitle("Item");
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.lblType.setEditable(false);
        gbc.fill = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        final JLabel jl1 = new JLabel("ID");
        this.add(jl1, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        this.add(this.cboItemID, gbc);
        gbc.fill = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        final JLabel jl2 = new JLabel("Type");
        this.add(jl2, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        this.add(this.lblType, gbc);
        gbc.fill = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        final JLabel jlb = new JLabel("Name");
        this.add(jlb, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        final JTextField txtField = new JTextField();
        gbc.gridwidth = 1;
        this.add(txtField, gbc);
        gbc.fill = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        final JLabel jlc = new JLabel("Price");
        this.add(jlc, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        final JTextField txtPrice = new JTextField();
        gbc.gridwidth = 1;
        this.add(txtPrice, gbc);
        gbc.fill = 2;
        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(this.btn_delete, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        this.btn_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                ItemDelete.this.dispose();
            }
        });
        this.add(this.btn_close, gbc);
        this.showItem();
        this.cboItemID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (ItemDelete.this.cboItemID.getSelectedIndex() > 0) {
                    final String sql = "select * from item inner join type on type_id = type.id where item.id = " + ItemDelete.this.cboItemID.getSelectedItem();
                    final ResultSet rs = ItemDelete.this.obj.selectData(sql);
                    try {
                        rs.next();
                        txtField.setText(rs.getString(2));
                        txtPrice.setText(rs.getString(4));
                        ItemDelete.this.lblType.setText(rs.getString(6));
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
                else {
                    txtField.setText("");
                    txtPrice.setText("");
                    ItemDelete.this.lblType.setText("");
                }
            }
        });
        this.btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (ItemDelete.this.cboItemID.getSelectedIndex() <= 0) {
                    JOptionPane.showMessageDialog(null, "Choose ID");
                }
                else if (JOptionPane.showConfirmDialog(null, "Delete Confirm", "Delete Confirm", 0) == 0) {
                    final String sql = "delete from item where id = " + ItemDelete.this.cboItemID.getSelectedItem();
                    ItemDelete.this.obj.saveData(sql);
                    JOptionPane.showMessageDialog(null, "Delete Successfully");
                    ItemDelete.this.showItem();
                }
            }
        });
        this.setPreferredSize(this.getSize());
        this.setVisible(true);
    }
    
    public void showItem() {
        final String sql = "select * from item inner join type on type_id = type.id order by item.id";
        final ResultSet rs = this.obj.selectData(sql);
        this.cboItemID.removeAllItems();
        this.cboItemID.addItem("--Select--");
        try {
            while (rs.next()) {
                this.cboItemID.addItem(rs.getInt(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(final String[] args) {
        final ItemDelete delete = new ItemDelete();
    }
}
