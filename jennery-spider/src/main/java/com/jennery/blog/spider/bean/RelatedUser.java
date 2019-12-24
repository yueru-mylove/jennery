package com.jennery.blog.spider.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RelatedUser extends User {

    @JsonProperty("total_wordage")
    private Integer totalWordage;

    @JsonProperty("total_likes_count")
    private Integer totalLikesCount;
}
