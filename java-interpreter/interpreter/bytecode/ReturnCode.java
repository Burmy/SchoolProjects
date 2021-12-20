package interpreter.bytecode;

// The Return ByteCode can take 0 to 1 arguments. The arguments have no effect on its functionality.
// The Return ByteCode must store the return value at the top of the runtime stack.
// The Return ByteCode must empty the current frame of all values when the function is complete.
// The Return ByteCode must pop the top value from the framePointer stack to remove the frame boundary.
// The return ByteCode must pop the top of the return address stack and save it into program counter.
// If dumping is on, the Return ByteCode needs to be dumped according the specifications in the Dumping formats section.

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ReturnCode extends ByteCode {

    String id;
    int value;

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
        virtualMachine.setProgramCounter(virtualMachine.popReturnAddress());
        virtualMachine.popFrame();
        value = virtualMachine.peek();
    }

    public String toString() {
        int i = id.indexOf("<");
        String baseId;
        if (i < 0) {
            baseId = id;
        } else {
            baseId = id.substring(0, i);
        }
        return "RETURN " + id + "   EXIT " + baseId + " : " + value;
    }
}
