package com.jennery.blog.spider.context;

import com.alibaba.fastjson.JSON;
import com.jennery.blog.spider.bean.Entry;
import com.jennery.blog.spider.bean.SearchResult;
import com.jennery.blog.spider.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SpiderContext {

    private final RestTemplate restTemplate;

    @Autowired
    public SpiderContext(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public void searchSpiderJianshu(String word) {
        HttpEntity<SearchResult> entity = new HttpEntity<>(generatorHeaders());
        Map<String, Object> params = new HashMap<>();
        params.put("word", word);
        ResponseEntity<SearchResult> searchResult = restTemplate.exchange(Constant.JIANSHU_SEARCH_URL, HttpMethod.POST, entity, SearchResult.class, params);
        if (searchResult.getStatusCode().value() != HttpStatus.OK.value()) {
            throw new RuntimeException(searchResult.getStatusCode().toString());
        }
        SearchResult result = searchResult.getBody();
        if (null == result || result.getTotalPages() == null || result.getTotalPages() == 0) {
            throw new RuntimeException("无搜索结果！");
        }
        final Integer totalPages = result.getTotalPages();
        for (int i = 0; i < totalPages; i++) {
            List<Entry> entries = result.getEntries();
            String entriesString = entries.stream().map(JSON::toJSONString).collect(Collectors.joining(";\n"));
        }

        System.out.println(JSON.toJSONString(searchResult));
    }

    private void handlePerPage(List<Entry> entries) {
        for (Entry entry : entries) {

        }
    }


    public HttpHeaders generatorHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
        httpHeaders.add(HttpHeaders.ACCEPT, "application/json");
        return httpHeaders;
    }


}
