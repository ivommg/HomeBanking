package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.DTOS.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")//asociar una peticion a una ruta

//los controladores sirven para escuchar y responder peticiones.

public class ClientControllers {
    @Autowired//genera una instancia del repositorio en el controlador
    ClientService clientService;
    @Autowired
    AccountService accountService;

    @RequestMapping("/clients")
    public List<ClientDTO> getClients() {
        return clientService.getClientsDTO();
    }

    @RequestMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.getClientDTO(id);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    //================Creo un nuevo servicio para registras un cliente=================


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/clients")
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {

        if (firstName.isEmpty()) {
            return new ResponseEntity<>("Missing FirstName", HttpStatus.FORBIDDEN);
        }
        if (lastName.isEmpty()) {
            return new ResponseEntity<>("Missing LastName", HttpStatus.FORBIDDEN);
        }
        if (email.isEmpty()) {
            return new ResponseEntity<>("Missing Email", HttpStatus.FORBIDDEN);
        }
        if (password.isEmpty()) {
            return new ResponseEntity<>("Missing Password", HttpStatus.FORBIDDEN);
        }
        if (clientService.findByEmail(email) != null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password));
        clientService.saveClient(client);


        if (client.getAccounts().size() >= 3) {
            return new ResponseEntity<>("You have reached the maximum number of accounts for a customer", HttpStatus.FORBIDDEN);
        } else {
            Account account=new Account("VIN"+getRandomNumber(10000000,100000000),LocalDate.now(),00.00);
            client.addAcconts(account);
            accountService.saveAccounts(account);
            return new ResponseEntity<>(HttpStatus.CREATED);//El client me causa recursividad.
       }
    }

    //===========Creo un metodo para mostras el cliente autenticado=============

    @RequestMapping("/clients/current")
    public ClientDTO getClient(Authentication authentication) {
        accountService.saveAccounts(new Account("Vin" + getRandomNumber(10000000, 10000000), LocalDate.now(), 00.00));
        return new ClientDTO(clientService.findByEmail(authentication.getName()));
    }


}
