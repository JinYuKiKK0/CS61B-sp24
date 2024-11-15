package deque;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;


public class LinkedListDeque61B<T> implements Deque61B<T> {
    private Node sentinel;
    private int size;
    private Node last;

    public class LinkedListIterator<T> implements Iterator<T>{
        private int wizPos;

        public LinkedListIterator(){
            wizPos =0;
        }
        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T temp = (T) get(wizPos);
            wizPos++;
            return temp;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>();
    }


    public class Node{
        public T data;
        public Node next;
        public Node pre;
        public Node(T Data, Node preNode, Node nextNode){
            data = Data;
            pre = preNode;
            next = nextNode;
        }
    }

    public LinkedListDeque61B() {
        sentinel = new Node(null,null,null);
        last = new Node(null,sentinel,null);
        sentinel.next = last;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node Snext = sentinel.next;
        sentinel.next = new Node((T) x, sentinel, sentinel.next);
        Snext.pre = sentinel.next;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node Lpre = last.pre;
        last.pre = new Node((T)x,last.pre,last);
        Lpre.next = last.pre;
        size++;
    }

    @Override
    public List toList() {
        Node pNode = sentinel.next;
        List<T> returnList = new ArrayList<>();
        //遍历链表
        while(pNode!=last){
            returnList.add(pNode.data);
            pNode = pNode.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if(sentinel.next == last){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        Node oldFirst = sentinel.next;
        Node newFirst = oldFirst.next;
        if(oldFirst == last){
            System.out.println("You can't remove anything from a empty list");
            return null;
        }
        sentinel.next = newFirst;
        newFirst.pre = sentinel;
        size--;
        return oldFirst.data;
    }

    @Override
    public T removeLast() {
        Node oldLast = last.pre;
        Node newLast = oldLast.pre;
        if(oldLast == sentinel){
            System.out.println("You can't remove anything from a empty list");
            return null;
        }
        newLast.next = last;
        last.pre= newLast;
        size--;
        return oldLast.data;
    }

    @Override
    public T get(int index) {
        if(index >= size||index <0){
            System.out.println("OutOfBoundsIndex");
            return null;
        }
        Node pNode;
        if(index +1<=(size/2)){
            pNode = sentinel.next;
            for(int i=0;i<index;i++){
                pNode = pNode.next;
            }return pNode.data;
        }else {
            pNode = last.pre;
            for(int i=0;i<size-index-1;i++){
                pNode = pNode.pre;
            }return pNode.data;
        }
    }

    @Override
    public T getRecursive(int index) {
        if(index <0||index >size){
            System.out.println("ArrayOutOfBoundsIndex");
            return null;
        }
        return getRecursive(sentinel.next,index);
    }

    private  T getRecursive(Node node,int index){
        if(index == 0)
            return node.data;
        return getRecursive(node.next,index-1);
    }

}
