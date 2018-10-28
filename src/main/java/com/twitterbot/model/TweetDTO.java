package com.twitterbot.model;


import org.springframework.stereotype.Component;

@Component
public class TweetDTO {

    private String content;

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
