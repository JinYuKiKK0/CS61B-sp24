import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B {
    private T[] array;
    private final int INIT_CAPACITY = 8;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque61B() {
        array = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private int minusOne(int index) {
        return Math.floorMod(index - 1, array.length);
    }

    private int plusOne(int index) {
        return Math.floorMod(index + 1, array.length);
    }

    private int plusOne(int index, int length) {
        return Math.floorMod(index + 1, length);
    }

    private void resize() {
        if (size == array.length) {
            expand();
        }
        if (size < (array.length) * 0.25 && array.length > 8) {
            reduce();
        }
    }

    private void expand() {
        resizeHelper(array.length * 2);
    }

    private void reduce() {
        resizeHelper(array.length / 2);
    }

    private void resizeHelper(int capacity) {
        T[] tempArr = array;
        int begin = plusOne(nextFirst);
        int end = minusOne(nextLast);
        array = (T[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
        for (int i = begin; i != end; i = plusOne(i, tempArr.length)) {
            array[nextLast] = tempArr[i];
            nextLast = plusOne(nextLast);
        }
        array[nextLast] = tempArr[end];
        nextLast = plusOne(nextLast);
    }

    @Override
    public void addFirst(Object item) {
        resize();
        array[nextFirst] = (T) item;
        size++;
        nextFirst = minusOne(nextFirst);
    }

    @Override
    public void addLast(Object item) {
        resize();
        array[nextLast] = (T) item;
        size++;
        nextLast = plusOne(nextLast);
    }

    @Override
    public int size() {
        return size;
    }

    private T getFirst() {
        return array[plusOne(nextFirst)];
    }

    @Override
    public Object removeFirst() {
        if (isEmpty()) {
            return null;
        }
        resize();
        T removedItem = getFirst();
        nextFirst = plusOne(nextFirst);
        array[nextFirst] = null;
        size--;
        return removedItem;
    }

    private T getLast() {
        return array[minusOne(nextLast)];
    }

    @Override
    public Object removeLast() {
        if (isEmpty()) {
            return null;
        }
        resize();
        T removedItem = getLast();
        nextLast = minusOne(nextLast);
        array[nextLast] = null;
        size--;
        return removedItem;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index > array.length - 1) {
            return null;
        }
        return array[index];
    }

    public void printDeque() {
        for (int index = plusOne(nextFirst); index != nextLast; index = plusOne(index)) {
            System.out.print(array[index] + " ");
        }
        System.out.println();
    }

    @Override
    public List toList() {
        ArrayList<Object> list = new ArrayList<>();
        //iterate the whole array
        int pointer = Math.floorMod(nextFirst + 1, array.length);
        while (pointer != nextLast) {
            list.add(array[pointer]);
            pointer = Math.floorMod(pointer + 1, array.length);
        }
        return list;//[f,c,g,h,e,d,b,a,Z]
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
