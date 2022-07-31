package com.jcomm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionFun {

    public static void main(String[] args) {

//        Pattern p = Pattern.compile("a*b");
//        Matcher m = p.matcher("aaaaaab");
//        System.out.println(m.matches());

        String s = " /v/region/$environment/file_$stream";
        System.out.println(s.replaceAll("\\$environment", "sword"));

    }



}
