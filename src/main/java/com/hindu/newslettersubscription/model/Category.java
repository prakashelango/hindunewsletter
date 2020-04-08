package com.hindu.newslettersubscription.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "newslettercategory")
public class Category {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private boolean susbsciptionStatus;
    @Column
    private LocalDateTime createdDatetime;
    @Column
    private LocalDateTime updatedDateTime;

}
