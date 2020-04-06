package com.hindu.newslettersubscription.service;

import com.hindu.newslettersubscription.model.Category;
import com.hindu.newslettersubscription.serviceimpl.PubSubService;

public interface Publisher {
    void publish(Category category, PubSubService pubSubService);
}
