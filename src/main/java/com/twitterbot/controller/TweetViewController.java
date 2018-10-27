package com.twitterbot.controller;

import com.twitterbot.configuration.TwitterConfiguration;
import com.twitterbot.dto.TweetDTO;
import com.twitterbot.entity.Tweet;
import com.twitterbot.repository.TweetRepository;
import com.twitterbot.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.HashSet;
import java.util.List;

@Controller
public class TweetViewController {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private TwitterService twitterService;

    @GetMapping("/edit/{id}")
    public ModelAndView editTweet(@PathVariable Integer id) {

        ModelAndView model = new ModelAndView("edit");

        if (this.tweetRepository.existsById(id)) {
            Tweet tweet = this.tweetRepository.getOne(id);
            model.addObject("tweet", tweet);
            System.out.println(tweet.getContent());
        }

        return model;
    }

    @PostMapping("/edit/{id}")
    public String editProcess(@PathVariable Integer id, TweetDTO tweetDTO) {
        if (this.tweetRepository.existsById(id)) {
            Tweet tweet = this.tweetRepository.getOne(id);
            tweet.setContent(tweetDTO.getContent());

            this.tweetRepository.saveAndFlush(tweet);

            return "redirect:/database";
        }

        return "redirect:/database";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteTweet(@PathVariable Integer id) {

        ModelAndView model = new ModelAndView("delete");

        if (this.tweetRepository.existsById(id)) {
            Tweet tweet = this.tweetRepository.getOne(id);
            model.addObject("tweet", tweet);
        }

        return model;
    }

    @PostMapping("/delete/{id}")
    public String deleteProcess(@PathVariable Integer id) {
        if (!this.tweetRepository.existsById(id)) {
            return "redirect:/database";
        }

        Tweet tweet = this.tweetRepository.getOne(id);


        this.tweetRepository.delete(tweet);

        return "redirect:/database";
    }

    @GetMapping("/trends")
    public ModelAndView getTrendingTweets() throws TwitterException {
        List<String> trends = twitterService.getTrendingTweets();

        ModelAndView model = new ModelAndView("trends");
        model.addObject("trends", trends);

        return model;
    }
}
