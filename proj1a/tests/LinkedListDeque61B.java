import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B {
    private Node sentinel;
    private Node last;
    private int size;
    public class Node{
        T data;
        Node pre;
        Node next;
    }

    public LinkedListDeque61B() {
    }

    @Override
    public void addFirst(Object x) {

    }

    @Override
    public void addLast(Object x) {

    }

    @Override
    public List toList() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Object removeFirst() {
        return null;
    }

    @Override
    public Object removeLast() {
        return null;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public Object getRecursive(int index) {
        return null;
    }
}
