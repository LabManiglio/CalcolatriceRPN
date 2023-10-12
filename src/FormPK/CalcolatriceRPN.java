package FormPK;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcolatriceRPN {
    private JPanel Panel;
    private JButton a7;
    private JButton a8;
    private JButton a9;
    private JButton CE;
    private JButton a4;
    private JButton a1;
    private JButton a0;
    private JButton a5;
    private JButton a6;
    private JButton Division;
    private JButton a2;
    private JButton a3;
    private JButton Moltiplication;
    private JButton Plus;
    private JButton button15;
    private JButton Enter;
    private JLabel Result;
    private JButton Punto;
    private JButton OpenParenthesis;
    private JButton CloseParenthesis;
    private JLabel Espressione;
    String ESP ="";
    public CalcolatriceRPN() {

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton surceButton = (JButton) e.getSource();
                Add(surceButton.getText());
            }
        };
        a9.addActionListener(listener);
        a6.addActionListener(listener);
        a3.addActionListener(listener);
        a7.addActionListener(listener);
        a8.addActionListener(listener);
        a4.addActionListener(listener);
        a1.addActionListener(listener);
        a0.addActionListener(listener);
        a5.addActionListener(listener);
        a2.addActionListener(listener);
        Moltiplication.addActionListener(listener);
        Plus.addActionListener(listener);
        button15.addActionListener(listener);
        Division.addActionListener(listener);
        Punto.addActionListener(listener);
        OpenParenthesis.addActionListener(listener);
        CloseParenthesis.addActionListener(listener);
        CE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ESP = "";
                Result.setText(ESP);
                Espressione.setText(ESP);
            }
        });
        Enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Result.setText(ESP);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CalcolatriceRPN");
        frame.setContentPane(new CalcolatriceRPN().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);





    }
    public void  Add(String a){
        ESP +=a;
        Espressione.setText(ESP);
    }
}
