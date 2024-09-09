package cws.it.comptcqrs.commonapi.dtos;

import cws.it.comptcqrs.commonapi.enums.AccountStatus;
import lombok.Data;

@Data
public class ResponseAccountDto {
    private String accountId;
    private double  balance;
    private AccountStatus status;
}
