package dataStructures;


import exceptions.Overflow;
import exceptions.PriQOverflowException;
import exceptions.PriQUnderflowException;
import exceptions.Underflow;

public class Heap<data extends Comparable<data>> implements PriorityQueueInterface<data> {
    arrayList<data> elements;
    private int lastIndex, maxIndex;

    public Heap(int maxSize) {
        elements = new arrayList<data>(maxSize);
        lastIndex = -1;
        maxIndex = maxSize - 1;
    }

    @Override
    public boolean isEmpty() {
        return (lastIndex == -1);
    }

    /** Throws PriQOverflowException if the priority queue is full
     *  otherwise, adds element to this priority queue*/
    @Override
    public void enqueue(data element) throws PriQOverflowException {
        if (lastIndex == maxIndex)
            throw new PriQOverflowException("Priority queue is full");

        else {
            lastIndex++;
            elements.add(lastIndex, element);
            reheapUp(element);
        }

        return;
    }

    /** Throws PriQUnderflowException if the priority queue is empty
     * otherwise, removes element with highest priority from the
     * priority queue and returns it.*/
    @Override
    public data dequeue() throws PriQUnderflowException {
        data hold;      // element to be dequeued and returned
        data toMove;    // element to move down heap
        if (lastIndex == -1)
            throw new PriQUnderflowException("Priority queue is empty");

        else {
            hold = elements.get(0);              // remember element to be returned
            toMove = elements.remove(lastIndex); // element to reheap down
        }

        lastIndex--;
        if (lastIndex != -1)
            reheapDown(toMove);

        return hold;
    }

    /** Current lastIndex position is empty.
     * Inserts element into the tree and ensures shape and order properties.*/
    private void reheapUp(data element) {
        int hole = lastIndex;

        // hole is not root
        while ((hole > 0)
                && (element.compareTo(elements.get((hole - 1) / 2)) > 0)) // element > hole's parent
        {
            System.out.println("currently in while statement hole = " + hole);
            elements.set(hole, elements.get((hole - 1) / 2));   // move hole's parent down
            hole = (hole - 1) / 2;                             // move hole up
        }

        System.out.println("not in while statment hole=" + hole);
        elements.set(hole, element);    // place element into final hole

        return;
    }

    /** Current root position is "empty";
     * Inserts element into the tree and ensures shape and order properties.*/
    private void reheapDown(data element) {
        int hole = 0;      // current index of hole
        int newhole;       // index where hole should move to
        newhole = newHole(hole, element);   // find next hole

        while (newhole != hole) {
            System.out.println("newhole is not = hole");
            elements.set(hole, elements.get(newhole));  // move element up
        }

        System.out.println("outside while statement");
        hole = newhole;
        newhole = newHole(hole, element);
        elements.set(hole, element);
    }

    /** If either child of hole is larger than element return the index
     * of the larger child; otherwise return the index of hole. */
    private int newHole(int hole, data element) {
        int left = (hole * 2) + 1;
        int right = (hole * 2) + 2;

        // hole has no children
        if (left > lastIndex)
        {
            System.out.println("no children");
            return hole;
        } else if (left == lastIndex) {
            System.out.println("hole has left child");
            // hole has left child only
            System.out.println("element = " + element);
            if (element.compareTo(elements.get(left)) < 0)// element < left child
            {
                System.out.println("element < leftchild");
                return left;
            } else// element >= left child
                System.out.println("element = " + element + " hole = " + hole);
            return hole;
        } else System.out.println("hole has 2 children");

        // hole has two children
        if (elements.get(left).compareTo(elements.get(right)) < 0) // left child < right child
            if (elements.get(right).compareTo(element) <= 0) // right child <= element
            {
                System.out.println("left = " + elements.get(left) + "<= right = " + elements.get(right));
                return hole;
            } else// element < right child
            {
                System.out.println("left = " + elements.get(left) + "< right= " + elements.get(right));
                return right;
            }
        else
            // left child >= right child
            if (elements.get(left).compareTo(element) <= 0)// left child <= element
            {
                System.out.println("left = " + elements.get(left) + " element = " + element + " <= 0");
                return hole;
            } else// element < left child
            {
                System.out.println("element = " + element + "< left child");
                return left;
            }
    }

    /** Structures heap data into graph data structure */
    public void moveToGraph(double distance) throws Overflow, Underflow {
        elements.getToGraph(distance);
        // TODO Auto-generated method stub
    }
}
