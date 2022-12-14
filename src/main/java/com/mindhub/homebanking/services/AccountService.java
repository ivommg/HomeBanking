package com.mindhub.homebanking.services;

import com.mindhub.homebanking.DTOS.AccountDTO;
import com.mindhub.homebanking.models.Account;

import java.util.List;

public interface AccountService {
    public List<AccountDTO> getAccountsDTO();
    public AccountDTO getAccountDTO(Long id);
    public void saveAccounts(Account account);
    public Account findByNumber(String number);
}
