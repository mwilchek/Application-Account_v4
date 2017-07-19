package dataStructures;

public interface WeightedGraphInterface<data> {
    void clearMarks();

    boolean isEmpty();

    boolean isFull();

    boolean isMarked(String vertex);

    void addVertex(data vertex);

    boolean hasVertex(data vertex);

    boolean markVertex(String vertex);

    UnboundedQueueInterface<String> getToVertices(String vertex);

    void addEdge(String startVertex, String endVertex, double weight);

    int weightIs(data fromVertex, data toVertex);

    data getUnmarked();
}