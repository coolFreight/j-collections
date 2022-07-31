package com.jcomm.designpatterns.composite;

import java.util.Iterator;

public class Waitress {
    private MenuComponent allMenus;

    public Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }

    public void printMenu() {
        allMenus.print();
    }

    public void getVegetarain() {
        Iterator<MenuComponent> iterator = allMenus.iterator();
        while (iterator.hasNext()) {
            MenuComponent mc = iterator.next();
            try {
                if (mc.isVegetarian()) {
                    System.out.println(mc.getDescription());
                }
            } catch (UnsupportedOperationException uoe) {
                System.err.print("not defined  -----------------");
            }
        }
    }
}
