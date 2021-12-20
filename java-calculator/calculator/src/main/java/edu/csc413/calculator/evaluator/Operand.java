package edu.csc413.calculator.evaluator;

/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */
public class Operand {
    /**
     * construct operand from string token.
     */
    private int operand;
    public Operand(String token) {
        this.operand = Integer.parseInt(token);
    }

    /**
     * construct operand from integer
     */
    public Operand(int value) {
        this.operand = value;
    }

    /**
     * return value of operand
     */
    public int getValue() {
        return this.operand;
    }

    /**
     * Check to see if given token is a valid
     * operand.
     */
    public static boolean check(String token) {
        return token.matches("\\d+");
    }
}
