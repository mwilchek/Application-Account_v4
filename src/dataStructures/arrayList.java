package dataStructures;
import java.util.Scanner;
public class arrayList<T> implements Comparable<Object>
{

    private T element, delete, temp;
    Scanner input= new Scanner(System.in);
    //binarySearchTree<T> tree= new binarySearchTree<>();
    private int maxSize;
    public int place;
    weightedGraph<String> weight = new weightedGraph<>();
    //	private int maxxSize=maxSize+1;
    public String []arrayOfCities= new String [7];//leaving maxsize without adding 1 creates error

    public arrayList(int maxSize)
    {
        weight= new weightedGraph<>(maxSize);
        System.out.println("arraylist constructor");
        this.maxSize=maxSize;
    }

    public T getElement() {
        return element;
    }
    public void setElement(T element) {
        this.element = element;
    }
    public void setMaxSize(int maxSize)
    {
        System.out.println("arraylist setMaxsize");
        this.maxSize = maxSize;
    }
    public int getMaxSize()
    {
        System.out.println("arraylist getmaxsize");
        return maxSize;
    }

    public T remove(int lastIndex)
    {
        arrayOfCities[lastIndex]=null;
        return null;
    }
    @SuppressWarnings("unchecked")
    public T get(int hole)
    {// get is made to retrieve child of the passed in position from the array and delete it from the linked list
        System.out.println("arraylist get 2nd");
        System.out.println("searching for position number "+hole);
        for(place=0;place<=hole;place++)
        {
            if((hole)==place)
            {
                //delete= (T) (delete+arrayOfCities[place]);
                setElement((T) arrayOfCities[place]);
                //T delete= (T) arrayOfCities[place];
                //tree.contains(delete);
                System.out.println("now getting "+getElement()+" from arrayofcities");
                //\\arrayOfCities[place]=null;
                System.out.println("from place number "+place);
            }
        }
        System.out.println("now leaving get method");
        return getElement();
    }
    public T set(int hole, T newElement)
    {// place the element from get method into a new place in the array and place it back in the linkedlist
        // in their correct positions (switch)

        System.out.println("arraylist set");
        System.out.println("this element needs a new position-->"+getElement()+" in position "+place);
        System.out.println("it will switch with "+newElement+" in position "+hole);
        if(place!=hole)//(!((String) newElement).contains((CharSequence) getElement()))
        {
            for(int newPlace=0;newPlace<=hole;newPlace++)//atlanta loop
            {
                if(newPlace==hole)
                {
                    System.out.println("place # "+hole+" "+arrayOfCities[hole]);
                    temp=(T) arrayOfCities[hole];
                }
            }
            for(int oldPlace=0;oldPlace<=place;oldPlace++)//atlanta loop
            {
                if(oldPlace==place)
                {
                    arrayOfCities[hole]=arrayOfCities[place];
                    arrayOfCities[place]=(String) temp;
                    //System.out.println("oldplace # "+place+" "+arrayOfCities[place]);
                }
            }

            //			arrayOfCities[place]=(String) getElement();
            //			if(arrayOfCities[place]==null)
            //			{
            //				//System.out.println("add in new element");
            //				//newElement=(T) input.next();
            //				//input.nextLine();
            //				System.out.println(newElement+" is now in the empty space");
            //				arrayOfCities[place]=(String) newElement;
            //			}
            //			else if (place==hole)
            //			{
            //				System.out.println(getElement()+" is now in position "+hole);
            //				arrayOfCities[hole]=(String) getElement();
            //			}

        }
        for(int place=0;place<arrayOfCities.length;place++)
        {
            System.out.println("place "+place+" "+arrayOfCities[place]);
            //tree.toString(arrayOfCities[place]);
        }
        //tree.contains(newElement);
        //System.out.println("tree.list="+tree.list);

        return getElement();
    }
    public T add (int lastIndex, T element)
    {// Adds element to tree; tree retains its BST property.
        //adds it to an array and then returns that specific city
        setElement(element);
        System.out.println("now in arrayList add");
        System.out.println("passed in element="+getElement());
        System.out.println("lastIndex=="+lastIndex);
        arrayOfCities[lastIndex]=(String) getElement();
        //weight.addVertex(arrayOfCities[lastIndex]);
        //tree.add(getElement());
        return getElement();
    }
    @Override
    public int compareTo(Object o)
    {
        return 0;
    }

    public void getToGraph() throws StackOverflowException, StackUnderflowException, QueueUnderflowException
    {
        weight.addVertex(arrayOfCities[0]);
        weight.addVertex( arrayOfCities[1]);
        weight.addVertex(arrayOfCities[2]);
        weight.addVertex(arrayOfCities[3]);
        weight.addVertex(arrayOfCities[4]);
        weight.addVertex(arrayOfCities[5]);
        weight.addVertex(arrayOfCities[6]);

        weight.addEdge(arrayOfCities[0], arrayOfCities[0], 0);
        weight.addEdge(arrayOfCities[0],arrayOfCities[1], 600);
        weight.addEdge(arrayOfCities[0], arrayOfCities[2],1300 );
        weight.addEdge(arrayOfCities[1], arrayOfCities[3], 1400);
        weight.addEdge(arrayOfCities[2], arrayOfCities[4], 1500);
        weight.addEdge(arrayOfCities[2], arrayOfCities[5], 2080);
        weight.addEdge(arrayOfCities[2], arrayOfCities[6], 220);

        setElement((T) arrayOfCities[0]);
        String endElement= arrayOfCities[arrayOfCities.length-1];
        //weightedGraph<String> graph = new weightedGraph<>();

        //weight.getToPath((String) getElement(), endElement);
        //weight.isPath(weight, (String) getElement(), endElement);
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
