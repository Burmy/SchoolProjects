package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

class RunTimeStack {

    private final ArrayList<Integer> runTimeStack;
    private final Stack<Integer> framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    /**
     * Used for dumping the current state of the runTimeStack .
     * It will print portions of the stack based on respective
     * frame markers .
     * Example [1 ,2 ,3] [4 ,5 ,6] [7 ,8]
     * Frame pointers would be 0 ,3 ,6
     */
    public void dump() {
        Iterator iterator = framePointer.iterator();
        int firstFrame = (Integer) iterator.next();
        int secondFrame;

        int i = 0;
        while (i < framePointer.size()) {
            if (iterator.hasNext()) {
                secondFrame = (Integer) iterator.next();
            } else {
                secondFrame = runTimeStack.size();
            }
            System.out.print("[");

            while (secondFrame > firstFrame) {
                System.out.print(runTimeStack.get(firstFrame));
                if (firstFrame != secondFrame - 1) {
                    System.out.print(", ");
                }
                firstFrame++;
            }
            System.out.print("] ");
            firstFrame = secondFrame;
            i++;
        }
        System.out.println();
    }

    private int LastIndex() {
        return this.runTimeStack.size() - 1;
    }

    /**
     * returns the top of the runtime stack , but does not remove
     *
     * @return copy of the top of the stack .
     */
    public int peek() {
        return this.runTimeStack.get(LastIndex());
    }

    /**
     * push the value i to the top of the stack .
     *
     * @param valueToPush value to be pushed .
     * @return value pushed
     */
    public int push(int valueToPush) {
        this.runTimeStack.add(valueToPush);
        return this.peek();
    }

    /**
     * removes to the top of the runtime stack .
     *
     * @return the value popped .
     */
    public int pop() {
        if (!runTimeStack.isEmpty() && framePointer.peek() < runTimeStack.size()) {
            return this.runTimeStack.remove(LastIndex());
        }
        return 0;
    }

    /**
     * Takes the top item of the run time stack , and stores
     * it into a offset starting from the current frame .
     *
     * @param offset number of slots above current frame marker
     * @return the item just stored
     */
    public int store(int offset) {
        int topItem = pop();
        this.runTimeStack.set(framePointer.peek() + offset, topItem);
        return topItem;
    }

    /**
     * Takes a value from the run time stack that is at offset
     * from the current frame marker and pushes it onto the top of
     * the stack .
     *
     * @param offset number of slots above current frame marker
     * @return item just loaded into the offset
     */
    public int load(int offset) {
        int value = runTimeStack.get(framePointer.peek() + offset);
        this.runTimeStack.add(value);
        return value;
    }

    /**
     * create a new frame pointer at the index offset slots down
     * from the top of the runtime stack .
     *
     * @param offset slots down from the top of the runtime stack
     */
    public void newFrameAt(int offset) {
        this.framePointer.push(runTimeStack.size() - offset);
    }

    /**
     * pop the current frame off the runtime stack . Also removes
     * the frame pointer value from the FramePointer Stack .
     */
    public void popFrame() {
        int topItem = peek();
        int pop = framePointer.pop();
        for (int i = runTimeStack.size() - 1; i >= pop; i--) {
            this.pop();
        }
        this.push(topItem);
    }

    //This method is used by CallCode to print out arguments
    public ArrayList<Integer> getArguments() {
        int frameIndex = framePointer.peek();
        ArrayList<Integer> args = new ArrayList<>();
        for (int i = frameIndex; i < runTimeStack.size(); i++) {
            args.add(runTimeStack.get(i));
        }
        return args;
    }

}
