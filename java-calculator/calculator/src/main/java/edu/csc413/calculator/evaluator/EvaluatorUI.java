package edu.csc413.calculator.evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import edu.csc413.calculator.exceptions.InvalidTokenException;

public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField expressionTextField = new TextField();
    private Panel buttonPanel = new Panel();

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] buttonText = {
        "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3",
        "*", "(", "0", ")", "/","C", "^", "CE", "="
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression, clears last entry up until the last operator.
     */
    private Button[] buttons = new Button[buttonText.length];

    public static void main(String argv[]) {
        new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.expressionTextField.setPreferredSize(new Dimension(700, 70));
        this.expressionTextField.setFont(new Font("Courier", Font.BOLD, 45));
        this.expressionTextField.setBackground(new Color(191, 190, 190));
        this.expressionTextField.setForeground(new Color(0, 0, 0));

        add(expressionTextField, BorderLayout.NORTH);
        expressionTextField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button tempButtonReference;
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            tempButtonReference = new Button(buttonText[i]);
            tempButtonReference.setFont(new Font("Courier", Font.BOLD, 50));
            buttons[i] = tempButtonReference;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(700, 600);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * This function is triggered anytime a button is pressed
     * on our Calculator GUI.
     * @param actionEventObject Event object generated when a
     *                    button is pressed.
     */
    public void actionPerformed(ActionEvent actionEventObject) {
        String buttonPressed = actionEventObject.getActionCommand();
        String oldText = this.expressionTextField.getText();

        if("0".equals(buttonPressed)){
            this.expressionTextField.setText(oldText + buttonPressed);
        }else if("1".equals(buttonPressed)){
            this.expressionTextField.setText(oldText + buttonPressed);
        }else if("2".equals(buttonPressed)){
            this.expressionTextField.setText(oldText + buttonPressed);
        }else if("3".equals(buttonPressed)){
            this.expressionTextField.setText(oldText + buttonPressed);
        }else if("4".equals(buttonPressed)){
            this.expressionTextField.setText(oldText + buttonPressed);
        }else if("5".equals(buttonPressed)){
            this.expressionTextField.setText(oldText + buttonPressed);
        }else if("6".equals(buttonPressed)){
            this.expressionTextField.setText(oldText + buttonPressed);
        }else if("7".equals(buttonPressed)){
            this.expressionTextField.setText(oldText + buttonPressed);
        }else if("8".equals(buttonPressed)){
            this.expressionTextField.setText(oldText + buttonPressed);
        }else if("9".equals(buttonPressed)){
            this.expressionTextField.setText(oldText + buttonPressed);
        }else if("+".equals(buttonPressed)){
            this.expressionTextField.setText(oldText + buttonPressed);
        }else if("-".equals(buttonPressed)){
            this.expressionTextField.setText(oldText + buttonPressed);
        }else if("*".equals(buttonPressed)){
            this.expressionTextField.setText(oldText + buttonPressed);
        }else if("/".equals(buttonPressed)){
            this.expressionTextField.setText(oldText + buttonPressed);
        }else if("^".equals(buttonPressed)){
            this.expressionTextField.setText(oldText + buttonPressed);
        }else if("(".equals(buttonPressed)){
            this.expressionTextField.setText(oldText + buttonPressed);
        }else if(")".equals(buttonPressed)){
            this.expressionTextField.setText(oldText + buttonPressed);
        }else if("C".equals(buttonPressed)){
            this.expressionTextField.setText("");
        }else if ("CE".equals(buttonPressed)) {
            if (oldText.length()>=1) {
                this.expressionTextField.setText(oldText.substring(0, oldText.length() - 1));
            }
        } else if ("=".equals(buttonPressed)) {
            try {
                int result = new Evaluator().evaluateExpression(oldText);
                this.expressionTextField.setText(String.valueOf(result));
            } catch (InvalidTokenException e) {
                e.printStackTrace();
            }
        }
    }
}
