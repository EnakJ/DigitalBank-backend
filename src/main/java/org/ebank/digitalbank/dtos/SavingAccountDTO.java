package org.ebank.digitalbank.dtos;


import lombok.Data;
import org.ebank.digitalbank.entities.AccountBank;


@Data
public class SavingAccountDTO extends AccountBankDTO {
    private double interestRate;
}
