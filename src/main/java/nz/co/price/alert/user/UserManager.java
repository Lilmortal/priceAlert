package nz.co.price.alert.user;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class UserManager extends AbstractActor {
    private final LoggingAdapter logger = Logging.getLogger(getContext().getSystem(), this);

    public static Props props() {
        return Props.create(UserManager.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().matchEquals("createUserGroup", msg -> {
            System.out.println("test");
            ActorRef userGroup = getContext().actorOf(UserGroup.props());
            System.out.println(userGroup.path());
            userGroup.tell("normal", getSelf());
        }).build();
    }
}
