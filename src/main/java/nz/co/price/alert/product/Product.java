package nz.co.price.alert.product;

import akka.actor.AbstractActor;
import akka.actor.AbstractLoggingActor;
import akka.actor.Props;

public class Product extends AbstractLoggingActor {
    public enum Msg {
        CREATE
    }

    public static Props props() {
        return Props.create(Product.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().matchEquals(Msg.CREATE, r -> {
            System.out.println("Test");
        }).build();
    }
}
