public class UnionFind {
    // TODO: Instance variables
    private final int[] UnionArray;
    //an array mapping to UnionArray

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        UnionArray = new int[N];
        for (int i = 0; i < UnionArray.length; i++) {
            UnionArray[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        return -UnionArray[find(v)];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        if(UnionArray[v] < 0){
            return -UnionArray[v];
        }
        return UnionArray[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if(v<0 || v>=UnionArray.length){
            throw new IllegalArgumentException();
        }
        if(UnionArray[v] < 0){
            return v;
        }
        UnionArray[v] =find(UnionArray[v]);
        return UnionArray[v];
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (connected(v1, v2)) {
            return;
        }
        int root1 = find(v1);
        int root2 = find(v2);
        int size1 = sizeOf(root1);  // root1 的值是负数，所以要取反
        int size2 = sizeOf(root2);

        if (size1 > size2) {
            UnionArray[root2] = root1;           // 将root2指向root1
            UnionArray[root1] = -(size1 + size2); // 更新root1的大小
        } else {
            UnionArray[root1] = root2;           // 将root1指向root2
            UnionArray[root2] = -(size1 + size2); // 更新root2的大小
        }
    }
}
