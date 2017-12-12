package nz.co.price.alert;

import akka.actor.ActorSystem;
import akka.http.javadsl.ServerBinding;
import nz.co.price.alert.routes.RoutesServer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.CompletionStage;

public class PriceAlertApplication {
    public static void main(String[] args) {
        PriceAlertInitializer priceAlertInitializer = new PriceAlertInitializer();
        priceAlertInitializer.start();
    }
}
