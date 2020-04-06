package com.hindu.newslettersubscription.repository;

import com.hindu.newslettersubscription.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
