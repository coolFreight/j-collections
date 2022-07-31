package com.jcomm.datastructures;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jovaughnlockridge1 on 5/25/17.
 */
public class JavaGenericsTest {


    @Test
    public void testExtendsGenerics(){


        List<Integer> listOfInts = new ArrayList<>();
        List<? extends Number> addToList = new ArrayList<Integer>();

        listOfInts.add(25);
        listOfInts.add(13);
        listOfInts.add(544);



        addToList = listOfInts;

        addToList.stream().forEach(l -> System.out.println(l.longValue() +" class is "+l.getClass()));


    }




}
