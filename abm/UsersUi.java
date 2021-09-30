package abm;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class UsersUi extends JFrame implements ActionListener{
    static JPanel tx_panel;
    static JPanel bt_panel;
    static JPanel table_panel;
    private JLabel lb_id, lb_user, lb_pass, lb_profile;
    private JTextField tx_id, tx_user, tx_pass;
    private JButton bt_save, bt_edit, bt_clear, bt_delete;
    DefaultTableModel tableModel = new DefaultTableModel();
    JTable table = new JTable(tableModel);
    JComboBox<ComboItem> cm_profile;
    
    private UsersDB db;

    public UsersUi(){
        db = new UsersDB();

        ButtonsPanel();
        InputsPanel();
        TablePanel();
        setTableData(db.getAllUsers());
        setComboBoxData(db.getAllProfiles());
        //window config
        setLayout(null);
        setResizable(false);
        setBounds(0,0,818,670);
        setLocationRelativeTo(null);
        setVisible(true);
        //add panels to window
        add(bt_panel);
        add(tx_panel);
        add(table_panel);
    }


    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(bt_save)){
            Object item = cm_profile.getSelectedItem();
            String profile_id = ((ComboItem)item).getValue();
            String[] userData;

            if (tx_id.getText().equals("")){
                userData = new String[] {
                    tx_user.getText(),
                    profile_id,
                    tx_pass.getText(),
                    "admin"
                };
                db.insertUser(userData);
            }
            else{
                userData = new String[] {
                    tx_id.getText(),
                    tx_user.getText(),
                    profile_id,
                    tx_pass.getText(),
                    "admin"
                };
                db.modifyUser(userData);
            }
        }
        if (e.getSource().equals(bt_edit)){
            setFieldsData(db.getUserById(table.getValueAt(table.getSelectedRow(), 0)));
        }
        if (e.getSource().equals(bt_delete)){
            if(JOptionPane.showConfirmDialog(this, "Esta seguro de eliminar este usuario?")==0){
                db.deleteUser(table.getValueAt(table.getSelectedRow(), 0));
            }
        }
        if (e.getSource().equals(bt_clear)){
            clearFields();
        }
        setTableData(db.getAllUsers());
        
    }

    private void ButtonsPanel(){
        //buttons creation
        bt_save = new JButton("Guardar");
        bt_save.addActionListener(this);
        bt_edit = new JButton("Editar");
        bt_edit.addActionListener(this);
        bt_clear = new JButton("Limpiar");
        bt_clear.addActionListener(this);
        bt_delete = new JButton("Borrar");
        bt_delete.addActionListener(this);

        //panel creation 
        bt_panel = new JPanel();
        bt_panel.setLayout(null);
        bt_panel.setBackground(Color.lightGray);
        bt_panel.setBounds(0,200,800,70);
        bt_panel.setVisible(true);
        //Set Buttons position
        bt_save.setBounds(10, 10, 187, 50);
        bt_edit.setBounds(207, 10, 187, 50);
        bt_clear.setBounds(404, 10, 187, 50);
        bt_delete.setBounds(601, 10, 187, 50);
        //add_buttons
        bt_panel.add(bt_save);
        bt_panel.add(bt_edit);
        bt_panel.add(bt_clear);
        bt_panel.add(bt_delete);
    }
    private void InputsPanel(){
        //inputs and comboBox
        tx_id = new JTextField();
        tx_id.setEnabled(false);
        tx_user = new JTextField();
        tx_pass = new JTextField();
        cm_profile = new JComboBox<ComboItem>();
        //labels
        lb_id = new JLabel("ID");
        lb_user = new JLabel("USER");
        lb_pass = new JLabel("PASSWORD");
        lb_profile = new JLabel("PROFILE");
        //creating Panel
        tx_panel = new JPanel();
        tx_panel.setLayout(null);
        tx_panel.setBackground(Color.cyan);
        tx_panel.setBounds(0, 0, 800, 200);
        tx_panel.setVisible(true);

        //add inputs 
        tx_panel.add(tx_id);
        tx_panel.add(tx_user);
        tx_panel.add(tx_pass);
        tx_panel.add(cm_profile);
        //add labels
        tx_panel.add(lb_id);
        tx_panel.add(lb_user);
        tx_panel.add(lb_pass);
        tx_panel.add(lb_profile);

        //set position and size to labels
        lb_id.setBounds(10, 16, 100, 30);
        lb_user.setBounds(10, 62, 100, 30);
        lb_pass.setBounds(10, 108, 100, 30);
        lb_profile.setBounds(10, 150, 100, 30);
        //set position and size inputs
        tx_id.setBounds(110, 16, 670, 30);
        tx_user.setBounds(110, 62, 670, 30);
        tx_pass.setBounds(110, 108, 670, 30);
        cm_profile.setBounds(110, 150, 670, 30);
    }
    private void TablePanel(){
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setBounds(10,10,780,340);
        //add columns
        tableModel.addColumn("ID");  
        tableModel.addColumn("USUARIO");  
        tableModel.addColumn("PASSWORD");  
        tableModel.addColumn("PERFIL");  
        tableModel.addColumn("INSERTADO");  
        tableModel.addColumn("ACTUALIZADO"); 
        
        //panel creation
        table_panel = new JPanel();
        table_panel.setLayout(null);
        table_panel.setBounds(0, 270, 800, 400);
        table_panel.setBackground(Color.darkGray);
        table_panel.setVisible(true);
        
        table_panel.add(table);
    }
    private void setTableData(String[][] rows){
        tableModel.setRowCount(0);
        for (String[] user : rows) {
            tableModel.insertRow(tableModel.getRowCount(), new Object[] {
                user[0],//id
                user[1],//name
                user[2],//password
                user[3],//profile
                user[4],//inserted date
                user[5],//modified
            });
        }
    }
    private void setComboBoxData(String [][] profilesData){
        for (String[] profile : profilesData) {
            cm_profile.addItem(new ComboItem(profile[1], profile[0]));
        }
    }
    private void setFieldsData(String [] user){
        tx_id.setText(user[0]);
        tx_user.setText(user[1]);
        tx_pass.setText(user[2]);
    }
    private void clearFields(){
        cm_profile.removeAllItems();
        tx_id.setText("");
        tx_user.setText("");
        tx_pass.setText("");
        setComboBoxData(db.getAllProfiles());
    }
}
