package interpreter.bytecode;

// Notify the VirtualMachine that execution needs to be Halted.
// Halt takes no arguments.
// Halt ByteCodes are not be Dumped.
// Halt cannot execute a system.exit function call.

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class HaltCode extends ByteCode {

    @Override
    public void init(ArrayList<String> args) {
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.haltProgram();
    }

    @Override
    public String toString() {
        return "HALT";
    }
}