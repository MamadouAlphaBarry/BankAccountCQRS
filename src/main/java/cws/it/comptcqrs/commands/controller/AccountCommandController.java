package cws.it.comptcqrs.commands.controller;


import cws.it.comptcqrs.commonapi.command.CreateAccountCommand;
import cws.it.comptcqrs.commonapi.dtos.AccountActivatedEvent;
import cws.it.comptcqrs.commonapi.dtos.CreateAccountRequestDto;
import cws.it.comptcqrs.commonapi.dtos.CreditAccountDto;
import cws.it.comptcqrs.commonapi.enums.AccountStatus;
import cws.it.comptcqrs.commonapi.dtos.ResponseAccountDto;
import cws.it.comptcqrs.commonapi.event.AccountCreatedEvent;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("commands/account")
@AllArgsConstructor
public class AccountCommandController {
   //@Autowired u
    private CommandGateway commandGateway;
    private EventStore eventStore;
    @PostMapping("/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDto request){
        CompletableFuture<String> responseCommand = commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                request.getInitialAmount(),
                request.getCurrency(),
                AccountStatus.CREATED
        ));
        return responseCommand ;
    }

    @PutMapping("/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountDto request){
        CompletableFuture<String> commandResponse = commandGateway.send(new AccountCreatedEvent(
                request.getAccoundId(),
                request.getSolde(),
                request.getCurrency()
        ));
        return commandResponse;
    }
   @ExceptionHandler(Exception.class)
    public String execptionHandler(Exception e){
        return  e.getMessage();

    }

    @PutMapping("/activate/{id}")
    public ResponseAccountDto activateAccount(@PathVariable String id){
        commandGateway.send(new AccountActivatedEvent(
          id
      ));
        return null;
    }
    @GetMapping("/eventStore/{accountId}")
    public Stream eventStore(@PathVariable String accountId){
      return eventStore.readEvents(accountId).asStream();
    }
}
