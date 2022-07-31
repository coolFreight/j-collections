package com.jcomm.designpatterns.composite;

import java.util.ArrayList;
import java.util.Iterator;

public class PanacakeHouseMenu implements Iterator<MenuItem> {
    private ArrayList<MenuItem> menuItems;

    public PanacakeHouseMenu() {

    }

    public void addItem(String name, String description, boolean vegetarian, double price){
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.add(menuItem);
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public MenuItem next() {
        return null;
    }
}
