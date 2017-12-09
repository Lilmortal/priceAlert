package nz.co.price.alert.routes;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

public class RoutesServer extends AllDirectives implements Server {
    private ActorSystem system;

    public RoutesServer(ActorSystem system) {
        this.system = system;
    }

    public CompletionStage<ServerBinding> createServer(String hostName, int portNumber) {
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        Routes routes = new Routes();
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = routes.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow, ConnectHttp.toHost(hostName, portNumber), materializer);

        // TODO: Maybe can use AspectJ for this
        System.out.println("Running server in " + hostName + ":" + portNumber);

        return binding;
    }
}
