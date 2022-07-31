package com.jcomm.datastructures.multidimensionArray;

public class BrokenMultiDimensionArray {

    /**
     * |0|1|1|0|
     * |0|1
     * |
     * |
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] matrix = new int[4][4];
        matrix[0][1] = 1;
        matrix[0][2] = 1;
        matrix[1][1] = 1;
        matrix[1][3] = 1;
        matrix[2][1] = 1;
        matrix[3][0] =1;
        matrix[3][1] =1;
        System.out.println(CountIsland(matrix));

    }

    public static int CountIsland(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix.length;

        int islandCount = 0;
        for(int row =0; rows < rows; row++){
            for(int column =0; column < columns; column++){
                if(matrix[row][column] == 1){
                    countConnection(matrix, row, column);
                    islandCount++;
                }
            }
        }

        return islandCount;

    }

    private static void countConnection(int[][] matrix, int row, int column){
        if(((row < 0 || row >= matrix.length) || (column < 0 || column >= matrix.length)
                || matrix[row][column] == 0)){
            return;
        }
        matrix[row][column] =0;
        countConnection(matrix, row+1, column);
        countConnection(matrix, row, column+1);
        countConnection(matrix, row-1, column);
        countConnection(matrix, row, column-1);
    }
}
