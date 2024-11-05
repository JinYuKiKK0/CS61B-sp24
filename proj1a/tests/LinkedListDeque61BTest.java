import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest{


     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.

    /**
     * This test Check that isEmpty works on an empty deque.
     */
    @Test
    public void is_empty_true(){
        Deque61B<Integer> lld1  = new LinkedListDeque61B<>();
        assertThat(lld1.isEmpty()).isTrue();
    }

    /**
     * Check that isEmpty works on a non-empty deque.
     */
    @Test
    public void is_empty_false(){
        Deque61B<String> lld1  = new LinkedListDeque61B<>();
        lld1.addFirst("front");
        lld1.addLast("front");
        assertThat(lld1.isEmpty()).isFalse();
    }

    /**
     * Check that size works.
     */
    @Test
    public void size(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(5);
        lld1.addFirst(4);
        lld1.addLast(6);
        assertThat(lld1.size()).isEqualTo(3);
    }

    /**
     * Add some elements to a deque and remove them all, then check that size still works.
     */
    @Test
    public void size_after_remove_to_empty(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(5);
        lld1.addFirst(4);
        lld1.addFirst(3);
        int length = lld1.size();
        for(int i=0;i< length;i++){
            lld1.removeFirst();
        }
        assertThat(lld1.size()).isEqualTo(0);
    }

    /**
     * Remove from an empty deque, then check that size still works.
     */
    @Test
    public void size_after_remove_from_empty(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.removeFirst();
        lld1.removeLast();
        assertThat(lld1.size()).isEqualTo(0);
    }

    /**
     * Check that get works on a valid index.
     */
    @Test
    public void get_valid(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(5);
        lld1.addFirst(4);
        lld1.addFirst(3);
        lld1.addFirst(2);
        lld1.addFirst(1);
        lld1.addLast(6);
        //[1,2,3,4,5,6]
        // 0 1 2 3 4 5
        assertThat(lld1.get(2)).isEqualTo(3);
        assertThat(lld1.get(4)).isEqualTo(5);
    }
    /**
     * Check that get works on a large, out of bounds index.
     */
    @Test
    public void get_oob_large(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4);
        assertThat(lld1.get(5)).isEqualTo(null);
    }

    /**
     * Check that get works on a negative index.
     */
    @Test
    public void get_oob_neg(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(5);
        lld1.addFirst(4);
        lld1.addFirst(3);
        assertThat(lld1.get(-1)).isEqualTo(null);
    }

    /**
     * Check that getRecursive works on a valid index.
     */
    @Test
    public void get_recursive_valid(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(5);
        lld1.addFirst(4);
        lld1.addFirst(3);
        lld1.addFirst(2);
        lld1.addFirst(1);
        lld1.addLast(6);
        //[1,2,3,4,5,6]
        // 0 1 2 3 4 5
        assertThat(lld1.getRecursive(2)).isEqualTo(3);
        assertThat(lld1.getRecursive(4)).isEqualTo(5);
    }

    /**
     *  Check that getRecursive works on a large, out of bounds index.
     */
    @Test
    public void get_recursive_oob_large(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4);
        assertThat(lld1.getRecursive(5)).isEqualTo(null);
    }

    /**
     * Check that getRecursive works on a negative index.
     */
    @Test
    public void get_recursive_oob_neg(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(5);
        lld1.addFirst(4);
        lld1.addFirst(3);
        assertThat(lld1.getRecursive(-1)).isEqualTo(null);
    }

    @Test
    public void remove_first(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(5);
        lld1.addFirst(4);
        lld1.addFirst(3);
        lld1.addFirst(2);
        lld1.addFirst(1);
        lld1.addLast(6);//[1,2,3,4,5,6]
        lld1.removeFirst();
        assertThat(lld1.toList()).containsExactly(2,3,4,5,6).inOrder();
    }
    @Test
    public void remove_last(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(5);
        lld1.addFirst(4);
        lld1.addFirst(3);
        lld1.addFirst(2);
        lld1.addFirst(1);
        lld1.addLast(6);//[1,2,3,4,5,6]
        lld1.removeLast();
        assertThat(lld1.toList()).containsExactly(1,2,3,4,5).inOrder();
    }
    @Test
    public void remove_first_to_empty(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(5);
        lld1.addFirst(4);
        lld1.addFirst(3);
        int length = lld1.size();
        for(int i=0;i< length;i++){
            lld1.removeFirst();
        }
        assertThat(lld1.removeFirst()).isEqualTo(null);
    }
    @Test
    public void remove_last_to_empty(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(5);
        lld1.addFirst(4);
        lld1.addFirst(3);
        int length = lld1.size();
        for(int i=0;i< length;i++){
            lld1.removeLast();
        }
        assertThat(lld1.removeLast()).isEqualTo(null);
    }
}