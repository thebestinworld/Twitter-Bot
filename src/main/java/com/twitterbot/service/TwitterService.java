package com.twitterbot.service;

import com.twitterbot.configuration.TwitterConfiguration;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class TwitterService {

    private final TwitterConfiguration twitterConfiguration;


    public TwitterService(TwitterConfiguration twitterConfiguration) {
        this.twitterConfiguration = twitterConfiguration;
    }

    public void tweet(String content) throws TwitterException {
        Twitter twitter = twitterConfiguration.getTwitter();
        twitter.updateStatus(content);
    }

    public String getLatestTweet() throws TwitterException {
        Twitter twitter = twitterConfiguration.getTwitter();

        List<Status> statuses = twitter.getHomeTimeline();

        return statuses.get(0).getText();
    }

    public int getTweetsCount() throws TwitterException {
        Twitter twitter = twitterConfiguration.getTwitter();
        User user = twitter.showUser("RandomProfileBG");


        return user.getStatusesCount();
    }

    public int getFollowersCount() throws TwitterException {
        Twitter twitter = twitterConfiguration.getTwitter();
        User user = twitter.showUser("RandomProfileBG");

        return user.getFollowersCount();
    }

    public List<String> getTrendingTweets() throws TwitterException {
        Twitter twitter = twitterConfiguration.getTwitter();
        Trends trends = twitter.getPlaceTrends(	23424977);
        List<String> trendNames = new ArrayList<>();
        int count = 0;
        for (Trend trend : trends.getTrends()) {
            if (count < 10) {
                trendNames.add(trend.getName());
                count++;
            }
        }
        return trendNames;
    }
}
