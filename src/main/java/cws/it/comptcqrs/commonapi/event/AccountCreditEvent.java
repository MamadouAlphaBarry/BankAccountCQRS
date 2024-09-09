package cws.it.comptcqrs.commonapi.event;

import lombok.Getter;

public class AccountCreditEvent extends BaseEvent<String>{
   @Getter
   private double solde;
    @Getter String currency;

    public AccountCreditEvent(String id, double solde, String currency) {
        super(id);
        this.solde = solde;
        this.currency = currency;
    }
}
