package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ccsw
 *
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    /**
     * {@inheritDoc}
     */
    public List<Client> findAll() {
        return (List<Client>) this.clientRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    public void save(Long id, ClientDto dto) {

        Client existingClient = this.clientRepository.findClientByName(dto.getName());

        if (existingClient != null && (id == null || !existingClient.getId().equals(id))) {
            throw new RuntimeException("El cliente " + dto.getName() + " ya existe.");
        }
        Client client;

        if (id == null) {
            client = new Client();
        } else {
            client = this.clientRepository.findById(id).orElse(null);
        }

        client.setName(dto.getName());

        this.clientRepository.save(client);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long id) throws Exception {
        if(this.clientRepository.findById(id).orElse(null) == null){
            throw new Exception("Not exists");
        }

        this.clientRepository.deleteById(id);
    }

}
