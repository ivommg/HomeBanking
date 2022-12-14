package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.DTOS.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")

public class AccountControllers {
    @Autowired
    AccountService accountService;
    @Autowired
    ClientService clientService;

    @RequestMapping("/accounts")
    public List<AccountDTO> getAccounts(){
        return accountService.getAccountsDTO();
    }

    @RequestMapping("/account/{id}")
    public AccountDTO getAccount(@PathVariable Long id){
        return accountService.getAccountDTO(id);
    }

//==========Creo un metodo para crear cuentas a los clientes autenticados====================

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @RequestMapping(path = "/clients/current/accounts",method = RequestMethod.POST)
    public ResponseEntity<Object> createAccounts(Authentication authentication) {
        Client clientAuthentic= clientService.findByEmail(authentication.getName());
        if (clientAuthentic.getAccounts().size() >= 3) {
            return new ResponseEntity<>("You have reached the maximum number of accounts for a customer", HttpStatus.FORBIDDEN);
        } else {
            Account account=new Account("VIN"+getRandomNumber(10000000,100000000),LocalDate.now(),00.00);
            clientAuthentic.addAcconts(account);
            accountService.saveAccounts(account);
            return new ResponseEntity<>("Your account was successfully created",HttpStatus.CREATED);
        }
    }



}

