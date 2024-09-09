package cws.it.comptcqrs.commonapi.command;

public class UpdateAccountCommand extends BaseCommand<String>{
    private double soldInitial;
    private String currency;

    public UpdateAccountCommand(String id, double soldInitial, String currency) {
        super(id);
        this.soldInitial = soldInitial;
        this.currency = currency;
    }


}
