package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.DTOS.LoanAplicationDTO;
import com.mindhub.homebanking.DTOS.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;
import com.mindhub.homebanking.services.LoanService;
import com.mindhub.homebanking.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {
    @Autowired
    ClientService clientService;
    @Autowired
    AccountService accountService;
    @Autowired
    TransactionsService transactionsService;
    @Autowired
    LoanService loanService;
    @Autowired
    private ClientLoanRepository clientLoanRepository;


    @RequestMapping("/loans")
    public List<LoanDTO> ListLoanDTO(){
        return loanService.ListLoanDTO();
    }


    @Transactional
    @PostMapping("/loans")
    private ResponseEntity<Object> createLoan (Authentication authentication, @RequestBody LoanAplicationDTO loan){
        Client client= clientService.findByEmail(authentication.getName());
        Loan loan1=loanService.getLoan(loan.getId());
        Account account1=accountService.findByNumber(loan.getAccountDestinit());
        Set<ClientLoan> clientLoanSet= client.getClientLoans().stream().filter(clientLoan1-> clientLoan1.getLoan().getName().equals(loan1.getName())).collect(Collectors.toSet());
        if (loan.getId()<=0){
            return new ResponseEntity<>("Id entered does not exist.",HttpStatus.FORBIDDEN);
        }
        if (loan.getAmount()<=0 || loan.getAmount().isNaN()){
            return new ResponseEntity<>("It is not possible to enter this amount.",HttpStatus.FORBIDDEN);
        }
        if (loan.getPayment()<=0){
            return new ResponseEntity<>("Fees paid are not valid.",HttpStatus.FORBIDDEN);
        }
        if (loan.getAccountDestinit().isEmpty()){
            return new ResponseEntity<>("The destination account entered is not valid.",HttpStatus.FORBIDDEN);
        }
        if (loan1==null){
            return new ResponseEntity<>("The loan does not exist.",HttpStatus.FORBIDDEN);
        }
        if (loan1.getMaxAmount()<=loan.getAmount()){
            return new ResponseEntity<>("The loan amount requested exceeds the maximum loan amount available.",HttpStatus.FORBIDDEN);
        }
        if (!loan1.getPayments().contains(loan.getPayment())){
            return new ResponseEntity<>("the requested quota is not available.",HttpStatus.FORBIDDEN);
        }
        if (accountService.findByNumber(loan.getAccountDestinit())==null){
            return new ResponseEntity<>("The target account does not exist.",HttpStatus.FORBIDDEN);
        }
        if (!client.getAccounts().contains(accountService.findByNumber(loan.getAccountDestinit()))){
            return new ResponseEntity<>("The destination account does not belong to the customer.",HttpStatus.FORBIDDEN);
        }
        if(clientLoanSet.size()>0){
            return new ResponseEntity<>("You already have a loan of this type.",HttpStatus.FORBIDDEN);
        }
        if(loan1.getName().equals("Hipotecario")){
            ClientLoan clientLoan1=new ClientLoan(loan.getAmount()*1.2, loan.getPayment(),clientService.findByEmail(authentication.getName()),loan1);
            clientLoanRepository.save(clientLoan1);
        }
        if(loan1.getName().equals("Automotriz")){
            ClientLoan clientLoan2=new ClientLoan(loan.getAmount()*1.1, loan.getPayment(),clientService.findByEmail(authentication.getName()),loan1);
            clientLoanRepository.save(clientLoan2);
        }
        if(loan1.getName().equals("Personal")){
            ClientLoan clientLoan3=new ClientLoan(loan.getAmount()*1.5, loan.getPayment(),clientService.findByEmail(authentication.getName()),loan1);
            clientLoanRepository.save(clientLoan3);
        }
        accountService.findByNumber(loan.getAccountDestinit()).setBalance(account1.getBalance()+loan.getAmount());

        Transaction transaction1=new Transaction(TransactionType.CREDIT,loan.getAmount(), LocalDateTime.now(),loan1.getName()+" Loan Aproved.");
        accountService.findByNumber(loan.getAccountDestinit()).addtransaction(transaction1);
        transactionsService.SaveTransaction(transaction1);



        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
