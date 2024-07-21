package org.ebank.digitalbank.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ebank.digitalbank.entities.AccountOperation;
import org.ebank.digitalbank.entities.Customer;
import org.ebank.digitalbank.enums.AccountStatus;

import java.util.Date;
import java.util.List;

@Data
public class AccountBankDTO {
    private String id;
    private String type;
    private Date createdAt;
    private double balance;
    private CustomerDTO customerDTO;
    private AccountStatus status;
    private String currency;
}
