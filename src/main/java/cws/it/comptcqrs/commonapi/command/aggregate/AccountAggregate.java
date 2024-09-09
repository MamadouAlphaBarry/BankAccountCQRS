package cws.it.comptcqrs.commonapi.command.aggregate;


import cws.it.comptcqrs.commonapi.command.CreateAccountCommand;
import cws.it.comptcqrs.commonapi.command.CreditAccountCommand;
import cws.it.comptcqrs.commonapi.command.exception.SoldeInsuffisanteException;
import cws.it.comptcqrs.commonapi.dtos.AccountActivatedEvent;
import cws.it.comptcqrs.commonapi.enums.AccountStatus;
import cws.it.comptcqrs.commonapi.event.AccountCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String accoundId;
    private double balance;
    private String curency;
    private AccountStatus status;

    public AccountAggregate() {
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        if (createAccountCommand.getInitialBalance() < 0){
            throw  new RuntimeException("cest impossible de creer avec le solde negatif");
        }

        AggregateLifecycle.apply(new AccountCreatedEvent(
                createAccountCommand.getId(),
                createAccountCommand.getInitialBalance(),
                createAccountCommand.getCurrency()
        ));
    }
    @EventSourcingHandler
    public void  on(AccountCreatedEvent event){
        this.accoundId= event.getId();
        this.balance= event.getInitialBalance();
        this.curency= event.getCurrency();
        this.status= AccountStatus.CREATED;
        AggregateLifecycle.apply(new AccountActivatedEvent(
                event.getId(),
                AccountStatus.ACTIVATED

        ));
    }
    @EventSourcingHandler
    public void on(AccountActivatedEvent  event){
       this.status= event.getStatus();
    }


    @CommandHandler
    public void  handle(CreditAccountCommand command) throws SoldeInsuffisanteException {
      if (command.getAmount()<= 0) throw new SoldeInsuffisanteException("SOlde Insuffisant");
    } 
}
