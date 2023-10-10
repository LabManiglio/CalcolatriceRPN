package FormPK;

import javax.swing.*;

public class CalcolatriceRPN {
    private JPanel Panel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("CalcolatriceRPN");
        frame.setContentPane(new CalcolatriceRPN().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
