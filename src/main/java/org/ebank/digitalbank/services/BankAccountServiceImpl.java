package org.ebank.digitalbank.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ebank.digitalbank.dtos.*;
import org.ebank.digitalbank.entities.*;
import org.ebank.digitalbank.enums.AccountStatus;
import org.ebank.digitalbank.enums.OperationType;
import org.ebank.digitalbank.exceptions.AccountBankNotFoundException;
import org.ebank.digitalbank.exceptions.BalanceNotSufficientException;
import org.ebank.digitalbank.exceptions.CustomerNotFountException;
import org.ebank.digitalbank.mappers.DtoMapper;
import org.ebank.digitalbank.repositories.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service @Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {
    private CustomerRepository customerRepository;
    private AccountBankRepository accountBankRepository;
    private AccountOperationRepository accountOpRepository;
    private CurrentAccountRepository currentAccRepository;
    private SavingAccountRepository savingAccRepository;
    private DtoMapper dtoMapper;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("Saving new Customer ...");
        Customer customer = dtoMapper.fromCustomerDTO(customerDTO);
        Customer save = customerRepository.save(customer);

        return dtoMapper.fromCustomer(save);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        log.info("Updating Customer ...");
        Customer customer = dtoMapper.fromCustomerDTO(customerDTO);
        Customer save = customerRepository.save(customer);

        return dtoMapper.fromCustomer(save);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public CurrentAccountDTO saveCurrentAccountBank(double initialBalance, Long customerId) throws CustomerNotFountException {
        log.info("Saving new current account ...");
        Customer customer = customerRepository.getReferenceById(customerId);
        //vérifier qu'il existe bien un Customer avec un Id customerId

        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCustomer(customer);
        currentAccount.setStatus(AccountStatus.CREATED);
        currentAccount.setCreatedAt(new Date());
        currentAccount.setOverdraft(Math.random()*100000);
        currentAccount.setCurrency("currency of account "+currentAccount.getBalance());

        CurrentAccount savedAccount = accountBankRepository.save(currentAccount);

        return dtoMapper.fromCurrAcc(savedAccount);
    }

    @Override
    public SavingAccountDTO saveSavingAccountBank(double initialBalance, Long customerId) throws CustomerNotFountException  {
        log.info("Saving new saving account ...");
        Customer customer = customerRepository.getReferenceById(customerId);

        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setBalance(initialBalance);
        savingAccount.setCustomer(customer);
        savingAccount.setStatus(AccountStatus.CREATED);
        savingAccount.setCreatedAt(new Date());
        savingAccount.setInterestRate(5.7);
        savingAccount.setCurrency("currency of account "+savingAccount.getBalance());

        SavingAccount savedAccount = accountBankRepository.save(savingAccount);

        return dtoMapper.fromSavAcc(savedAccount);
    }

    @Override
    public CurrentAccountDTO getCurAccByCustomer(CustomerDTO customerDTO) {

        CurrentAccount currentAccount = currentAccRepository.getCurrentAccountByCustomer(dtoMapper.fromCustomerDTO(customerDTO));

        return dtoMapper.fromCurrAcc(currentAccount);
    }

    @Override
    public SavingAccountDTO getSavAccByCustomer(CustomerDTO customerDTO) {
        SavingAccount savingAccount = savingAccRepository.getSavingAccountByCustomer(dtoMapper.fromCustomerDTO(customerDTO));

        return dtoMapper.fromSavAcc(savingAccount);
    }

    @Override
    public List<CustomerDTO> listCustomers() {

        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> dtoMapper.fromCustomer(customer)).toList();
    }

    @Override
    public AccountBankDTO getAccountBank(String accountId) throws AccountBankNotFoundException {
        AccountBank accountBank = accountBankRepository.findById(accountId)
                .orElseThrow(() -> new AccountBankNotFoundException("Account bank not found !"));
        if(accountBank instanceof CurrentAccount){
             return dtoMapper.fromCurrAcc((CurrentAccount) accountBank);
        } else {
             return dtoMapper.fromSavAcc((SavingAccount) accountBank);
        }
    }

    @Override
    public void debit(String accountId, double amount, String description) throws AccountBankNotFoundException, BalanceNotSufficientException {
        AccountBank accountBank = accountBankRepository.getReferenceById(accountId);

        if (accountBank.getBalance() <= amount) throw new BalanceNotSufficientException("Solde insuffisant !");
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAccountBank(accountBank);
        accountOperation.setDate(new Date());
        accountOperation.setAmount(amount);

        accountOpRepository.save(accountOperation);
        accountBank.setBalance(accountBank.getBalance() - amount);

    }

    @Override
    public void credit(String accountId, double amount, String description) throws AccountBankNotFoundException {
        AccountBank accountBank = accountBankRepository.getReferenceById(accountId);

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAccountBank(accountBank);
        accountOperation.setDate(new Date());
        accountOperation.setAmount(amount);

        accountOpRepository.save(accountOperation);
        accountBank.setBalance(accountBank.getBalance() + amount);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDest, double amount) throws AccountBankNotFoundException, BalanceNotSufficientException {
        this.debit(accountIdSource, amount, "Opération de transfert !");
        this.credit(accountIdDest, amount, "Reception de transfert !");

    }

    @Override
    public CustomerDTO getCustomer(Long id) throws CustomerNotFountException {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->  new CustomerNotFountException("Customer does not exist !"));


        return dtoMapper.fromCustomer(customer);
    }

    @Override
    public List<AccountBankDTO> accountBankDTOList(){
        List<AccountBankDTO> accountBankDTOList = accountBankRepository.findAll().stream().map(accountBank1 -> {
            if (accountBank1 instanceof CurrentAccount) {
                return dtoMapper.fromCurrAcc((CurrentAccount) accountBank1);
            } else {
                return dtoMapper.fromSavAcc((SavingAccount) accountBank1);
            }
        }).toList();

        return accountBankDTOList;
    }

    @Override
    public List<AccountOperationDTO> getAccountOperationsById(String accountId) {

        return accountOpRepository.findAccountOperationByAccountBankId(accountId).stream().map(operaton ->
                dtoMapper.fromAccOperation(operaton)).toList();

    }


    @Override
    public AccountHistoryDTO getOperationHistory(String accountId, int page, int size) {
        AccountBank accountBank = accountBankRepository.findById(accountId).orElse(null);

        Page<AccountOperation> accountOperationPage = accountOpRepository.findAccountOperationByAccountBankId(accountId, PageRequest.of(page, size));

        List<AccountOperationDTO> accountOperationDTOS = accountOperationPage.getContent().stream().map(accountOperation1 -> dtoMapper.fromAccOperation(accountOperation1)).toList();

        AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
        accountHistoryDTO.setOperationDTOList(accountOperationDTOS);
        accountHistoryDTO.setSize(size);
        accountHistoryDTO.setTotalPages(accountOperationPage.getTotalPages());
        accountHistoryDTO.setCurrentPage(page);
        accountHistoryDTO.setId(accountBank.getId());
        accountHistoryDTO.setBalance(accountBank.getBalance());

        return accountHistoryDTO;
    }

    @Override
    public List<CustomerDTO> searchCustomer(String keyword) {
        List<Customer> customers = customerRepository.searchCustomer("%"+keyword+"%");

        return customers.stream().map(custom -> dtoMapper.fromCustomer(custom)).toList();
    }

}
