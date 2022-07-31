package com.jcomm.flow.events;

import java.util.Random;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

public class Consumer implements Flow.Subscriber<Event> {
    private String name;
    private Flow.Subscription subscription;


    public Consumer(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription =  subscription;
        this.subscription.request(1);
        showMessage("Subscription OK");

    }

    @Override
    public void onNext(Event event) {
        showMessage("An event has arrived: "+event.getSource()+": "+event.getMsg());
        this.subscription.request(1);
        processEvent(event);

    }

    @Override
    public void onError(Throwable throwable) {
        showMessage("An error has occurred");
        throwable.printStackTrace();

    }

    @Override
    public void onComplete() {
       showMessage("No more events");
    }

    private void processEvent(Event event) {
        Random random = new Random();
        int number = random.nextInt(3);
        try {
            TimeUnit.SECONDS.sleep(number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    
    private void showMessage(String text) {
        System.out.println(Thread.currentThread().getName()+": "+this.name+":"+text);
    }
}
