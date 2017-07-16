package dataStructures;

import exceptions.PriQOverflowException;
import exceptions.PriQUnderflowException;

public interface PriorityQueueInterface<data extends Comparable<data>>

{

    boolean isEmpty();

    // Returns true if this priority queue is empty, false otherwise.

    //  boolean isFull();

    // Returns true if this priority queue is full, false otherwise.

    void enqueue(data element) throws PriQOverflowException;

    // Throws PriQOverflowException if this priority queue is full;

    // otherwise, adds element to this priority queue.

    data dequeue() throws PriQUnderflowException;

    // Throws PriQUnderflowException if this priority queue is empty;

    // otherwise, removes element with highest priority from this

    // priority queue and returns it.

}