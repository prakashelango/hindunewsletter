package com.hindu.newslettersubscription.config;

import com.hindu.newslettersubscription.repository.CategoryRepository;
import com.hindu.newslettersubscription.repository.UserRepository;

public interface RepoConfig {

    CategoryRepository getCategoryRepository();

    UserRepository getUserRepository();
}
