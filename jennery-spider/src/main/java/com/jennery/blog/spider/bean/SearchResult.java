package com.jennery.blog.spider.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SearchResult {

    private String q;
    private Integer page;
    private String type;
    @JsonProperty("total_count")
    private Integer totalCount;

    @JsonProperty("per_page")
    private Integer perPage;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("order_by")
    private String orderBy;

    private List<Entry> entries;

    @JsonProperty("related_users")
    private List<RelatedUser> relatedUsers;

    @JsonProperty("more_related_users")
    private Boolean moreRelatedUsers;

    @JsonProperty("related_collections")
    private List<RelatedCollection> relatedCollections;

    @JsonProperty("more_related_collections")
    private Boolean moreRelatedCollections;
}
