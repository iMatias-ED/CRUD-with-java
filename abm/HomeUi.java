package abm;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;

public class HomeUi extends JFrame implements ActionListener{
    UsersUi ui_users;
    CustomersUi ui_customers;
    ProfilesUi ui_profiles;
    ProductsUi ui_products;
    JLabel title;
    JButton bt_users, bt_customers, bt_profiles, bt_products;    

    public HomeUi(){
        //title
        title = new JLabel("SUPERMERCADO");
        title.setBounds(25,30,480,70);
        title.setFont(new Font("Arial", Font.PLAIN, 50));
        //buttons creation
        bt_users = new JButton("ABM USUARIOS");
        bt_customers = new JButton("ABM CLIENTES");
        bt_profiles = new JButton("ABM PERFILES");
        bt_products = new JButton("ABM PRODUCTOS");
        //buttons size and position
        bt_users.setBounds(150, 150, 200, 70);
        bt_customers.setBounds(150, 230, 200, 70);
        bt_profiles.setBounds(150, 310, 200, 70);
        bt_products.setBounds(150, 390, 200, 70);
        //add ActionListener
        bt_users.addActionListener(this);
        bt_customers.addActionListener(this);
        bt_profiles.addActionListener(this);
        bt_products.addActionListener(this);

        //add widgets
        add(title);
        add(bt_users);
        add(bt_customers);
        add(bt_profiles);
        add(bt_products);

        //window config
        setLayout(null);
        setBounds(0,0,500,550);
        setLocationRelativeTo(null);
        setVisible(true);
    }

            //
    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(bt_customers)){
            ui_customers = new CustomersUi();
            ui_customers.setVisible(true);
        }
        if (e.getSource().equals(bt_products)){
            ui_products = new ProductsUi();
            ui_products.setVisible(true);
        }
        if (e.getSource().equals(bt_profiles)){
            ui_profiles = new ProfilesUi();
            ui_profiles.setVisible(true);
        }
        if (e.getSource().equals(bt_users)){
            ui_users = new UsersUi();
            ui_users.setVisible(true);
        }

    }
}
