import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    boolean[][] array;
    WeightedQuickUnionUF uf;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        if(N<=0){
            throw new IllegalArgumentException("You can't create a grid with less than 0 element");
        }
        array = new boolean[N][N];
        uf=new WeightedQuickUnionUF(N*N+2);
        
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        exceptionProcess(row,col);

        array[row][col] = true;
        connected2Union(row, col);
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        exceptionProcess(row,col);
        return array[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        exceptionProcess(row,col);

        int blockIndex = xyTo1D(row, col);
        int i=0;
        while(i++< array.length){
            if(uf.connected(blockIndex,i)){
                return true;
            }
        }

        return false;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
            int count =0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if(array[i][j]){
                    count++;
                }
            }
        }
        return count;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        int N = array.length-1;
        int i =0;
        while(i< array.length){
            if(isFull(N,i++))
                return true;
        }
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    private int xyTo1D(int r, int c) {//e.g xyTo1D(2,4) = 14
        return r * array.length + c;
    }
    private void exceptionProcess(int row,int col){
        if((row<0 ||row>=array.length) ||(col<0||col>=array.length)){
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * 判断(row,col)相邻的站点是否开放，如果有开放站点，则将两站点连通
     * @param row
     * @param col
     */
    private void connected2Union(int row, int col){
        int d1=xyTo1D(row,col);
        int d2;

        if(row >0&&isOpen(row-1,col)){
            d2=xyTo1D(row-1,col);
            uf.union(d1,d2);
        }
        if(row< array.length-1&&isOpen(row+1,col)){
            d2=xyTo1D(row+1,col);
            uf.union(d1,d2);
        }
        if(col>0&&isOpen(row,col-1)){
            d2=xyTo1D(row,col-1);
            uf.union(d1,d2);
        }
        if(col< array.length-1&&isOpen(row,col+1)){
            d2=xyTo1D(row,col+1);
            uf.union(d1,d2);
        }
    }

    public static void main(String[] args) {

    }
    // TODO: Remove all TODO comments before submitting.

}
