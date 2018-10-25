package com.twitterbot.service;

import com.twitterbot.configuration.TwitterConfiguration;
import org.springframework.stereotype.Service;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

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
}
