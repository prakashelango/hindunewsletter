package com.hindu.newslettersubscription.serviceimpl;

import com.hindu.newslettersubscription.common.Promise;
import com.hindu.newslettersubscription.common.React;
import com.hindu.newslettersubscription.common.Reader;
import com.hindu.newslettersubscription.model.Category;
import com.hindu.newslettersubscription.model.CategoryInfo;
import com.hindu.newslettersubscription.model.User;
import com.hindu.newslettersubscription.model.UserInfo;
import com.hindu.newslettersubscription.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SubscriberFacade {

    private static PubSubService pubSubService = new PubSubService();

    public static Reader<UserRepository, Promise<UserInfo>> subscribeCategory(final UserInfo userInfo) {
        return Reader.of(userRepository -> React.of(() -> userInfo)
                .then(SubscriberFacade::getCategoryFromInfo)
                .then(userRepository::save)
                .thenV(SubscriberFacade::doSubscribe)
                .then(SubscriberFacade::getCategoryInfoFromCategory)
                .getPromise());
    }

    public static Reader<UserRepository, Promise<UserInfo>> unSubscribeCategory(final UserInfo userInfo) {
        return Reader.of(userRepository -> React.of(() -> userInfo)
                .then(SubscriberFacade::getCategoryFromInfo)
                .thenV(userRepository::delete)
                .thenV(SubscriberFacade::doUnSubscribe)
                .then(SubscriberFacade::getCategoryInfoFromCategory)
                .getPromise());
    }

    public static Reader<UserRepository, Promise<List<UserInfo>>> fetchSubscribedCategory() {
        return Reader.of(userRepository -> React.of(() -> userRepository.findAll())
                .then(SubscriberFacade::getUserInfoFromuser)
                .getPromise());
    }


    private static void doSubscribe(User user){
        user.getCategory().stream().forEach( category -> new SubscriberImpl().subscribe(category,pubSubService) );
    }

    private static void doUnSubscribe(User user){
        user.getCategory().stream().forEach( category -> new SubscriberImpl().unSubscribe(category,pubSubService) );
    }

    private static User getCategoryFromInfo(final UserInfo userInfo){
        return User.builder()
                .category(getCategoryListFromInfoList(userInfo.getCategoryInfo()))
                .email(userInfo.getEmail())
                .createdDatetime(LocalDateTime.now())
                .updatedDateTime(LocalDateTime.now()).build();
    }

    private static List<Category> getCategoryListFromInfoList(List<CategoryInfo> categoryInfo){
        return categoryInfo.stream().map(categoryInfo1 -> Category.builder()
                .categoryImage(categoryInfo1.getCategoryImage())
                .categoryType(categoryInfo1.getCategoryType())
                .susbsciptionStatus(categoryInfo1.isSusbsciptionStatus())
                .categoryDetails(categoryInfo1.getCategoryDetails()).build()).collect(Collectors.toList());
    }

    private static UserInfo getCategoryInfoFromCategory(final User user){
        return UserInfo.builder()
                .email(user.getEmail())
                .categoryInfo(getCategoryFromCategoryList(user.getCategory())).build();
    }

    private static List<CategoryInfo> getCategoryFromCategoryList(List<Category> category){
        return category.stream().map(categoryInfo1 -> CategoryInfo.builder()
                .categoryImage(categoryInfo1.getCategoryImage())
                .categoryType(categoryInfo1.getCategoryType())
                .susbsciptionStatus(categoryInfo1.isSusbsciptionStatus())
                .categoryDetails(categoryInfo1.getCategoryDetails()).build()).collect(Collectors.toList());
    }

    private static List<UserInfo> getUserInfoFromuser(List<User> user){
        return user.stream().map(user1 -> UserInfo.builder()
                .categoryInfo(getCategoryFromCategoryList(user1.getCategory()))
                .email(user1.getEmail())
                .id(user1.getId())
                .build()).collect(Collectors.toList());
    }
}
