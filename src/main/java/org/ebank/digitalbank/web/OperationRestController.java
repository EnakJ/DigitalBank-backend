package org.ebank.digitalbank.web;

import org.ebank.digitalbank.exceptions.AccountBankNotFoundException;
import org.ebank.digitalbank.exceptions.BalanceNotSufficientException;
import org.ebank.digitalbank.services.BankAccountService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class OperationRestController {

    private BankAccountService bankAccountService;

    public OperationRestController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/accounts/debit/{accountId}/{amount}")
    public void debit(@PathVariable String accountId,@PathVariable double amount, String description) throws
            AccountBankNotFoundException, BalanceNotSufficientException {

        bankAccountService.debit(accountId, amount, description);
    }

    @GetMapping("/accounts/credit/{accountId}/{amount}")
    public void credit(@PathVariable String accountId,@PathVariable double amount, String description) throws
            AccountBankNotFoundException, BalanceNotSufficientException {

        bankAccountService.credit(accountId, amount, description);
    }
}
