package FormPK;

import javax.swing.*;

public class CalcolatriceRPN {
    private JPanel Panel;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton ceButton;
    private JButton a4Button;
    private JButton a1Button;
    private JButton a0Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton button10;
    private JButton a2Button;
    private JButton a3Button;
    private JButton xButton;
    private JButton button14;
    private JButton button15;
    private JButton button16;

    public static void main(String[] args) {
        JFrame frame = new JFrame("CalcolatriceRPN");
        frame.setContentPane(new CalcolatriceRPN().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
