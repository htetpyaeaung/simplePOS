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
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class TypeList extends JFrame
{
    JLabel lblName;
    JTextField text;
    JButton btn_search;
    JButton btn_showAll;
    JPanel p2;
    DefaultTableModel model;
    
    public TypeList() {
        this.lblName = new JLabel("Name");
        this.text = new JTextField(10);
        this.btn_search = new JButton("Search");
        this.btn_showAll = new JButton("Show All");
        this.p2 = new JPanel();
        this.model = new DefaultTableModel();
        this.setSize(500, 300);
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo(null);
        this.setTitle("Type Lists");
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        this.model.addColumn("ID");
        this.model.addColumn("Name");
        final JLabel label = new JLabel("Name:");
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
        this.btn_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String sql = "select * from type where name like '" + TypeList.this.text.getText() + "%'";
                TypeList.this.showData(sql);
            }
        });
        this.btn_showAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String sql = "select * from type";
                TypeList.this.showData(sql);
                TypeList.this.text.setText("");
            }
        });
        final String sql = "select * from type";
        this.showData(sql);
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
                final Object[] data = { rs.getInt(1), rs.getString(2) };
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
        final TypeList tList = new TypeList();
    }
}
