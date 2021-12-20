package interpreter.bytecode;

// The FalseBranch ByteCode will be used to execute conditional jumps.
// FalseBranch will have one argument.
// This argument is a Label that will mark a place in the program to jump to.
// FalseBranch will remove the top value of the run time stack and check to see if the value is 0.
// If the value is 0, jump the corresponding label.
// If the value is something else, move to the next ByteCode in the program

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends ByteCode {

    String label;
    int address;

    @Override
    public void init(ArrayList<String> args) {
        this.label = (args.get(0));
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        if (virtualMachine.pop() == 0) {
            virtualMachine.setProgramCounter(address);
        }
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return "FALSEBRANCH " + label;
    }
}
