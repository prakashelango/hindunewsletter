package com.hindu.newslettersubscription.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
}
