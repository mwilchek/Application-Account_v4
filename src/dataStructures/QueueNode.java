package dataStructures;


public class QueueNode<data> {

    protected QueueNode<data> front;
    protected QueueNode<data> rear;
    private QueueNode link;
    private data info;

    public QueueNode(data info) {
        this.info = info;
        link = null;
        front = null;
        rear = null;
    }

    public QueueNode<data> getFront() {
        return front;
    }

    public void setFront(QueueNode<data> front) {
        this.front = front;
    }

    public QueueNode<data> getRear() {
        return rear;
    }

    public void setRear(QueueNode<data> rear) {
        this.rear = rear;
    }

    public data getInfo() {
        return info;
    }

    public void setInfo(data info) {
        this.info = info;
    }

    public QueueNode getLink() {
        return link;
    }

    public void setLink(QueueNode link) {
        this.link = link;
    }
}
