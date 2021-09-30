package abm;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame implements ActionListener {
    Main window;
    JTextField tx_user, tx_pass;
    JLabel lb_title, lb_user, lb_pass;
    JButton bt_login;
    HomeUi ui_home;
    UsersDB db;

    public static void main(String[] args) {
        Main window = new Main();
        window.setLayout(null);
        window.setBounds(0,0,500,550);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public Main(){
        db = new UsersDB();
        //title
        lb_title = new JLabel("LOGIN");
        lb_title.setFont(new Font("Arial", Font.PLAIN, 50));
        lb_title.setBounds(170, 50, 480, 70);

        //labels
        lb_user = new JLabel("USUARIO");
        lb_pass = new JLabel("CONTRASEÑA");
        //labels size and position
        lb_user.setBounds(20, 200, 80, 40);
        lb_pass.setBounds(20, 270, 80, 40);

        //text fields
        tx_user = new JTextField();
        tx_pass = new JTextField();
        //text fields size and position
        tx_user.setBounds(110, 200, 350, 40);
        tx_pass.setBounds(110, 270, 350, 40);
        
        //login button
        bt_login = new JButton("INGRESAR");
        bt_login.setBounds(200, 350, 100, 60);
        bt_login.addActionListener(this);

        //add widgets
        add(lb_title);
        add(lb_user);
        add(lb_pass);
        add(tx_user);
        add(tx_pass);
        add(bt_login);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(bt_login)){
            if(db.checkUserLogin(tx_user.getText(), tx_pass.getText())){
                ui_home = new HomeUi();
                ui_home.setVisible(true);
                setVisible(false);
            }
            else{
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
            }
            
        }
    }
}
