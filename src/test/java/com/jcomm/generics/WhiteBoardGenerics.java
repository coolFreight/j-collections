package com.jcomm.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhiteBoardGenerics {

    public static void main(String[] args) {

       List<AbstractLineItem> ll = new ArrayList<>();
       ll.add(new AbstractLineItem() {
           @Override
           public String lineItem() {
               return super.lineItem();
           }
       });


       List<? extends AbstractLineItem> l = ll;
        l.stream().forEach(System.out::println);
    }
}
