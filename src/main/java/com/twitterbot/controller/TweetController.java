package com.twitterbot.controller;

import com.twitterbot.dto.StatsDTO;
import com.twitterbot.dto.TweetDTO;
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
public class TweetController {

    private final TwitterService twitterService;

    @Autowired
    public TweetController(TwitterService twitterService) {
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
        tweetDTO.setContent(twitterService.getLatestTweet());
        return ResponseEntity.ok(tweetDTO);
    }

    @GetMapping("/getTweetCount")
    public ResponseEntity getTweetCount() throws TwitterException {
        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setTweets(twitterService.getTweetsCount());
        statsDTO.setFollowers(twitterService.getFollowersCount());
        return ResponseEntity.ok(statsDTO);

    }
}
