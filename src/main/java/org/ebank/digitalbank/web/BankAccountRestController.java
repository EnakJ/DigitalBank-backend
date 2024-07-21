package org.ebank.digitalbank.web;

import org.ebank.digitalbank.dtos.AccountBankDTO;
import org.ebank.digitalbank.dtos.AccountHistoryDTO;
import org.ebank.digitalbank.dtos.AccountOperationDTO;
import org.ebank.digitalbank.exceptions.AccountBankNotFoundException;
import org.ebank.digitalbank.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class BankAccountRestController {
    private BankAccountService bankAccountService;

    public BankAccountRestController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }


    @GetMapping("/accounts")
    public List<AccountBankDTO> getAccountBankList(){
        return bankAccountService.accountBankDTOList();
    }

    @GetMapping("/accounts/{accountId}")
    public AccountBankDTO getBankAccount(@PathVariable String accountId) throws AccountBankNotFoundException {

        return bankAccountService.getAccountBank(accountId);
    }

    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getAccountOperation(@PathVariable String accountId){

        return bankAccountService.getAccountOperationsById(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperation")
    public AccountHistoryDTO getAccountOperation(
            @PathVariable String accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size){

        return bankAccountService.getOperationHistory(accountId, page, size);
    }

}
