package com.twitterbot.controller;

import com.twitterbot.model.StatsDTO;
import com.twitterbot.model.TweetDTO;
import com.twitterbot.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

@RestController
public class TweetRestController {

    private final TwitterService twitterService;

    @Autowired
    public TweetRestController(TwitterService twitterService) {
        this.twitterService = twitterService;
    }


    @PostMapping("/postTweet")
    public ResponseEntity postTweet(@RequestBody TweetDTO tweetDTO) throws TwitterException {
        twitterService.tweet(tweetDTO.getContent());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/getTweet")
    public ResponseEntity getTweet() throws TwitterException {
        TweetDTO tweetDTO = new TweetDTO();
        tweetDTO.setContent(twitterService.getLastTweet());

        return ResponseEntity.ok(tweetDTO);
    }

    @GetMapping("/getStats")
    public ResponseEntity getStats() throws TwitterException {
        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setTweets(twitterService.getTweetsCount());
        statsDTO.setFollowers(twitterService.getFollowersCount());

        return ResponseEntity.ok(statsDTO);

    }


}
