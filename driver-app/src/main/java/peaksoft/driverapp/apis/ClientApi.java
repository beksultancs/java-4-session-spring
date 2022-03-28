package peaksoft.driverapp.apis;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.driverapp.dto.ClientResponseDto;
import peaksoft.driverapp.dto.ClientSaveDto;
import peaksoft.driverapp.models.entities.Client;
import peaksoft.driverapp.services.ClientService;

/**
 * @author Beksultan
 */
@RestController
@RequestMapping("api/clients")
@AllArgsConstructor
public class ClientApi {

    private final ClientService clientService;

    @PostMapping("/save")
    public ClientResponseDto save(@RequestBody ClientSaveDto client) {
        return clientService.save(client);
    }
}
