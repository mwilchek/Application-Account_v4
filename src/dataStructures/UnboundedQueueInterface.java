package dataStructures;

import exceptions.Underflow;

/**
 * Created by seeme on 7/16/2017.
 */
public interface UnboundedQueueInterface<data> {

    boolean isEmpty();

    String dequeue() throws Underflow;


    void enqueue(data vertex);

}
