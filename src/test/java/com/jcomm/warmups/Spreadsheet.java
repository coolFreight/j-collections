package com.jcomm.warmups;

import java.util.HashMap;

/**
 * Created by jovaughnlockridge1 on 3/8/16.
 */
public class Spreadsheet {



    public static void main (String[] a){
        System.out.println(Spreadsheet.getSpreadsheetColumnFromString("BA"));
        System.out.println(Spreadsheet.getColumnId("BA"));
    }



    public static int getColumnId(String id){

        id = id.toLowerCase().trim();
        HashMap<Character, Integer> map = new HashMap<>();

        int count =0;
        char l = 'a';
        while(count <26){
            System.out.println("char:"+l+" count:"+count);
            map.put(l, count);
            l++;
            count++;
        }

        int sum =0;
        for(int x = id.length()-1; x>=0; x--){
            char loc = id.charAt(x);
            int charLoc = map.get(loc);
            sum += (charLoc + Math.pow(25, x));
        }
        return sum;
    }


    public static int getSpreadsheetColumnFromString(String input) {
        int result = 0;
        int val = 0;
        char c;

        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (c >= 'a' && c <='z') {
                val = (c/'a') + (c%'a');
            } else if (c >= 'A' && c <='Z') {
                val = (c/'A') + (c%'A');
            }
            result = val + i * 26;
        }
        return result;
    }




}
