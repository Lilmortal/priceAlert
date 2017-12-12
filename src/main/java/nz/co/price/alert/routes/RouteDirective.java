package nz.co.price.alert.routes;

import akka.http.javadsl.server.Route;

public interface RouteDirective {
    Route getRoute();
}
