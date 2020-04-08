package com.hindu.newslettersubscription.serviceimpl;

import com.hindu.newslettersubscription.model.Category;
import com.hindu.newslettersubscription.service.Subscriber;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
@Service
public class PubSubService {

    Queue<Category> newsLetterQueue = new LinkedList<Category>();

    Map<String, Set<Subscriber>> subscribersTopicMap = new HashMap<String, Set<Subscriber>>();

    public Consumer<Category> addCategoryToQueue = category -> newsLetterQueue.add(category);

    public BiConsumer<Category, Subscriber> removeSubscriber = (newsLetter, subscriber) -> {
        if (subscribersTopicMap.containsKey(newsLetter.getCategoryType())) {
            Set<Subscriber> subscribers = subscribersTopicMap.get(newsLetter.getCategoryType());
            subscribers.remove(subscriber);
            subscribersTopicMap.put(newsLetter.getCategoryType(), subscribers);

        }
    };

    private static final BiPredicate<Category, Category> isnewsLettercategorySamePredicate =
            (existingCategory, newCategory) ->  existingCategory.getCategoryType().equalsIgnoreCase(newCategory.getCategoryType());

    public void getMessagesForSubscriberOfCategory(Category newsLetter, Subscriber subscriber) {
        if(newsLetterQueue.isEmpty()){
            System.out.println("No messages from publishers to display");
        }else {
            newsLetterQueue.stream().map(newsLetter1 -> newsLetterQueue.remove())
                    .filter(existingnewsLetter -> isnewsLettercategorySamePredicate.test(existingnewsLetter, newsLetter))
                    .map(en -> subscribersTopicMap.get(newsLetter.getCategoryType()))
                    .forEach(subscribers -> subscribers.stream()
                            .filter(subscriber1 -> subscriber1.equals(subscriber))
                            .map(subscriber1 -> subscriber.getSubscriberNewsLetter())
                            .forEach(subscriberss -> {
                                subscriberss.add(newsLetter);
                                subscriber.setSubscriberNewsLetter(subscriberss);
                            }));
        }
    }

}
