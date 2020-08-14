// 
// Decompiled by Procyon v0.5.36
// 

package test;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class SaleList extends JFrame
{
    JLabel lblName;
    JComboBox text;
    JButton btn_search;
    JButton btn_showAll;
    JPanel p2;
    DefaultTableModel model;
    DB obj;
    
    public SaleList() {
        this.lblName = new JLabel("Name");
        this.text = new JComboBox();
        this.btn_search = new JButton("Search");
        this.btn_showAll = new JButton("Show All");
        this.p2 = new JPanel();
        this.model = new DefaultTableModel();
        this.obj = new DB();
        this.setSize(500, 300);
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo(null);
        this.setTitle("Type Lists");
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        this.model.addColumn("Sale ID");
        this.model.addColumn("Date");
        this.model.addColumn("Name");
        this.model.addColumn("Price");
        this.model.addColumn("Qty");
        final JLabel label = new JLabel("Date:");
        final JTable table = new JTable(this.model) {
            private static final long serialVersionUID = 1L;
            
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };
        this.add(label);
        this.add(this.text);
        this.add(this.btn_search);
        this.add(this.btn_showAll);
        this.add(table);
        this.getContentPane().add(new JScrollPane(table), "Center");
        String sql = "SELECT * FROM `sale_detail` INNER JOIN `sale` on sale_id = sale.id INNER JOIN `item` on item_id = item.id";
        this.showData(sql);
        this.text.addItem("--Select--");
        sql = "select distinct date from sale";
        final ResultSet rs = this.obj.selectData(sql);
        try {
            while (rs.next()) {
                this.text.addItem(rs.getString(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        this.btn_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (SaleList.this.text.getSelectedIndex() <= 0) {
                    JOptionPane.showMessageDialog(null, "Choose Date");
                }
                else {
                    final String sql = "SELECT * FROM `sale_detail` INNER JOIN `sale` on sale_id = sale.id INNER JOIN `item` on item_id = item.id where date = '" + SaleList.this.text.getSelectedItem() + "'";
                    SaleList.this.showData(sql);
                }
            }
        });
        this.btn_showAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String sql = "SELECT * FROM `sale_detail` INNER JOIN `sale` on sale_id = sale.id INNER JOIN `item` on item_id = item.id";
                SaleList.this.showData(sql);
                SaleList.this.text.setSelectedIndex(0);
            }
        });
        this.setVisible(true);
    }
    
    public void showData(final String sql) {
        final DB obj = new DB();
        final ResultSet rs = obj.selectData(sql);
        if (this.model.getRowCount() > 0) {
            for (int j = this.model.getRowCount() - 1; j > -1; --j) {
                this.model.removeRow(j);
            }
        }
        try {
            while (rs.next()) {
                final Object[] data = { rs.getInt(2), rs.getString(7), rs.getString(10), rs.getInt(5), rs.getInt(4) };
                this.model.addRow(data);
            }
            if (this.model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "No Results");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(final String[] args) {
        final SaleList sList = new SaleList();
    }
}
