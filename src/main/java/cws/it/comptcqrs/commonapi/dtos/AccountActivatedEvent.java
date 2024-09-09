package cws.it.comptcqrs.commonapi.dtos;

import cws.it.comptcqrs.commonapi.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountActivatedEvent {

  private String id;
    private AccountStatus status;
    public AccountActivatedEvent(String id) {
    }
}
