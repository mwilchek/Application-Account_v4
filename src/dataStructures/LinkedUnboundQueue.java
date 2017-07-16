package dataStructures;

import exceptions.Underflow;

public class LinkedUnboundQueue implements UnboundedQueueInterface<data>
{
    int numElements;
    LinkedListNode<data> front, rear;
    data element;

    @Override
    public boolean isEmpty()
    {
        return (numElements == 0);
    }
    public boolean isFull()
    // Returns true if this queue is full, otherwise returns
    {
        System.out.println("numElements ==7");
        return (numElements == 7);
    }
    @Override
    public String dequeue() throws Underflow
    {
        if (isEmpty())
            throw new Underflow("Dequeue attempted on empty queue.");
        else
        {
            element = front.getInfo();
            front = front.getLink();
            if (front == null)
                rear = null;
        }
        return (String) element;
    }

    @Override
    public void enqueue(data element)
    {
        System.out.println("vertex in LinkedunbndQeue");
        LinkedListNode<data> newNode = new LinkedListNode<data>(element);
        if (rear == null)
            front = newNode;
        else
            rear.setLink(newNode);
        rear = newNode;
    }
}
