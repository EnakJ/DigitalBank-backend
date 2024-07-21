package org.ebank.digitalbank.dtos;

import lombok.Data;
import java.util.List;

@Data
public class AccountHistoryDTO {
    private String id;
    private double balance;
    private int currentPage;
    private int totalPages;
    private int size;
    private List<AccountOperationDTO> operationDTOList;
}
