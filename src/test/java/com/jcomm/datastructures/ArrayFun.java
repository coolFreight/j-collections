package com.jcomm.datastructures;

import com.javamex.classmexer.MemoryUtil;
import com.jcomm.threads.Bone;
import com.jcomm.threads.Dog;

import java.util.List;

/**
 * Created by jovaughnlockridge1 on 9/21/15.
 */
public class ArrayFun {


private int a[] = new int[6];


    public static void main(String arg[]){

        Dog d = new Dog();
        Bone b = new Bone();
        //b.setDog(d);
        //d.setBone(b);

        String g = "";

        byte l = 0;




        System.out.println(MemoryUtil.deepMemoryUsageOf(new char[0]));

        displaySizeMinAndMax(Byte.TYPE, Byte.SIZE, Byte.MIN_VALUE, Byte.MAX_VALUE);
        displaySizeMinAndMax(Short.TYPE, Short.SIZE, Short.MIN_VALUE, Short.MAX_VALUE);
        displaySizeMinAndMax(Character.TYPE, Character.SIZE, (int) Character.MIN_VALUE, (int) Character.MAX_VALUE);
        displaySizeMinAndMax(Integer.TYPE, Integer.SIZE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        displaySizeMinAndMax(Long.TYPE, Long.SIZE, Long.MIN_VALUE, Long.MAX_VALUE);
        displaySizeMinAndMax(Float.TYPE, Float.SIZE, Float.MIN_VALUE, Float.MAX_VALUE);
        displaySizeMinAndMax(Double.TYPE, Double.SIZE, Double.MIN_VALUE, Double.MAX_VALUE);


    }


    public static void displaySizeMinAndMax(Class<?> type, int size, Number min, Number max) {
        System.out.printf("type:%-6s size:%-2s min:%-20s max:%s\n", type, size, min, max);
    }



}
