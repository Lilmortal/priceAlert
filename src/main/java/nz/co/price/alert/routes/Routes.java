package nz.co.price.alert.routes;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

public class Routes extends AllDirectives {

    public Route createRoute() {
        return route(
                path("hello", () ->
                        get(() ->
                                complete("Test")
                        )
                )
        );
    }
}
