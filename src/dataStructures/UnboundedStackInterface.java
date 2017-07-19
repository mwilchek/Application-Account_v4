package dataStructures;

import exceptions.Overflow;
import exceptions.Underflow;

public interface UnboundedStackInterface<data> {

    String push(String startVertex) throws Overflow;

    String top(String vertex) throws Underflow;

    void pop() throws Underflow;

    boolean isEmpty();

}