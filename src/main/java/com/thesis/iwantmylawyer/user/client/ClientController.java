package com.thesis.iwantmylawyer.user.client;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable String clientId){
         return new ResponseEntity<>(clientService.getClientById(clientId),HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Void> updateClient(@RequestBody UpdateClientRequest updateClientRequest){
        clientService.updateClient(updateClientRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createClient(@RequestBody CreateClientRequest createClientRequest){
        clientService.createClient(createClientRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
