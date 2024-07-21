package org.ebank.digitalbank.repositories;

import org.ebank.digitalbank.dtos.CustomerDTO;
import org.ebank.digitalbank.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.prenom like :kw OR c.nom like :kw")
    List<Customer> searchCustomer(@Param("kw") String keyword);
}
