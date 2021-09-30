package clase_15;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class MainUi extends JFrame implements ActionListener{
    static connectDB db;
    static JButton bt_getData; 
    static JButton bt_insert; 
    static JButton bt_update; 
    static JButton bt_delete; 

    static JLabel lb_id, lb_user;
    static JTextField tx_id, tx_user;

    static JPanel bt_panel;
    static JPanel tx_panel;

    public MainUi(){
        db = new connectDB();
        /*
        -  Button panel
        */

        //Create Buttons
        bt_getData = new JButton("Leer datos");
        bt_getData.addActionListener(this);
        bt_insert = new JButton("Insertar");
        bt_insert.addActionListener(this);
        bt_update = new JButton("Actualizar");
        bt_update.addActionListener(this);
        bt_delete = new JButton("Eliminar");
        bt_delete.addActionListener(this);

        //Create Buttons panel
        bt_panel = new JPanel();
        bt_panel.setLayout(null);
        bt_panel.setBackground(Color.cyan);
        bt_panel.setBounds(10,10,460,250);
        
        //Adding buttons to bt_panel
        bt_panel.add(bt_getData);
        bt_getData.setBounds(10, 10, 420, 50);
        bt_panel.add(bt_insert);
        bt_insert.setBounds(10, 70, 420, 50);
        bt_panel.add(bt_update);
        bt_update.setBounds(10, 130, 420, 50);
        bt_panel.add(bt_delete);
        bt_delete.setBounds(10, 190, 420, 50);
        
        /*
        - Inputs panel
        */
        lb_id = new JLabel("ID");
        lb_user = new JLabel("USER");
        tx_id = new JTextField();
        tx_user = new JTextField();

        //create inputs panel
        tx_panel = new JPanel();
        tx_panel.setLayout(null);
        tx_panel.setBackground(Color.GRAY);
        tx_panel.setBounds(10, 270, 460, 170);

        //adding inputs and labels to tx_panel
        tx_panel.add(lb_id);
        lb_id.setBounds(10, 20, 100, 50);
        tx_panel.add(lb_user);
        lb_user.setBounds(10, 90, 100, 50);
        tx_panel.add(tx_id);
        tx_id.setBounds(100, 20, 350, 50);
        tx_panel.add(tx_user);
        tx_user.setBounds(100, 90, 350, 50);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(bt_getData)){
            tx_user.setText(db.getData(tx_id.getText()));
        }
        if (e.getSource().equals(bt_insert)){
            db.insertUser(tx_user.getText());
        }
        if (e.getSource().equals(bt_update)){
            db.updateUser(tx_id.getText(), tx_user.getText());
        }
        if (e.getSource().equals(bt_delete)){
            db.deleteUser(tx_id.getText());
        }
    }
    
    public static void main(String[] args){
        MainUi window = new MainUi();
        window.setLayout(null);
        window.add(bt_panel);
        window.add(tx_panel);
        window.setSize(500,500);
        window.setVisible(true);
    }

}
