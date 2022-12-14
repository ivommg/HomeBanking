package com.mindhub.homebanking.services;

import com.mindhub.homebanking.DTOS.ClientDTO;
import com.mindhub.homebanking.models.Client;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface ClientService {
    public List<ClientDTO> getClientsDTO();
    public ClientDTO getClientDTO(Long id);
    public Client findByEmail(String email);
    public void saveClient(Client client);

}
