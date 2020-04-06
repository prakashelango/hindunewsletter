package com.hindu.newslettersubscription.service;

import com.hindu.newslettersubscription.model.Category;
import com.hindu.newslettersubscription.serviceimpl.PubSubService;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class Subscriber {
    private List<Category> subscriberNewsLetter = new ArrayList<Category>();

    public abstract void subscribe(Category category, PubSubService pubSubService);

    public abstract void unSubscribe(Category category, PubSubService pubSubService);

    public abstract void getNewsLetterForSubsciberofNewsLetter(Category category, PubSubService pubSubService);

    public void printNewsLetter(){
        subscriberNewsLetter.forEach(category -> System.out.println("List of News Letters -> "+ category.getCategoryType() + " : " + category.getCategoryDetails()));
    }
}
