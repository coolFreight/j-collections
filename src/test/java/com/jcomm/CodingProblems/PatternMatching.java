package com.jcomm.CodingProblems;

import java.util.Objects;

public class PatternMatching {


    public static boolean containsText(String source,  String text) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(text);
        if(text.length() == 0 || source.length() == 0)
            return false;

        for(int sourcePtr = 0; sourcePtr < source.length(); sourcePtr++) {
            if(source.charAt(sourcePtr) == text.charAt(0)) {
                if(source.length() - sourcePtr < text.length()) {
                    return false;
                }
                int tempSourcePtr = sourcePtr;
                int textPtr = 0;
                for(; textPtr < text.length(); textPtr++) {
                    if(text.charAt(textPtr) != source.charAt(tempSourcePtr++)) {
                        break;
                    }
                }
                if(textPtr == text.length()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(PatternMatching.containsText("eeess", "ees"));
        int ar [] = null;   // O(1)

        System.out.println((int)(Math.sqrt(3)));

//        Math.ceil()
    }

}
