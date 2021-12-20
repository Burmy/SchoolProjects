package interpreter.bytecode;

// Label takes one argument, a label which is used to denote a location in the program.

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;


public class LabelCode extends ByteCode {

    String label;

    @Override
    public void init(ArrayList<String> args) {
        this.label = (args.get(0));
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
    }

    public String getLabel() {
        return label;
    }

    public String toString() {
        return "LABEL " + label;
    }
}