package com.twitterbot.controller;

import com.twitterbot.model.TweetDTO;
import com.twitterbot.entity.Tweet;
import com.twitterbot.repository.TweetRepository;
import com.twitterbot.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import twitter4j.TwitterException;

import java.util.List;

@Controller
public class TweetController {

    private final TweetRepository tweetRepository;
    private final TwitterService twitterService;

    @Autowired
    public TweetController(TweetRepository tweetRepository, TwitterService twitterService) {
        this.tweetRepository = tweetRepository;
        this.twitterService = twitterService;
    }


    @GetMapping("/create")
    public String create(){
        return "create";
    }

    @PostMapping("/create")
    public String creteProcess(TweetDTO tweetDTO){
        Tweet tweet = new Tweet(tweetDTO.getContent());
        this.tweetRepository.saveAndFlush(tweet);

        return "redirect:/database";
    }

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



    @GetMapping("/database")
    public ModelAndView database(){
        ModelAndView model = new ModelAndView("database");
        List<Tweet> tweets = tweetRepository.findAllByOrderByIdAsc();
        model.addObject("tweets", tweets);


        return model;
    }


    @GetMapping("/trends")
    public ModelAndView getTrendingTweets() throws TwitterException {
        List<String> trends = twitterService.getTrendingTweets();

        ModelAndView model = new ModelAndView("trends");
        model.addObject("trends", trends);

        return model;
    }

    @GetMapping("/search")
    public ModelAndView searchTweet(TweetDTO tweetDTO) throws TwitterException {
        List<String> result = twitterService.searchTweet(tweetDTO.getContent());
        ModelAndView model = new ModelAndView("search");
        model.addObject("result",result);
        return  model;
    }


    @Scheduled(fixedRate=60*60*1000, initialDelay=10*60*1000)
    public void tweetFromDatabase() throws TwitterException {
        List<Tweet> tweets = tweetRepository.findAllByOrderByIdAsc();
        Tweet tweet = tweets.get(0);
        twitterService.tweet(tweet.getContent());
        tweetRepository.delete(tweet);
    }
}
