package com.mindhub.homebanking.DTOS;

import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;

import java.time.LocalDateTime;

public class TransactionDTO {
    private long id;
    private TransactionType type;
    private Double amount;
    private LocalDateTime date;
    private String description;



    public TransactionDTO() {
    }
    public TransactionDTO(Transaction transaction){
        this.id= transaction.getId();
        this.type=transaction.getType();
        this.amount=transaction.getAmount();
        this.date=transaction.getDate();
        this.description=transaction.getDescription();
    }



    public long getId() {
        return id;
    }
    public TransactionType getType() {
        return type;
    }
    public Double getAmount() {
        return amount;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public String getDescription() {
        return description;
    }
}
