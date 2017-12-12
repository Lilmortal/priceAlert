package nz.co.price.alert.actors.product;

import nz.co.price.alert.routes.interfaces.Get;
import nz.co.price.alert.routes.interfaces.Param;
import nz.co.price.alert.routes.interfaces.RouteAnnotation;
import nz.co.price.alert.routes.interfaces.RoutesAnnotation;

@RoutesAnnotation
public class ProductRoute {
    @Get
    @RouteAnnotation("test")
    public void product(@Param("lol") int lol) {

    }
}
