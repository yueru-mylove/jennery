package com.jennery.blog.spider.bean;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entry {

    private Integer id;
    private String title;
    private String slug;
    private String content;

    private User user;
    private Notebook notebook;

    private Boolean commentable;

    @JsonProperty("public_comments_count")
    private Integer publicCommentsCount;

    @JsonProperty("likes_count")
    private Integer likesCount;

    @JsonProperty("views_count")
    private Integer viewsCount;

    @JsonProperty("total_rewards_count")
    private Integer totalRewardsCount;
    @JsonProperty("first_shared_at")
    private String firstSharedAt;


}
