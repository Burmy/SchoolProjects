package interpreter.bytecode;

// Pop takes one argument which is the number of values to remove from the run time stack.
// Pop is not allowed operate across frame boundaries.
// If dump is on, Pop is required to be dumped. Examples are given in this document.

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;


public class PopCode extends ByteCode {

    int value;
    String id;

    @Override
    public void init(ArrayList<String> args) {
        this.value = Integer.parseInt(args.get(0));
        if (args.size() > 1) this.id = args.get(1);
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        for (int i = 0; i < value; i++) {
            virtualMachine.pop();
        }
    }

    @Override
    public String toString() {
        return "POP " + value;
    }
}
