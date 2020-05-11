package com.jennery.blog.spider.input;


import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class JianshuSearchInput {

    @NotBlank(message = "关键词不能为空！")
    private String word;
}
