package nz.co.price.alert.actors.user;

import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import nz.co.price.alert.routes.RouteDirective;
import nz.co.price.alert.routes.interfaces.*;

// TODO: Make this singleton
public class UserRoute extends AllDirectives implements RouteDirective {
    @Override
    public Route getRoute() {
        return get(() ->
                path(PathMatchers.segment("login").slash(PathMatchers.integerSegment()), userId ->
                        parameter("username", username ->
                                complete(username + " is cool." + " Id: " + userId)
                        )
                )
        );
//        ),
//        post(() ->
//                path("oauth", () ->
//                        path("register", () ->
//                                entity(Jackson.unmarshaller(User.class), user ->
//                                        onSuccess(() ->
//                                                complete("User is saved."))
//                                )
//                        )
//                )
//        );
    }
}
