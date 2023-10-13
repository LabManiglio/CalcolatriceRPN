package FormPK;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            public void actionPerformed(ActionEvent e) { //CE
                ESP = "";
                Result.setText(ESP);
                Espressione.setText(ESP);
            }
        });




        Enter.addActionListener(new ActionListener() { //=
            @Override
            public void actionPerformed(ActionEvent e) {
                Result.setText(ESP);
                Result.setText( PostFix(ESP));
            }
        });
    }
    public void  Add(String a){
        ESP +=a;
        Espressione.setText(ESP);
    }

    public String PostFix(String ESP){
        List<String> in = splitStringToList(ESP);
        List<String> out = new LinkedList<>();


        Stack<String> operatori = new Stack<>();
/*
       String a = "";
        for (String token : in) {
            a+=token+="|";
        }
        return a;
*/

        int counterout = 0;
String corrente ;
    for(int i =0 ;i<in.size();i++){
        corrente = in.get(i);

        if(!Objects.equals(corrente,"X") && !Objects.equals(corrente,"/") && !Objects.equals(corrente,"+") && !Objects.equals(corrente,"-")){
            out.add(counterout, corrente); //se è un numero lo agiunge
        }else {//OPERANDI


            if(corrente =="("){

            }else if(corrente ==")") {

            }else if(!operatori.isEmpty()) {
             if (ValutaPrecedenza(operatori.peek()) < ValutaPrecedenza(corrente) || operatori.peek() == "(") {
                    operatori.push(corrente);
                }
             else if(ValutaPrecedenza(operatori.peek()) >= ValutaPrecedenza(corrente) ){
                out.add(counterout,operatori.pop());
                operatori.push(corrente);


                //Controllo ricorsivo tipo ??
             }




            }else{
                operatori.push(corrente);//nel caso non sia vuoto lo stack cosi che non dia errore nel peek
            }

        }
        for (String token : in) {
            System.out.print(token +"  ");
        }
        System.out.print("\n");
        for (String token : operatori) {
            System.out.print(token +"  ");
        }
        System.out.print("\n");
        for (String token : out) {
            System.out.print(token +"  ");
        }
        System.out.print("\n|||||||\n");

    }


        String a = "";
        for (String token : out) {
            a+=token+="°";
        }
        return a;



    }


//INTERESSANTE
    public static List<String> splitStringToList(String input) {
        List<String> tokens = new LinkedList<>(); // Usa LinkedList al posto di ArrayList
        Pattern pattern = Pattern.compile("([()+X/-])|\\d+(\\.\\d+)?");

        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        return tokens;
    }
//*************************
public int ValutaPrecedenza(String a){
       switch(a) {
        case "+":
            return 1;
           case "-":
               return 1;

           case "X":
               return 2;

           case "/":
               return 2;

           default:
               return 0;

    }
}

























    public static void main(String[] args) {
        JFrame frame = new JFrame("CalcolatriceRPN");
        frame.setContentPane(new CalcolatriceRPN().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
