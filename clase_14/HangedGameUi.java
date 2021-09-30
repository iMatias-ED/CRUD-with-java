package clase_14;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;
import java.awt.Font;

public class HangedGameUi extends javax.swing.JFrame implements ActionListener{
    
    private JLabel lb_insertedLetters;
    private JLabel lb_insertedLettersTitle;
    private JButton bt_reset;
    private JLabel lb_user_input;
    private JTextField input_letters;
    private JLabel image;
    private JButton bt_addWord;
    private HangedGameLogic logic = new HangedGameLogic();
    private String selectedWord;

    public HangedGameUi(){
        setLayout(null);
        selectedWord = logic.getRandomWord();

        lb_insertedLettersTitle = new JLabel("LETRAS PROBADAS:");
        lb_insertedLettersTitle.setBounds(10,10,400,30);
        add(lb_insertedLettersTitle);

        lb_insertedLetters = new JLabel(logic.getInitialLabelContent());
        lb_insertedLetters.setBounds(10,70,400,50);
        lb_insertedLetters.setFont(new Font("Arial", Font.PLAIN, 50));;
        add(lb_insertedLetters);

        bt_reset = new JButton("RESET");
        bt_reset.setBounds(10,140, 100, 35);
        bt_reset.addActionListener(this);
        add(bt_reset);

        lb_user_input = new JLabel("PROBAR LETRA");
        lb_user_input.setBounds(10, 185, 100, 35);
        add (lb_user_input);

        input_letters = new JTextField();
        input_letters.setBounds(10, 240, 100, 45);
        input_letters.addActionListener(this);
        add(input_letters);

        bt_addWord = new JButton("Agregar");
        bt_addWord.setBounds(120,240,50,45);
        bt_addWord.addActionListener(this);
        add(bt_addWord);

        image = new JLabel();
        image.setIcon(new ImageIcon("clase_14/images/imagen0.png"));
        image.setBounds(410, 10, 380, 330);
        add(image);

    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == bt_reset){
            selectedWord = logic.getRandomWord();
            lb_insertedLetters.setText(logic.getInitialLabelContent());
            image.setIcon(new ImageIcon("clase_14/images/imagen0.png"));
            bt_addWord.setEnabled(true);
        }
        if (e.getSource()==bt_addWord){
            int errorNumber = logic.checkInput(input_letters.getText());
            if (errorNumber>=6){
                image.setIcon(new ImageIcon("clase_14/images/imagen6.png"));
                bt_addWord.setEnabled(false);
            }
            else {
                String imageUrl = "clase_14/images/imagen"+errorNumber+".png";
                image.setIcon(new ImageIcon(imageUrl));
            }

            String newContent = logic.getLabelContent(input_letters.getText(), lb_insertedLetters.getText());

            if (newContent.equals("#PLAYER-VICTORY#")){
                image.setIcon(new ImageIcon("clase_14/images/ganaste.png"));
                lb_insertedLetters.setText(selectedWord.toUpperCase());
                bt_addWord.setEnabled(false);
                return;
            }

            lb_insertedLetters.setText(newContent);
            input_letters.setText("");
        }
    }
    public static void main(String[] args){
        HangedGameUi ui = new HangedGameUi();
        ui.setBounds(0,0,800,350);
        ui.setVisible(true);
        ui.setLocationRelativeTo(null);
        ui.setResizable(false);
    }

}


