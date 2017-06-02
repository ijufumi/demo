package jp.ijufumi.bot.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
public class HatebuBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(HatebuBotApplication.class, args);
	}

	@Autowired
    TweetService tweetService;

	@PostConstruct
	public void afterPropertySet() throws IOException {
	    tweetService.searchAndTweet();
    }

    @Bean
    public Twitter twitter(@Value("${OAUTH_CONSUMER_KEY}") String oauthConsumerKey,
                           @Value("${OAUTH_CONSUMER_SECRET}") String oauthConsumerSecret,
                           @Value("${OAUTH_ACCESS_TOKEN}") String oauthAccessToken,
                           @Value("${OAUTH_ACCESS_TOKEN_SECRET}") String oauthAccessTokenSecret) {
        AccessToken accessToken = new AccessToken(oauthAccessToken, oauthAccessTokenSecret);

        Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(oauthConsumerKey, oauthConsumerSecret);
        twitter.setOAuthAccessToken(accessToken);

        return twitter;
    }

    @Bean
    public Logger logger(InjectionPoint point) {
	    return LoggerFactory.getLogger(point.getMember().getDeclaringClass());
    }
}
