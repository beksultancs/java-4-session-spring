package peaksoft.driverapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.driverapp.dto.ClientResponseDto;
import peaksoft.driverapp.dto.ClientSaveDto;
import peaksoft.driverapp.dto.mapper.ClientMapper;
import peaksoft.driverapp.exceptions.BadRequestException;
import peaksoft.driverapp.models.entities.Client;
import peaksoft.driverapp.repositories.ClientRepository;

/**
 * @author Beksultan
 */

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

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

        ClientResponseDto clientResponseDto = clientMapper.deConvert(save);

        return clientResponseDto;
    }
}
