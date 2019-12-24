package com.jennery.blog.spider.controller;

import com.jennery.blog.spider.context.SpiderContext;
import com.jennery.blog.spider.input.JianshuSearchInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpiderController {

    private final SpiderContext spiderContext;

    @Autowired
    public SpiderController(SpiderContext spiderContext) {
        this.spiderContext = spiderContext;
    }

    @PostMapping("/search")
    public void searchJianshu(@RequestBody JianshuSearchInput searchInput, BindingResult result) {
        dealBindingResult(result);
        spiderContext.searchSpiderJianshu(searchInput.getWord());

    }

    @GetMapping("/search/{word}")
    public void searchJianshu(@PathVariable("word") String word) {
        spiderContext.searchSpiderJianshu(word);
    }


    private void dealBindingResult(BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getAllErrors().toString());
        }
    }

}
