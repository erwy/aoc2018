package day9;

public class Node {
    private Node next;
    private Node previous;
    private long value;

    public Node(long value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node remove() {
        next.setPrevious(previous);
        previous.setNext(next);
        return this;
    }

    public void addAfter(Node node) {
        node.setNext(next);
        node.setPrevious(this);
        this.getNext().setPrevious(node);
        this.setNext(node);


    }

    public long getValue() {
        return value;
    }
}
