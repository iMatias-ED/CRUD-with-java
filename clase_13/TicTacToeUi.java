package clase_13;

import javax.swing.*;
import java.awt.event.*;

public class TicTacToeUi extends JFrame implements ActionListener{
    public JLabel title;
    public JButton bt_a1;
    public JButton bt_a2;
    public JButton bt_a3;
    public JButton bt_b1;
    public JButton bt_b2;
    public JButton bt_b3;
    public JButton bt_c1;
    public JButton bt_c2;
    public JButton bt_c3;
    public JButton bt_reset;
    private TicTacToeLogic logic;

    public TicTacToeUi(){
        logic = new TicTacToeLogic();

        setLayout(null);
        title = new JLabel("Tic Tac Toe");
        title.setBounds(200,15, 150,40);
        add(title);

        bt_a1 = createButton(1, 1);
        add(bt_a1);
        bt_a2 = createButton(2, 1);
        add(bt_a2);
        bt_a3 = createButton(3, 1);
        add(bt_a3);

        bt_b1 = createButton(1, 2);
        add(bt_b1);
        bt_b2 = createButton(2, 2);
        add(bt_b2);
        bt_b3 = createButton(3, 2);
        add(bt_b3);

        bt_c1 = createButton(1, 3);
        add(bt_c1);
        bt_c2 = createButton(2, 3);
        add(bt_c2);
        bt_c3 = createButton(3, 3);
        add(bt_c3);

        bt_reset = new JButton("Reset");
        bt_reset.setBounds(200,400,100,30);
        add(bt_reset);
        bt_reset.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == bt_reset){
            resetButtons();
            changeButtonsStatus(true);
            title.setText("Tic Tac Toe");
            return;
        }

        JButton clicked_bt = (JButton) e.getSource();
        clicked_bt.setText(logic.getPlayer());
        clicked_bt.setEnabled(false);
        if (logic.winnerControl(getButtonsValues())){
            title.setText("El ganador es: "+logic.getPlayer());
            changeButtonsStatus(false);
            return;
        }
        logic.changePlayer();
    }
    public static void main(String[] args){
        TicTacToeUi game = new TicTacToeUi();
        game.setBounds(0,0,500,500);
        game.setVisible(true);
        game.setLocationRelativeTo(null);
        game.setResizable(false);
    }

//private methods
    private JButton createButton(int row, int column){
        int x_padding = (int) 200/4;
        int x = Math.round(100*(row-1) + x_padding*row);
        
        int y_padding = (int) 30/3;
        int y = Math.round(100*(column-1) + y_padding*column);

        JButton button = new JButton("");
        button.setBounds(x, y+50, 100, 100);
        button.addActionListener(this);
        return button;
    }
    private String[] getButtonsValues(){
        String valuesArray[] = new String[]{
            bt_a1.getText(),
            bt_a2.getText(),
            bt_a3.getText(),

            bt_b1.getText(),
            bt_b2.getText(),
            bt_b3.getText(),

            bt_c1.getText(),
            bt_c2.getText(),
            bt_c3.getText()
        };

        return valuesArray;
    }
    private void resetButtons(){
        bt_a1.setText("");
        bt_a2.setText("");
        bt_a3.setText("");
        bt_b1.setText("");
        bt_b2.setText("");
        bt_b3.setText("");
        bt_c1.setText("");
        bt_c2.setText("");
        bt_c3.setText("");
    }
    private void changeButtonsStatus(Boolean status){
        bt_a1.setEnabled(status);
        bt_a2.setEnabled(status);
        bt_a3.setEnabled(status);
        bt_b1.setEnabled(status);
        bt_b2.setEnabled(status);
        bt_b3.setEnabled(status);
        bt_c1.setEnabled(status);
        bt_c2.setEnabled(status);
        bt_c3.setEnabled(status);
    }
}