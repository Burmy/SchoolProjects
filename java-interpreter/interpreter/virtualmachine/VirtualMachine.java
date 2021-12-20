package interpreter.virtualmachine;

import interpreter.bytecode.ByteCode;
import interpreter.bytecode.DumpCode;
import interpreter.bytecode.HaltCode;

import java.util.ArrayList;
import java.util.Stack;

public class VirtualMachine {

    private RunTimeStack runTimeStack;
    private Stack<Integer> returnAddress;
    private final Program program;
    private int programCounter = 0;
    private boolean isRunning = true;
    private boolean dump = true;

    public VirtualMachine(Program program) {
        this.program = program;
    }

    public void executeProgram() {

        runTimeStack = new RunTimeStack();
        returnAddress = new Stack<>();

        while (isRunning) {
            ByteCode code = program.getCode(programCounter);

            code.execute(this);
            if (dump && !(code instanceof DumpCode) && !(code instanceof HaltCode)) {
                System.out.println(code);
                runTimeStack.dump();
            }
            programCounter++;
        }
    }

    public void push(int i) {
        runTimeStack.push(i);
    }

    public int pop() {
        return runTimeStack.pop();
    }

    public int peek() {
        return runTimeStack.peek();
    }

    public void newFrame(int offset) {
        runTimeStack.newFrameAt(offset);
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int index) {
        programCounter = index;
    }

    public void pushReturnAddress(int address) {
        returnAddress.push(address);
    }

    public void setDump(boolean trigger) {
        dump = trigger;
    }

    public void haltProgram() {
        isRunning = false;
    }

    public void load(int offset) {
        runTimeStack.load(offset);
    }

    public int store(int offset) {
        return runTimeStack.store(offset);
    }

    public void popFrame() {
        runTimeStack.popFrame();
    }

    public int popReturnAddress() {
        return returnAddress.pop();
    }

    public ArrayList<Integer> returnArgs() {
        return runTimeStack.getArguments();
    }
}
