package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Card;

public interface CardService {
    public Card getCardId ( Long id);
    public void SaveCard (Card card);
    public void DeletedCard (Card card);
}
