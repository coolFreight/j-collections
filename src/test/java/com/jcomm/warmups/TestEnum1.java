package com.jcomm.warmups;

import java.util.ArrayList;

/**
 * Created by jovaughnlockridge1 on 3/12/16.
 */
public class TestEnum1 {



    public static void main (String arg[]) {

        ArrayList<String> l = new ArrayList<>(4);

        l.add(0, "joca");
        l.add(1,"pol");
        l.add(2,  "eol");
        l.add(1,  "duppeeee");
        l.add("pwwaa");

        System.out.println(l.get(0));
        System.out.println(l.get(1));
        System.out.println(l.get(2));
        System.out.println(l.get(3));

    }

}
