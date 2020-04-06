package com.hindu.newslettersubscription.serviceimpl;

import com.hindu.newslettersubscription.config.RepoConfig;
import com.hindu.newslettersubscription.repository.CategoryRepository;
import com.hindu.newslettersubscription.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepoConfigImpl implements RepoConfig {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }
}
