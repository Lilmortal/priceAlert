package nz.co.price.alert.user;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class User extends AbstractActor {
    private String name;

    public static Props props(String name) {
        return Props.create(User.class, name);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().matchEquals("view", r -> {
            System.out.println(r);
            System.out.println("LOL");
        }).build();
    }
}