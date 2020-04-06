package com.hindu.newslettersubscription.serviceimpl;

import com.hindu.newslettersubscription.model.Category;
import com.hindu.newslettersubscription.service.Subscriber;
import org.springframework.stereotype.Service;

@Service
public class SubscriberImpl extends Subscriber {

    @Override
    public void subscribe(Category category, PubSubService pubSubService) {
        pubSubService.addCategoryToQueue.accept(category);
    }

    @Override
    public void unSubscribe(Category category, PubSubService pubSubService) {
        pubSubService.removeSubscriber.accept(category, this);
    }

    @Override
    public void getNewsLetterForSubsciberofNewsLetter(Category category, PubSubService pubSubService) {
        pubSubService.getMessagesForSubscriberOfCategory(category, this);
    }
}
