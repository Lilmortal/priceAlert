package nz.co.price.alert.routes;

import akka.actor.ActorSystem;
import akka.http.javadsl.ServerBinding;

import java.util.concurrent.CompletionStage;

public interface Server {
    CompletionStage<ServerBinding> createServer(String hostName, int portNumber);
}
