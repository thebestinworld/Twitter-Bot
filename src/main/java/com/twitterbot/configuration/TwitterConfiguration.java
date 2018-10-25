package com.twitterbot.configuration;

import com.twitterbot.Constants;
import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Component
public class TwitterConfiguration {

    public Twitter getTwitter() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(Constants.CONSUMER_KEY)
                .setOAuthConsumerSecret(Constants.CONSUMER_SECRET)
                .setOAuthAccessToken(Constants.ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(Constants.TOKEN_SECRET);
        TwitterFactory tf = new TwitterFactory(cb.build());

        return tf.getInstance();
    }
}
