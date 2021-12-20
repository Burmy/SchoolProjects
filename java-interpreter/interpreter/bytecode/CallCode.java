package interpreter.bytecode;

// Call ByteCode takes 1 argument, a label to jump to.
// Call Code must go through address resolution to figure out where it needs to jump to in the Program before the program is ran.
// Call Code must store a return address onto the Return Address Stack.
// Call Code must Jump the address in the program that corresponds to a label code.
// If dumping is on, the Call ByteCode needs to be dumped according the specifications in the Dumping formats section.

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class CallCode extends ByteCode {

    String id;
    int address;
    private ArrayList<Integer> args;

    @Override
    public void init(ArrayList<String> args) {
        if (args.size() > 0) {
            this.id = args.get(0);
        } else {
            this.id = "";
        }
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {

        virtualMachine.pushReturnAddress(virtualMachine.getProgramCounter());
        virtualMachine.setProgramCounter(address);
        args = virtualMachine.returnArgs();
    }

    public String getLabel() {
        return id;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public String toString() {
        int n = id.indexOf("<");
        String baseId;
        if (n < 0) {
            baseId = id;
        } else {
            baseId = id.substring(0, n);
        }
        String base = "CALL " + id + "     " + baseId + "(";

        for (int i = 0; i < args.size(); i++) {
            if (i == args.size() - 1) {
                base += args.get(i);
            } else {
                base += args.get(i) + ",";
            }
        }

        return base + ")";
    }

}
