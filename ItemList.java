// 
// Decompiled by Procyon v0.5.36
// 

package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class ItemList extends JFrame
{
    JLabel lblName;
    JTextField txtName;
    JComboBox cbo_type;
    JButton btn_search;
    JButton btn_showAll;
    JPanel p2;
    DB obj;
    DefaultTableModel model;
    
    public ItemList() {
        this.lblName = new JLabel("Name");
        this.txtName = new JTextField(5);
        this.cbo_type = new JComboBox();
        this.btn_search = new JButton("Search");
        this.btn_showAll = new JButton("Show All");
        this.p2 = new JPanel();
        this.obj = new DB();
        this.model = new DefaultTableModel();
        this.setSize(500, 300);
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo(null);
        this.setTitle("Item Lists");
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        final JPanel outerPanel = new JPanel(new BorderLayout());
        final JPanel topPanel = new JPanel(new BorderLayout());
        final JLabel label = new JLabel("Name:");
        final JTable table = new JTable(this.model);
        this.add(label);
        this.add(this.cbo_type);
        this.add(this.btn_search);
        this.add(this.btn_showAll);
        this.add(table);
        this.model.addColumn("ID");
        this.model.addColumn("Name");
        this.model.addColumn("Type");
        this.model.addColumn("Price");
        outerPanel.add(table, "Center");
        this.add(outerPanel);
        this.getContentPane().add(new JScrollPane(table), "Center");
        this.showData("select * from item inner join type on type_id = type.id");
        this.showType();
        this.btn_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (ItemList.this.cbo_type.getSelectedIndex() <= 0) {
                    JOptionPane.showMessageDialog(null, "Enter Type");
                }
                else {
                    final String sql = "select * from item inner join type on type_id = type.id where type.name = '" + ItemList.this.cbo_type.getSelectedItem() + "'";
                    ItemList.this.showData(sql);
                }
            }
        });
        this.btn_showAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                ItemList.this.showData("select * from item inner join type on type_id = type.id");
                ItemList.this.cbo_type.setSelectedIndex(0);
            }
        });
        this.setVisible(true);
    }
    
    public void showData(final String sql) {
        if (this.model.getRowCount() > 0) {
            for (int j = this.model.getRowCount() - 1; j > -1; --j) {
                this.model.removeRow(j);
            }
        }
        final ResultSet rs = this.obj.selectData(sql);
        try {
            while (rs.next()) {
                final Object[] itemD = { rs.getInt(1), rs.getString(2), rs.getString(6), rs.getInt(4) };
                this.model.addRow(itemD);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void showType() {
        final String sql = "select * from type";
        final ResultSet rs = this.obj.selectData(sql);
        this.cbo_type.addItem("--Select--");
        try {
            while (rs.next()) {
                this.cbo_type.addItem(rs.getString(2));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(final String[] args) {
        final ItemList iList = new ItemList();
    }
}
