package com.jennery.blog.spider.util;

import com.jennery.blog.spider.constant.Constant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpiderHttpUtil<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpiderHttpUtil.class);
    public static RestTemplate restTemplate = new RestTemplate();


    public static List<String> dealSubSelector(Elements elements, String subSelector, String attr) {
        List<String> attrValueList = new ArrayList<>();
        if (null != elements) {
            for (Element ele : elements) {
                String href = ele.select("a").attr("href");
                if (!StringUtils.isEmpty(href)) {
                    attrValueList.add(href);
                }
            }
        }
        return attrValueList;
    }


    public static Elements getElements(String url, HttpMethod method, String selector) {
        String html = SpiderHttpUtil.getHtml(url, method, String.class, null);
        if (StringUtils.isEmpty(html)) {
            LOGGER.info("spide html [{}] but got empty html [{}]", Constant.QIANKU_VIDEO_URL, html);
        }
        return Jsoup.parse(html).select(selector);
    }


    public static <T> T getHtml(String url, HttpMethod method, Class<T> clazz, Object param) {
        final ResponseEntity<T> result = getResponseEntity(url, method, clazz, param);
        if (result.getStatusCode().value() != HttpStatus.OK.value()) {
            throw new RuntimeException(result.getStatusCode().toString());
        }
        return result.getBody();
    }


    @SuppressWarnings("unchecked")
    public static <T> ResponseEntity<T> getResponseEntity(String url, HttpMethod method, Class<T> clazz, Object param) {
        if (null == param) {
            return getHtmlWithOutParam(url, method, clazz);
        } else if (param instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) param;
            return getHtmlWithMapParam(url, method, clazz, map);
        } else {
            return getHtmlWithMapParam(url, method, clazz, param);
        }
    }

    public static <T> ResponseEntity<T> getHtmlWithOutParam(String url, HttpMethod method, Class<T> clazz) {
        return restTemplate.exchange(url, method, generatorHttpEntity(), clazz);
    }


    public static <T> ResponseEntity<T> getHtmlWithMapParam(String url, HttpMethod method, Class<T> clazz, Map<String, Object> params) {
        return restTemplate.exchange(url, method, generatorHttpEntity(), clazz, params);
    }

    public static <T> ResponseEntity<T> getHtmlWithMapParam(String url, HttpMethod method, Class<T> clazz, Object params) {
        return restTemplate.exchange(url, method, generatorHttpEntity(), clazz, params);
    }

    public static HttpEntity<?> generatorHttpEntity() {
        return new HttpEntity<>(generatorHeaders());
    }


    public static HttpHeaders generatorHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
        httpHeaders.add(HttpHeaders.ACCEPT, "application/json");
        return httpHeaders;
    }


    public static String reviseUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url cannot be null or empty");
        }
        if (url.startsWith("//")) {
            return Constant.HTTP_SHORT_SCHEMA_PREFIX + url;
        }
        if (url.startsWith(Constant.HTTP_SIMPLE_SCHEMA_PREFIX)) {
            return url;
        }
        return Constant.HTTP_FULL_SCHEMA_PREFIX + url;
    }
}
