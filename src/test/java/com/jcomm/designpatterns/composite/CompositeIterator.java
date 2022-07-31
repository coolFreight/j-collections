package com.jcomm.designpatterns.composite;

import java.util.Iterator;
import java.util.Stack;

public class CompositeIterator implements Iterator<MenuComponent> {
    private Stack<Iterator<MenuComponent>> stack = new Stack<>();

    public CompositeIterator(Iterator<MenuComponent> menuComponentIterator) {
        stack.push(menuComponentIterator);
    }

    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) {
            return false;
        }
        if (stack.peek().hasNext()) {
            return true;
        } else {
            stack.pop();
            if (stack.isEmpty()) {
                return false;
            } else {
                return stack.peek().hasNext();
            }
        }
    }

    @Override
    public MenuComponent next() {
        MenuComponent menuComponent = stack.peek().next();
        if (menuComponent instanceof Menu) {
            stack.push(menuComponent.iterator());
        }
        return menuComponent;
    }
}
