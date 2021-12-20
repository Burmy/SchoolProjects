package interpreter.virtualmachine;

import interpreter.bytecode.*;

import java.util.ArrayList;

public class Program {

    private final ArrayList<ByteCode> program;

    public Program() {
        program = new ArrayList<>();
    }

    protected ByteCode getCode(int programCounter) {
        return this.program.get(programCounter);
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter
     * HINT: make note what type of data-structure ByteCodes are stored in.
     */
    public void resolveAddress() {

        ArrayList<Integer> label = new ArrayList<>();
        ArrayList<String> value = new ArrayList<>();

        //1st pass through the arraylist keeping track of label codes and their labels
        for (int i = 0; i < this.program.size(); i++) {
            if (this.program.get(i) instanceof LabelCode) {
                label.add(i);
                value.add(((LabelCode) this.program.get(i)).getLabel());
            }
        }

        // 2nd pass through the arraylist look for call, goto and falsebranch code and do the following:
        // look at stored label codes and find the 1 that as the matching label value;
        for (ByteCode bc : this.program) {
            if (bc instanceof CallCode) {
                for (int i = 0; i < value.size(); i++) {
                    if (value.get(i).equals(((CallCode) bc).getLabel())) {
                        ((CallCode) bc).setAddress(label.get(i));
                    }
                }
            } else if (bc instanceof GotoCode) {
                for (int i = 0; i < value.size(); i++) {
                    if (value.get(i).equals(((GotoCode) bc).getLabel())) {
                        ((GotoCode) bc).setAddress(label.get(i));
                    }
                }
            } else if (bc instanceof FalseBranchCode) {
                for (int i = 0; i < value.size(); i++) {
                    if (value.get(i).equals(((FalseBranchCode) bc).getLabel())) {
                        ((FalseBranchCode) bc).setAddress(label.get(i));
                    }
                }

            }
        }
    }

    public void add(ByteCode bc) {
        program.add(bc);
    }
}

