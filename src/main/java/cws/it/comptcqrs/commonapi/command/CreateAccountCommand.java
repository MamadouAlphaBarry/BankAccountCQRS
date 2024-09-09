package cws.it.comptcqrs.commonapi.command;

import cws.it.comptcqrs.commonapi.enums.AccountStatus;
import cws.it.comptcqrs.commonapi.event.AccountCreatedEvent;
import lombok.Getter;
import org.axonframework.eventsourcing.EventSourcingHandler;

public class CreateAccountCommand extends  BaseCommand<String>{
   @Getter
   private  double initialBalance;
   @Getter private String currency;
   private AccountStatus status;

    public CreateAccountCommand(String id, double amount, String currency,AccountStatus status) {
        super(id);
        this.initialBalance = amount;
        this.currency = currency;
        this.status=status;
    }

}
