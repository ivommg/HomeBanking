package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.CardService;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    CardService cardService;
    @Autowired
    ClientService clientService;



    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


    @PostMapping("/clients/current/cards")

    public ResponseEntity<Object> createCard(Authentication authentication, @RequestParam CardType cardType, @RequestParam CardColor cardColor){
        Client client= clientService.findByEmail(authentication.getName());
        Set<Card> type =client.getCards().stream().filter(card -> card.getType()==cardType).collect(Collectors.toSet());


        if(cardType == null){
            return new ResponseEntity<>("No ingresaste un tipo de card",HttpStatus.FORBIDDEN);
        }
        if(cardColor == null){
            return new ResponseEntity<>("No ingresaste un tipo de card",HttpStatus.FORBIDDEN);
        }
        if (type.size() >= 3) {
            return new ResponseEntity<>("No puedes crear mas", HttpStatus.FORBIDDEN);
        }


        Card card= new Card(cardType,cardColor,getRandomNumber(1000,10000)+" "+getRandomNumber(1000,10000)+" "+getRandomNumber(1000,10000)+" "+getRandomNumber(1000,10000),getRandomNumber(100,1000), LocalDate.now(),LocalDate.now().plusYears(5),client);

        cardService.SaveCard(card);

        return new ResponseEntity<>("Creaste una nueva Card",HttpStatus.CREATED);
    }
    @PatchMapping("clients/currents/cards")
    public ResponseEntity<Object> DeletedCard (@RequestParam Long id){
        Card card = cardService.getCardId(id);
        if (id==null){
            return new ResponseEntity<>("No existe la tarjeta ingresada",HttpStatus.FORBIDDEN);
        }
        cardService.DeletedCard(card);
        return new ResponseEntity<>("Se elimino la tarjeta correctamente",HttpStatus.ACCEPTED);
    }

    /*@DeleteMapping("/clients/current/card")
    public ResponseEntity<Card> deletCard(){

        return new ResponseEntity<>("card was successfully removed",HttpStatus.CREATED);
    }*/

}
