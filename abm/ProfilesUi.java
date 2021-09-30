package abm;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ProfilesUi extends JFrame implements ActionListener{
    static JPanel tx_panel;
    static JPanel bt_panel;
    static JPanel table_panel;
    private JLabel lb_id, lb_profile;
    private JTextField tx_id, tx_profile;
    private JButton bt_save, bt_edit, bt_clear, bt_delete;
    DefaultTableModel tableModel = new DefaultTableModel();
    JTable table = new JTable(tableModel);
    
    private ProfilesDB db;

    public ProfilesUi(){
        db = new ProfilesDB();

        ButtonsPanel();
        InputsPanel();
        TablePanel();
        setTableData(db.getAllProfiles());
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
            String[] profileData;

            if (tx_id.getText().equals("")){
                profileData = new String[] {
                    tx_profile.getText(),
                    "admin"
                };
                db.insertProfile(profileData);
            }
            else{
                profileData = new String[] {
                    tx_id.getText(),
                    tx_profile.getText(),
                    "admin"
                };
                db.modifyProfile(profileData);
            }
        }
        if (e.getSource().equals(bt_edit)){
            setFieldsData(db.getProfileById(table.getValueAt(table.getSelectedRow(), 0)));
        }
        if (e.getSource().equals(bt_delete)){
            if(JOptionPane.showConfirmDialog(this, "Esta seguro de eliminar este perfil?")==0){
                db.deleteProfile(table.getValueAt(table.getSelectedRow(), 0));
            }
        }
        if (e.getSource().equals(bt_clear)){
            clearFields();
        }
        setTableData(db.getAllProfiles());
        
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
        tx_profile = new JTextField();
        //labels
        lb_id = new JLabel("ID");
        lb_profile = new JLabel("PROFILE");
        //creating Panel
        tx_panel = new JPanel();
        tx_panel.setLayout(null);
        tx_panel.setBackground(Color.blue);
        tx_panel.setBounds(0, 0, 800, 200);
        tx_panel.setVisible(true);

        //add inputs 
        tx_panel.add(tx_id);
        tx_panel.add(tx_profile);
        //add labels
        tx_panel.add(lb_id);
        tx_panel.add(lb_profile);

        //set position and size to labels
        lb_id.setBounds(10, 40, 100, 40);
        lb_profile.setBounds(10, 130, 100, 40);
        //set position and size inputs
        tx_id.setBounds(110, 40, 670, 40);
        tx_profile.setBounds(110, 130, 670, 40);
    }
    private void TablePanel(){
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setBounds(10,10,780,340);
        //add columns
        tableModel.addColumn("ID");  
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
        for (String[] profile : rows) {
            tableModel.insertRow(tableModel.getRowCount(), new Object[] {
                profile[0],//id
                profile[1],//name
                profile[2],//insert date
                profile[3],//modified date
            });
        }
    }
    private void setFieldsData(String [] user){
        tx_id.setText(user[0]);
        tx_profile.setText(user[1]);
    }
    private void clearFields(){
        tx_id.setText("");
        tx_profile.setText("");
    }
}
