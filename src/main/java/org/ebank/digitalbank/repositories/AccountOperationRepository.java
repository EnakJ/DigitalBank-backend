package org.ebank.digitalbank.repositories;

import org.ebank.digitalbank.entities.AccountOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
    List<AccountOperation> findAccountOperationByAccountBankId(String accountId);
    Page<AccountOperation> findAccountOperationByAccountBankId(String accountId, Pageable pageable);
}
