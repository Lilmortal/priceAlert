package nz.co.price.alert.routes;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import nz.co.price.alert.routes.interfaces.Routes;
import nz.co.price.alert.routes.interfaces.Type;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class RoutesServer extends AllDirectives implements Server {
    private static final String PACKAGE_PREFIX_NAME = "nz.co.price.alert";

    private ActorSystem system;

    public RoutesServer(ActorSystem system) {
        this.system = system;
    }

    @Override
    public CompletionStage<ServerBinding> createServer(String hostName, int portNumber) {
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow, ConnectHttp.toHost(hostName, portNumber), materializer);

        // TODO: Maybe can use AspectJ for this
        System.out.println("Running server in " + hostName + ":" + portNumber);

        return binding;
    }

    private Route createRoute() {
        Route a = getRoutes();
        return route(
                pathPrefix("api", () ->
                                pathPrefix("v1", () -> a
//                                                get(() ->
//                                                                path(PathMatchers.segment("login").slash(PathMatchers.integerSegment()), userId ->
//                                                                        parameter("username", username ->
//                                                                                complete(username + " is cool." + " Id: " + userId)
//                                                                        )
//                                                                )
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
                ;
    }

    private Route getRoutes() {
//        Route b = get(() ->
//                path(PathMatchers.segment("login").slash(PathMatchers.integerSegment()), userId ->
//                        parameter("username", username ->
//                                complete(username + " is cool." + " Id: " + userId)
//                        )
//                )
//        );
//
//        Route c = get(() ->
//                path(PathMatchers.segment("test").slash(PathMatchers.integerSegment()), userId ->
//                        parameter("username", username ->
//                                complete(username + " is cool." + " Id: " + userId)
//                        )
//                )
//        );
//
//        b = b.orElse(c);
//        Route a = route(b);
//        return a;

//        Route route = route();
        Reflections reflections = new Reflections(PACKAGE_PREFIX_NAME);

        Set<Class<?>> routeClasses = reflections.getTypesAnnotatedWith(Routes.class);
        List<Method[]> methods = routeClasses.stream().map(Class::getDeclaredMethods).collect(Collectors.toList());

        List<Method[]> filteredMethods = methods.stream().map(methodArray -> Arrays.stream(methodArray).filter(method -> method.isAnnotationPresent(nz.co.price.alert.routes.interfaces.Route.class)).toArray(Method[]::new)).collect(Collectors.toList());

        for (Method[] filteredMethod : filteredMethods) {
            for (Method method : filteredMethod) {
                Parameter[] parameters = method.getParameters();
                for (Parameter parameter : parameters) {
                    System.out.println(method.getName() + " " + parameter.getName() + " " + parameter.getType());
                }
//                Route newRoute = route();
//                if (method.isAnnotationPresent(Get.class)) {
//                    route = route.orElse(get(() ->
//                        path(PathMatchers.segment(method.getAnnotation(nz.co.price.alert.routes.interfaces.Route.class).parameter()), () ->
//                            parameter(method.getParameters())
//                        )
//                    ))
//                }
                for (Annotation[] annotations : method.getParameterAnnotations()) {
                    for (Annotation annotation : annotations) {
                        if (annotation.annotationType().equals(Type.class)) {
//                            System.out.println(method.getAnnotation(nz.co.price.alert.routes.interfaces.Route.class).parameter());
                        }
                    }
                }
            }
//                System.out.println(method);
        }
        return null;
    }
}
