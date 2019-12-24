package com.jennery.blog.spider.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {

    private Integer id;
    private String nickname;
    private String slug;

    @JsonProperty("avatar_url")
    private String avatarUrl;
}
