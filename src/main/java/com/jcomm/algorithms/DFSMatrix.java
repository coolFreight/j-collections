package com.jcomm.algorithms;

public class DFSMatrix {

    private final int ROWS = 5;
    private final int COLS = 5;

    int [][] matrix = new int[ROWS][COLS];


    public static void main(String[] args) {

        DFSMatrix dfsMatrix = new DFSMatrix();
        dfsMatrix.init();
    }

    public void init() {
        matrix[2][3] = 1;


        int count = 0;
        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLS; col++ ) {
                if(matrix[row][col] == 1) {
                    count++;
                }
            }
        }

        System.out.println("Number of 1s found are "+count);
    }


    int [] left = {0, -1};

//    System.out.println( matrix[ );
}
