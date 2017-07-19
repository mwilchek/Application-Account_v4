package dataStructures;

import exceptions.Overflow;
import exceptions.Underflow;

public class LinkedStack<data> implements UnboundedStackInterface<data> {
    private int length = 7;
    private int topIndex = -1;
    private String[] stack = new String[length];

    /** Returns true if this stack is empty, otherwise returns false */
    @Override
    public boolean isEmpty() {
        if (topIndex == -1) {
            System.out.println("Linked stack is empty.");
            return true;
        } else
            System.out.println("Linked stack is empty.");
        return false;
    }

    /** Returns true if this stack is full, otherwise returns false. */
    public boolean isFull() {
        if (topIndex == (stack.length - 1)) {
            System.out.println("Stack is full. ");
            return true;
        } else System.out.println("Stack is not full. ");
        return false;
    }

    @Override
    public String push(String element) throws Overflow {
        if (!isFull()) {
            topIndex++;
            stack[topIndex] = element;
            System.out.println("stack[topindex]= " + stack[topIndex]);
        } else
            System.out.println("Push attempted on a full stack.");
        return element;
    }

    @Override
    public String top(String vertex) throws Underflow {
        data topOfStack = null;

        if (!isEmpty()) {
            topOfStack = (data) stack[topIndex];
        } else
            System.out.println("Top attempted on a empty stack.");
        return (String) topOfStack;
    }

    @Override
    public void pop() throws Underflow {
        if (!isEmpty()) {
            System.out.println(stack[topIndex] + " is now removed. ");
            String temp = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
        } else
            System.out.println("Pop attempted on an empty stack.");
    }
}