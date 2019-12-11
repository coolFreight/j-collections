package com.jcomm.flow.news;

import java.util.concurrent.Flow;

public class ConsumerData {
    private Flow.Subscriber<News> subscriber;
    private MySubscription mySubscription;

    public Flow.Subscriber<News> getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Flow.Subscriber<News> subscriber) {
        this.subscriber = subscriber;
    }

    public MySubscription getMySubscription() {
        return mySubscription;
    }

    public void setMySubscription(MySubscription mySubscription) {
        this.mySubscription = mySubscription;
    }
}
