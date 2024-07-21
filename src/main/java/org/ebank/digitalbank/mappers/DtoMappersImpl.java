package org.ebank.digitalbank.mappers;

import org.ebank.digitalbank.dtos.AccountOperationDTO;
import org.ebank.digitalbank.dtos.CurrentAccountDTO;
import org.ebank.digitalbank.dtos.CustomerDTO;
import org.ebank.digitalbank.dtos.SavingAccountDTO;
import org.ebank.digitalbank.entities.AccountOperation;
import org.ebank.digitalbank.entities.CurrentAccount;
import org.ebank.digitalbank.entities.Customer;
import org.ebank.digitalbank.entities.SavingAccount;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
public class DtoMappersImpl implements DtoMapper{

    @Override
    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        copyProperties(customer, customerDTO);

        return customerDTO;
    }

    @Override
    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        copyProperties(customerDTO, customer);

        return customer;
    }

    @Override
    public CurrentAccountDTO fromCurrAcc(CurrentAccount currentAccount){
        CurrentAccountDTO currentAccountDTO = new CurrentAccountDTO();

        copyProperties(currentAccount, currentAccountDTO);

        return currentAccountDTO;
    }

    @Override
    public CurrentAccount fromCurrAccDTO(CurrentAccountDTO currentAccountDTO){
        CurrentAccount currentAccount = new CurrentAccount();

        copyProperties(currentAccountDTO, currentAccount);

        return currentAccount;
    }

    @Override
    public SavingAccountDTO fromSavAcc(SavingAccount savingAccount){
        SavingAccountDTO savingAccountDTO = new SavingAccountDTO();

        copyProperties(savingAccount, savingAccountDTO);

        return savingAccountDTO;
    }

    @Override
    public SavingAccount fromSavAccDTO(SavingAccountDTO savingAccountDTO){
        SavingAccount savingAccount = new SavingAccount();

        copyProperties(savingAccountDTO, savingAccount);

        return savingAccount;
    }

    @Override
    public AccountOperationDTO fromAccOperation(AccountOperation accountOp){
        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();

        copyProperties(accountOp, accountOperationDTO);

        return accountOperationDTO;
    }

    @Override
    public AccountOperation fromAccOperationDTO(AccountOperationDTO accountOpDTO){
        AccountOperation accountOperation = new AccountOperation();

        copyProperties(accountOpDTO, accountOperation);

        return accountOperation;
    }

}
