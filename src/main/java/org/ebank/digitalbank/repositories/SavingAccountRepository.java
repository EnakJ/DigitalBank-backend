package org.ebank.digitalbank.repositories;

import org.ebank.digitalbank.entities.Customer;
import org.ebank.digitalbank.entities.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingAccountRepository extends JpaRepository<SavingAccount, String> {
    SavingAccount getSavingAccountByCustomer(Customer customer);
}
