package nz.co.price.alert.company;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;

public class CompanyActor extends AbstractLoggingActor {
    private String name;

    public static Props props() {
        return Props.create(CompanyActor.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(CreateCompanyMessage.class, r -> {
            name = r.getName();
        }).build();
    }
}
