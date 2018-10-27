package com.twitterbot.controller;

import com.twitterbot.entity.Tweet;
import com.twitterbot.repository.TweetRepository;
import com.twitterbot.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.TwitterException;

import java.util.List;

@Component
public class ScheduleTask {

    @Autowired
    private TwitterService twitterService;
    @Autowired
    private TweetRepository tweetRepository;



    @Scheduled(fixedRate=60*60*1000, initialDelay=10*60*1000)
    public void reportCurrentTime() throws TwitterException {
        List<Tweet> tweets = tweetRepository.findAllByOrderByIdAsc();
        Tweet tweet = tweets.get(0);
        twitterService.tweet(tweet.getContent());
        tweetRepository.delete(tweet);
    }


}
