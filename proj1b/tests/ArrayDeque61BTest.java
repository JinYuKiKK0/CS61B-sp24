import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }
    @Test
    public void toList_validTest(){
        Deque61B ad = new ArrayDeque61B<>();
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addFirst(3);
        ad.addLast(0);
        ad.addFirst(4);
        ad.addFirst(5);
        ad.addLast(-1);
        assertThat(ad.toList()).containsExactly(5,4,3,2,1,0,-1).inOrder();
    }

@Test
    public void add_first_from_empty(){
        Deque61B ad = new ArrayDeque61B<>();
        ad.addFirst(1);
        assertThat(ad.toList()).containsExactly(1).inOrder();
    }
    @Test
    public void isEmptyTest(){
        Deque61B ad = new ArrayDeque61B<>();
        assertThat(ad.isEmpty()).isTrue();
    }
@Test
    public void getTest(){
        Deque61B ad = new ArrayDeque61B<>();
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addFirst(3);
        ad.addLast(0);
        ad.addFirst(4);
        ad.addFirst(5);
        ad.addLast(-1);
        assertThat(ad.get(4)).isEqualTo(5);
    }
    @Test
    public void removeFirstTest(){
        Deque61B ad = new ArrayDeque61B<>();
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addFirst(3);
        ad.addLast(0);
        ad.addFirst(4);
        ad.addFirst(5);
        ad.addLast(-1);
        assertThat(ad.removeFirst()).isEqualTo(5);
        assertThat(ad.toList()).containsExactly(4,3,2,1,0,-1);
    }
    @Test
    public void removeLastTest(){
        Deque61B ad = new ArrayDeque61B<>();
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addFirst(3);
        ad.addLast(0);
        ad.addFirst(4);
        ad.addFirst(5);
        ad.addLast(-1);
        assertThat(ad.removeLast()).isEqualTo(-1);
        assertThat(ad.toList()).containsExactly(5,4,3,2,1,0);
    }
    @Test
    public void resizingUpTest(){
        ArrayDeque61B ad = new ArrayDeque61B<>();
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addFirst(3);
        ad.addLast(0);
        ad.addFirst(4);
        ad.addFirst(5);
        ad.addLast(-1);
        ad.addLast(-2);
        ad.addFirst(101);
        ad.addLast(102);
        assertThat(ad.toList()).containsExactly(101,5,4,3,2,1,0,-1,-2,102);
    }
    @Test
    public void resizingDownTest(){
        ArrayDeque61B ad = new ArrayDeque61B<>();
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addFirst(3);
        ad.addLast(0);
        ad.addFirst(4);
        ad.addFirst(5);
        ad.addLast(-1);
        ad.addLast(-2);
        ad.addFirst(101);
        ad.addLast(102);
        for(int i=0;i<9;i++){
            ad.removeFirst();
        }
        assertThat(ad.toList()).containsExactly(102);
    }

}
