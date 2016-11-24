package jp.ijufumi.bot.twitter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Component
public class TweetService {
    static final String BASE_URL = "http://empowerment-engineering.hatenablog.com";

    Twitter twitter;
    Logger logger;

    public TweetService(Twitter twitter, Logger logger) {
        this.twitter = twitter;
        this.logger = logger;
    }

    public void searchAndTweet() throws IOException {
        String date = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("YYYY/MM/dd"));
        Pattern targetPattern = Pattern.compile(BASE_URL + "/entries/" + date + "/[0-9]+");

        Document archivePage = Jsoup.connect(BASE_URL + "/archive").get();
        Elements linkList = archivePage.select("a.entry-title-link");
        int n = linkList.size();
        logger.info("link size : {}", n);
        for (int i=0;i < n;i++) {
            Element link = linkList.get(i);
            String url = link.attr("href");

            if (targetPattern.matcher(url).matches()) {
                logger.info("url : {}", url);
                tweetLink(link);
            }
            else {
                break;
            }
        }
    }

    private void tweetLink(Element link) {
        try {
            twitter.updateStatus(String.format("[後で見る]%s\n%s", link.text(), link.attr("href")));
        } catch (TwitterException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
