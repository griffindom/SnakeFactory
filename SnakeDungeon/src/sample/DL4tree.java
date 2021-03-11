package sample;

public class DL4tree<T> {
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    DL4tree(LinkedNode<T> head, LinkedNode<T> tail, int size) {
        this.head = head;
        this.tail = tail;
        this.size = size;
    }
    DL4tree(LinkedNode<T> root, LinkedNode<T> tail) {
        this(root, tail, 1);
    }
    public void addToFirst(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data type into a data structure");
        }
        LinkedNode<T> newNode = new LinkedNode<T>(data);
        newNode.setPrevious(tail);
        tail.setFirst(newNode);
        newNode.setFirst(null);
        newNode.setSecond(null);
        newNode.setThird(null);
        newNode.setFourth(null);
        tail = newNode;
        tail.setPrevious(head);
        size++;
        }
    public void addToSecond(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data type into a data structure");
        }
        LinkedNode<T> newNode = new LinkedNode<T>(data);
        newNode.setPrevious(tail);
        tail.setSecond(newNode);
        newNode.setFirst(null);
        newNode.setSecond(null);
        newNode.setThird(null);
        newNode.setFourth(null);
        tail = newNode;
        tail.setPrevious(head);
        size++;
    }
    public void addToThird(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data type into a data structure");
        }
        LinkedNode<T> newNode = new LinkedNode<T>(data);
        newNode.setPrevious(tail);
        tail.setThird(newNode);
        newNode.setFirst(null);
        newNode.setSecond(null);
        newNode.setThird(null);
        newNode.setFourth(null);
        tail = newNode;
        tail.setPrevious(head);
        size++;
    }
    public void addToFourth(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data type into a data structure");
        }
        LinkedNode<T> newNode = new LinkedNode<T>(data);
        newNode.setPrevious(tail);
        tail.setFourth(newNode);
        newNode.setFirst(null);
        newNode.setSecond(null);
        newNode.setThird(null);
        newNode.setFourth(null);
        tail = newNode;
        tail.setPrevious(head);
        size++;
    }
    public void goToPrevious() {
        if (head.getPrevious() == null) {
            throw new NullPointerException("Cant go back from the starting room");
        }
        LinkedNode<T> temp = head;
        temp.setPrevious(head);
        temp = tail;
        temp.setPrevious(head);
    }
    public void goToFirst() {
        LinkedNode<T> temp = tail;
        temp.setFirst(tail);
        tail.setPrevious(head);
    }
    public void goToSecond() {
        LinkedNode<T> temp = tail;
        temp.setSecond(tail);
        tail.setPrevious(head);
    }
    public void goToThird() {
        LinkedNode<T> temp = tail;
        temp.setThird(tail);
        tail.setPrevious(head);
    }
    public void goToFourth() {
        LinkedNode<T> temp = tail;
        temp.setFourth(tail);
        tail.setPrevious(head);
    }
    public int size() {
        return size;
    }
}
