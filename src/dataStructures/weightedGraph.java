package dataStructures;

public class weightedGraph<data> implements WeightedGraphInterface<data>
{
    weightedGraph<String> graph;
    public int index=0;
    public static final int NULL_EDGE = 0;
    private static final int DEFCAP = 50;
    public int maxVertices, numVertices;
    public data[] vertices;
    private data vertex;
    public data getVertex() {
        return vertex;
    }
    public void setVertex(data vertex) {
        this.vertex = vertex;
    }
    public data[] getVertices() {
        return vertices;
    }
    public void setVertices(data[] vertices) {
        this.vertices = vertices;
    }
    private int[][] edges;
    private boolean[] marks;
    static public String endVertex;
    @SuppressWarnings("unchecked")
    public weightedGraph()
    // Instantiates a graph with capacity DEFCAP vertices.
    {
        System.out.println("top constructor");
        numVertices = 0;
        maxVertices = DEFCAP;
        vertices = (data[ ]) new Object[DEFCAP];
        marks = new boolean[DEFCAP];
        edges = new int[DEFCAP][DEFCAP];
    }
    @SuppressWarnings("unchecked")
    public weightedGraph(int maxV)
    // Instantiates a graph with capacity maxV.
    {
        System.out.println(" second constructor");
        numVertices = 0;
        maxVertices = maxV;
        vertices = (data[ ]) new Object[maxV];
        marks = new boolean[maxV];
        edges = new int[maxV][maxV];
    }
    public void addVertex(data vertex)
    // Preconditions:
    //This graph is not full.
    //Vertex is not already in this graph.
    //Vertex is not null.
    // Adds vertex to this graph.
    {
        System.out.println();
        System.out.println();
        System.out.println("WEIGHTEDGRAPH");
        System.out.println("addVertex");
        vertices[numVertices] = vertex;
        System.out.println("vertices["+numVertices+"]="+vertices[numVertices]+" vertex "+vertex);
        for (int index = 0; index < numVertices; index++)
        {
            edges[numVertices][index] = NULL_EDGE;
            System.out.println("edges ="+NULL_EDGE);
            edges[index][numVertices] = NULL_EDGE;
            System.out.println("edges ="+NULL_EDGE);
        }
        numVertices++;
        setVertex(vertex);
    }
    @Override
    public void addEdge(data startVertex, data endVertex, int weight) {
        System.out.println();
        System.out.println("addEdge");
        int row;
        int column;
        row = indexIs(startVertex);
        column = indexIs(endVertex);
        edges[row][column] = weight;
        if(row<7 && column<7)
        {
            System.out.println("startVertex="+startVertex+" endVertex="+endVertex);
            System.out.println("row="+row+" column="+column);
            System.out.println("weight="+weight);
        }
    }

    @SuppressWarnings("unused")
    private int indexIs(data startVertex)
    {
        index=0;
        vertices= getVertices();
        System.out.println("indexIs method");
        System.out.println("startVertex="+startVertex);
        if( startVertex==null || startVertex!=null)
        {
            setVertices(vertices);
            return index;
        }
        else if(index<vertices.length)//(startVertex!=null && vertices[index]!=null)
        {
            System.out.println("vertices "+vertices[index]);
            while (!startVertex.equals(vertices[index]) && index<vertices.length)
            {
                System.out.println("index="+index);
                if(startVertex.equals(vertices[index]))
                {
                    setVertices(vertices);
                    return index;
                }
                index++;
            }
        }
        setVertices(vertices);
        return index;
    }
    @SuppressWarnings("unused")
    public  boolean isPath(weightedGraph<String> graph, String startVertex, String endVertex) throws StackOverflowException,
            StackUnderflowException, QueueUnderflowException
    {
        System.out.println();
        System.out.println("isPath");
        UnboundedStackInterface<String> stack = new LinkedListStack<String> ();
        UnboundedQueueInterface<String> vertexQueue = new LinkedUnbndQueue<String>();
        boolean found = false;
        String Vertex = null;//= (String) getVertex();
        String item;
        graph.clearMarks();
        stack.push(startVertex);
        do
        {
            System.out.println("START of do statement");
            Vertex = stack.top(Vertex);
            stack.pop();
            if (Vertex .contains(endVertex))
            {
                System.out.println(Vertex+"vertex == endvertex"+endVertex);
                System.out.println("just traveled from "+Vertex+" to "+endVertex);
                found = true;
            }
            else
            {
                System.out.println(Vertex+" vertex != endvertex "+endVertex);
                if (!graph.isMarked(Vertex))
                {
                    System.out.println("!graph.isMarked(vertex)");
                    graph.markVertex(Vertex);
                    vertexQueue = graph.getToVertices(Vertex);//gets cities close by
                    while (!vertexQueue.isEmpty())
                    {
                        System.out.println("!vertexQueue.isEmpty()");
                        item = vertexQueue.dequeue();
                        if (!graph.isMarked(item))
                            stack.push(item);
                    }
                }
                System.out.println("unable to find other town");
            }
        } while (!stack.isEmpty() && !found);
        return found;
    }

    @Override
    public void clearMarks() {
        // Sets marks for all vertices to false.
        //numVertices = 0;
        //vertices = (T[ ]) new Object[maxVertices];
        //marks = new boolean[maxVertices];
        //edges = new int[maxVertices][maxVertices];
        if(isEmpty())
        {
            System.out.println("clearMarks");

            for(int place=0; place<=maxVertices-1;place++)
            {
                if(marks[place]==true)
                {
                    System.out.println("marks==true");
                    marks[place]=false;
                }
                System.out.println("marks now "+marks[place]);
            }
        }
        else
        {
            System.out.println("clearMarks empty");
        }
    }
    @Override
    public boolean isMarked(String vertex) {
        // Returns true if vertex is marked; otherwise, returns false.
        if(vertex==null)
        {
            System.out.println("not marked");
            return false;
        }
        System.out.println("isMarked");
        return true;
    }
    @Override
    public boolean markVertex(String vertex) {
        // Sets mark for vertex to true.
        System.out.println("markVertex");
        if(vertex==null)
        {
            for(index=0;index<=maxVertices-1;index++)//maxVertices
            {
                System.out.println(vertex+"!="+vertices[index]);
                if(vertex==vertices[index] && vertices[index]==null)
                {
                    System.out.println(vertex+" vertex=vertices[index] "+vertices[index]);
                    vertex=(String) vertices[index];
                    return true;
                }
            }
        }
        System.out.println("returns true");
        return true;
    }
    @SuppressWarnings("unchecked")
    @Override
    public UnboundedQueueInterface<String> getToVertices(String vertex) {
        // Returns a queue of the vertices that are adjacent from vertex.
        System.out.println("getToVertices");
        System.out.println("vertex="+vertex);
        UnboundedQueueInterface<data> adjVertices = new LinkedUnbndQueue<data>();
        int start;
        int end;
        start = indexIs((T) vertex);
        for (end = 0; end < numVertices; end++)
        {
            System.out.println("start="+start+" end"+end);
            System.out.println("edges="+edges[start][end]+" nulledge="+ NULL_EDGE);
            if (edges[start][end] != NULL_EDGE)// corrects start -1
            {
                System.out.println("start="+start+" end"+end);
                System.out.println("edges="+edges[start][end]);
                adjVertices.enqueue(vertices[end]);
            }
        }
        //System.out.println("end ="+vertices[end]);
        return (UnboundedQueueInterface<String>) adjVertices;
    }
    public boolean isEmpty(weightedGraph<String> graph)
    {
        if(graph==null)
        {
            System.out.println("isEmpty");
            return true;
        }
        System.out.println("notEmpty");
        return false;
    }
    @Override
    public boolean isFull()
    {
        System.out.println("isFull");
        return getVertices().length==7;
    }
    @Override
    public boolean hasVertex(data vertex) {
        // Returns true if this graph contains vertex; otherwise, returns false.
        System.out.println("hasVertex");
        return false;
    }

    @Override
    public int weightIs(data fromVertex, data toVertex) {
        System.out.println("weightIs");
        return 0;
    }
    @Override
    public data getUnmarked()
    {
        System.out.println("getUnMarked");
        return null;
    }
    @Override
    public boolean isEmpty()
    {
        System.out.println("isEmpty()");
        if(getVertices().length==-1)
        {return getVertices().length==-1;}
        return true;
    }
}
