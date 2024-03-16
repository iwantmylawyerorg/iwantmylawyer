package com.thesis.iwantmylawyer.user.client;

import com.thesis.iwantmylawyer.constant.Constant;
import com.thesis.iwantmylawyer.user.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;
    private final PasswordEncoder passwordEncoder;


    public ClientService(ClientRepository clientRepository, ClientConverter clientConverter, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.clientConverter = clientConverter;
        this.passwordEncoder = passwordEncoder;
    }
    public ClientResponse getClientById(String id){
        return clientConverter.convert(findById(id));
    }

    public void createClient(CreateClientRequest createClientRequest){

        if(clientRepository.findByEmail(createClientRequest.email()).isPresent()){
            throw new ClientAlreadyExistsException(Constant.CLIENT_EMAIL_ALREADY_EXISTS_EXCEPTION);
        }
        Client client = new Client(
                createClientRequest.email(),
                passwordEncoder.encode(createClientRequest.password()),
                createClientRequest.firstName(),
                createClientRequest.lastName(),
                createClientRequest.telephoneNo(),
                Role.CLIENT
        );
        clientRepository.save(client);
    }
    public void updateClient(UpdateClientRequest updateClientRequest){
        if(clientRepository.findByEmail(updateClientRequest.email()).isPresent()){
            throw new ClientAlreadyExistsException(Constant.CLIENT_EMAIL_ALREADY_EXISTS_EXCEPTION);
        }

        Client client = findById(updateClientRequest.id());
        client.setEmail(updateClientRequest.email().trim().isEmpty() ? client.getEmail() : updateClientRequest.email());
        client.setFirstName(updateClientRequest.firstName().trim().isEmpty() ? client.getFirstName() : updateClientRequest.firstName());
        client.setLastName(updateClientRequest.lastName().trim().isEmpty() ? client.getLastName() : updateClientRequest.lastName());
        client.setPassword(updateClientRequest.password().trim().isEmpty() ? client.getPassword() : updateClientRequest.password());
        client.setTelephoneNo(updateClientRequest.telephoneNo().trim().isEmpty() ? client.getTelephoneNo() : updateClientRequest.telephoneNo());

        clientRepository.save(client);
    }

    public Client findById(String id){
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(Constant.CLIENT_DOES_NOT_FOUND_EXCEPTION));
    }



}
