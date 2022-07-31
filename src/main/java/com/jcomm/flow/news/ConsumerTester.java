package com.jcomm.flow.news;


import java.time.LocalDate;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.Stream;

public class ConsumerTester {

    public static void main(String[] args) throws InterruptedException {

        SubmissionPublisher<News> publisher = new SubmissionPublisher<>(ForkJoinPool.commonPool(), 1);

        publisher.subscribe(new ConsumerSubscriber());

        int x = 1;
        while (x++ > 0) {
            News news = new News();
            news.setTxt("Money news vol" + x);
            news.setCategory(News.ECONOMIC);
            news.setDate(LocalDate.now());

            System.out.println("Publishing news "+news);
            int lagOrDrops = publisher.offer(news, (k, v) -> false);
            System.out.println("The number of drops "+lagOrDrops);
            Thread.sleep(500);
            System.out.println();
            System.out.println();
        }


        Thread.sleep(90000);
    }
}
