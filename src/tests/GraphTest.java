package tests;

/**
 * Created by seeme on 7/16/2017.
 */

import dataStructures.BinarySearchTree;
import dataStructures.Heap;
import exceptions.PriQOverflowException;
import exceptions.PriQUnderflowException;

import java.util.Scanner;

public class GraphTest {
    public static int amountOfPlaces;
    public static String town;
    //PriQueueInterface<String> in= new PriQueueInterface<>();
    //static weightedGraph<String> weight= new weightedGraph<>();
    static Scanner input = new Scanner(System.in);

    public static <T> void main(String args[]) throws PriQOverflowException
            , PriQUnderflowException, StackOverflowException, StackUnderflowException, QueueUnderflowException {
        //System.out.println(" How many cities do you want to be added");
        amountOfPlaces = 7;// input.nextInt();
        Heap<String> heap = new Heap<>(amountOfPlaces);

        String first = "washington";
        String second = "atlanta";
        String third = "dallas";
        String fourth = "houston";
        String fifth = "austin";
        String sixth = "denver";
        String seventh = "chicago";
        heap.enqueue(first);
        System.out.println();
        heap.enqueue(second);
        System.out.println();
        heap.enqueue(third);
        System.out.println();
        heap.enqueue(fourth);
        System.out.println();
        heap.enqueue(fifth);
        System.out.println();
        heap.enqueue(sixth);
        System.out.println();
        heap.enqueue(seventh);




		/*for (int temp=1;temp<=amountOfPlaces;temp++)
        {
			System.out.println("What's the name of town #"+temp+" you want in the list?");
			town=input.next();
			heap.enqueue(town);
		}*/
        heap.movetoGraph();
        //weight.
        //heap.dequeue();
        //method();
    }

    public static <data> void method() {
        data element = (data) input.next();
        BinarySearchTree<data> t = new BinarySearchTree<>();
        t.contains(element);
    }
}
