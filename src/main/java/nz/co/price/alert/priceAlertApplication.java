package nz.co.price.alert;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import nz.co.price.alert.user.UserManager;

import java.io.IOException;

public class priceAlertApplication {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("priceAlert");
        try {
            ActorRef userManagerActor = system.actorOf(UserManager.props());
            userManagerActor.tell("createUserGroup", userManagerActor);

            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            system.terminate();
        }

        System.setProperty("http.proxyHost", "icecrown.isis.airnz.co.nz");
        System.setProperty("http.proxyPort", "3128");
        System.setProperty("https.proxyHost", "icecrown.isis.airnz.co.nz");
        System.setProperty("https.proxyPort", "3128");
//        while(true) {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("What do you want to search...");
//
//            String search = scanner.next();
//            Document doc = null;
//            try {
//                doc = Jsoup.connect(String.format("https://www.pbtech.co.nz/search?sf=%s", search)).get();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            LOG.info(doc.title());
//            Elements newsHeadlines = doc.select("#scrollPanel .item_description a");
//            for (Element headline : newsHeadlines) {
//
//                LOG.info(String.format("%s\n\t%s",
//                        headline.attr("title"), headline.absUrl("href")));
//            }
//
//            Elements specializedHeadlines = doc.select("#recently_browsed .item_short_name a");
//            for (Element headline : specializedHeadlines) {
//
//                LOG.info(String.format("%s\n\t%s",
//                        headline.attr("title"), headline.absUrl("href")));
//            }
//        }
    }
}
