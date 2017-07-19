package dataStructures;

import exceptions.Underflow;

public class LinkedUnboundQueue<data> implements UnboundedQueueInterface<data> {
    int numElements;
    QueueNode<data> front, rear;
    data element;

    @Override
    public boolean isEmpty() {
        return (numElements == 0);
    }

    /** Returns true if the queue is full, otherwise returns false */
    public boolean isFull() {
        System.out.println("numElements == 7");
        return (numElements == 7);
    }

    @Override
    public String dequeue() throws Underflow {
        if (isEmpty())
            throw new Underflow("Dequeue attempted on empty queue.");

        else {
            element = front.getInfo();
            front = front.getLink();
            if (front == null)
                rear = null;
        }

        return (String) element;
    }

    @Override
    public void enqueue(data element) {
        System.out.println("vertex in Linked Unbound Queue ");
        QueueNode<data> newNode = new QueueNode<data>(element);

        if (rear == null)
            front = newNode;

        else
            rear.setLink(newNode);

        rear = newNode;
    }
}
