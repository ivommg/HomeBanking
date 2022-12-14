package com.mindhub.homebanking.DTOS;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class LoanAplicationDTO {

    private long id;
    private Double amount;
    private Integer payment;
    private String accountDestinit;

    public LoanAplicationDTO(long id, Double amount, Integer payment, String accountDestinit) {
        this.id = id;
        this.amount = amount;
        this.payment = payment;
        this.accountDestinit = accountDestinit;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public Integer getPayment() {
        return payment;
    }
    public void setPayment(Integer payment) {
        this.payment = payment;
    }
    public String getAccountDestinit() {
        return accountDestinit;
    }
    public void setAccountDestinit(String accountDestinit) {
        this.accountDestinit = accountDestinit;
    }

}
