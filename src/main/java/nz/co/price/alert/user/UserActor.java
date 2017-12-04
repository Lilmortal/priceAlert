package nz.co.price.alert.user;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import nz.co.price.alert.product.CreateProductMessage;
import nz.co.price.alert.product.ProductActor;

import static nz.co.price.alert.user.UserActor.Msg.GET_USERNAME;

public class UserActor extends AbstractLoggingActor {
    private String username;

    public enum Msg {
        GET_USERNAME
    }

    public static Props props() {
        return Props.create(UserActor.class);
    }

    public final class UsernameRecorded {
        public UsernameRecorded(String username) {
            UserActor.this.username = username;
        }
    }

    // Put this in company
//    @Override
//    public void preStart() throws Exception {
//        ActorRef productActor = getContext().actorOf(ProductActor.props(), "product");
//        productActor.tell(ProductActor.Msg.CREATE, getSelf());
//    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(UpdateUsernameMessage.class, r -> {
            username = r.getUsername();
            log().info(this.username + " is now updated.");
            getSender().tell(new UsernameRecorded(this.username), getSelf());
        }).matchEquals(GET_USERNAME, r -> {
            log().info("The username is " + username);
        }).match(CreateProductMessage.class, r -> {
            ActorRef productActor = getContext().actorOf(ProductActor.props(), r.getProduct());
            productActor.tell(new CreateProductMessage(r.getProduct()), getSelf());
        }).build();
    }
}