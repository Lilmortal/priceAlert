package nz.co.price.alert.user;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import nz.co.price.alert.product.CreateProductMessage;
import nz.co.price.alert.product.ProductActor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

import static nz.co.price.alert.user.UserActor.Msg.GET_USERNAME;

public class UserActor extends AbstractLoggingActor {
    private String username;

    public enum Msg {
        GET_USERNAME
    }

    public static Props props() {
        return Props.create(UserActor.class);
    }

    public final class UsernameRecorded {
        public UsernameRecorded(String username) {
            UserActor.this.username = username;
        }
    }

    @Override
    public void preStart() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to search...");
        while (true) {
            String product = scanner.nextLine();

            Document doc = null;
            try {
                doc = Jsoup.connect(String.format("https://www.pbtech.co.nz/search?sf=%s", product)).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            log().info(doc.title());
            Elements newsHeadlines = doc.select("#scrollPanel .item_description a");
            for (Element headline : newsHeadlines) {

                log().info(String.format("%s\n\t%s",
                        headline.attr("title"), headline.absUrl("href")));
            }

            Elements specializedHeadlines = doc.select("#recently_browsed .item_short_name a");
            for (Element headline : specializedHeadlines) {

                log().info(String.format("%s\n\t%s",
                        headline.attr("title"), headline.absUrl("href")));
            }
            ActorRef productActor = getContext().actorOf(ProductActor.props(), "product");
            productActor.tell(ProductActor.Msg.CREATE, getSelf());
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(UpdateUsernameMessage.class, r -> {
            username = r.getUsername();
            log().info(this.username + " is now updated.");
            getSender().tell(new UsernameRecorded(this.username), getSelf());
        }).matchEquals(GET_USERNAME, r -> {
            log().info("The username is " + username);
        }).match(CreateProductMessage.class, r -> {
            ActorRef productActor = getContext().actorOf(ProductActor.props(), r.getProduct());
            productActor.tell(new CreateProductMessage(r.getProduct()), getSelf());
        }).build();
    }
}
