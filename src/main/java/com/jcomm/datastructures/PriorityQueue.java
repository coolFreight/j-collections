package com.jcomm.datastructures;

import java.util.Objects;

/**
 * Created by jova on 5/4/15.
 */
public interface PriorityQueue<T,V extends Comparable> {

    T peek();

    void insert(Task<T,V> elem) throws Exception;

    T top();

    void update(Task<T,V> elem);

    record Task<T,V> ( T value,  V priority) {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Task<?, ?> task = (Task<?, ?>) o;
            return Objects.equals(value, task.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
