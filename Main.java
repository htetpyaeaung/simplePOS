// 
// Decompiled by Procyon v0.5.36
// 

package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Main extends JFrame
{
    JPanel p1;
    JMenuBar mnu;
    JMenu mnu_file;
    JMenu mnu_entry;
    JMenu mnu_update;
    JMenu mnu_delete;
    JMenu mnu_list;
    JMenuItem mnu_file_exit;
    JMenuItem mnu_file_sale;
    JMenuItem mnu_entry_type;
    JMenuItem mnu_entry_item;
    JMenuItem mnu_update_type;
    JMenuItem mnu_update_item;
    JMenuItem mnu_delete_type;
    JMenuItem mnu_delete_item;
    JMenuItem mnu_list_type;
    JMenuItem mnu_list_item;
    JMenuItem mnu_list_sale;
    
    public Main() {
        this.p1 = new JPanel();
        this.mnu = new JMenuBar();
        this.mnu_file = new JMenu("File");
        this.mnu_entry = new JMenu("Entry");
        this.mnu_update = new JMenu("Update");
        this.mnu_delete = new JMenu("Delete");
        this.mnu_list = new JMenu("List");
        this.mnu_file_exit = new JMenuItem("Exit");
        this.mnu_file_sale = new JMenuItem("Sale");
        this.mnu_entry_type = new JMenuItem("Type");
        this.mnu_entry_item = new JMenuItem("Item");
        this.mnu_update_type = new JMenuItem("Type");
        this.mnu_update_item = new JMenuItem("Item");
        this.mnu_delete_type = new JMenuItem("Type");
        this.mnu_delete_item = new JMenuItem("Item");
        this.mnu_list_type = new JMenuItem("Type");
        this.mnu_list_item = new JMenuItem("Item");
        this.mnu_list_sale = new JMenuItem("Sale");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(3);
        this.setTitle("KFC SALE POS SYSTEM");
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.mnu.add(this.mnu_file);
        this.mnu.add(this.mnu_entry);
        this.mnu.add(this.mnu_update);
        this.mnu.add(this.mnu_delete);
        this.mnu.add(this.mnu_list);
        this.mnu_file.add(this.mnu_file_exit);
        this.mnu_file.add(this.mnu_file_sale);
        this.mnu_entry.add(this.mnu_entry_type);
        this.mnu_entry.add(this.mnu_entry_item);
        this.mnu_update.add(this.mnu_update_type);
        this.mnu_update.add(this.mnu_update_item);
        this.mnu_delete.add(this.mnu_delete_type);
        this.mnu_delete.add(this.mnu_delete_item);
        this.mnu_list.add(this.mnu_list_type);
        this.mnu_list.add(this.mnu_list_item);
        this.mnu_list.add(this.mnu_list_sale);
        final Image img = Toolkit.getDefaultToolkit().getImage("KFC.jpg");
        this.setContentPane(new JPanel() {
            public void paintComponent(final Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        });
        this.mnu_entry_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final TypeCreate tc = new TypeCreate();
            }
        });
        this.mnu_update_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final TypeUpdate tu = new TypeUpdate();
            }
        });
        this.mnu_delete_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final TypeDelete td = new TypeDelete();
            }
        });
        this.mnu_list_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final TypeList tl = new TypeList();
            }
        });
        this.mnu_entry_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final ItemCreate tc = new ItemCreate();
            }
        });
        this.mnu_update_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final ItemUpdate tu = new ItemUpdate();
            }
        });
        this.mnu_delete_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final ItemDelete td = new ItemDelete();
            }
        });
        this.mnu_list_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final ItemList tl = new ItemList();
            }
        });
        this.mnu_list_sale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final SaleList sl = new SaleList();
            }
        });
        this.mnu_file_sale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final Sale s = new Sale();
            }
        });
        this.add(this.mnu, "North");
        this.setVisible(true);
    }
    
    public static void main(final String[] args) {
        final Main mNu = new Main();
    }
}
