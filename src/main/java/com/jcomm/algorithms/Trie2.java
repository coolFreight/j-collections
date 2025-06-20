package com.jcomm.algorithms;

import java.util.Arrays;

public class Trie2 {

      private final Node root;

    public Trie2() {
        root = new Node();
    }

    public static void main(String[] args) {
        Trie2 t = new Trie2();
//        "addWord","addWord","addWord","addWord","search", "search","addWord","search","search","search","search","search","search"]
//         ["at"],  ["and"],    ["an"],   ["add"],  ["a"],   [".at"],["bat"]   ,[".at"], ["an."], ["a.d."] ,["b."] ,["a.d"] ,["."]]
        //"WordDictionary","addWord","addWord","addWord","search"
//        [],["xgvk"],["wykzbvwdsoyfowqicymzd"],["xajbtjyjuwgoynjgu"],["wykzbvwdso..owqicymzd"]
        t.addWord("xgvk");
                 //wykzbvwdso..owqicymzd
        t.addWord("wykzbvwdsoyfowqicymzd");
        t.addWord("xajbtjyjuwgoynjgu");
        System.out.println(t.search("wykzbvwdso..owqicymzd"));
//        t.search("a");
//        t.search(".at");
//        t.addWord("bat");
//        t.search(".at");
//        t.search("an.");
//        t.search("a.d.");
//        t.search("b.");
//        t.search("a.d");
        t.search(".");

    }


    public void addWord(String word) {
        Node node = root;
        for (int index = 0; index < word.length(); index++) {
            int pos = word.charAt(index) - 'a';
            if (node.alphabet[pos] == null) {
                Node newNode = new Node();
                node.alphabet[pos] = newNode;
                node = newNode;
            } else {
                node = node.alphabet[pos];
            }
        }
        node.isLastWord = true;


    }


    public boolean search(String word) {
       int firstLetter = word.charAt(0) - 'a';
       return  search(word.charAt(0)  == '.' ? null : root.alphabet[firstLetter], word, 0);
    }


    private boolean search(Node wildCard, String word, int ptr) {
        if (ptr == word.length() - 1 && word.charAt(ptr) == '.') {
            return wildCard != null && wildCard.isLastWord;
        }
        Node node = wildCard;
        for (int index = ptr + 1; index < word.length(); index++) {
            if (word.charAt(index) == '.') {
                int find = 0;
                while (find < node.alphabet.length) {
                    if (node.alphabet[find] != null) {
                        var found = search(node.alphabet[find], word, index);
                        if (found) {
                            return true;
                        }
                    }
                    find++;
                }
                return false;
            }
            int pos = word.charAt(index) - 'a';
            if (node.alphabet[pos] == null) {
                return false;
            } else {
                node = node.alphabet[pos];
            }
        }
        return node.isLastWord;
    }

    private class Node {
        Node[] alphabet = new Node[26];
        boolean isLastWord = false;

        public String toString() {
            StringBuilder sb = new StringBuilder();
            Arrays.stream(alphabet).forEach(c -> sb.append(c).append(","));
            return sb.toString();
        }
    }
}

