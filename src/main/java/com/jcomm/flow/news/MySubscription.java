package com.jcomm.flow.news;

import java.util.Set;
import java.util.concurrent.Flow;
import java.util.concurrent.atomic.AtomicLong;

public class MySubscription implements Flow.Subscription {

    private boolean cancelled;
    private final AtomicLong requested = new AtomicLong(0);
    private Set<Integer> categories;

    @Override
    public void request(long n) {
        requested.addAndGet(n);
    }

    @Override
    public void cancel() {
        this.cancelled = true;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public long getRequested() {
        return requested.get();
    }

    public long decreaseRequested() {
        return requested.decrementAndGet();
    }

    public void setCategories(Set<Integer> categories) {
        this.categories = categories;
    }

    public boolean hasCatergory(Integer category) {
        return categories.contains(category);
    }
}
