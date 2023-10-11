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
    String ESP ="";
    public CalcolatriceRPN() {
        a0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Add(a0.getText());
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
        Result.setText(ESP);
    }
}
