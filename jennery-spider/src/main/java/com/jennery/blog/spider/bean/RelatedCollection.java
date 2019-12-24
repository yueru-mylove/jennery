package com.jennery.blog.spider.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RelatedCollection {

    private Integer id;
    private String title;
    private String slug;
    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("public_notes_count")
    private Integer publicNotesCount;

    @JsonProperty("likes_count")
    private Integer likesCount;
}
