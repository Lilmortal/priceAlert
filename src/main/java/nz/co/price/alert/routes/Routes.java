package nz.co.price.alert.routes;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.http.javadsl.marshallers.jackson.Jackson;
import nz.co.price.alert.actors.user.User;

public class Routes extends AllDirectives {

    public Route createRoute() {
        Route a = get(() ->
                path(PathMatchers.segment("login").slash(PathMatchers.integerSegment()), userId ->
                        parameter("username", username ->
                                complete(username + " is cool." + " Id: " + userId)
                        )
                )
        );
        return route(
                pathPrefix("api", () ->
                                pathPrefix("v1", () ->
                                                get(() ->
                                                                path(PathMatchers.segment("login").slash(PathMatchers.integerSegment()), userId ->
                                                                        parameter("username", username ->
                                                                                complete(username + " is cool." + " Id: " + userId)
                                                                        )
                                                                )
//                                        ),
//                                post(() ->
//                                        path("oauth", () ->
//                                                path("register", () ->
//                                                        entity(Jackson.unmarshaller(User.class), user -> {
//                                                            System.out.println(user);
//                                                            return onSuccess(() ->
//                                                                    complete("User is saved."))
//                                                        })
//                                                )
//                                        )
                                                )
                                )
                )
        );
    }
}
