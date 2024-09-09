package cws.it.comptcqrs.commonapi.command;

public class DebitAccountCommand extends BaseCommand<String>{
    private double balance;
    private String currency;

    public DebitAccountCommand(String id, double balance, String currency) {
        super(id);
        this.balance = balance;
        this.currency = currency;
    }
}
