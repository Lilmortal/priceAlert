package nz.co.price.alert.actors.product;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import nz.co.price.alert.actors.company.CompanyActor;
import nz.co.price.alert.actors.company.CreateCompanyMessage;

public class ProductActor extends AbstractLoggingActor {
    public enum Msg {
        CREATE
    }

    public static Props props() {
        return Props.create(ProductActor.class);
    }

    @Override
    public void preStart() throws Exception {
        ActorRef companyActor = getContext().actorOf(CompanyActor.props(), "company");
        companyActor.tell(new CreateCompanyMessage("company"), getSelf());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().matchEquals(Msg.CREATE, r -> {
        }).build();
    }
}
