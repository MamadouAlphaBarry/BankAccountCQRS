package cws.it.comptcqrs.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditAccountDto {
    private String accoundId;
    private double solde;
    private String currency;
}
