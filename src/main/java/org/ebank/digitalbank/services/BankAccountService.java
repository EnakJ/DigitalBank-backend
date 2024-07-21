package org.ebank.digitalbank.services;

import org.ebank.digitalbank.dtos.*;
import org.ebank.digitalbank.entities.CurrentAccount;
import org.ebank.digitalbank.entities.Customer;
import org.ebank.digitalbank.entities.SavingAccount;
import org.ebank.digitalbank.exceptions.AccountBankNotFoundException;
import org.ebank.digitalbank.exceptions.BalanceNotSufficientException;
import org.ebank.digitalbank.exceptions.CustomerNotFountException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customer);
    CurrentAccountDTO saveCurrentAccountBank(double initialBalance, Long customerId) throws CustomerNotFountException;
    SavingAccountDTO saveSavingAccountBank(double initialBalance, Long customerId) throws CustomerNotFountException;

    CurrentAccountDTO getCurAccByCustomer(CustomerDTO customerDTO);


    SavingAccountDTO getSavAccByCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> listCustomers();
    AccountBankDTO getAccountBank(String accountId) throws AccountBankNotFoundException;
    void debit(String accountId, double amount, String description) throws AccountBankNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BalanceNotSufficientException, AccountBankNotFoundException;
    void transfer(String accountIdSource, String accountIdDest, double amount) throws AccountBankNotFoundException, BalanceNotSufficientException;

    CustomerDTO getCustomer(Long id) throws CustomerNotFountException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    List<AccountBankDTO> accountBankDTOList();

    List<AccountOperationDTO> getAccountOperationsById(String accountId);

    AccountHistoryDTO getOperationHistory(String accountId, int page, int size);

    List<CustomerDTO> searchCustomer(String keyword);
}
