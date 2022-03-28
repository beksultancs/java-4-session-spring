package peaksoft.driverapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.driverapp.dto.client.ClientResponseDto;
import peaksoft.driverapp.dto.client.ClientSaveDto;
import peaksoft.driverapp.dto.client.GetClientDto;
import peaksoft.driverapp.dto.mapper.ClientMapper;
import peaksoft.driverapp.dto.mapper.GetClientMapper;
import peaksoft.driverapp.exceptions.BadRequestException;
import peaksoft.driverapp.exceptions.NotFoundException;
import peaksoft.driverapp.models.entities.Client;
import peaksoft.driverapp.repositories.ClientRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * @author Beksultan
 */

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final GetClientMapper getClientMapper;

    public ClientResponseDto save(ClientSaveDto clientSaveDto) {
        String email = clientSaveDto.getEmail();

        boolean exists = clientRepository.existsByEmail(email);

        if (exists) {
            throw new BadRequestException(
                    String.format("client with email = %s has already exists", email)
            );
        }

        Client client = clientMapper.convert(clientSaveDto);

        Client save = clientRepository.save(client);

        return clientMapper.deConvert(save);
    }

    public List<ClientResponseDto> findAll() {
         return clientRepository.findAll()
                 .stream()
                 .map(clientMapper::deConvert)
                 .toList();
    }

    public GetClientDto findBy(UUID id, String email, String phoneNumber) {
        int counter = id != null ? 1 : 0;
        counter += !isNullOrEmpty(email) ? 1 : 0;
        counter += !isNullOrEmpty(phoneNumber) ? 1 : 0;

        if (counter > 1) {
            throw new BadRequestException(
                    "You should choose only one field"
            );
        }
        if (id != null) {
            Client client = findById(id);

            return getClientMapper.convert(client);
        }

        if (!isNullOrEmpty(email)) {
            Client client = clientRepository.findByEmail(email)
                    .orElseThrow(() -> new NotFoundException(
                            String.format("client with email = %s does not exists", email)
                    ));
            return getClientMapper.convert(client);
        }

        if (!isNullOrEmpty(phoneNumber)) {
            Client client = clientRepository.findByPhoneNumber(phoneNumber)
                    .orElseThrow(() -> new NotFoundException(
                            String.format("client with phone number = %s does not exists", phoneNumber)
                    ));
            return getClientMapper.convert(client);
        }

        throw new BadRequestException("You should write one of {id, email, phonenumber} to get client");
    }

    public void delete(UUID clientId) {
        boolean exists = clientRepository.existsById(clientId);

        if (!exists) {
            throw new BadRequestException(
                    String.format("client with id = %s does not exists", clientId)
            );
        }

        clientRepository.deleteById(clientId);
    }

    @Transactional
    public ClientResponseDto update(UUID clientId, ClientSaveDto clientSaveDto) {
        Client client = findById(clientId);

        String currentName = client.getName();
        String newName = clientSaveDto.getName();

        if (!currentName.equals(newName)) {
            client.setName(newName);
        }

        String currentEmail = client.getEmail();
        String newEmail = clientSaveDto.getEmail();

        if (!currentEmail.equals(newEmail)) {
            client.setEmail(newEmail);
        }

        String currentPhoneNumber = client.getPhoneNumber();
        String newPhoneNumber = clientSaveDto.getPhoneNumber();

        if (!currentPhoneNumber.equals(newPhoneNumber)) {
            client.setPhoneNumber(newPhoneNumber);
        }

        String currentAccountNumber = client.getBankAccount().getAccountNumber();
        String newAccountNumber = clientSaveDto.getAccountNumber();

        if (!currentAccountNumber.equals(newAccountNumber)) {
            client.getBankAccount().setAccountNumber(newAccountNumber);
        }

        return clientMapper.deConvert(client);
    }

    private Client findById(UUID uuid) {
        return clientRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException(
                        String.format("client with id = %s does not exists", uuid)
                ));
    }

}
