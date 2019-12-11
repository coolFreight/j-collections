package com.jcomm.flow.news;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.ThreadPoolExecutor;

public class MyPublisher implements Flow.Publisher<News> {
    private ConcurrentLinkedDeque<ConsumerData> consumers;
    private ThreadPoolExecutor executor;


    public MyPublisher() {
        consumers = new ConcurrentLinkedDeque<>();
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Override
    public void subscribe(Flow.Subscriber<? super News> subscriber) {
        ConsumerData consumerData = new ConsumerData();
        consumerData.setSubscriber((Flow.Subscriber<News>) subscriber);

        MySubscription subscription = new MySubscription();
        consumerData.setMySubscription(subscription);

        subscriber.onSubscribe(subscription);

        consumers.add(consumerData);
    }

    public void publish(News news) {
        consumers.forEach(consumerData -> {
            try {
                executor.execute(new PublisherTask(consumerData, news));
            } catch (Exception e) {
                consumerData.getSubscriber().onError(e);
            }
        });
    }

    public void shutdown() {
        consumers.forEach(consumerData -> {
            consumerData.getSubscriber().onComplete();
        });
        executor.shutdown();
    }


}
