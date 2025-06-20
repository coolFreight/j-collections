package com.jcomm.sorting;

public class Student {
    private final String name;
    private final int section;

    public Student(String name, int section) {
        this.name = name;
        this.section = section;
    }

    public int getKey() {
        return section;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", section=" + section +
                '}';
    }
}
