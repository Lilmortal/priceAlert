package nz.co.price.alert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

public class priceAlertApplication {
    private final static Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What do you want to search...");

            String search = scanner.next();
            Document doc = null;
            try {
                doc = Jsoup.connect(String.format("https://www.pbtech.co.nz/search?sf=%s", search)).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            LOG.info(doc.title());
            Elements newsHeadlines = doc.select("#scrollPanel .item_description a");
            for (Element headline : newsHeadlines) {

                LOG.info(String.format("%s\n\t%s",
                        headline.attr("title"), headline.absUrl("href")));
            }

            Elements specializedHeadlines = doc.select("#recently_browsed .item_short_name a");
            for (Element headline : specializedHeadlines) {

                LOG.info(String.format("%s\n\t%s",
                        headline.attr("title"), headline.absUrl("href")));
            }
        }
    }
}
