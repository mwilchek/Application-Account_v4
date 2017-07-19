package dataStructures;

import exceptions.Overflow;
import exceptions.Underflow;
import java.util.Scanner;

public class arrayList<data> implements Comparable<Object> {

    public int sizeCap = 7;
    public int place;
    public String[] arrayOfCities = new String[sizeCap];

    weightedGraph<String> weight = new weightedGraph<>();
    private data element, temp;
    private int maxSize;

    public arrayList(int maxSize) {
        weight = new weightedGraph<>(maxSize);
        System.out.println("arrayList constructor");
        this.maxSize = maxSize;
    }

    public data getElement() {
        return element;
    }

    public void setElement(data element) {
        this.element = element;
    }

    public int getMaxSize() {
        System.out.println("arraylist getMaxSize()");
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        System.out.println("arrayList setMaxSize");
        this.maxSize = maxSize;
    }

    public data remove(int lastIndex) {
        arrayOfCities[lastIndex] = null;
        return null;
    }

    /** Get is made to retrieve child of the passed in position from the array and delete it from the linked list */
    @SuppressWarnings("unchecked")
    public data get(int hole) {
        System.out.println("arrayList get 2nd");
        System.out.println("searching for position number " + hole);

        for (place = 0; place <= hole; place++) {
            if ((hole) == place) {
                setElement((data) arrayOfCities[place]);
                System.out.println("now getting " + getElement() + " from arrayOfCities");
                System.out.println("from place number " + place);
            }
        }

        return getElement();
    }

    /** Place the element from get method into a new place in the array and place it back in the LinkedList*/
    public data set(int hole, data newElement) {
        System.out.println("arraylist has been set.");
        System.out.println("This element needs a new position:" + getElement() + " in position " + place);
        System.out.println("It will switch with " + newElement + " in position " + hole);
        if (place != hole) {
            for (int newPlace = 0; newPlace <= hole; newPlace++) {
                if (newPlace == hole) {
                    System.out.println("Place #" + hole + " " + arrayOfCities[hole]);
                    temp = (data) arrayOfCities[hole];
                }
            }
            for (int oldPlace = 0; oldPlace <= place; oldPlace++) {
                if (oldPlace == place) {
                    arrayOfCities[hole] = arrayOfCities[place];
                    arrayOfCities[place] = (String) temp;
                }
            }

        }
        for (int place = 0; place < arrayOfCities.length; place++) {
            System.out.println("Place " + place + " " + arrayOfCities[place]);
        }

        return getElement();
    }

    /** Adds element to tree; tree retains its BST property */
    public data add(int lastIndex, data element) {
        setElement(element);
        System.out.println("Now in arrayList add");
        System.out.println("Passed in element = " + getElement());
        System.out.println("lastIndex == " + lastIndex);
        arrayOfCities[lastIndex] = (String) getElement();

        return getElement();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public void getToGraph(double distance) throws Overflow, Underflow {

        // add a vertex for newly added data objects in array List
        weight.addVertex(arrayOfCities[0]);
        weight.addVertex(arrayOfCities[1]);
        weight.addVertex(arrayOfCities[2]);
        weight.addVertex(arrayOfCities[3]);
        weight.addVertex(arrayOfCities[4]);
        weight.addVertex(arrayOfCities[5]);
        weight.addVertex(arrayOfCities[6]);

        // add edges to in between each newly added vertexes with defined weights
        weight.addEdge(arrayOfCities[0], arrayOfCities[0], distance); //0
        weight.addEdge(arrayOfCities[0], arrayOfCities[1], distance); //400
        weight.addEdge(arrayOfCities[0], arrayOfCities[2], distance); //1100
        weight.addEdge(arrayOfCities[1], arrayOfCities[3], distance); //1300
        weight.addEdge(arrayOfCities[2], arrayOfCities[4], distance); //1500
        weight.addEdge(arrayOfCities[2], arrayOfCities[5], distance); //2100
        weight.addEdge(arrayOfCities[2], arrayOfCities[6], distance); //200

        // get first and last vertex/nodes from array List
        setElement((data) arrayOfCities[0]);
        String endElement = arrayOfCities[arrayOfCities.length - 1];

        // update weights based on paths/directions for each node
        System.out.println();
        weight.isPath(weight, arrayOfCities[0], arrayOfCities[0]);
        System.out.println();
        weight.isPath(weight, arrayOfCities[0], arrayOfCities[1]);
        System.out.println();
        weight.isPath(weight, arrayOfCities[0], arrayOfCities[2]);
        System.out.println();
        weight.isPath(weight, arrayOfCities[1], arrayOfCities[3]);
        System.out.println();
        weight.isPath(weight, arrayOfCities[2], arrayOfCities[4]);
        System.out.println();
        weight.isPath(weight, arrayOfCities[2], arrayOfCities[5]);
        System.out.println();
        weight.isPath(weight, arrayOfCities[2], arrayOfCities[6]);
    }
}
