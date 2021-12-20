package interpreter.bytecode;

// When asking for user input, use the following prompt: ”Please enter an integer : ”
// The Read ByteCode needs to verify that the value given is actually a number.
// If an invalid number is given, state that the input is invalid and ask for another value.
// Continue to do so until a valid value is given.
// Push the validated integer to the VirtualMachine’s RunTimeStack.
// If dumping is on, Simply print ”READ” to the console.

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;
import java.util.Scanner;


public class ReadCode extends ByteCode {

    int value;

    @Override
    public void init(ArrayList<String> args) {
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter an Integer : ");
            value = sc.nextInt();
            virtualMachine.push(value);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid Integer!");
        }
    }

    @Override
    public String toString() {
        return "READ";
    }
}
