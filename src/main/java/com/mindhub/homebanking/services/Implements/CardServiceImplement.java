package com.mindhub.homebanking.services.Implements;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImplement implements CardService {
    @Autowired
    CardRepository cardRepository;

    @Override
    public Card getCardId(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public void SaveCard(Card card) {
        cardRepository.save(card);
    }

    @Override
    public void DeletedCard(Card card) {
        cardRepository.delete(card);
    }
}
