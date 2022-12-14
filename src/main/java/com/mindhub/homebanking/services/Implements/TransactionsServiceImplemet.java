package com.mindhub.homebanking.services.Implements;

import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionsServiceImplemet implements TransactionsService {
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void SaveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
