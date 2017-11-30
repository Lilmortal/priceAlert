package nz.co.price.alert.user;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class UserGroup extends AbstractActor {
    public static Props props() {
        return Props.create(UserGroup.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().matchEquals("normal", r -> {
            ActorRef user = getContext().actorOf(User.props());
            user.tell("view", getSelf());
        }).build();
    }
}
