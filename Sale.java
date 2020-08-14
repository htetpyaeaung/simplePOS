// 
// Decompiled by Procyon v0.5.36
// 

package test;

import java.time.temporal.TemporalAccessor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Sale extends JFrame
{
    JPanel p1;
    JPanel p2;
    JPanel p3;
    JButton btn_remove;
    JButton btn_add;
    JLabel lbl;
    JLabel lbl1;
    JLabel lbl2;
    JComboBox cbo_type;
    JComboBox cbo_item;
    JTextField txt_qty;
    JLabel lbl4;
    JTextField txt_total;
    JButton btn_save;
    DB obj;
    int typeID;
    int itemID;
    int price;
    int total;
    DefaultTableModel model;
    
    public Sale() {
        this.p1 = new JPanel();
        this.p2 = new JPanel();
        this.p3 = new JPanel();
        this.btn_remove = new JButton("Remove");
        this.btn_add = new JButton("Add");
        this.lbl = new JLabel("Type");
        this.lbl1 = new JLabel("Item");
        this.lbl2 = new JLabel("Qty");
        this.cbo_type = new JComboBox();
        this.cbo_item = new JComboBox();
        this.txt_qty = new JTextField(10);
        this.lbl4 = new JLabel("Total");
        this.txt_total = new JTextField(20);
        this.btn_save = new JButton("Save");
        this.obj = new DB();
        this.typeID = 0;
        this.itemID = 0;
        this.price = 0;
        this.total = 0;
        this.model = new DefaultTableModel();
        this.setSize(700, 300);
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo(null);
        this.setTitle("Sale");
        this.setLayout(new BorderLayout());
        this.p1.setLayout(new FlowLayout());
        this.p1.add(this.lbl);
        this.p1.add(this.cbo_type);
        this.p1.add(this.lbl1);
        this.p1.add(this.cbo_item);
        this.p1.add(this.lbl2);
        this.p1.add(this.txt_qty);
        this.p1.add(this.btn_add);
        this.p1.add(this.btn_remove);
        this.model.addColumn("Item ID");
        this.model.addColumn("Name");
        this.model.addColumn("Qty");
        this.model.addColumn("Price");
        final JTable table = new JTable(this.model);
        this.p2.add(table);
        final JScrollPane pane = new JScrollPane(table);
        this.p3.setLayout(new FlowLayout());
        this.p3.add(this.lbl4);
        this.p3.add(this.txt_total);
        this.p3.add(this.btn_save);
        this.add(this.p1, "North");
        this.add(pane, "Center");
        this.add(this.p3, "South");
        this.showType();
        this.cbo_item.addItem("--Select--");
        this.cbo_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (Sale.this.cbo_type.getSelectedIndex() <= 0) {
                    Sale.this.cbo_item.removeAllItems();
                    Sale.this.cbo_item.addItem("--Select--");
                    Sale.this.txt_qty.setText("");
                }
                else {
                    String sql = "select * from type where name = '" + Sale.this.cbo_type.getSelectedItem() + "'";
                    final ResultSet rs = Sale.this.obj.selectData(sql);
                    try {
                        rs.next();
                        Sale.this.typeID = rs.getInt(1);
                        sql = "select * from item where type_id = " + Sale.this.typeID;
                        Sale.this.showItem(sql);
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
        this.btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (Sale.this.cbo_type.getSelectedIndex() <= 0) {
                    JOptionPane.showMessageDialog(null, "Choose Type");
                }
                else if (Sale.this.cbo_item.getSelectedIndex() <= 0) {
                    JOptionPane.showMessageDialog(null, "Choose Item");
                }
                else if (Sale.this.txt_qty.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter qty");
                }
                else {
                    boolean numeric = true;
                    numeric = Sale.this.txt_qty.getText().matches("-?\\d+(\\.\\d+)?");
                    if (!numeric) {
                        JOptionPane.showMessageDialog(null, "Enter numbers");
                    }
                    else {
                        boolean add = true;
                        for (int i = 0; i < Sale.this.model.getRowCount(); ++i) {
                            if (Integer.parseInt(Sale.this.model.getValueAt(i, 0).toString()) == Sale.this.itemID) {
                                int oldQty = Integer.parseInt(Sale.this.model.getValueAt(i, 2).toString());
                                oldQty += Integer.parseInt(Sale.this.txt_qty.getText());
                                Sale.this.model.setValueAt(new Integer(oldQty), i, 2);
                                add = false;
                                final Sale this$0 = Sale.this;
                                this$0.total += Integer.parseInt(Sale.this.txt_qty.getText()) * Sale.this.price;
                                Sale.this.txt_total.setText(String.valueOf(Sale.this.total));
                                Sale.this.cbo_type.setSelectedIndex(0);
                                Sale.this.cbo_item.setSelectedIndex(0);
                                Sale.this.txt_qty.setText("");
                            }
                        }
                        if (add) {
                            final Object[] saleD = { Sale.this.itemID, Sale.this.cbo_item.getSelectedItem(), Sale.this.txt_qty.getText(), Sale.this.price };
                            Sale.this.model.addRow(saleD);
                            final Sale this$2 = Sale.this;
                            this$2.total += Integer.parseInt(Sale.this.txt_qty.getText()) * Sale.this.price;
                            Sale.this.txt_total.setText(String.valueOf(Sale.this.total));
                            Sale.this.cbo_type.setSelectedIndex(0);
                            Sale.this.cbo_item.setSelectedIndex(0);
                            Sale.this.txt_qty.setText("");
                        }
                    }
                }
            }
        });
        this.btn_remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (Sale.this.model.getRowCount() <= 0) {
                    JOptionPane.showMessageDialog(null, "No Records");
                }
                else {
                    int index = 0;
                    Sale.this.total = 0;
                    index = table.getSelectedRow();
                    Sale.this.model.removeRow(index);
                    for (int i = 0; i < Sale.this.model.getRowCount(); ++i) {
                        final Sale this$0 = Sale.this;
                        this$0.total += Integer.parseInt(Sale.this.model.getValueAt(i, 2).toString()) * Integer.parseInt(Sale.this.model.getValueAt(i, 3).toString());
                    }
                    Sale.this.txt_total.setText(String.valueOf(Sale.this.total));
                }
            }
        });
        this.btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (Sale.this.model.getRowCount() <= 0) {
                    JOptionPane.showMessageDialog(null, "No Records");
                }
                else {
                    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    final LocalDateTime now = LocalDateTime.now();
                    final String date = dtf.format(now);
                    int sale_id = 0;
                    String sql = "insert into sale(date, total) values('" + date + "'," + Sale.this.total + ")";
                    Sale.this.obj.saveData(sql);
                    sql = "select max(id) from sale";
                    final ResultSet rs = Sale.this.obj.selectData(sql);
                    try {
                        rs.next();
                        sale_id = rs.getInt(1);
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                    for (int i = 0; i < Sale.this.model.getRowCount(); ++i) {
                        sql = "insert into sale_detail(sale_id, item_id, qty, price) values(" + sale_id + "," + Integer.parseInt(Sale.this.model.getValueAt(i, 0).toString()) + "," + Integer.parseInt(Sale.this.model.getValueAt(i, 2).toString()) + "," + Integer.parseInt(Sale.this.model.getValueAt(i, 3).toString()) + ")";
                        Sale.this.obj.saveData(sql);
                    }
                    JOptionPane.showMessageDialog(null, "Save Successfully");
                    if (Sale.this.model.getRowCount() > 0) {
                        for (int j = Sale.this.model.getRowCount() - 1; j > -1; --j) {
                            Sale.this.model.removeRow(j);
                        }
                    }
                    Sale.this.txt_total.setText("");
                    Sale.this.total = 0;
                }
            }
        });
        this.cbo_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (Sale.this.cbo_item.getSelectedIndex() <= 0) {
                    Sale.this.itemID = 0;
                    Sale.this.price = 0;
                }
                else {
                    final String sql = "select * from item where name = '" + Sale.this.cbo_item.getSelectedItem() + "'";
                    final ResultSet rs = Sale.this.obj.selectData(sql);
                    try {
                        rs.next();
                        Sale.this.itemID = rs.getInt(1);
                        Sale.this.price = rs.getInt(4);
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
        this.setVisible(true);
    }
    
    public void showType() {
        this.cbo_type.removeAllItems();
        this.cbo_type.addItem("--Select--");
        final String sql = "select * from type";
        final ResultSet rs = this.obj.selectData(sql);
        try {
            while (rs.next()) {
                this.cbo_type.addItem(rs.getString(2));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void showItem(final String sql) {
        this.cbo_item.removeAllItems();
        this.cbo_item.addItem("--Select--");
        final ResultSet rs = this.obj.selectData(sql);
        try {
            while (rs.next()) {
                this.cbo_item.addItem(rs.getString(2));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(final String[] args) {
        final Sale nSale = new Sale();
    }
}
