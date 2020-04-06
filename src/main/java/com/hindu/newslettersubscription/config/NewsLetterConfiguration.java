package com.hindu.newslettersubscription.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.hindu.newslettersubscription.repository")
@EnableTransactionManagement
@EntityScan(basePackages = "com.hindu.newslettersubscription.model")
public class NewsLetterConfiguration {

}
