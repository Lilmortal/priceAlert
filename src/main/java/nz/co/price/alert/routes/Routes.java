package nz.co.price.alert.routes;

import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import nz.co.price.alert.actors.user.User;
import nz.co.price.alert.actors.user.UserRoute;


public class Routes extends AllDirectives {
    private static final String PACKAGE_PREFIX_NAME = "nz.co.price.alert";

    RouteDirective userRoute = new UserRoute();

    public Route createRoute() {
        return route(
                pathPrefix("api", () ->
                        pathPrefix("v1", () ->
                                userRoute.getRoute()
                        )
                )
        );
    }

//    private RouteAnnotation getRoutes() {
////        RouteAnnotation b = get(() ->
////                path(PathMatchers.segment("login").slash(PathMatchers.integerSegment()), userId ->
////                        parameter("username", username ->
////                                complete(username + " is cool." + " Id: " + userId)
////                        )
////                )
////        );
////
////        RouteAnnotation c = get(() ->
////                path(PathMatchers.segment("test").slash(PathMatchers.integerSegment()), userId ->
////                        parameter("username", username ->
////                                complete(username + " is cool." + " Id: " + userId)
////                        )
////                )
////        );
////
////        b = b.orElse(c);
////        RouteAnnotation a = route(b);
////        return a;
//
////        RouteAnnotation route = route();
//        Reflections reflections = new Reflections(PACKAGE_PREFIX_NAME);
//
//        Set<Class<?>> routeClasses = reflections.getTypesAnnotatedWith(RoutesAnnotation.class);
//        List<Method[]> methods = routeClasses.stream().map(Class::getDeclaredMethods).collect(Collectors.toList());
//
//        List<Method[]> filteredMethods = methods.stream().map(methodArray -> Arrays.stream(methodArray).filter(method -> method.isAnnotationPresent(nz.co.price.alert.routes.interfaces.RouteAnnotation.class)).toArray(Method[]::new)).collect(Collectors.toList());
//
//        for (Method[] filteredMethod : filteredMethods) {
//            for (Method method : filteredMethod) {
////                Parameter[] parameters = method.getParameters();
////                for (Parameter parameter : parameters) {
////                    System.out.println(method.getName() + " " + parameter.getName() + " " + parameter.getType());
////                }
////                RouteAnnotation newRoute = route();
////                if (method.isAnnotationPresent(Get.class)) {
////                    route = route.orElse(get(() ->
////                        path(PathMatchers.segment(method.getAnnotation(nz.co.price.alert.routes.interfaces.RouteAnnotation.class).parameter()), () ->
////                            parameter(method.getParameters())
////                        )
////                    ))
////                }
//
//                String parameter = method.getAnnotation(nz.co.price.alert.routes.interfaces.RouteAnnotation.class).value();
//                for (Annotation[] annotations : method.getParameterAnnotations()) {
//                    for (Annotation annotation : annotations) {
//                        if (annotation.annotationType().equals(Param.class)) {
//                            Param param = (Param) annotation;
//                            int parameterRoute = parameter(param.value(), param ->)
//                            System.out.println(method.getAnnotation(nz.co.price.alert.routes.interfaces.RouteAnnotation.class).value() + " " + param.value());
//
//                            // Search for {param}, and
//                        }
//                    }
//                }
//            }
////                System.out.println(method);
//        }
//        return null;
//    }
}
