package nz.co.price.alert.actors.product;

import nz.co.price.alert.routes.interfaces.Get;
import nz.co.price.alert.routes.interfaces.Route;
import nz.co.price.alert.routes.interfaces.Routes;
import nz.co.price.alert.routes.interfaces.Type;

@Routes
public class ProductRoute {
    @Get
    @Route("test")
    public void product(@Type int lol) {

    }
}
