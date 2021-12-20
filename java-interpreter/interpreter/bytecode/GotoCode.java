package interpreter.bytecode;

// Goto has one argument, a label to jump to.
// Goto’s Label must have its address resolved before the program begins executing.
// If dump is on, Goto is required to be dumped. Examples are given in this document.

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class GotoCode extends ByteCode {

    String label;
    int address;

    @Override
    public void init(ArrayList<String> args) {
        this.label = (args.get(0));
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.setProgramCounter(address - 1);
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public String getLabel() {
        return this.label;
    }

    public String toString() {
        return "GOTO " + label;
    }
}
