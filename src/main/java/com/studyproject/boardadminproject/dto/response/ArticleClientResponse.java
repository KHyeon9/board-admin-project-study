package com.studyproject.boardadminproject.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.studyproject.boardadminproject.dto.ArticleDto;

import java.util.List;


//  가져올 api의 데이터가 _embedded와 page로 이루어져 있어서 이것을 알려주기 위해 작성
public record ArticleClientResponse(
        @JsonProperty("_embedded") Embedded embedded,
        @JsonProperty("page") Page page
        ) {

    public static ArticleClientResponse empty() {
        return new ArticleClientResponse(
                new Embedded(List.of()),
                new Page(1, 0, 1, 0)
        );
    }

    public static ArticleClientResponse of(List<ArticleDto> articles) {
        return new ArticleClientResponse(
                new Embedded(articles),
                new Page(articles.size(), articles.size(), 1, 0)
        );
    }

    public List<ArticleDto> articles() { return this.embedded.articles(); }

    public record Embedded(List<ArticleDto> articles) {}

    public record Page(
            int size,
            long totalElements,
            int totalPages,
            int number
    ) {}
}
