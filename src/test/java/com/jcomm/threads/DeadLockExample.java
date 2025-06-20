package com.jcomm.threads;

import com.jcomm.models.Bone;
import com.jcomm.models.Dog;
import com.jcomm.models.Person;
import com.jcomm.models.Person1;

public class DeadLockExample {


    public static void main(String[] args) {

        Dog spot = new Dog( );
        Dog rufus = new Dog();
        Bone b1 = new Bone();
        Bone b2 = new Bone();

        spot.setBone(b1);
        b1.setDog(spot);

        rufus.setBone(b2);
        b2.setDog(rufus);

        Thread t1 = new Thread(new Person(spot));
        Thread t2 = new Thread(new Person1(rufus));

        t2.start();
        t1.start();
    }

}
