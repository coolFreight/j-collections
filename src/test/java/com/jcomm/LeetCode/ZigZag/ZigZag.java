package com.jcomm.LeetCode.ZigZag;

import com.jcomm.datastructures.CollectionsHelper;

/**
 * Created by jovaughnlockridge1 on 8/21/17.
 */
public class ZigZag {

    public static void main(String[] args) {
        String word =  args[0];

        int noOfRows = Integer.parseInt(args[1]);

        String [][] matrix = new String[3][3];

        matrix[0][0] = "hello";
        matrix[1][0] = "world";



        ZigZag zigZag = new ZigZag();
        zigZag.start(word, noOfRows);


    }


    public void start(String word, int rows) {

        int tempCol = word.length()/rows;

        char [][] matrix;
        int COLS = 0;
        if(rows == 1){
            matrix =  new char[word.length()][0];
        }else {
            int cols = (tempCol - 2);
            COLS = tempCol * cols;
            matrix = new char[tempCol * cols][rows];
        }

        int totalWordCount = 0;
        int colIndx = 0;
        while(totalWordCount < word.length()){

            int rowInsert = 0;
            for(; rowInsert < rows; rowInsert++){
                if(totalWordCount != word.length())
                    matrix[colIndx][rowInsert] = word.charAt(totalWordCount++);
            }

            if(totalWordCount == word.length())
                break;

            int countInserts = 0;
            --rowInsert;
            while(countInserts < rows-2){
                if(totalWordCount != word.length()) {
                    matrix[++colIndx][--rowInsert] = word.charAt(totalWordCount++);
                    countInserts++;
                }
            }
            ++colIndx;

            if(totalWordCount == word.length())
                break;
        }


        for(int x = 0; x <rows ; x++){
            for(int y = 0; y < COLS; y++){
                System.out.print(matrix[y][x]);
            }
            System.out.println();
        }


    }





}
