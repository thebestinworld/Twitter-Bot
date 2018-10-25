package com.twitterbot.dto;


import org.springframework.stereotype.Component;

@Component
public class TweetDTO {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
