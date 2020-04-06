package com.hindu.newslettersubscription.serviceimpl;

import com.hindu.newslettersubscription.model.Category;
import com.hindu.newslettersubscription.service.Publisher;

public class PublisherImpl implements Publisher {

    @Override
    public void publish(Category category, PubSubService pubSubService) {
        pubSubService.addCategoryToQueue.accept(category);
    }
}
