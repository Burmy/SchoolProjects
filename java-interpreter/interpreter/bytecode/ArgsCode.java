package interpreter.bytecode;

// The Args byteCode has one argument, the number of values that will be a part of the new activation frame.
// The Args ByteCode will need to push the starting index of the new frame to the framePointer stack.
// If dump is on, the Args ByteCode is required to be dumped. Examples are given in this document.

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ArgsCode extends ByteCode {

    int value;
    String id;

    @Override
    public void init(ArrayList<String> args) {
        this.value = Integer.parseInt(args.get(0));
        if (args.size() > 1) this.id = args.get(1);
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.newFrame(value);
    }

    @Override
    public String toString() {
        return "ARGS " + value;
    }
}

