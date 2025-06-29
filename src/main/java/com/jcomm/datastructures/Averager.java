package com.jcomm.datastructures;

import java.util.function.IntConsumer;

public class Averager implements IntConsumer {

    private int total = 0;
    private int count = 0;

    public void accept(int i) {
        this.total += i;
        this.count++;
    }

    public double average() {
        return count > 0 ? ((double) total) / count : 0;
    }

    public void combine(Averager other) {
        total += other.total;
        count += other.count;
    }
}
