package com.twitterbot.service;

import com.twitterbot.config.TwitterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class TwitterService {

    private final TwitterConfiguration twitterConfiguration;
    private final Twitter twitter;

    @Autowired
    public TwitterService(TwitterConfiguration twitterConfiguration) {
        this.twitterConfiguration = twitterConfiguration;
        this.twitter = this.twitterConfiguration.getTwitter();
    }


    public void tweet(String content) throws TwitterException {
        this.twitter.updateStatus(content);
    }

    public String getLastTweet() throws TwitterException {
        List<Status> statuses = this.twitter.getHomeTimeline();

        return statuses.get(0).getText();
    }

    public int getTweetsCount() throws TwitterException {
        User user = this.twitter.showUser("RandomProfileBG");

        return user.getStatusesCount();
    }

    public int getFollowersCount() throws TwitterException {
        User user = this.twitter.showUser("RandomProfileBG");

        return user.getFollowersCount();
    }

    public List<String> getTrendingTweets() throws TwitterException {
        Trends trends = this.twitter.getPlaceTrends(23424977);//USA Trending Tweets
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


    public List<String> searchTweet(String string) throws TwitterException {
        Query query = new Query();
        query.setLang("en");
        query.setQuery(string + " -filter:retweets");
        QueryResult result;
        result = this.twitter.search(query);
        List<Status> tweets = result.getTweets();
        List<String> searchResult = new ArrayList<>();
        int count = 0;
        for (Status status : tweets) {
            if (count < 10) {
                searchResult.add(status.getText());
                count++;
            }
        }
        return searchResult;
    }
}
