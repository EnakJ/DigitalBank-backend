package org.ebank.digitalbank.repositories;

import org.ebank.digitalbank.entities.AccountBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBankRepository extends JpaRepository<AccountBank, String> {
}
