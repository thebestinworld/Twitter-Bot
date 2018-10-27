package com.twitterbot.controller;

import com.twitterbot.dto.TweetDTO;
import com.twitterbot.entity.Tweet;
import com.twitterbot.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/getTweets")
    public ResponseEntity getTweets(){

        List<Tweet> tweets = tweetRepository.findAllByOrderByIdAsc();
        return ResponseEntity.ok(tweets);

    }



}
