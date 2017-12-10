package nz.co.price.alert.actors.user;

import nz.co.price.alert.routes.interfaces.Get;
import nz.co.price.alert.routes.interfaces.Post;
import nz.co.price.alert.routes.interfaces.Route;
import nz.co.price.alert.routes.interfaces.Routes;

@Routes
public class UserRoute {
    @Get
    @Route(parameter = "")
    public void test() {

    }

    @Post
    @Route(parameter = "")
    public void lala(String fish) {

    }
}
