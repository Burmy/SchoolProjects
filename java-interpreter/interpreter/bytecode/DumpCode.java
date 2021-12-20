package interpreter.bytecode;

// The Dump ByteCode has 1 argument. Either ”ON” or ”OFF”
// The Dump ByteCode must request the VirtualMachine to turn dumping either ”ON” or ”OFF”.
// The Dump ByteCode is NOT TO BE DUMPED.

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class DumpCode extends ByteCode {

    boolean dump;

    @Override
    public void init(ArrayList<String> args) {
        if (args.get(0).equals("ON")) {
            dump = true;
        } else if (args.get(0).equals("OFF")) {
            dump = false;
        }
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.setDump(dump);
    }

    @Override
    public String toString() {
        return "DUMP " + dump;
    }
}
