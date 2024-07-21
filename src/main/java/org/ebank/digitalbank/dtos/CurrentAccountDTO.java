package org.ebank.digitalbank.dtos;

import lombok.Data;

@Data
public class CurrentAccountDTO extends AccountBankDTO {
    private double overdraft;
}
