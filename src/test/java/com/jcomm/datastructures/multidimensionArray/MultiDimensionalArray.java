package com.jcomm.datastructures.multidimensionArray;

public class MultiDimensionalArray {


    /**
     * |0|1|0|1|1|
     * |0|1|0|1|0|
     * |0|1|0|1|1|
     * |0|1|0|0|1|
     * |1|1|1|1|0|
     */


    public static void main(String[] args) {
//        int[][] arr = new int[5][5];
//        arr[0][1] = 1;
//        arr[1][1] = 1;
//        arr[2][1] = 1;
//        arr[3][1] = 1;
//        arr[4][1] = 1;
//        arr[0][4] = 1;
//        arr[4][2] = 1;
//        arr[4][3] = 1;
//        arr[0][3] = 1;
//        arr[1][3] = 1;
//        arr[2][3] = 1;
//        arr[0][4] = 1;
//        arr[2][4] = 1;
//        arr[3][4] = 1;

        int[][] arr = new int[4][4];

        arr[0][1] = 1;
        arr[0][2] = 1;
        arr[1][1] = 1;
        arr[1][3] = 1;
        arr[2][1] = 1;
        arr[3][0] =1;
        arr[3][1] =1;
        

        System.out.println(countIslands(arr));

    }

    public static int countIslands(int [][] arr) {
        int rowCount = arr.length;
        int colCount = arr.length;
        int countIslands = 0;
        for(int row = 0; row < rowCount; row++){
            for(int col = 0; col < colCount; col++){
                if(arr[row][col] == 1){
                    countIslands++;
                    removeIsland(arr, row, col);
                }
            }
        }
        return countIslands;
    }

    public static void removeIsland(int arr[][], int row, int col){
        if(row == arr.length || row < 0)
            return ;
        if(col == arr.length || col < 0)
            return;
        if(arr[row][col] == 0 )
            return;
        arr[row][col] = 0;
        removeIsland(arr,row+1, col);
        removeIsland(arr, row, col+1);
        removeIsland(arr, row-1, col);
        removeIsland(arr, row, col-1);
    }
}
