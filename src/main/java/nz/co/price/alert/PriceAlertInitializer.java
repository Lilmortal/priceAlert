package nz.co.price.alert;

import akka.actor.ActorSystem;
import akka.http.javadsl.ServerBinding;
import nz.co.price.alert.routes.RoutesServer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.CompletionStage;

public class PriceAlertInitializer {
    private static final String CONFIG = "config.properties";

    public void start() {
        Properties properties = new Properties();
        Optional<InputStream> inputStream = Optional.of(PriceAlertApplication.class.getClassLoader().getResourceAsStream(CONFIG));
        ActorSystem system = ActorSystem.create("priceAlert");

        if (!inputStream.isPresent()) {

            System.out.println("Config file could not be read.");
            return;
        }

        try {
            properties.load(inputStream.get());

            String hostName = properties.getProperty("hostName");
            int portNumber = Integer.parseInt(properties.getProperty("portNumber"));

            RoutesServer routesServer = new RoutesServer(system);
            CompletionStage<ServerBinding> binding = routesServer.createServer(hostName, portNumber);

            System.out.println("Press enter to stop...");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }

            binding.thenCompose(ServerBinding::unbind)
                    .thenAccept(unbound -> system.terminate());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream.isPresent()) {
                try {
                    inputStream.get().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

//        ActorRef userManagerActor = system.actorOf(UserManagerActor.props(), "userManager");
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Param in your username.");
//        while (scanner.hasNextLine()) {
//            String username = scanner.nextLine();
//            if (username.isEmpty()) {
//                System.out.println("Username is empty.");
//                continue;
//            }
//            userManagerActor.tell(new CreateUserMessage(username), userManagerActor);
//            break;
////            userManagerActor.tell(UserManagerActor.Msg.LIST_ALL_USERS, userManagerActor);
//        }
//        System.out.println("Logged in.");



//            System.setProperty("http.proxyHost", "icecrown.isis.airnz.co.nz");
//            System.setProperty("http.proxyPort", "3128");
//            System.setProperty("https.proxyHost", "icecrown.isis.airnz.co.nz");
//            System.setProperty("https.proxyPort", "3128");
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