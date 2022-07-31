package com.jcomm.desingpatterns.singleton;

public class SingletonTester {


    public static void main(String[] args) {
        Elvis e = Elvis.INSTANCE;
        e.leavingTheBuilding();
    }
}
