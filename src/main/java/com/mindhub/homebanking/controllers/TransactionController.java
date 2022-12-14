package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;
import com.mindhub.homebanking.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    ClientService clientService;
    @Autowired
    AccountService accountService;
    @Autowired
    public TransactionsService transactionsService;

    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<Object> addTransacctions
            (Authentication authentication,@RequestParam Double amount,@RequestParam String descriptions,
             @RequestParam String accountOrigin, @RequestParam String accountDestiny){
        Client authenticatedCustomer= clientService.findByEmail(authentication.getName());
        Account originatingAccount = accountService.findByNumber(accountOrigin);
        Account targetAccount = accountService.findByNumber(accountDestiny);

        if (authenticatedCustomer.getAccounts().contains(accountOrigin)){
            return new ResponseEntity<>("The account does not belong to the authenticated customer.",HttpStatus.FORBIDDEN);
        }
        if (amount.isNaN() || amount==0){
            return new ResponseEntity<>("Missing an amount ", HttpStatus.FORBIDDEN);
        }
        if (descriptions.isEmpty()){
            return new ResponseEntity<>("Missing details",HttpStatus.FORBIDDEN);
        }
        if (accountOrigin.isEmpty()){
            return new ResponseEntity<>("The original account is missing",HttpStatus.FORBIDDEN);
        }
        if(accountDestiny.isEmpty()) {
            return new ResponseEntity<>("The target account is missing",HttpStatus.FORBIDDEN);
        }
        if(accountOrigin.equals(accountDestiny)){
            return new ResponseEntity<>("Your accounts are the same.",HttpStatus.FORBIDDEN);
        }
        if(originatingAccount == null){
            return new ResponseEntity<>("The source account does not exist.",HttpStatus.FORBIDDEN);
        }
        if (targetAccount == null){
            return new ResponseEntity<>("The source account does not exist.",HttpStatus.FORBIDDEN);
        }
        if (accountService.findByNumber(accountOrigin).getBalance()<amount){
            return new ResponseEntity<>("You do not have the balance to perform the operation.",HttpStatus.FORBIDDEN);
        }
        Transaction debitTransaction = new Transaction(TransactionType.DEBIT,amount, LocalDateTime.now(),descriptions +' '+ originatingAccount.getNumber());
        Transaction creditTransaction = new Transaction(TransactionType.CREDIT,amount,LocalDateTime.now(),descriptions +' '+ targetAccount.getNumber());
        originatingAccount.addtransaction(debitTransaction);
        targetAccount.addtransaction(creditTransaction);
        originatingAccount.setBalance(originatingAccount.getBalance()-amount);
        targetAccount.setBalance(targetAccount.getBalance()+amount);
        transactionsService.SaveTransaction(debitTransaction);
        transactionsService.SaveTransaction(creditTransaction);
        return new ResponseEntity<>("The transaction has been successfully completed.",HttpStatus.FORBIDDEN);
    }



}
