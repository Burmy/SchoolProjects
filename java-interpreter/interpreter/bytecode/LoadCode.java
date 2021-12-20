package interpreter.bytecode;

// The Load ByteCode can have 1 to 2 arguments.
// – one argument is the offset in the current frame where the value is to be copied from.
// – The second argument, if present, is the identifier (variable) the value belongs to.
// Load must copy the value at the offset in the current and push it to the top of the stack.
// Load must not remove any values from the runtime stack.
// Load cannot operate across frame boundaries.
// If dump is on, Load needs to be dumped according the specifications given in the Dumping Formats section.

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;


public class LoadCode extends ByteCode {

    int offset;
    String id;

    @Override
    public void init(ArrayList<String> args) {
        this.offset = Integer.parseInt(args.get(0));
        if (args.size() > 1) this.id = args.get(1);
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.load(offset);
    }

    @Override
    public String toString() {
        String base = "LOAD " + offset;
        if (id != null) {
            base += (" " + id + "     <load " + id + ">");
        }
        return base;
    }
}
