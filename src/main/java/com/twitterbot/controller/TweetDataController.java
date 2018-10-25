package com.twitterbot.controller;

import com.twitterbot.dto.TweetDTO;
import com.twitterbot.entity.Tweet;
import com.twitterbot.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

@RestController
public class TweetDataController {

    @Autowired
    private TweetRepository tweetRepository;

    @PostMapping("/addTweet")
    public ResponseEntity creteProcess(@RequestBody TweetDTO tweetDTO){
        Tweet tweet = new Tweet(tweetDTO.getContent());
        this.tweetRepository.saveAndFlush(tweet);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
