package com.jcomm.flow.news;

public class PublisherTask implements Runnable{
    private ConsumerData consumerData;
    private News news;

    public PublisherTask(ConsumerData consumerData, News news) {
        this.consumerData = consumerData;
        this.news = news;
    }

    @Override
    public void run() {
        var subscription = consumerData.getMySubscription();
        if(!(subscription.isCancelled()) && (subscription.getRequested() > 0L)
                && (subscription.hasCatergory(news.getCategory()))){
            consumerData.getSubscriber().onNext(news);
            subscription.decreaseRequested();
        }
    }
}
