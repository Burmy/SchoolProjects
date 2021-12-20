package interpreter.bytecode;

// Prints the top of the runtime stack to the console.
// NO OTHER information can be printed by the Write ByteCode when printing the value.
// If dumping is on, Simply print ”WRITE” to the console

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class WriteCode extends ByteCode {

    @Override
    public void init(ArrayList<String> args) {
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.peek();
    }

    @Override
    public String toString() {
        return "WRITE";
    }
}