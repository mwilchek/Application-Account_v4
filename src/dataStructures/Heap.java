package dataStructures;


import exceptions.PriQOverflowException;
import exceptions.PriQUnderflowException;

public class Heap<T extends Comparable<T>> implements PriorityQueueInterface<T>
{
    arrayList<T> elements;
    private int lastIndex, maxIndex;

    public Heap(int maxSize)
    {
        System.out.println("heap constructor");
        elements= new arrayList<T>(maxSize);
        lastIndex=-1;
        maxIndex=maxSize-1;
    }
    @Override
    public boolean isEmpty()
    {
        System.out.println("is empty method");
        return (lastIndex==-1);
    }

    /*public String toString()
    {
        binarySearchTree<T> t= new binarySearchTree<>();
        for(int place=0;place<elements.arrayOfCities.length;place++)
        {
            System.out.println("place "+place+" "+elements.arrayOfCities[place]);
            t.toString(elemets.arrayOfCities[place]);
        }
        return t.toString();
    }*/
    @Override
    public void enqueue(T element) throws PriQOverflowException
    // Throws PriQOverflowException if this priority queue is full;
    // otherwise, adds element to this priority queue.
    {
        System.out.println("enqeue");
        if (lastIndex == maxIndex)
            throw new PriQOverflowException("Priority queue is full");
        else
        {
            lastIndex++;
            elements.add(lastIndex, element);
            reheapUp(element);
        }
        return;
    }

    @Override
    public T dequeue() throws PriQUnderflowException
    // Throws PriQUnderflowException if this priority queue is empty;
    // otherwise, removes element with highest priority from this
    // priority queue and returns it.
    {
        System.out.println("deqeue method");
        T hold;      // element to be dequeued and returned
        T toMove;    // element to move down heap
        if (lastIndex == -1)
            throw new PriQUnderflowException("Priority queue is empty");
        else
        {
            hold = elements.get(0);              // remember element to be returned
            toMove = elements.remove(lastIndex); // element to reheap down
        }

        lastIndex--;
        if (lastIndex != -1)
            reheapDown(toMove);
        return hold;
    }

    private void reheapUp(T element)
    // Current lastIndex position is empty.
    // Inserts element into the tree and ensures shape and order properties.
    {
        System.out.println("rehapup");
        int hole = lastIndex;
        while ((hole > 0)                                // hole is not root
                && (element.compareTo(elements.get((hole - 1) / 2) ) > 0))// element > hole's parent
        {
            System.out.println("currently in while statement hole="+hole);
            elements.set(hole,elements.get((hole - 1) / 2));   // move hole's parent down
            hole = (hole - 1) / 2;                             // move hole up
        }
        System.out.println("not in while statment hole="+hole);
        elements.set(hole, element);                   // place element into final hole
        return;
    }
    private void reheapDown(T element)
    // Current root position is "empty";
    // Inserts element into the tree and ensures shape and order properties.
    {
        System.out.println("rehapdown");
        int hole = 0;      // current index of hole
        int newhole;       // index where hole should move to
        newhole = newHole(hole, element);   // find next hole
        while (newhole != hole)
        {System.out.println("newhole is not = hole");
            elements.set(hole, elements.get(newhole));  // move element up
        }
        System.out.println("outside while statement");
        hole = newhole;
        newhole = newHole(hole, element);
        elements.set(hole, element);
    }

    private int newHole(int hole, T element)
    //If either child of hole is larger than element return the index
    //of the larger child; otherwise return the index of hole.
    {
        System.out.println("newHole");
        int left = (hole * 2) + 1;
        int right = (hole * 2) + 2;
        if (left > lastIndex)// hole has no children
        {System.out.println("no children");
            return hole;}
        else
        if (left == lastIndex){ System.out.println("hole has left child");
            // hole has left child only
            System.out.println("element ="+element);
            if (element.compareTo(elements.get(left)) < 0)// element < left child
            {System.out.println("element<leftchild");
                return left;}
            else// element >= left child
                System.out.println("element="+element+" hole="+hole);
            return hole;}
        else System.out.println("hole has 2 children");
        // hole has two children
        if (elements.get(left).compareTo(elements.get(right)) < 0)// left child < right child
            if (elements.get(right).compareTo(element) <= 0)// right child <= element
            {System.out.println("left ="+elements.get(left)+"<= right="+elements.get(right));
                return hole;}
            else// element < right child
            {System.out.println("left ="+elements.get(left)+"< right="+elements.get(right));
                return right;}
        else
            // left child >= right child
            if (elements.get(left).compareTo(element) <= 0)// left child <= element
            {System.out.println("left="+elements.get(left)+" element="+element+"<=0");
                return hole;}
            else// element < left child
            {System.out.println("element="+element+"< left child");
                return left;}
    }
    public void movetoGraph() throws StackOverflowException, StackUnderflowException, QueueUnderflowException
    {
        elements.getToGraph();
        // TODO Auto-generated method stub

    }
}
