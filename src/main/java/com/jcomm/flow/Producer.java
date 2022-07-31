package com.jcomm.flow;


import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {
    private SubmissionPublisher<Event> publisher;
    private String name;

    public Producer(SubmissionPublisher<Event> publisher, String name) {
        this.publisher = publisher;
        this.name = name;
    }

    @Override
    public void run() {

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Event event = new Event();
            event.setMsg("Event number "+i);
            event.setSource(this.name);
            event.setDate(LocalDate.now());
            publisher.submit(event);
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
