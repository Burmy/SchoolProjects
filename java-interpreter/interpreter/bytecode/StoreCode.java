package interpreter.bytecode;

// The Store ByteCode can have 1 to 2 arguments.
// – one argument is the offset in the current frame where the value that is popped is to be stored.
// – The second argument, if present, is the identifier (variable) the value being moved belongs to.
// Store must pop the top of the runtime stack and store the value at the offset in the current frame.
// Store cannot operate across frame boundaries.
// If dump is on, Store needs to be dumped according the specifications given in the Dumping Formats section.

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode {

    int offset;
    int topOfStack;
    String id;

    @Override
    public void init(ArrayList<String> args) {
        this.offset = Integer.parseInt(args.get(0));
        if (args.size() > 1) this.id = args.get(1);
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        topOfStack = virtualMachine.store(offset);
    }

    @Override
    public String toString() {
        String base = "STORE " + offset;
        if (id != null) {
            base += (" " + id + "      " + id + "=" + topOfStack);
        }
        return base;
    }
}
