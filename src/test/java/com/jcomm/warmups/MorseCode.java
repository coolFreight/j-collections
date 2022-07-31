package com.jcomm.warmups;

import java.util.HashSet;
import java.util.Set;

public class MorseCode {

    private String [] morseCodes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."
    };
    private Set<String> wordTransformations = new HashSet<>();

    public int uniqueMorseRepresentations(String[] words) {
        if(words == null || words.length == 0){
            return 0;
        }

        for(String word : words){
            StringBuilder wordTransformed = new StringBuilder();
            for(char letter : word.toCharArray()){
                wordTransformed = wordTransformed.append(morseCodes[getCharIndex(letter)]);
            }
            System.out.println(wordTransformed.toString());
            wordTransformations.add(wordTransformed.toString());
        }
        return wordTransformations.size();
    }

    private int getCharIndex(char letter) {
        return Math.abs(97 - letter);
    }

    public static void main(String[] args) {

        MorseCode l = new MorseCode();

        String input [] = {"rwjje","aittjje","auyyn","lqtktn","lmjwn"};
        System.out.println(l.uniqueMorseRepresentations(input));
    }
}
