package org.ebank.digitalbank;

import lombok.AllArgsConstructor;
import org.ebank.digitalbank.dtos.AccountBankDTO;
import org.ebank.digitalbank.dtos.CurrentAccountDTO;
import org.ebank.digitalbank.dtos.CustomerDTO;
import org.ebank.digitalbank.entities.Customer;
import org.ebank.digitalbank.enums.AccountStatus;
import org.ebank.digitalbank.exceptions.AccountBankNotFoundException;
import org.ebank.digitalbank.exceptions.BalanceNotSufficientException;
import org.ebank.digitalbank.exceptions.CustomerNotFountException;
import org.ebank.digitalbank.repositories.*;
import org.ebank.digitalbank.services.BankAccountService;
import org.ebank.digitalbank.services.BankAccountServiceImpl;
import org.ebank.digitalbank.web.CustomerRestController;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankApplication {


    public static void main(String[] args) {
        SpringApplication.run(DigitalBankApplication.class, args);
    }

    @Bean
    CommandLineRunner start() {
        return args -> {

        };
    }

}
