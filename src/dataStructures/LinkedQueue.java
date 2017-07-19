package dataStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedQueue<data> implements Iterable<data> {
    private int N;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue

    /** Initializes an empty queue */
    public LinkedQueue() {
        first = null;
        last = null;
        N = 0;
        assert check();
    }

    /** Checks if the priority queue is empty.
     * @return true if this queue is empty; false otherwise */
    public boolean isEmpty() {
        return first == null;
    }

    /** Returns the number of items in the priority queue
     * @return the number of items in this queue */
    public int size() {
        return N;
    }

    /** Returns the item least recently added to the queue
     * @return the item least recently added to the queue
     * @throws java.util.NoSuchElementException if the queue is empty */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return (Item) first.item;
    }

    /** Adds the item to the queue
     * @param item the item to add */
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = (data) item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
        assert check();
    }

    /** Removes and returns the item on the queue that was least recently added
     * @return the item on the queue that was least recently added
     * @throws java.util.NoSuchElementException if the queue is empty */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = (Item) first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        assert check();
        return item;
    }

    /** Returns a string representation of the queue.
     * @return the sequence of items in FIFO order, separated by spaces */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (data item : this) {
            s.append(item + " ");
        }
        return s.toString();
    }

    /** Checks internal invariants */
    private boolean check() {
        if (N < 0) {
            return false;
        } else if (N == 0) {
            if (first != null) return false;
            if (last != null) return false;
        } else if (N == 1) {
            if (first == null || last == null) return false;
            if (first != last) return false;
            if (first.next != null) return false;
        } else {
            if (first == null || last == null) return false;
            if (first == last) return false;
            if (first.next == null) return false;
            if (last.next != null) return false;

            // checks instance variable N
            int numberOfNodes = 0;
            for (Node x = first; x != null && numberOfNodes <= N; x = x.next) {
                numberOfNodes++;
            }

            if (numberOfNodes != N) return false;

            // checks instance variable last
            Node lastNode = first;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            if (last != lastNode) return false;
        }

        return true;
    }

    /** Returns an iterator that iterates over the items in the queue in FIFO order
     * @return an iterator that iterates over the items in the queue in FIFO order */
    public Iterator<data> iterator() {
        return (Iterator<data>) new ListIterator();
    }

    /** Instantiate linked list class*/
    private class Node {
        private data item;
        private Node next;
    }

    /** An iterator class that doesn't implement remove() to make it optional */
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = (Item) current.item;
            current = current.next;
            return item;
        }
    }
}