import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Calculator implements ActionListener{

    boolean isOperatorClicked = false;
    JFrame jf;
    JLabel displayLabel;
    JButton zeroButton,oneButton,twoButton,threeButton,fourButton,fiveButton,sixButton,sevenButton,eightButton,nineButton;
    JButton additionButton,subtractionButton,multiplicationButton,divisionButton;
    JButton dotButton,equalButton,clearButton;

    public Calculator(){
        jf = new JFrame("calculator");
        jf.setLayout(null);
        jf.setSize(600,600);
        jf.setLocation(500, 100);

        displayLabel = new JLabel();
        displayLabel.setBounds(30, 50, 540, 40);
        displayLabel.setBackground(Color.GRAY);
        displayLabel.setOpaque(true);
        displayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        displayLabel.setForeground(Color.white);
        jf.add(displayLabel);

        sevenButton = new JButton("7");
        sevenButton.setBounds(30, 130, 80, 80);
        sevenButton.addActionListener(this);
        jf.add(sevenButton);

        eightButton = new JButton("8");
        eightButton.setBounds(130, 130, 80, 80);
        eightButton.addActionListener(this);
        jf.add(eightButton);

        nineButton = new JButton("9");
        nineButton.setBounds(230, 130, 80, 80);
        nineButton.addActionListener(this);
        jf.add(nineButton);

        divisionButton = new JButton("/");
        divisionButton.setBounds(330, 130, 80, 80);
        divisionButton.addActionListener(this);
        jf.add(divisionButton);

        fourButton = new JButton("4");
        fourButton.setBounds(30, 230, 80, 80);
        fourButton.addActionListener(this);
        jf.add(fourButton);

        fiveButton = new JButton("5");
        fiveButton.setBounds(130, 230, 80, 80);
        fiveButton.addActionListener(this);
        jf.add(fiveButton);

        sixButton = new JButton("6");
        sixButton.setBounds(230, 230, 80, 80);
        sixButton.addActionListener(this);
        jf.add(sixButton);

        multiplicationButton = new JButton("*");
        multiplicationButton.setBounds(330, 230, 80, 80);
        multiplicationButton.addActionListener(this);
        jf.add(multiplicationButton);

        oneButton = new JButton("1");
        oneButton.setBounds(30, 330, 80, 80);
        oneButton.addActionListener(this);
        jf.add(oneButton);

        twoButton = new JButton("2");
        twoButton.setBounds(130, 330, 80, 80);
        twoButton.addActionListener(this);
        jf.add(twoButton);

        threeButton = new JButton("3");
        threeButton.setBounds(230, 330, 80, 80);
        threeButton.addActionListener(this);
        jf.add(threeButton);

        subtractionButton = new JButton("-");
        subtractionButton.setBounds(330, 330, 80, 80);
        subtractionButton.addActionListener(this);
        jf.add(subtractionButton);

        dotButton = new JButton(".");
        dotButton.setBounds(30, 430, 80, 80);
        dotButton.addActionListener(this);
        jf.add(dotButton);

        zeroButton = new JButton("0");
        zeroButton.setBounds(130, 430, 80, 80);
        zeroButton.addActionListener(this);
        jf.add(zeroButton);

        equalButton = new JButton("=");
        equalButton.setBounds(230, 430, 80, 80);
        equalButton.addActionListener(this);
        jf.add(equalButton);

        additionButton = new JButton("+");
        additionButton.setBounds(330, 430, 80, 80);
        additionButton.addActionListener(this);
        jf.add(additionButton);

        clearButton = new JButton("clear");
        clearButton.setBounds(430, 430, 80, 80);
        clearButton.addActionListener(this);
        jf.add(clearButton);

        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private double evaluateExpression(String expression) throws Exception {

        java.util.Stack<Double> numbers = new java.util.Stack<>();
        java.util.Stack<Character> operators = new java.util.Stack<>();
        int i = 0;
        
        while (i < expression.length()) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder sb = new StringBuilder();

                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i));
                    i++;
                }

                numbers.push(Double.parseDouble(sb.toString()));
                continue;
            }

            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {

                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(ch)) {
                    double b = numbers.pop();
                    double a = numbers.pop();
                    char op = operators.pop();
                    numbers.push(applyOp(a, b, op));
                }
                operators.push(ch);
            }

            i++;

        }

        while (!operators.isEmpty()) {
            double b = numbers.pop();
            double a = numbers.pop();
            char op = operators.pop();
            numbers.push(applyOp(a, b, op));
        }

        return numbers.pop();
    }

    private int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    private double applyOp(double a, double b, char op) throws Exception {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) {
                    throw new Exception("Division by zero");
                }
                return a / b;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==oneButton){
            if(isOperatorClicked){
                displayLabel.setText("1");
                isOperatorClicked=false;
            }
            else{
                displayLabel.setText(displayLabel.getText()+"1");
            }
        }
        else if (e.getSource()==twoButton) {

            if(isOperatorClicked){
                displayLabel.setText("2");
                isOperatorClicked=false;
            }
            else{
                displayLabel.setText(displayLabel.getText()+"2");
            }
        }

        else if (e.getSource()==threeButton) {

            if(isOperatorClicked){
                displayLabel.setText("3");
                isOperatorClicked=false;
            }
            else{
                displayLabel.setText(displayLabel.getText()+"3");
            }
        }

        else if (e.getSource()==fourButton) {

            if(isOperatorClicked){
                displayLabel.setText("4");
                isOperatorClicked=false;
            }
            else{
                displayLabel.setText(displayLabel.getText()+"4");
            }
        }

        else if(e.getSource()==fiveButton){

            if(isOperatorClicked){
                displayLabel.setText("5");
                isOperatorClicked=false;
            }
            else{
                displayLabel.setText(displayLabel.getText()+"5");
            }
        }

        else if(e.getSource()==sixButton){

            if(isOperatorClicked){
                displayLabel.setText("6");
                isOperatorClicked=false;
            }
            else{
                displayLabel.setText(displayLabel.getText()+"6");
            }
        }

        else if(e.getSource()==sevenButton){

            if(isOperatorClicked){
                displayLabel.setText("7");
                isOperatorClicked=false;
            }
            else{
                displayLabel.setText(displayLabel.getText()+"7");
            }
        }

        else if(e.getSource()==eightButton){

            if(isOperatorClicked)
            {
                displayLabel.setText("8");
                isOperatorClicked=false;
            }
            else{
                displayLabel.setText(displayLabel.getText()+"8");
            }
        }

        else if(e.getSource()==nineButton){

            if(isOperatorClicked){
                displayLabel.setText("9");
                isOperatorClicked=false;
            }
            else{
                displayLabel.setText(displayLabel.getText()+"9");
            }
        }

        else if(e.getSource()==zeroButton){

            if(isOperatorClicked){
                displayLabel.setText("0");
                isOperatorClicked=false;
            }
            else{
                displayLabel.setText(displayLabel.getText()+"0");
            }
        }

        else if(e.getSource()==additionButton){
            displayLabel.setText(displayLabel.getText() + "+");
        }
        else if(e.getSource()==subtractionButton){
            displayLabel.setText(displayLabel.getText() + "-");
        }
        else if(e.getSource()==multiplicationButton){
            displayLabel.setText(displayLabel.getText() + "*");
        }
        else if(e.getSource()==divisionButton){
            displayLabel.setText(displayLabel.getText() + "/");
        }
        else if(e.getSource()==dotButton){
            displayLabel.setText(displayLabel.getText()+".");;
        }

        else if(e.getSource()==equalButton){

            try {
                String expression = displayLabel.getText();
                double result = evaluateExpression(expression);
                displayLabel.setText(result + "");
            } 
            catch (Exception ex) {
                displayLabel.setText("ERROR");
            }
        }

        else if(e.getSource()==clearButton){
            displayLabel.setText("");
        }
    }
}