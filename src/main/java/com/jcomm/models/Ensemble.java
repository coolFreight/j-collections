package com.jcomm.models;

public enum Ensemble {
    DUET, TRIO, QUARTET, QUINTET,
    SEXTET, SEPTET, OCTET, NONET, DECTET, SOLO;

    public int numberOfMusicians() { return ordinal() + 1;}


    public static void main(String[] args) {
        System.out.println(Ensemble.SOLO.numberOfMusicians());
    }
}
