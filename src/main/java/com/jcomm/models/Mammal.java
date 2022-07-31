package com.jcomm.models;

/**
 * Created by jovaughnlockridge1 on 3/24/17.
 */
public class Mammal {

    public Mammal(Dog d){}

    private Mammal() {
        System.out.println("MAMMAL constructor called");
    }

    public String soul(){
        System.out.println("Mammal soul "+toString());
        return toString();
    }
}
