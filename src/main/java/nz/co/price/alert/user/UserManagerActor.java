package nz.co.price.alert.user;

import akka.actor.AbstractActor;
import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserManagerActor extends AbstractLoggingActor {
    private AtomicInteger id = new AtomicInteger(0);
    private Map<Integer, ActorRef> users = new ConcurrentHashMap<>();


    public enum Msg {
        LIST_ALL_USERS
    }

    public static Props props() {
        return Props.create(UserManagerActor.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(CreateUserMessage.class, r -> {
            ActorRef userActor = getContext().actorOf(UserActor.props(), r.getUsername());
            log().info(id.toString());
            users.put(id.getAndIncrement(), userActor);
        }).matchEquals(Msg.LIST_ALL_USERS, r -> {
            log().info(users.values().toString(), users.size());
        }).build();
    }
}
