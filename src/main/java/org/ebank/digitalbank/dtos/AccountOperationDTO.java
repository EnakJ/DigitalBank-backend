package org.ebank.digitalbank.dtos;


import lombok.Data;
import org.ebank.digitalbank.enums.OperationType;
import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date date;
    private double amount;
    private OperationType type;

}
