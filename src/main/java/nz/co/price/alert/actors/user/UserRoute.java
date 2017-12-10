package nz.co.price.alert.actors.user;

import nz.co.price.alert.routes.interfaces.*;

@Routes
public class UserRoute {
    @Get
    @Route("")
    public void test() {

    }

    @Post
    @Route("")
    public void lala(@Param("fish") String fish) {

    }
}
