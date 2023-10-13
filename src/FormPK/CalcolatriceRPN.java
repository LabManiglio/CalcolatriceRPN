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
    private JLabel PostFissaL;

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
                PostFissaL.setText(".................");
                Result.setText(".................");
                Espressione.setText(".................");
            }
        });




        Enter.addActionListener(new ActionListener() { //=
            @Override
            public void actionPerformed(ActionEvent e) {
                Result.setText(ESP);
                Result.setText( PostFix(ESP).toString());
            }
        });
    }
    public void  Add(String a){
        ESP +=a;
        Espressione.setText(ESP);
    }

    public Double PostFix(String ESP){
        //VARIABILI
        List<String> in = splitStringToList(ESP);
        List<String> out = new LinkedList<>();
        Stack<String> operatori = new Stack<>();

        int counterout = 0;
        String corrente ;

//*********************************

    for(int i =0 ;i<in.size();i++){
        //PRENDE IL CORRENTE
        corrente = in.get(i);

        if(!Objects.equals(corrente,"X") && !Objects.equals(corrente,"/") && !Objects.equals(corrente,"+") && !Objects.equals(corrente,"-") && !Objects.equals(corrente,"(")&& !Objects.equals(corrente,")")){
            //AGGIUNGE I NUMERI
            out.add(counterout, corrente);
            counterout++;
        }
        //*********************************OPERANDI*********************************
        else {

            //PARENTESI APERTA
            if( Objects.equals(corrente,"(")){
                operatori.push(corrente);
            }


            //PARENTESI CHIUSA
            else if(  Objects.equals(corrente,")")) {

                while(  !Objects.equals(operatori.peek(),"(")){
                    out.add(counterout,operatori.pop());
                    counterout++;
                }//ECCEZIONE CICLO INFINITO ???
                operatori.pop();

            }
            else if(!operatori.isEmpty()) {
             if (ValutaPrecedenza(operatori.peek()) < ValutaPrecedenza(corrente)   ) {
                    operatori.push(corrente);
                }
             else if(ValutaPrecedenza(operatori.peek()) >= ValutaPrecedenza(corrente) ){
                 while( !operatori.isEmpty()&&ValutaPrecedenza(operatori.peek()) >= ValutaPrecedenza(corrente)  ){
                     out.add(counterout,operatori.pop());
                        counterout++;
                 }
                 operatori.push(corrente);
             }

            }else{
                operatori.push(corrente);//nel caso non sia vuoto lo stack cosi che non dia errore nel peek
            }

        }



        //*********************************
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
        //*********************************
    }

    //RICOPIA I RIMANENTI NELLE OPERAZIONI SUK RISULTATO
     while(!operatori.isEmpty()){
         out.add(counterout,operatori.pop());
         counterout++;
     }


     //******************************
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
        //******************************

//RISULTATI

        String Postfissa = "";
        for (String token : out) {
            Postfissa=Postfissa+token+" ";
        }
        PostFissaL.setText(Postfissa);//Scrive la notazione postfissa
        Double risultato = CalcolaPostfissa(Postfissa);
        return risultato;



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


    public static double CalcolaPostfissa(String expression) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                // Se il token è un numero va nello stack
                stack.push(Double.parseDouble(token));
            } else {
                // Se il token è un operatore si calcola
                double o2 = stack.pop();
                double o1 = stack.pop();
                double result = EseguiOperazione(o1, o2, token);
                stack.push(result);
            }
        }

        return stack.pop(); // Il risultato finale è l'elemento finale nello stack
    }

    public static double EseguiOperazione(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "X":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new ArithmeticException("Divisione per zero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Operatore non valido: " + operator);
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
