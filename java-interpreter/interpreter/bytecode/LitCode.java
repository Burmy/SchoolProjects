package interpreter.bytecode;

// The Lit ByteCode takes 1 or 2 arguments.
// The Lit ByteCode should only push 1 value to the top of the runtime stack.
// If dumping is on, Lit ByteCode needs to be dumped according the specifications in the Dumping formats section.

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LitCode extends ByteCode {

    int value;
    String id;

    @Override
    public void init(ArrayList<String> args) {
        this.value = Integer.parseInt(args.get(0));
        if (args.size() > 1) this.id = args.get(1);
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.push(value);
    }

    @Override
    public String toString() {
        String base = "LIT " + value;
        if (id != null) {
            base += (" int " + id);
        }
        return base;
    }
}


