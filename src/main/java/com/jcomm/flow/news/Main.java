package com.jcomm.flow.news;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        MyPublisher publisher = new MyPublisher();

        Flow.Subscriber<News> consumer1, consumer2, consumer3;

        Set<Integer> sports = new HashSet<>();
        sports.add(News.SPORTS);
        consumer1 = new Consumer("Sports Consumer", sports);

        Set<Integer> science = new HashSet<>();
        science.add(News.SCIENCE);
        consumer2 = new Consumer("Science Consumer", science);

        Set<Integer> all = new HashSet<>();
        all.add(News.SPORTS);
        all.add(News.ECONOMIC);
        all.add(News.SCIENCE);
        all.add(News.WORLD);
        consumer3 = new Consumer("All Consumer", all);

        publisher.subscribe(consumer1);
        publisher.subscribe(consumer2);
        publisher.subscribe(consumer3);

        System.out.print("Main: Start\n");

        News news=new News();
        news.setTxt("Basketball news");
        news.setCategory(News.SPORTS);
        news.setDate(LocalDate.now());

        publisher.publish(news);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        news=new News();
        news.setTxt("Money news");
        news.setCategory(News.ECONOMIC);
        news.setDate(LocalDate.now());
        publisher.publish(news);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        news=new News();
        news.setTxt("Europe news");
        news.setCategory(News.WORLD);
        news.setDate(LocalDate.now());
        publisher.publish(news);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        news=new News();
        news.setTxt("Space news");
        news.setCategory(News.SCIENCE);
        news.setDate(LocalDate.now());
        publisher.publish(news);

        publisher.shutdown();
        System.out.print("Main: End\n");

    }
}
