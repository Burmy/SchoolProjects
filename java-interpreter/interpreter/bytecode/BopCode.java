package interpreter.bytecode;

// Bop must pop 2 values from the runtime stack.
// Bop must push 1 value, the result, back to the top of the runtime stack.
// Bop must implement the following binary operations:
//   Addition: +
//   Subtraction: −
//   Division: /
//   Multiplication: ∗
//   Equality: ==
//   Not-Equal To: ! =
//   Less-Than Equal To: <=
//   Greater Than: >
//   Greater Than Equal To: >=
//   Less Than: <
//   Logical OR: |
//   Logical AND: &
// If dump is on, the Bop ByteCode is required to be dumped. Examples are given in this document.

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;


public class BopCode extends ByteCode {

    String operator;
    int value2;
    int value1;

    @Override
    public void init(ArrayList<String> args) {
        this.operator = args.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        value2 = virtualMachine.pop();
        value1 = virtualMachine.pop();

        switch (operator) {
            case "+":
                virtualMachine.push(value1 + value2);
                break;
            case "-":
                virtualMachine.push(value1 - value2);
                break;
            case "/":
                virtualMachine.push(value1 / value2);
                break;
            case "*":
                virtualMachine.push(value1 * value2);
                break;
            case "==":
                if (value1 == value2)
                    virtualMachine.push(1);
                else
                    virtualMachine.push(0);
                break;
            case "!=":
                if (value1 != value2)
                    virtualMachine.push(1);
                else
                    virtualMachine.push(0);
                break;
            case "<=":
                if (value1 <= value2)
                    virtualMachine.push(1);
                else
                    virtualMachine.push(0);
                break;
            case ">":
                if (value1 > value2)
                    virtualMachine.push(1);
                else
                    virtualMachine.push(0);
                break;
            case ">=":
                if (value1 >= value2)
                    virtualMachine.push(1);
                else
                    virtualMachine.push(0);
                break;
            case "<":
                if (value1 < value2)
                    virtualMachine.push(1);
                else
                    virtualMachine.push(0);
                break;
            case "|":
                virtualMachine.push(value1 | value2);
                break;
            case "&":
                virtualMachine.push(value1 & value2);
        }

    }

    @Override
    public String toString() {
        return "BOP " + operator;
    }

}