package tests;

import dataStructures.BinarySearchTree;
import dataStructures.Heap;
import java.util.Scanner;

public class HeapToGraphTest {
    public static int amountOfPlaces;
    public static String town;
    //PriQueueInterface<String> in = new PriQueueInterface<>();
    //static weightedGraph<String> weight = new weightedGraph<>();
    static Scanner input = new Scanner(System.in);

    public static <data> void main(String args[]) throws Exception {
        //System.out.println("How many cities do you want to be added? ");
        //amountOfPlaces = input.nextInt();
        amountOfPlaces = 7;
        Heap<String> heap = new Heap<>(amountOfPlaces);

        String Washington = "Washington";
        String Atlanta = "Atlanta";
        String Dallas = "Dallas";
        String Houston = "Houston";
        String Austin = "Austin";
        String Denver = "Denver";
        String Chicago = "Chicago";

        // Adds String data to Priority Queue
        heap.enqueue(Washington);
        System.out.println();
        heap.enqueue(Atlanta);
        System.out.println();
        heap.enqueue(Dallas);
        System.out.println();
        heap.enqueue(Houston);
        System.out.println();
        heap.enqueue(Austin);
        System.out.println();
        heap.enqueue(Denver);
        System.out.println();
        heap.enqueue(Chicago);
        System.out.println();

        double distance = 100;

        for (int i=0; i <= 7; i++)
            distance += 200;
            heap.moveToGraph(distance); // Test move heap to graph structure
    }

    public static <data extends Comparable<data>> void test() {
        data element = (data) input.next();
        BinarySearchTree<data> t = new BinarySearchTree<>();
        t.contains(element);
    }
}
