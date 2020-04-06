package com.hindu.newslettersubscription.serviceimpl;

import com.hindu.newslettersubscription.common.Promise;
import com.hindu.newslettersubscription.common.React;
import com.hindu.newslettersubscription.common.Reader;
import com.hindu.newslettersubscription.model.Category;
import com.hindu.newslettersubscription.model.CategoryInfo;
import com.hindu.newslettersubscription.repository.CategoryRepository;

import java.util.List;

public class SubscriberFacade {

    public static Reader<CategoryRepository, Promise<CategoryInfo>> subscribeCategory(final CategoryInfo categoryInfo) {
        return Reader.of(categoryRepository -> React.of(() -> categoryInfo)
                .then(SubscriberFacade::getCategoryFromInfo)
                .then(categoryRepository::save)
                .then(SubscriberFacade::getCategoryInfoFromCategory)
                .getPromise());
    }

    public static Reader<CategoryRepository, Promise<CategoryInfo>> unSubscribeCategory(final CategoryInfo categoryInfo) {
        return Reader.of(categoryRepository -> React.of(() -> categoryInfo)
                .then(SubscriberFacade::getCategoryFromInfo)
                .thenV(categoryRepository::delete)
                .then(SubscriberFacade::getCategoryInfoFromCategory)
                .getPromise());
    }

    private static Category getCategoryFromInfo(final CategoryInfo categoryInfo){
        return Category.builder()
                .categoryDetails(categoryInfo.getCategoryDetails())
                .categoryType(categoryInfo.getCategoryType())
                .categoryImage(categoryInfo.getCategoryImage()).build();
    }

    private static CategoryInfo getCategoryInfoFromCategory(final Category category){
        return CategoryInfo.builder()
                .categoryDetails(category.getCategoryDetails())
                .categoryType(category.getCategoryType())
                .categoryImage(category.getCategoryImage()).build();
    }
}
