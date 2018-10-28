package com.twitterbot.model;

import org.springframework.stereotype.Component;

@Component
public class StatsDTO {

    private int tweets;
    private int followers;

    public int getTweets() {
        return this.tweets;
    }

    public void setTweets(int tweets) {
        this.tweets = tweets;
    }

    public int getFollowers() {
        return this.followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }
}
