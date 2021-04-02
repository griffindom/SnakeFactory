package game.support;

public class DL4tree<T> {
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    DL4tree(LinkedNode<T> head, LinkedNode<T> tail, int size) {
        this.head = head;
        this.tail = tail;
        this.size = size;
    }
    DL4tree(LinkedNode<T> head, LinkedNode<T> tail) {
        this(head, tail, 1);
    }

    public void addToFirst(T data) {
        if (data == null) {
            throw new IllegalArgumentException("NULL DATA");
        }
        LinkedNode<T> newNode = new LinkedNode<T>(data);
        newNode.setPrevious(tail);
        tail.setFirst(newNode);
        newNode.setFirst(null);
        newNode.setSecond(null);
        newNode.setThird(null);
        newNode.setFourth(null);
        head = tail;
        tail = newNode;
        size++;
    }

    public void addToSecond(T data) {
        if (data == null) {
            throw new
                    IllegalArgumentException("Cannot insert null data type into a data structure");
        }
        LinkedNode<T> newNode = new LinkedNode<T>(data);
        newNode.setPrevious(tail);
        tail.setSecond(newNode);
        newNode.setFirst(null);
        newNode.setSecond(null);
        newNode.setThird(null);
        newNode.setFourth(null);
        head = tail;
        tail = newNode;
        size++;
    }

    public void addToThird(T data) {
        if (data == null) {
            throw new
                    IllegalArgumentException("Cannot insert null data type into a data structure");
        }
        LinkedNode<T> newNode = new LinkedNode<T>(data);
        newNode.setPrevious(tail);
        tail.setThird(newNode);
        newNode.setFirst(null);
        newNode.setSecond(null);
        newNode.setThird(null);
        newNode.setFourth(null);
        head = tail;
        tail = newNode;
        size++;
    }
    
    public void addToFourth(T data) {
        if (data == null) {
            throw new
                    IllegalArgumentException("Cannot insert null data type into a data structure");
        }
        LinkedNode<T> newNode = new LinkedNode<T>(data);
        newNode.setPrevious(tail);
        tail.setFourth(newNode);
        newNode.setFirst(null);
        newNode.setSecond(null);
        newNode.setThird(null);
        newNode.setFourth(null);
        head = tail;
        tail = newNode;
        size++;
    }
    public LinkedNode<T> goToPrevious() {
        if (tail.getPrevious() == null) {
            throw new NullPointerException("Cant go back from the starting room");
        }
        tail = head;
        head = tail.getPrevious();
        return tail;
    }
    public void goToFirst() {
        LinkedNode<T> temp = tail;
        head = tail;
        tail = temp.getFirst();
    }
    public void goToSecond() {
        LinkedNode<T> temp = tail;
        head = tail;
        tail = temp.getSecond();
    }
    public void goToThird() {
        LinkedNode<T> temp = tail;
        head = tail;
        tail = temp.getThird();
    }
    public void goToFourth() {
        LinkedNode<T> temp = tail;
        head = tail;
        tail = temp.getFourth();
    }

    public LinkedNode<T> getTail() {
        return this.tail;
    }

    public int getSize() {
        return this.size;
    }
}
