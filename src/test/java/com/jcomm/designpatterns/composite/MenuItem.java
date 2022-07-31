package com.jcomm.designpatterns.composite;


import java.util.Iterator;

public class MenuItem extends MenuComponent {
    private String name;
    private String description;
    private boolean vegatarian;
    private double price;

    public MenuItem(String name, String description, boolean vegatarian, double price) {
        this.name = name;
        this.description = description;
        this.vegatarian = vegatarian;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }


    public double getPrice() {
        return price;
    }

    @Override
    public boolean isVegetarian() {
        return vegatarian;
    }

    @Override
    public void print() {
        System.out.println(" " + getName());
        if (isVegetarian()) {
            System.out.println("(v)");
        }
        System.out.println(", " + getPrice());
        System.out.println("    -- " + getDescription());
    }

    @Override
    public Iterator<MenuComponent> iterator() {
        return new NullIterator();
    }

    private class NullIterator implements Iterator<MenuComponent> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public MenuItem next() {
            return null;
        }
    }
}
