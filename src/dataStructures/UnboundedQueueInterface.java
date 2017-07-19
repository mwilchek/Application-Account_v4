package dataStructures;

import exceptions.Underflow;

public interface UnboundedQueueInterface<data> {

    boolean isEmpty();

    String dequeue() throws Underflow;

    void enqueue(data vertex);

}
