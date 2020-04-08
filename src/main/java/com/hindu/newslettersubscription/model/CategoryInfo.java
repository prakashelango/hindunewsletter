package com.hindu.newslettersubscription.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryInfo {

    private Long id;
    private byte categoryImage;
    private String categoryType;
    private String categoryDetails;
    private boolean susbsciptionStatus;
}
