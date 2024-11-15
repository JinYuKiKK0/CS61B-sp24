import deque.ArrayDeque61B;
import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static com.google.common.truth.Truth.assertThat;

public class IteratorTest {
    @Test
    public void addLastTestBasicWithoutToList() {
        LinkedListDeque61B<Integer> lld1 = new LinkedListDeque61B<>();

        lld1.addLast(1); // after this call we expect: [1]
        lld1.addLast(2); // after this call we expect: [1, 2]
        lld1.addLast(3); // after this call we expect: [1, 2, 3]
        lld1.addLast(4); // after this call we expect: [1, 2, 3, 4]
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer integer : lld1) {
            list.add(integer);
        }
        assertThat(list).containsExactly(1, 2, 3, 4).inOrder();
    }

    @Test
    public void addLastTestBasicToList() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back");
    }

    @Test
    public void addLastTestBasicWithoutToList1(){
        Deque61B<Integer> b = new ArrayDeque61B<>();
        b.addLast(1);
        b.addLast(2);
        b.addLast(3);
        b.addLast(4);
        b.addLast(5);
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer integer : b) {
            list.add(integer);
        }
        assertThat(list).containsExactly(1,2,3,4,5).inOrder();
    }
}
