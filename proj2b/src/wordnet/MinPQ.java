package wordnet;

public class MinPQ <item>{
    item [] arrays;
    int size;

    public MinPQ(){
        arrays = (item[]) new Object[16];
        size = 0;
    }

    public int leftIndex(){
        return 0;
    }
    public int rightIndex(){
        return 0;
    }
    private void swim(int index){

    }
    private void sink(int index){

    }
    public void insert(item item){

    }


    public item removeSmallest(){
        return null;
    }
}
