package sample;

public class LinkedNode<T> {
    private T data;
    private LinkedNode<T> previous;
    private LinkedNode<T> first;
    private LinkedNode<T> second;
    private LinkedNode<T> third;
    private LinkedNode<T> fourth;
    private boolean isVisited;

    LinkedNode(T data, LinkedNode<T> previous, LinkedNode<T> first, LinkedNode<T> second,
               LinkedNode<T> third, LinkedNode<T> fourth, boolean isVisited) {
        this.data = data;
        this.previous = previous;
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.isVisited = isVisited;
    }

    LinkedNode(T data) {
        this(data, null, null, null, null, null, true);
    }

    public T getData() {
        return data;
    }

    LinkedNode<T> getPrevious() {
        return previous;
    }

    LinkedNode<T> getFirst() {
        return first;
    }

    LinkedNode<T> getSecond() {
        return second;
    }

    LinkedNode<T> getThird() {
        return third;
    }

    LinkedNode<T> getFourth() {
        return fourth;
    }

    boolean getIsVisited() {
        return isVisited;
    }

    void setPrevious(LinkedNode<T> previous) {
        this.previous = previous;
    }

    void setFirst(LinkedNode<T> first) {
        this.first = first;
    }

    void setSecond(LinkedNode<T> second) {
        this.second = second;
    }

    void setThird(LinkedNode<T> third) {
        this.third = third;
    }

    void setFourth(LinkedNode<T> fourth) {
        this.fourth = fourth;
    }

    void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    @Override
    public String toString() {
        return "Node containing " + data;
    }
}
