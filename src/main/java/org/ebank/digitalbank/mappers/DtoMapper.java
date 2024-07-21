package org.ebank.digitalbank.mappers;

import org.ebank.digitalbank.dtos.AccountOperationDTO;
import org.ebank.digitalbank.dtos.CurrentAccountDTO;
import org.ebank.digitalbank.dtos.CustomerDTO;
import org.ebank.digitalbank.dtos.SavingAccountDTO;
import org.ebank.digitalbank.entities.AccountOperation;
import org.ebank.digitalbank.entities.CurrentAccount;
import org.ebank.digitalbank.entities.Customer;
import org.ebank.digitalbank.entities.SavingAccount;

public interface DtoMapper {
    CustomerDTO fromCustomer(Customer customer);

    Customer fromCustomerDTO(CustomerDTO customerDTO);

    CurrentAccountDTO fromCurrAcc(CurrentAccount currentAccount);

    CurrentAccount fromCurrAccDTO(CurrentAccountDTO currentAccountDTO);

    SavingAccountDTO fromSavAcc(SavingAccount savingAccount);

    SavingAccount fromSavAccDTO(SavingAccountDTO savingAccountDTO);

    AccountOperationDTO fromAccOperation(AccountOperation accountOp);

    AccountOperation fromAccOperationDTO(AccountOperationDTO accountOpDTO);
}
