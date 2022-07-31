package com.jcomm.datastructures.multidimensionArray;

public class ArrayKata {
    public static void main(String[] args) {

        int [][] dim = new int [][] {
            new int [] {1, 2, 3, 7},
            new int [] {4,3, 3, 6},
                new int [] {4,3, 3, 6},
                new int [] {4,3, 3, 6},
                new int [] {4,3, 3, 6},
        };
        ArrayKata.transpose(dim);

    }

    public static void transpose(int [][] A){
        int COL_COUNT  = A[0].length;
        int ROW_COUNT = A.length;
        int [][] result = new int[COL_COUNT][ROW_COUNT];
        for(int row = 0; row < ROW_COUNT; row++){
            for(int col = 0; col < COL_COUNT; col++){
                result[col][row] = A[row][col];
            }
        }

    }


}
