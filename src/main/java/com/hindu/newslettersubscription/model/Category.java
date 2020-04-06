package com.hindu.newslettersubscription.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name = "newslettercategory")
public class Category {

    @Id
    @Column
    private Long id;
    @Column
    private byte categoryImage;
    @Column
    private String categoryType;
    @Column
    private String categoryDetails;
    @Column
    private String newsLetterContent;
    @Column
    private LocalDateTime createdDatetime;
    @Column
    private LocalDateTime updatedDateTime;

}
