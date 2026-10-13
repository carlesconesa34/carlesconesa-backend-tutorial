package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ccsw
 *
 */
@Tag(name = "Client", description = "API of Client")
@RequestMapping(value = "/client")
@RestController
@CrossOrigin(origins = "*")
public class ClientController {

    private long SEQUENCE = 1;
    private Map<Long, ClientDto> clients = new HashMap<Long, ClientDto>();

    /**
     * Método para recuperar todos los clientes
     *
     * @return {@link List} de {@link ClientDto}
     */
    @Operation(summary = "Find", description = "Method that returns a list of Clients")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<ClientDto> findAll() {

        return new ArrayList<ClientDto>(this.clients.values());
    }

    /**
     * Método para crear o actualizar un cliente
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    @Operation(summary = "Save or Update", description = "Method that saves or updates a Client")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody ClientDto dto) {

        ClientDto client;

        if (id == null) {
            client = new ClientDto();
            client.setId(this.SEQUENCE++);
            this.clients.put(client.getId(), client);
        } else {
            client = this.clients.get(id);
        }

        client.setName(dto.getName());
    }
}
