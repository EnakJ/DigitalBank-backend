package org.ebank.digitalbank.repositories;

import org.ebank.digitalbank.entities.CurrentAccount;
import org.ebank.digitalbank.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, String> {
    CurrentAccount getCurrentAccountByCustomer(Customer customer);
}
