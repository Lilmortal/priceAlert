package nz.co.price.alert.actors.product;

import nz.co.price.alert.routes.interfaces.Get;
import nz.co.price.alert.routes.interfaces.Param;
import nz.co.price.alert.routes.interfaces.Route;
import nz.co.price.alert.routes.interfaces.Routes;

@Routes
public class ProductRoute {
    @Get
    @Route("test")
    public void product(@Param("lol") int lol) {

    }
}
