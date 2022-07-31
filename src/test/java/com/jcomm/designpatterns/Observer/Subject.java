package com.jcomm.designpatterns.Observer;

/**
 * Created by jovaughnlockridge1 on 8/31/17.
 */
public interface Subject<T>{
    void registerObserver(T o);
    void removeObserver(T o);
    void notifyObservers();
}
