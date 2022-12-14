package com.mindhub.homebanking.services;

import com.mindhub.homebanking.DTOS.LoanDTO;
import com.mindhub.homebanking.models.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    public List<LoanDTO> ListLoanDTO();
    public Loan getLoan(Long id);
}
